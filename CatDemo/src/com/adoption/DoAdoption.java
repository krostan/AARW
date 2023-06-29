package com.adoption;

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

import com.entities.Adoption;
import com.managers.AdoptionManager;

/**
 * Servlet implementation class DoAdoption
 */
@WebServlet("/do-adoption")
public class DoAdoption extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoAdoption() {
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

		int userId = 0;
		int petId = 0;
		String errorMessage = "";
		boolean isSuccess = false;
		// 得到當前時間
		Date date = new Date();
		// 預設結果
		String adoptionResult = "等候申請結果";
		// 預設答覆日期
		Date recordDate = null;

		Adoption adoption;

		String adminUrl = (String) request.getAttribute("adminUrl");
		// 判斷是否從管理介面來的
		if (adminUrl != null) {
			try {
				String userStr = request.getParameter("userid");
				String petStr = request.getParameter("petid");
				userId = Integer.valueOf(userStr);
				petId = Integer.valueOf(petStr);
			} catch (NumberFormatException e) {
				System.out.println("不是數字");
				errorMessage = "請輸入數字";
			}

		} else {
			// 取得目前登入的會員ID
			userId = (int) session.getAttribute("userId");
			// 得到寵物ID
			String tmp = request.getParameter("pet");
			petId = Integer.valueOf(tmp);
		}

		List<Adoption> result = new ArrayList<>();
		result = AdoptionManager.getInstance().findByMemberId(userId);

		// 判斷是否目前會員已有預約其他浪浪
		boolean isAdoption = false;

		if (result.isEmpty()) {
			isAdoption = true;
		} else {
			for (Adoption adop : result) {
				if (!adop.getAdoptionResult().equals(adoptionResult)) {
					isAdoption = true;
				} else {
					isAdoption = false;
				}
			}
		}

		// 判斷該寵物是否已經被目前會員預約
		adoption = AdoptionManager.getInstance().findByMemberPetId(userId, petId);

		if ((adoption == null) && isAdoption) {
			if(errorMessage.equals("")) {
				isSuccess = AdoptionManager.getInstance().save(date, adoptionResult, recordDate, petId, userId);				
			}
			
			if (adminUrl != null) {
				String alertMessage = isSuccess ? "預約成功" : "預約失敗";
				out.println("<script>alert('" + alertMessage + "'); window.location='role/aCatAndDog.jsp';</script>");
				out.flush();
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
