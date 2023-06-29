package com.permiss;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entities.Member;
import com.managers.MemberManager;
import com.managers.PermissManager;

/**
 * Servlet implementation class DoPermiss
 */
@WebServlet("/do-permiss")
public class DoPermiss extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoPermiss() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String userIdStr = request.getParameter("userid");
		int userId = 0;
		String errorMessage = "";
		try {
			userId = Integer.parseInt(userIdStr);
		} catch (NumberFormatException e) {
			System.out.println("不是數字");
		}

		String role = request.getParameter("role");

		Member member = MemberManager.getInstance().findById(userId);
		boolean isSuccess = false;

		if (member != null) {
			isSuccess = PermissManager.getInstance().save(userId, role);
		}
		String alertMessage = isSuccess ? "權限新增成功" : "權限新增失敗" + errorMessage;
		out.println("<script>alert('" + alertMessage + "'); window.location='role/aCatAndDog.jsp';</script>");
		out.flush();
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
