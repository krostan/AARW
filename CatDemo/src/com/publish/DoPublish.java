package com.publish;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entities.Pet;
import com.entities.Publish;
import com.managers.PetManager;
import com.managers.PublishManager;

/**
 * Servlet implementation class DoPublish
 */
@WebServlet("/do-publish")
public class DoPublish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoPublish() {
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
		HttpSession session = request.getSession();

		PrintWriter out = response.getWriter();

		// 得到當前時間
		Date date = new Date();

		String adminUrl = (String) request.getAttribute("adminUrl");
		// 判斷是否從管理介面來的
		if (adminUrl != null) {
			String user = request.getParameter("userid");
			int userId = 0;

			String pet = request.getParameter("petid");
			int petId = 0;
			
			boolean isSuccess = false;
			String errorMessage = "";
			try {
				userId = Integer.parseInt(user);
				petId = Integer.parseInt(pet);

				Pet petobj = PetManager.getInstance().findById(petId);
				

				if (petobj.getMember().getUserId() == userId) {
					isSuccess = PublishManager.getInstance().save(date, petId, userId);
				}
			} catch (NumberFormatException e) {
				System.out.println("不是數字");
				errorMessage = "輸入的不是數字";
			}

			String alertMessage = isSuccess ? "刊登成功" : "刊登失敗 " + errorMessage;
			out.println("<script>alert('" + alertMessage + "'); window.location='role/aCatAndDog.jsp';</script>");
			out.flush();
		} else {

			int userId = (int) session.getAttribute("userId");

			String[] pets = request.getParameter("pet").split(",");
			List<Boolean> isSuccess = new ArrayList<>();

			// 判斷是否已經刊登過此寵物
			Publish publish;

			for (String ss : pets) {
				int petId = Integer.valueOf(ss);
				// 判斷該寵物是否已經刊登
				publish = PublishManager.getInstance().findByPetId(petId);

				if (publish == null) {
					isSuccess.add(PublishManager.getInstance().save(date, petId, userId));
				}
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
