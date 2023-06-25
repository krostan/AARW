package com.sort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entities.Publish;
import com.managers.PublishManager;

/**
 * Servlet implementation class NewSortPet
 */
@WebServlet("/new-sort-pet")
public class NewSortPet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewSortPet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("new-sort-pet");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		List<Publish> publishList = new ArrayList<>();

		String selectedOption = request.getParameter("sortData");
		String description = "";
		String sortData = "";

		// asc 小到大 desc 大到小
		// 刊登時間相同 則 透過 刊登編號 做排序

		if (selectedOption.equals("最新發布")) {
			description = "publish_date";
			sortData = "desc";
			
		} else if (selectedOption.equals("默認排序")) {
			description = "publish_date";
			sortData = "asc";
		}
		
		publishList = PublishManager.getInstance().findSortAll(description, sortData);
		
		request.setAttribute("publishBySort", publishList);
		
		request.getRequestDispatcher("/pets").forward(request, response);

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
