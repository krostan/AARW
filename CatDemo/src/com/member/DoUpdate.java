package com.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.managers.MemberManager;

/**
 * Servlet implementation class Update
 */
@WebServlet("/update")
public class DoUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoUpdate() {
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

		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String date = request.getParameter("birthday");
		Date birthday = null;

		try {
			birthday = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			System.out.println();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String email = request.getParameter("email");

		String[] genArray = { "男", "女" };
		int tempDender = Integer.parseInt(request.getParameter("gender"));
		String gender = genArray[tempDender - 1];

		String address = request.getParameter("address");
		int userId = (int) session.getAttribute("userId");

		boolean isSuccess;

		isSuccess = MemberManager.getInstance().updateMember(userId, name, phone, birthday, email, gender, address);

		/****************/
		// 如果是從/CatDemo/role/admin.jsp 過來的, adminUrl會有值
		String adminUrl = (String) request.getAttribute("adminUrl");
		/***************/
		if (adminUrl != null) {
			String alertMessage = isSuccess ? "會員更新成功" : "會員更新失敗";
			out.println("<script>alert('" + alertMessage + "'); window.location='role/aCatAndDog.jsp';</script>");
			out.flush();
		} else {
//			String message = isSuccess ? "更新成功" : "更新失敗";
//			session.setAttribute("message", message);
			response.sendRedirect(isSuccess ? "member" : "search");
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
