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
* @author ���� A.N.JELL: 
* @version ����ʱ�䣺2019��8��11�� ����10:03:31 
* ��˵�� : ʹ��HttpSessionListenerʵ���û�����ͳ��
*/
@WebListener
public class OnlieListener implements HttpSessionListener,HttpSessionAttributeListener{
	@Override
	public void sessionCreated(HttpSessionEvent se) {
//		HttpSession session = se.getSession();
//		ServletContext application = session.getServletContext();
//		//���sessionId���û���
//		HashMap<String, String> maps = (HashMap<String, String>) application.getAttribute("onlie");
//		String userName = (String) session.getAttribute("user");
//		// ����û�û�е�¼��user��ֵΪnull����ʱ����һ����ʼֵ
//		userName = (userName == null ? "·��" : userName);
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
		// ����û�û�е�¼��user��ֵΪnull����ʱ����һ����ʼֵ
		userName = (userName == null ? "·��" : userName);
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
