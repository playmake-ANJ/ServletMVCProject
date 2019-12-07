package zsc.ysh.mvc.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import zsc.ysh.mvc.model.Onlie;
import zsc.ysh.mvc.model.User;
import zsc.ysh.mvc.service.FactoryService;
import zsc.ysh.mvc.service.OnlieService;
import zsc.ysh.mvc.service.UserService;
import zsc.ysh.mvc.util.CookieUtil;
import zsc.ysh.mvc.util.DrawCode;

/** 
* @author 作者 A.N.JELL: 
* @version 创建时间：2019年7月14日 下午1:35:40 
* 类说明 
*/
public class UserController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private UserService userService = FactoryService.getUserService();
	private OnlieService onlieService = FactoryService.getOnlieService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		path = path.substring(1, path.indexOf("."));
		try {
			Method method = this.getClass().getDeclaredMethod(path, HttpServletRequest.class,
					HttpServletResponse.class);
			method.invoke(this, req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * @ 验证用户登录
	 */
	@SuppressWarnings("unused")
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String userName = req.getParameter("userName");
		String passwd = req.getParameter("passwd");
		String keepDays = req.getParameter("keep");
		boolean login = false;// true 表示已经登录，false表示未登录
		String cookieCount = null;// 获取Cookie的名称
		String ssid = null;// 密钥

		// 获取Cookie数组
		Cookie[] cookies = req.getCookies();

		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userKey")) {
					cookieCount = cookie.getValue();
				}
				if (cookie.getName().equals("ssid")) {
					ssid = cookie.getValue();
				}
			}
		}
		if (cookieCount != null && ssid != null) {
			// 处理login的值
			login = ssid.equals(CookieUtil.md5(cookieCount));
		}

		if (!login) {
			User user = userService.getOneByNameAndPasswd(userName, passwd);
			// 获取用户输入的验证码
			String code = req.getParameter("checkCode");
			String checkCode = (String) req.getSession().getAttribute("checkCode");
			if (user != null && checkCode.equals(code)) {
				keepDays = keepDays == null ? "" : keepDays;
				switch (keepDays) {
				case "7":
					// 创建Cookie对象，设置Cookie失效的时间为一周
					CookieUtil.creatCookie(userName, req, resp, 7 * 24 * 60 * 60);
					break;
				case "30":
					// 创建Cookie对象，设置Cookie失效的时间为一个月
					CookieUtil.creatCookie(userName, req, resp, 30 * 24 * 60 * 60);
					break;
				default:
					// 设置Cookie失效时间的默认值
					CookieUtil.creatCookie(userName, req, resp, -1);
					break;
				}
				// 记录session
				HttpSession session = req.getSession();
				session.setAttribute("user", userName);
				//登录成功后，把Online数据表的userName改为登录的用户名
				Onlie online = onlieService.getOnlieBySsid(session.getId());
				if(online != null) {
					online.setUserName(userName);
					//更新数据库
					onlieService.updateOnlie(online);
				}
				req.getRequestDispatcher("/userview.jsp").forward(req, resp);

			} else {
				req.setAttribute("erro", "提示：验证码、密码或账号错误！");
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			}
		} else {
			// 记录session
			HttpSession session = req.getSession();
			session.setAttribute("user", userName);
			//登录成功后，把Online数据表的userName改为登录的用户名
			Onlie online = onlieService.getOnlieBySsid(session.getId());
			if(online != null) {
				online.setUserName(userName);
				//更新数据库
				onlieService.updateOnlie(online);
			}
			req.getRequestDispatcher("/userview.jsp").forward(req, resp);
		}

	}

	/*
	 * @ 用户注册
	 */
	@SuppressWarnings("unused")
	private void reg(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String userName = req.getParameter("regUserName");
		String passwd = req.getParameter("regPassword");
		String address = req.getParameter("regAddress");
		String phone = req.getParameter("regPhone");
		if (userName == null || passwd == null || address == null || phone == null || "".equals(userName)
				|| "".equals(passwd) || "".equals(address) || "".equals(phone)) {
			req.setAttribute("regErro", "警告：不能存在有空的地方！");
			req.getRequestDispatcher("/reg.jsp").forward(req, resp);
			return;
		}
		// 判断注册用户的用户名是否存在
		else if (userName != null && passwd != null && address != null && phone != null) {
			UserService userService = FactoryService.getUserService();
			if (userService.getOneByName(userName) != null) {
				req.setAttribute("regUserNameErro", "警告：该用户名已被注册！");
				req.getRequestDispatcher("/reg.jsp").forward(req, resp);
				return;
			}
			User user = new User();
			user.setUserName(userName);
			user.setPasswd(passwd);
			user.setPhone(phone);
			user.setRegDate(new Date());
			user.setAddress(address);
			if (userService.insert(user) > 0) {
				// 如果注册成功，则跳会登录界面
				resp.sendRedirect(req.getContextPath() + "/index.jsp");
			} else if (userService.insert(user) == 0) {
				resp.sendRedirect(req.getContextPath() + "/erro.jsp");
			}
		}

	}

	/*
	 * @ 显示用户信息
	 */
	@SuppressWarnings("unused")
	private void userView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String userName = req.getParameter("seUserName");
		String address = req.getParameter("seAddress");
		String phone = req.getParameter("sePhone");
		List<User> userList = new ArrayList<User>();
		userList = userService.queryByData(userName, address, phone);
		req.setAttribute("userList", userList);
		// 只有转发才能把req,resp注入到JSP页面
		req.getRequestDispatcher("/userview.jsp").forward(req, resp);
	}

	@SuppressWarnings("unused")
	private void updateParpe(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(req.getParameter("userId"));
		User user = userService.getOneDataById(id);
		req.setAttribute("user", user);
		req.getRequestDispatcher("/update.jsp?id=" + id).forward(req, resp);

	}

	/*
	 * @修改信息
	 */
	@SuppressWarnings("unused")
	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(req.getParameter("id"));

		/*
		 * 判断修改的名字是否已存在
		 */
		User user = userService.getOneDataById(id);
		String userName = req.getParameter("updateUserName");

		long count = userService.getCountByName(userName);

		if (!user.getUserName().equals(userName) && count > 0) {
			req.setAttribute("repeadUserName", userName + "已被注册！");
			req.getRequestDispatcher("/updateParpe.anj?userId=" + id).forward(req, resp);
			return;
		}
		String passwd = req.getParameter("updatePassword");
		String address = req.getParameter("updateAddress");
		String phone = req.getParameter("updatePhone");
		user.setUserName(userName);
		user.setPasswd(passwd);
		user.setAddress(address);
		user.setPhone(phone);
		int row = userService.updateById(id, user);
		if (row > 0) {
			resp.sendRedirect(req.getContextPath() + "/userview.jsp");
		} else {
			resp.sendRedirect(req.getContextPath() + "/erro.jsp");
		}

	}

	/*
	 * @删除用户
	 */
	@SuppressWarnings("unused")
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userId = Integer.parseInt(req.getParameter("userId"));
		int row = userService.deleteById(userId);
		if (row > 0) {
			resp.sendRedirect(req.getContextPath() + "/userview.jsp");
		} else {
			resp.sendRedirect(req.getContextPath() + "/erro.jsp");
		}
	}

	/*
	 * @注销登录:删除Cookies和Session对象
	 */
	@SuppressWarnings("unused")
	private void loginOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 获取Cookie数组
		Cookie[] cookies = req.getCookies();
		// 删除Cookie对象
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("userKey")) {
					cookie.setMaxAge(0);
					resp.addCookie(cookie);
				}
				if (cookie.getName().equals("ssid")) {
					cookie.setMaxAge(0);
					resp.addCookie(cookie);
				}
			}
		}
		// 删除Session对象
		HttpSession session = req.getSession();
		if (session != null) {
			session.removeAttribute("user");
		}
		// 跳转到登录页面
		resp.sendRedirect(req.getContextPath() + "/index.jsp");
	}

	/*
	 * @获取验证码
	 */
	public void getCheckCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置格式
		resp.setContentType("img/jpg");
		// 画图
		DrawCode drCode = DrawCode.getInstance();
		drCode.setWidth(70);
		drCode.setHeight(20);
		// 获取4位验证码
		String checkCode = drCode.generateDrawCode();
		// 获取session对象，并将验证码保存在session对象中
		HttpSession session = req.getSession(true);
		session.setAttribute("checkCode", checkCode);
		// 使用输出流，将图片输出到JSP页面
		OutputStream out = resp.getOutputStream();
		ImageIO.write(drCode.generateCheckImg(checkCode), "jpg", out);

	}

	// 统计在线人数
	public void Online(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取登录用户的所有记录
		List<Onlie> onlineList = onlieService.getAllOnlie();
		req.getSession().setAttribute("onlie", onlineList);
		req.getRequestDispatcher("/online.jsp").forward(req, resp);
	}

}
