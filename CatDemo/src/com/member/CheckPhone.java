package com.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entities.Member;
import com.managers.MemberManager;

/**
 * Servlet implementation class CheckPhone
 */
@WebServlet("/check-phone")
public class CheckPhone extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CheckPhone() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");

		String phone = request.getParameter("phone");

		PrintWriter out = response.getWriter();
		Member phoneDate = MemberManager.getInstance().findByPhone(phone);

		int len = phone.length();

		if (phoneDate != null) {
			out.print("此電話已註冊");
		} else if (len < 8 || len > 12) {
			out.print("電話長度不正確");
		} else {
			out.print("");
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
