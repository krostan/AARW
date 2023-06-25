package com.publish;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entities.Pet;
import com.entities.Publish;
import com.managers.PublishManager;

/**
 * Servlet implementation class PublishServlet
 */
@WebServlet("/search-publish")
public class SearchPublish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchPublish() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		List<Publish> publish = new ArrayList<>();
		List<Pet> result = new ArrayList<>();

		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");

		// 總共頁面
		int pageTotal;
		// 每一頁的數量
		double pageSize = 6.0;
		// 取得當前頁面
		String tempPage = request.getParameter("page");
		String currentPage = (tempPage == null) ? "1" : request.getParameter("page");

		publish = PublishManager.getInstance().findAllOfMember(userId);
		
		for(Publish temp : publish) {
			result.add(temp.getPet());
		}
		
		pageTotal = (int) Math.ceil(result.size() / pageSize);

		session.setAttribute("pets", result);
		session.setAttribute("pageSize", pageSize);
		request.setAttribute("pageTotal", pageTotal);
		request.setAttribute("currentPage", currentPage);

		request.getRequestDispatcher("/members/member.jsp").forward(request, response);
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
