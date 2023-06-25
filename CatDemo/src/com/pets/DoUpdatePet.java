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
 * Servlet implementation class DoUpdatePet
 */
@MultipartConfig
@WebServlet("/update-pet")
public class DoUpdatePet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoUpdatePet() {
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
		PrintWriter out =response.getWriter();

		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("userId");
		int petId;
		
		String adminUrl = (String) request.getAttribute("adminUrl");		
		// 判斷是否從管理介面來的
		if (adminUrl != null) {
			petId = (int) session.getAttribute("petId");
			session.removeAttribute("petId");
		}else {
			String id = request.getParameter("petId");
			petId = Integer.parseInt(id);
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

		System.out.println("update-pet");
		Part filePart = request.getPart("image");

		// 取得檔案類型 如 image/jpeg
		String contentType = filePart.getContentType();
		System.out.println(contentType);

		// 找到最後一個 / 的索引
		int index = contentType.lastIndexOf("/");

		// 取得檔案類型的部分字串 如 jpeg
		String fileType = contentType.substring(index + 1);
		System.out.println(fileType);

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

		// 判斷檔名是否為空
		// 如果為空 則代表沒有新增.
		if (filePart.getSubmittedFileName() == "") {
			image = PetManager.getInstance().findById(petId).getPhotos();
		}

		System.out.println("update-pet" + image);
		boolean isSuccess;

		isSuccess = PetManager.getInstance().updatePet(petId, petName, breed, gender, coatColor, age, location, species,
				size, quest, image);

		if (isSuccess) {
			if (adminUrl != null) {
				String alertMessage = isSuccess ? "寵物更新成功" : "寵物更新失敗";
				out.println("<script>alert('" + alertMessage + "'); window.location='role/admin.jsp';</script>");
				out.flush();
			}
			else {
				response.sendRedirect("member");
			}
		} else {
			response.sendRedirect("search-pet");
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
