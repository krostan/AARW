package com.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.managers.MemberManager;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
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

		/************** 獲得表單傳過來的資料 *****************/
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");

		String date = request.getParameter("birthday");
		Date birthday = null;

		try {
			birthday = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			System.out.println();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String email = request.getParameter("email");

		String[] genArray = { "男", "女" };
		String tempStr = request.getParameter("gender");
		int tempDender = Integer.parseInt(tempStr);
		String gender = genArray[tempDender - 1];

		String address = request.getParameter("address");

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		// 得到驗證碼
		String rnd = (String) session.getAttribute("rnd");
		String input = request.getParameter("insrand");
		/********************************************/
		// 得到上一層的路徑
		String previousUrl = request.getHeader("Referer");
		URL urlPath = new URL(previousUrl);
		String path = urlPath.getPath();

		boolean isAdmin = path.equals("/CatDemo/role/admin.jsp");
		/*******************************************************/

		boolean isSuccess;

		// 如果不是從管理頁面跳過來的 或是 驗證碼不對 進入if
		if (!isAdmin && !rnd.equals(input)) {
			session.setAttribute("message", "驗證錯誤");
			response.sendRedirect("members/register.jsp");
		} else {
			isSuccess = MemberManager.getInstance().save(username, password, name, phone, birthday, email, gender,
					address);
			if (isAdmin) {
				String alertMessage = isSuccess ? "會員註冊成功" : "會員註冊失敗";
				out.println("<script>alert('" + alertMessage + "'); window.location='role/admin.jsp';</script>");
				out.flush();
			} else {
				if (isSuccess) {
					session.setAttribute("message", "註冊成功");
					response.sendRedirect("members/login.jsp");
				} else {
					session.setAttribute("message", "註冊失敗");
					response.sendRedirect("members/register.jsp");
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
