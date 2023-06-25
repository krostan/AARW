package com.pets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.database.FileOutput;
import com.managers.PetManager;

/**
 * Servlet implementation class AddPet
 */
@MultipartConfig
@WebServlet("/add-pet")
public class AddPet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddPet() {
		super();
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

		// 如果是從/CatDemo/role/admin.jsp 過來的, adminUrl會有值
		String adminUrl = (String) request.getAttribute("adminUrl");
		int userId;

		if (adminUrl != null) {
			String userNo = request.getParameter("userid");
			userId = Integer.parseInt(userNo);
		} else {
			userId = (int) session.getAttribute("userId");
		}

		String petName = request.getParameter("petName");
		String breed = request.getParameter("breed");
		String gender = request.getParameter("gender");
		String coatColor = request.getParameter("coatColor");
		String age = request.getParameter("age");
		String location = request.getParameter("location");
		String species = request.getParameter("species");
		String size = request.getParameter("size");
		String quest = request.getParameter("quest");

		Part filePart = request.getPart("image");

		// 使用者資料夾
		String mk = File.separator + userId;
		// 設定儲存檔案的資料夾路徑
		String savePath = getServletContext().getRealPath("/images") + mk;
		// 儲存檔案
		FileOutput.saveFile(filePart, savePath);
		// 得到新檔名
		String fileName = FileOutput.generateFileName(filePart);

		// 要儲存到資料庫的路徑 顯示時 可以直接使用的路徑
		String image = "images" + mk + File.separator + fileName;

		boolean isSuccess = PetManager.getInstance().save(petName, breed, gender, coatColor, age, location, species,
				size, quest, image, userId);

		if (adminUrl != null) {
			String alertMessage = isSuccess ? "寵物新增成功" : "寵物新增失敗";
			out.println("<script>alert('" + alertMessage + "'); window.location='role/admin.jsp';</script>");
			out.flush();
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
		doGet(request, response);
	}

}
