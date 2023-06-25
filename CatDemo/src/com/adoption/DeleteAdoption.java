package com.adoption;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.managers.AdoptionManager;

/**
 * Servlet implementation class DeleteAdoption
 */
@WebServlet("/delete-adoption")
public class DeleteAdoption extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteAdoption() {
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
		int adoptionId;
		
		String adminUrl = (String) request.getAttribute("adminUrl");
		// 判斷是否從管理介面來的
		if (adminUrl != null) {
			String deleteAdoptionNo = request.getParameter("deleteAdoptionNo");
			adoptionId = Integer.parseInt(deleteAdoptionNo);

		} else {
			String tmpId = request.getParameter("id");
			adoptionId = Integer.parseInt(tmpId);
		}

		String message = "刪除成功";
		boolean isDelete = AdoptionManager.getInstance().delete(adoptionId);

		if (!isDelete) {
			message = "刪除失敗";
		}
		if (adminUrl != null) {
			/* 如果這邊設定 跳轉 則 js裡面的 alert不會觸發 */
			out.print(isDelete);
		} else {
			request.setAttribute("message", message);
			response.sendRedirect("search-adoption");
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
