package com.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entities.Member;
import com.managers.MemberManager;

//member 點擊 更新會員  ,依照會員ID 查找SQL得到該member資料 ,轉送到jsp顯示 ,最後再依照更改的值 存入sql
/**
 * Servlet implementation class UpdateMember
 */
@WebServlet("/search")
public class SearchMember extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchMember() {
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
		
		int userId = (int) session.getAttribute("userId");
		
		// findById();
		Member member = new Member();
		member = MemberManager.getInstance().findById(userId);

		response.setContentType("text/html;charset=utf-8");
		
		request.setAttribute("member", member);
		request.getRequestDispatcher("/members/update.jsp").forward(request, response);

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
