package com.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entities.Member;
import com.google.gson.Gson;
import com.managers.MemberManager;

/**
 * Servlet implementation class AdminUpdateUser
 */
@WebServlet("/admin-update-user")
public class AdminUpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public AdminUpdateUser() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("admin-update-user");

		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String updateUserNo = request.getParameter("updateUserNo");
		int userNo = Integer.parseInt(updateUserNo);
		/* 將查詢的會員編號儲存在session 以便在之後的更新可以找到該使用者並更新其資料 */
		session.setAttribute("userId", userNo);

		Member member = MemberManager.getInstance().findById(userNo);

		// 日期轉字串
		String dateString = new SimpleDateFormat("yyyy-MM-dd").format(member.getBirthday());

		Map<String, String> map = new HashMap<>();

		map.put("name", member.getName());
		map.put("phone", member.getPhone());
		map.put("birthday", dateString);
		map.put("email", member.getEmail());
		map.put("gender", member.getGender());
		map.put("address", member.getAddress());

		Gson gson = new Gson();
		String json = gson.toJson(map);

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(json);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
