package com.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entities.ResetTokens;
import com.managers.ResetTokensManager;

/**
 * Servlet implementation class ResetPwd
 */
@WebServlet("/reset-pwd")
public class ResetPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetPwd() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("reset-password");
		
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String token = request.getParameter("token");
		session.setAttribute("token", token);
		
		// 取得現在時間
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		ResetTokens resetTokens = ResetTokensManager.getInstance().findByToken(token);
		
		// 取得截止日期
		LocalDateTime expirationDate = resetTokens.getExpirationDate();
		// 取得 是否使用過
		String isUsed = resetTokens.getIsUsed();
		
		// 當目前時間早於截止時間
		if(currentDateTime.isBefore(expirationDate)&& isUsed.equals("N")) {
			response.sendRedirect("members/resetpwd.jsp");
		}else {
			out.print("<script>alert('已超過有效時間'); window.location='members/login.jsp';</script>");
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
