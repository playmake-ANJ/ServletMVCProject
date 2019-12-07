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
* @author 作者 A.N.JELL: 
* @version 创建时间：2019年8月7日 下午4:27:03 
* 类说明 :拦截index.jsp,实现自动登录
*/
public class MyFilter extends HttpFilter{

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		//检查cookie，是否有记录
		//判断是否存在cookie
		//自动登录，转发到userview.jsp页面
		//否则正常执行程序
		//获取所有cookie
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
