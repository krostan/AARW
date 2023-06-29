package com.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.managers.MemberManager;
import com.managers.ResetTokensManager;

/**
 * Servlet implementation class UpdatePwd
 */
@WebServlet("/update-pwd")
public class UpdatePwd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdatePwd() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("update-pwd");

		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String token = (String) session.getAttribute("token");
		session.removeAttribute("token");
		
		String newPwd = request.getParameter("password1");
		String newPwd2 = request.getParameter("password2");
		
		boolean isSuccess = false;
		// 得到使用者ID
		int userId = ResetTokensManager.getInstance().findByToken(token).getUserId();
		
		// 是否使用token
		String isUsed ="";

		if ((userId > 0) && (newPwd.equals(newPwd2))) {
			isSuccess = MemberManager.getInstance().updatePwd(userId, newPwd);
		}

		if (isSuccess) {
			isUsed ="Y";
			ResetTokensManager.getInstance().updateResetTokens(userId, token, isUsed);
			out.println("<script>alert('密碼重置成功'); window.location='members/login.jsp';</script>");
		} else {
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
