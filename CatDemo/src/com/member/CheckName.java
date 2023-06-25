package com.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entities.Member;
import com.managers.MemberManager;

/**
 * Servlet implementation class Check
 */
@WebServlet("/check-name")
public class CheckName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckName() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");   
        
		String username = request.getParameter("username");

		PrintWriter out = response.getWriter();
		
		Member member;
		
		member = MemberManager.getInstance().findByName(username);
		int len = username.length();
		
		if(member !=null) {
			out.print("此帳號名稱已註冊");
		}else if(len<8 || len>12){
			out.print("帳號長度不正確, 請輸入8~12位");
		}else {
			out.print("");
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
