package com.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entities.Adoption;
import com.managers.AdoptionManager;

/**
 * Servlet implementation class AdminSearchAdoption
 */
@WebServlet("/admin-search-adoption")
public class AdminSearchAdoption extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminSearchAdoption() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("admin-search-adoption");

		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		PrintWriter out = response.getWriter();

		String adoptionStr = request.getParameter("updateAdoptionNo");
		int adoptionId = Integer.parseInt(adoptionStr);
		session.setAttribute("adoptionId", adoptionId);

		Adoption adoption = AdoptionManager.getInstance().findById(adoptionId);

		if (adoption == null) {
			out.print(false);
		} else {
			boolean isagree = adoption.getAdoptionResult().equals("同意");

			if (isagree) {
				out.print(false);
			} else {
				String result = AdoptionManager.getInstance().findResultById(adoptionId);
				out.print(result);
			}
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
