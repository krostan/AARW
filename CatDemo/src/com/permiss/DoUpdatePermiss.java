package com.permiss;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.managers.PermissManager;

/**
 * Servlet implementation class DoUpdatePermiss
 */
@WebServlet("/do-update-permiss")
public class DoUpdatePermiss extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoUpdatePermiss() {
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
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		int userId = (int) session.getAttribute("userId");
		session.removeAttribute("userId");
		
		String role = request.getParameter("role");

		boolean isSuccess = PermissManager.getInstance().updatePermiss(userId, role);

		String alertMessage = isSuccess ? "權限更新成功" : "權限更新失敗";
		out.println("<script>alert('" + alertMessage + "'); window.location='role/admin.jsp';</script>");
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
