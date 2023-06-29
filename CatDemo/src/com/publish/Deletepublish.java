package com.publish;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entities.Adoption;
import com.entities.Publish;
import com.managers.AdoptionManager;
import com.managers.PublishManager;

/**
 * Servlet implementation class DeletePublish
 */
@WebServlet("/delete-publish")
public class DeletePublish extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePublish() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Publish publish = null;
		boolean isDelete = false;
		int petId;
		String message = "刪除成功";

		String adminUrl = (String) request.getAttribute("adminUrl");
		// 判斷是否從管理介面來的
		if (adminUrl != null) {
			// 得到刊登編號
			String deletePublishNo = request.getParameter("deletePublishNo");
			int publishId = Integer.parseInt(deletePublishNo);
			// 得到寵物編號
			petId = PublishManager.getInstance().getPetId(publishId);
			
			// 刪除刊登
			isDelete = PublishManager.getInstance().delete(publishId);
		} else {
			String tmpId = request.getParameter("id");
			petId = Integer.parseInt(tmpId);

			// 刪除刊登
			publish = PublishManager.getInstance().findByPetId(petId);
			isDelete = PublishManager.getInstance().delete(publish.getPublishId());
		}

		// 透過寵物編號 刪除該刊登的 全部預約
		List<Adoption> adoptionList = AdoptionManager.getInstance().findAllByPetId(petId);

		for (Adoption a : adoptionList) {
			AdoptionManager.getInstance().delete(a.getAdoptionId());
		}

		if (!isDelete) {
			message = "刪除失敗";
		}

		if (adminUrl != null) {
			/* 如果這邊設定 跳轉 則 js裡面的 alert不會觸發*/
			out.print(isDelete);
		} else {
			request.setAttribute("message", message);
			response.sendRedirect("search-publish");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
