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
 * Servlet implementation class GetPwd
 */
@WebServlet("/resetpassword")
public class GetPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetPwd() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("resetpassword");

		HttpSession session = request.getSession();

		String username = (String) session.getAttribute("username");
		String oldPwd = request.getParameter("oldpassword");
		String newPwd = request.getParameter("password1");
		String newPwd2 = request.getParameter("password2");

		boolean isSuccess = false;

		// 驗證帳號 密碼 是否正確
		int userId = MemberManager.getInstance().authenticate(username, oldPwd);

		if ((userId > 0) && (newPwd.equals(newPwd2))) {
			isSuccess = MemberManager.getInstance().updatePwd(userId, oldPwd, newPwd);
		}
		
		if(isSuccess) {
			response.sendRedirect("member");
		}else {
			session.setAttribute("message", "輸入錯誤");
			response.sendRedirect("members/resetpwd.jsp");
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
