package com.member;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.managers.MemberManager;


/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// 得到驗證碼
		String rnd = (String) session.getAttribute("rnd");
		String input = request.getParameter("insrand");

		response.setContentType("text/html;charset=utf-8");
		
		// 得到使用者編號
		int userId = MemberManager.getInstance().authenticate(username, password);

		// 得到使用者的權限
//		Permiss permiss = null;
//		permiss = PermissManager.getInstance().findByUserId(userId);

		if (!rnd.equals(input)) {
			session.setAttribute("message", "驗證錯誤");
			response.sendRedirect("members/login.jsp");
		} else {
			// -1 為 找不到資料
			if (userId < 0) {
				session.setAttribute("message", "帳號密碼輸入錯誤");
				response.sendRedirect("members/login.jsp");
			} else {

//				if (permiss != null && (Objects.equals(permiss.getRole(), "系統管理者"))) {
//					response.sendRedirect("role/admin.jsp");
//				} else {
					String userName = MemberManager.getInstance().findById(userId).getUsername();
					session.setAttribute("userId", userId);
					session.setAttribute("username", userName);
					response.sendRedirect("member");
//				}
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
