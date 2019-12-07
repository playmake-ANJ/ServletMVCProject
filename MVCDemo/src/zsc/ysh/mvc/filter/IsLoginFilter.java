package zsc.ysh.mvc.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/** 
* @author ���� A.N.JELL: 
* @version ����ʱ�䣺2019��8��7�� ����5:13:30 
* ��˵�� :����ĳЩҳ�棨ֻ�ܵ�¼����ܷ��ʵ�ҳ�棩
*/
public class IsLoginFilter extends HttpFilter{

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = req.getSession();
		String userName = null;

		// ��ȡ���ʵĵ�ַ
		String path = req.getServletPath().substring(1);
		String initPath = getFilterConfig().getInitParameter("permission");
		// ��initPath�ַ���ת��ΪString���͵�����
		String[] paths = initPath.split(",");
		for (String pathStr : paths) {
			if (pathStr.equals(path)) {
				userName = (String)session.getAttribute("userName");
				if(userName != null) {
					//˵���ѵ�¼��
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
