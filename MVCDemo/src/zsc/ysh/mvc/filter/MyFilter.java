package zsc.ysh.mvc.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zsc.ysh.mvc.util.CookieUtil;

/** 
* @author ���� A.N.JELL: 
* @version ����ʱ�䣺2019��8��7�� ����4:27:03 
* ��˵�� :����index.jsp,ʵ���Զ���¼
*/
public class MyFilter extends HttpFilter{

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		//���cookie���Ƿ��м�¼
		//�ж��Ƿ����cookie
		//�Զ���¼��ת����userview.jspҳ��
		//��������ִ�г���
		//��ȡ����cookie
		Cookie[] cookies = req.getCookies();
		String userName = null;
		String ssid = null;
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("userKey")) {
					userName = cookie.getValue();
				}
				if(cookie.getName().equals("ssid")) {
					ssid = cookie.getValue();
				}
			}
			if(userName != null && ssid != null) {
				if(ssid.equals(CookieUtil.md5(userName))) {
					HttpSession session = req.getSession();
					session.setAttribute("userName", userName);
					resp.sendRedirect(req.getContextPath() + "/userview.jsp");
				}
			}
			chain.doFilter(req, resp);
		}
		else {
			chain.doFilter(req, resp);
		}
	}

}
