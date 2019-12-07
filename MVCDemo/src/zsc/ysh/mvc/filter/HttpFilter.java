package zsc.ysh.mvc.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
* @author 作者 A.N.JELL: 
* @version 创建时间：2019年8月7日 下午4:18:48 
* 类说明 :拦截器，实现自动登录
*/
public abstract class HttpFilter implements Filter {
	private FilterConfig filterConfig;
	public FilterConfig getFilterConfig() {
		return this.filterConfig;
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		init();
	}
	public void init() {
		
	}
	protected abstract void doFilter(HttpServletRequest req,HttpServletResponse resp,FilterChain chain)
			throws IOException, ServletException;
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		doFilter((HttpServletRequest)request, (HttpServletResponse)response, chain);
	}

	@Override
	public void destroy() {
		
	}
	
}
