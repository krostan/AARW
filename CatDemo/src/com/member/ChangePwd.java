package com.member;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class ChangePwd
 */
@WebServlet("/change-pwd")
public class ChangePwd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePwd() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("change-pwd");

		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String username = (String) session.getAttribute("username");
		String oldPwd = request.getParameter("oldpassword");
		String newPwd = request.getParameter("password1");
		String newPwd2 = request.getParameter("password2");

		// 要透過BCrypt 加密後 傳入 然後驗證帳號 比對密碼
		// 透過username 取得使用者資料
		Member member = MemberManager.getInstance().findByName(username);
		int userId = 0;
		// 密碼比對
		boolean isTrue = false;
		// 更新密碼
		boolean isSuccess = false;
		
		if (member != null) {
			String sqlUserPw = member.getPassword();
			// 將輸入的密碼 與資料庫的密碼 比對
			isTrue = BCryptStringUtil.verifyPassword(oldPwd, sqlUserPw);
			if (isTrue && (newPwd.equals(newPwd2))) {
				userId = member.getUserId();
				isSuccess = MemberManager.getInstance().updatePwd(userId, sqlUserPw, newPwd);
			}
		}

		if (isSuccess) {
			out.println("<script>alert('密碼修改成功'); window.location='member';</script>");
		} else {
			session.setAttribute("message", "輸入錯誤");
			response.sendRedirect("members/changepwd.jsp");
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
