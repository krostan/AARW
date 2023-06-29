package com.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.database.BCryptStringUtil;
import com.entities.Member;
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

		// 取得表單資料
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// 得到驗證碼
		String rnd = (String) session.getAttribute("rnd");
		String input = request.getParameter("insrand");

		response.setContentType("text/html;charset=utf-8");

		if (!rnd.equals(input)) {
			session.setAttribute("message", "驗證錯誤");
			response.sendRedirect("members/login.jsp");
		} else {

			int userId = 0;
			boolean isTrue = false;

			// 透過username 取得使用者資料
			Member member = MemberManager.getInstance().findByName(username);

			if (member != null) {
				// 將輸入的密碼 與資料庫的密碼 比對
				isTrue = BCryptStringUtil.verifyPassword(password, member.getPassword());

				if (isTrue) {
					// 取得編號
					userId = member.getUserId();
					session.setAttribute("userId", userId);
					session.setAttribute("username", username);
					response.sendRedirect("member");
				} else {
					session.setAttribute("message", "帳號或密碼輸入錯誤");
					response.sendRedirect("members/login.jsp");
				}
			} else {
				session.setAttribute("message", "帳號或密碼輸入錯誤");
				response.sendRedirect("members/login.jsp");
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
