package com.permiss;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entities.Permiss;
import com.managers.PermissManager;

/**
 * Servlet implementation class SearchPermiss
 */
@WebServlet("/search-permiss")
public class SearchPermiss extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchPermiss() {
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

		String permissUserIdStr = request.getParameter("updatePermissUserNo");
		int permissUserId = Integer.parseInt(permissUserIdStr);
		session.setAttribute("userId", permissUserId);

		Permiss permiss = PermissManager.getInstance().findByUserId(permissUserId);
		String role = "";
		
		if (permiss == null) {
			out.print(false);
		}else {
			role = permiss.getRole();
			out.println(role);
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
