package zsc.ysh.mvc.listener;

import java.util.Date;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import zsc.ysh.mvc.model.Onlie;
import zsc.ysh.mvc.service.FactoryService;
import zsc.ysh.mvc.service.OnlieService;

/** 
* @author 作者 A.N.JELL: 
* @version 创建时间：2019年8月11日 下午1:40:32 
* 类说明 
*/
/*基本思路：
 * 1、当请求进来时，记录访问者的信息，判断访问者，在数据库如果存在这个用户时，更新他的访问时间，如果没有记录，插入数据
 * 2、记录在线用户访问的时间，如果超过5分钟么有任何操作，表示用户离线，删除数据表的记录
 * 3、访问者登录成功后，将数据表中的UserName，从路人改为真正的用户名
 */
@WebListener
public class OnlineRequestListener implements ServletRequestListener{

	private OnlieService onlieService = FactoryService.getOnlieService();
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		//获取session对象
		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
		HttpSession session = req.getSession();
		//获取sessionID
		String ssid = session.getId();
		//获取用户名
		String userName = (String)session.getAttribute("user");
		userName = (userName == null? "路人甲" : userName);
		//获取用户访问的页面地址
		String page = req.getRequestURI();
		//获取Ip地址
		String ip = req.getRemoteAddr();
		Onlie onlie = onlieService.getOnlieBySsid(ssid);
		if(onlie == null) {
			//插入数据
			onlie = new Onlie(ssid,userName,page,ip,new Date());
			onlieService.insertOnlie(onlie);
		}else if(onlie != null){
			//更新数据
			onlie.setTime(new Date());
			onlie.setPage(page);
			onlieService.updateOnlie(onlie);
		}
		
	}

}
