package com.pets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entities.Publish;
import com.managers.PetManager;
import com.managers.PublishManager;

/**
 * Servlet implementation class DeletePet
 */
@WebServlet("/delete")
public class DeletePet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeletePet() {
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

		String previousUrl = request.getHeader("Referer");
		URL urlPath = new URL(previousUrl);
		String path = urlPath.getPath();

		int petId;

		String adminUrl = (String) request.getAttribute("adminUrl");
		// 判斷是否從管理介面來的
		if (adminUrl != null) {
			String deletePetNo = request.getParameter("deletePetNo");
			petId = Integer.parseInt(deletePetNo);
		} else {
			String tmpId = request.getParameter("petid");
			petId = Integer.parseInt(tmpId);
		}

		String message = "刪除成功";

		// 當要刪除浪浪資料時 連帶刪除 刊登此浪浪的資料
		Publish publish = PublishManager.getInstance().findByPetId(petId);
		if (publish != null) {
			PublishManager.getInstance().delete(publish.getPublishId());
		}

		boolean isDelete = PetManager.getInstance().delete(petId);

		if (!isDelete) {
			message = "刪除失敗";
		}

		request.setAttribute("message", message);

		if (adminUrl != null) {
			out.print(isDelete);
		} else if (path.equals("/CatDemo/pets")) {
			response.sendRedirect("pets");
		} else {
			response.sendRedirect("member");
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
