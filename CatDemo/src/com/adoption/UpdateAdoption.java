package com.adoption;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entities.Adoption;
import com.managers.AdoptionManager;
import com.managers.PublishManager;

/**
 * Servlet implementation class UpdateAdoption
 */
@WebServlet("/update-adoption")
public class UpdateAdoption extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateAdoption() {
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
		// 得到當前時間
		Date recordDate = new Date();
		// 編號
		int adoptionId = 0;
		// 結果
		String answer = "";

		// 判斷是否從管理介面來的
		String adminUrl = (String) request.getAttribute("adminUrl");
		if (adminUrl != null) {
			adoptionId = (int) session.getAttribute("adoptionId");
			session.removeAttribute("adoptionId");

			answer = request.getParameter("adoptionResult");

		} else {
			// 取得 不同意或同意
			String[] agree = { "不同意", "同意" };
			String isAgreeStr = request.getParameter("isAgr");
			int isAgree = Integer.parseInt(isAgreeStr);
			answer = agree[isAgree];

			// 取得預約編號
			String tmpId = request.getParameter("id");
			adoptionId = Integer.parseInt(tmpId);
		}

		// 更新
		boolean isSuccess = AdoptionManager.getInstance().updateAdoption(adoptionId, answer, recordDate);
		// 找到 petId
		int petId = AdoptionManager.getInstance().findById(adoptionId).getPet().getPetId();
		// 找到刊登編號
		int publishId = PublishManager.getInstance().findByPetId(petId).getPublishId();

		// 如果更新成功 且 預約結果為 同意
		// 刪除該刊登
		if (isSuccess && answer.equals("同意")) {
			PublishManager.getInstance().delete(publishId);

			// 刪除該預約結果 不是同意 的所有預約
			List<Adoption> adoptionList = AdoptionManager.getInstance().findAllByPetId(petId);
			for (Adoption adop : adoptionList) {
				if (!adop.getAdoptionResult().equals("同意")) {
					AdoptionManager.getInstance().delete(adop.getAdoptionId());
				}
			}
		}
		if (adminUrl != null) {
			String alertMessage = isSuccess ? "預約更新成功" : "預約更新失敗";
			out.println("<script>alert('" + alertMessage + "'); window.location='role/aCatAndDog.jsp';</script>");
			out.flush();
		} else {

			response.sendRedirect("search-adoption");
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
