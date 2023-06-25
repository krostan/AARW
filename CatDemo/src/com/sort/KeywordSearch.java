package com.sort;

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
import com.managers.PetManager;

/**
 * Servlet implementation class KeywordSearch
 */
@WebServlet("/keyword-search")
public class KeywordSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KeywordSearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("KeywordSearch");

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charsest=ust-8");
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");

		String kwSearch = request.getParameter("kwSearch");
		System.out.println("KeywordSearch = " + kwSearch);

		List<Pet> pets = new ArrayList<>();
		List<Pet> result = new ArrayList<>();
		
		pets = PetManager.getInstance().findByKeywordSearch(kwSearch);

		for (Pet p : pets) {
			if(p.getMember().getUserId() != userId) {
				result.add(p);
			}
		}

		if (!pets.isEmpty()) {
			session.setAttribute("kwSearchResult", result);
			response.sendRedirect("pets");
		} else {
			session.setAttribute("kwSearch", kwSearch);
			response.sendRedirect("members/notfound.jsp");
		}
//		session.setAttribute("kwSearchResult", pets);
//		response.sendRedirect("pets");
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
