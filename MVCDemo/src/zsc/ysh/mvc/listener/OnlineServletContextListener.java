package zsc.ysh.mvc.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.swing.Timer;

import zsc.ysh.mvc.model.Onlie;
import zsc.ysh.mvc.service.FactoryService;
import zsc.ysh.mvc.service.OnlieService;

/** 
* @author 作者 A.N.JELL: 
* @version 创建时间：2019年8月11日 下午2:36:00 
* 类说明 
*/
@WebListener
public class OnlineServletContextListener implements ServletContextListener{
	//5分钟，访问者如果没有任何操作，被视为离线
	private final int MAXMIN = 5 * 60 * 1000;
	private OnlieService onlineService = FactoryService.getOnlieService();
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//服务器启动的时候执行
		//定义定时器
		//每5秒钟执行一次
		new Timer(5 * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Onlie> list = onlineService.getAllOnlie();
				if(list != null && list.size() > 0) {
					for(Onlie ol : list) {
						//当前系统时间减去用户访问时间
						if((System.currentTimeMillis() - Long.parseLong(ol.getTime().getTime()+"")) > MAXMIN) {
							//超过5分钟后进行删除记录
							onlineService.deleteOnlie(ol.getSsid());
						}
					}
				}
				
			}
		}).start();
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		
	}

}
