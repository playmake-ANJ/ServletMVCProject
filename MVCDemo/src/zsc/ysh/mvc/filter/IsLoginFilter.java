package zsc.ysh.mvc.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/** 
* @author 作者 A.N.JELL: 
* @version 创建时间：2019年8月7日 下午5:13:30 
* 类说明 :拦截某些页面（只能登录后才能访问的页面）
*/
public class IsLoginFilter extends HttpFilter{

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = req.getSession();
		String userName = null;

		// 获取访问的地址
		String path = req.getServletPath().substring(1);
		String initPath = getFilterConfig().getInitParameter("permission");
		// 将initPath字符串转化为String类型的数组
		String[] paths = initPath.split(",");
		for (String pathStr : paths) {
			if (pathStr.equals(path)) {
				userName = (String)session.getAttribute("userName");
				if(userName != null) {
					//说明已登录过
					chain.doFilter(req, resp);
				}else {
					resp.sendRedirect(req.getContextPath() + "/index.jsp");
					chain.doFilter(req, resp);
				}
				break;
			}
		}
		chain.doFilter(req, resp);
	}

}
