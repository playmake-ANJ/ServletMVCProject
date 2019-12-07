package zsc.ysh.mvc.listener;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/** 
* @author 作者 A.N.JELL: 
* @version 创建时间：2019年8月11日 上午10:03:31 
* 类说明 : 使用HttpSessionListener实现用户在线统计
*/
@WebListener
public class OnlieListener implements HttpSessionListener,HttpSessionAttributeListener{
	@Override
	public void sessionCreated(HttpSessionEvent se) {
//		HttpSession session = se.getSession();
//		ServletContext application = session.getServletContext();
//		//存放sessionId和用户名
//		HashMap<String, String> maps = (HashMap<String, String>) application.getAttribute("onlie");
//		String userName = (String) session.getAttribute("user");
//		// 如果用户没有登录，user的值为null，此时给他一个初始值
//		userName = (userName == null ? "路人" : userName);
//		if (maps == null) {
//			maps = new HashMap<String, String>();
//		}
//		maps.put(session.getId(), userName);
//		application.setAttribute("onlie", maps);
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		ServletContext application = session.getServletContext();
		@SuppressWarnings("unchecked")
		HashMap<String, String> maps = (HashMap<String, String>) application.getAttribute("onlie");
		if(maps != null) {
			maps.remove(session.getId());
		}
		application.setAttribute("onlie", maps);
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		ServletContext application = session.getServletContext();
		@SuppressWarnings("unchecked")
		HashMap<String, String> maps = (HashMap<String, String>) application.getAttribute("onlie");
		String userName = (String) session.getAttribute("user");
		// 如果用户没有登录，user的值为null，此时给他一个初始值
		userName = (userName == null ? "路人" : userName);
		if(maps == null) {
			maps = new HashMap<String, String>();
		}
		maps.put(session.getId(), userName);
		application.setAttribute("onlie", maps);
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		
	}

}
