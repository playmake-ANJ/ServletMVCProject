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
* @author ���� A.N.JELL: 
* @version ����ʱ�䣺2019��7��14�� ����1:35:40 
* ��˵�� 
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
	 * @ ��֤�û���¼
	 */
	@SuppressWarnings("unused")
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String userName = req.getParameter("userName");
		String passwd = req.getParameter("passwd");
		String keepDays = req.getParameter("keep");
		boolean login = false;// true ��ʾ�Ѿ���¼��false��ʾδ��¼
		String cookieCount = null;// ��ȡCookie������
		String ssid = null;// ��Կ

		// ��ȡCookie����
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
			// ����login��ֵ
			login = ssid.equals(CookieUtil.md5(cookieCount));
		}

		if (!login) {
			User user = userService.getOneByNameAndPasswd(userName, passwd);
			// ��ȡ�û��������֤��
			String code = req.getParameter("checkCode");
			String checkCode = (String) req.getSession().getAttribute("checkCode");
			if (user != null && checkCode.equals(code)) {
				keepDays = keepDays == null ? "" : keepDays;
				switch (keepDays) {
				case "7":
					// ����Cookie��������CookieʧЧ��ʱ��Ϊһ��
					CookieUtil.creatCookie(userName, req, resp, 7 * 24 * 60 * 60);
					break;
				case "30":
					// ����Cookie��������CookieʧЧ��ʱ��Ϊһ����
					CookieUtil.creatCookie(userName, req, resp, 30 * 24 * 60 * 60);
					break;
				default:
					// ����CookieʧЧʱ���Ĭ��ֵ
					CookieUtil.creatCookie(userName, req, resp, -1);
					break;
				}
				// ��¼session
				HttpSession session = req.getSession();
				session.setAttribute("user", userName);
				//��¼�ɹ��󣬰�Online���ݱ��userName��Ϊ��¼���û���
				Onlie online = onlieService.getOnlieBySsid(session.getId());
				if(online != null) {
					online.setUserName(userName);
					//�������ݿ�
					onlieService.updateOnlie(online);
				}
				req.getRequestDispatcher("/userview.jsp").forward(req, resp);

			} else {
				req.setAttribute("erro", "��ʾ����֤�롢������˺Ŵ���");
				req.getRequestDispatcher("/index.jsp").forward(req, resp);
			}
		} else {
			// ��¼session
			HttpSession session = req.getSession();
			session.setAttribute("user", userName);
			//��¼�ɹ��󣬰�Online���ݱ��userName��Ϊ��¼���û���
			Onlie online = onlieService.getOnlieBySsid(session.getId());
			if(online != null) {
				online.setUserName(userName);
				//�������ݿ�
				onlieService.updateOnlie(online);
			}
			req.getRequestDispatcher("/userview.jsp").forward(req, resp);
		}

	}

	/*
	 * @ �û�ע��
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
			req.setAttribute("regErro", "���棺���ܴ����пյĵط���");
			req.getRequestDispatcher("/reg.jsp").forward(req, resp);
			return;
		}
		// �ж�ע���û����û����Ƿ����
		else if (userName != null && passwd != null && address != null && phone != null) {
			UserService userService = FactoryService.getUserService();
			if (userService.getOneByName(userName) != null) {
				req.setAttribute("regUserNameErro", "���棺���û����ѱ�ע�ᣡ");
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
				// ���ע��ɹ����������¼����
				resp.sendRedirect(req.getContextPath() + "/index.jsp");
			} else if (userService.insert(user) == 0) {
				resp.sendRedirect(req.getContextPath() + "/erro.jsp");
			}
		}

	}

	/*
	 * @ ��ʾ�û���Ϣ
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
		// ֻ��ת�����ܰ�req,respע�뵽JSPҳ��
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
	 * @�޸���Ϣ
	 */
	@SuppressWarnings("unused")
	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(req.getParameter("id"));

		/*
		 * �ж��޸ĵ������Ƿ��Ѵ���
		 */
		User user = userService.getOneDataById(id);
		String userName = req.getParameter("updateUserName");

		long count = userService.getCountByName(userName);

		if (!user.getUserName().equals(userName) && count > 0) {
			req.setAttribute("repeadUserName", userName + "�ѱ�ע�ᣡ");
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
	 * @ɾ���û�
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
	 * @ע����¼:ɾ��Cookies��Session����
	 */
	@SuppressWarnings("unused")
	private void loginOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ��ȡCookie����
		Cookie[] cookies = req.getCookies();
		// ɾ��Cookie����
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
		// ɾ��Session����
		HttpSession session = req.getSession();
		if (session != null) {
			session.removeAttribute("user");
		}
		// ��ת����¼ҳ��
		resp.sendRedirect(req.getContextPath() + "/index.jsp");
	}

	/*
	 * @��ȡ��֤��
	 */
	public void getCheckCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ���ø�ʽ
		resp.setContentType("img/jpg");
		// ��ͼ
		DrawCode drCode = DrawCode.getInstance();
		drCode.setWidth(70);
		drCode.setHeight(20);
		// ��ȡ4λ��֤��
		String checkCode = drCode.generateDrawCode();
		// ��ȡsession���󣬲�����֤�뱣����session������
		HttpSession session = req.getSession(true);
		session.setAttribute("checkCode", checkCode);
		// ʹ�����������ͼƬ�����JSPҳ��
		OutputStream out = resp.getOutputStream();
		ImageIO.write(drCode.generateCheckImg(checkCode), "jpg", out);

	}

	// ͳ����������
	public void Online(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡ��¼�û������м�¼
		List<Onlie> onlineList = onlieService.getAllOnlie();
		req.getSession().setAttribute("onlie", onlineList);
		req.getRequestDispatcher("/online.jsp").forward(req, resp);
	}

}
