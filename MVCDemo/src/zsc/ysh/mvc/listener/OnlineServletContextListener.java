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
* @author ���� A.N.JELL: 
* @version ����ʱ�䣺2019��8��11�� ����2:36:00 
* ��˵�� 
*/
@WebListener
public class OnlineServletContextListener implements ServletContextListener{
	//5���ӣ����������û���κβ���������Ϊ����
	private final int MAXMIN = 5 * 60 * 1000;
	private OnlieService onlineService = FactoryService.getOnlieService();
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//������������ʱ��ִ��
		//���嶨ʱ��
		//ÿ5����ִ��һ��
		new Timer(5 * 1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Onlie> list = onlineService.getAllOnlie();
				if(list != null && list.size() > 0) {
					for(Onlie ol : list) {
						//��ǰϵͳʱ���ȥ�û�����ʱ��
						if((System.currentTimeMillis() - Long.parseLong(ol.getTime().getTime()+"")) > MAXMIN) {
							//����5���Ӻ����ɾ����¼
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
