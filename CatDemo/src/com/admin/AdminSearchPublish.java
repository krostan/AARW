package com.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entities.Publish;
import com.google.gson.Gson;
import com.managers.PublishManager;

/**
 * Servlet implementation class AdminSearchPublish
 */
@WebServlet("/admin-search-publish")
public class AdminSearchPublish extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminSearchPublish() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("admin-search-publish");

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String publishStr = request.getParameter("updatePublishNo");
		int publishId = Integer.parseInt(publishStr);
		
		Publish publish = PublishManager.getInstance().findById(publishId);
		
		int userId = publish.getMember().getUserId();
		int petId = publish.getPet().getPetId();
		
		String userStr = String.valueOf(userId);
		String petStr = String.valueOf(petId);
		
		Map<String, String> map = new HashMap<>();

		map.put("userId", userStr);
		map.put("petId", petStr);
		
		Gson gson = new Gson();
		String json = gson.toJson(map);

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
