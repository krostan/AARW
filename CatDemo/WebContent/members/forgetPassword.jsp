<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, com.managers.MemberManager, com.dao.MemberDao, com.entities.Member"%>
<%@ page import="com.managers.ResetTokensManager, com.dao.ResetTokensDao"%>
<%@ page import="com.database.EmailSender, java.util.UUID, java.time.LocalDateTime, java.time.temporal.ChronoUnit"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet" />

<link rel="stylesheet" href="../css/login/loginStyle.css" />
<link rel="shortcut icon" href="../img/logo.png" />

<title>忘記密碼</title>
</head>
<body>
	<div class="background">
		<div class="ball"></div>
		<div class="ball"></div>
	</div>


	<form action="forgetPassword.jsp" method="POST" id="pwd_form">
		<h3 class="heading-primary">忘記密碼</h3>

		<label for="username">帳號</label> <input type="text" class="username"
			id="username" name="username" placeholder="Enter username" /> <label
			for="email">信箱</label> <input type="email" class="email" id="email"
			name="email" placeholder="Enter email" />
		<!-- pattern="^[A-Za-z0-9-_\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$" -->
		
		<%
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			String pwd = MemberManager.getInstance().findByNameEmail(username, email);

			if (username != null && pwd != null) {

				if (pwd.equals("")) {
					out.print("<h1 class='message_forget-h1'>資料錯誤</h1>");
				} else {
					//取得 member
					Member member = MemberManager.getInstance().findByName(username);
					int userId = member.getUserId();
					// 取得現在時間
					LocalDateTime currentDateTime = LocalDateTime.now();
					// 現在時間 + 10分鐘
					LocalDateTime expirationDate = currentDateTime.plus(10, ChronoUnit.MINUTES);
					// 得到token
					String resetToken = UUID.randomUUID().toString();
					
					ResetTokensManager.getInstance().save(userId, resetToken, expirationDate);
					
					EmailSender.sendEmail(email, resetToken);
					out.println("<script>alert('郵件已成功寄出'); window.location='../members/login.jsp';</script>");
				}
			}
		%>

		<div class="socail grid forget_btn-box">
			<div>
				<a href="login.jsp" class="btn-icon btn--full">返回</a>
			</div>
			<button type="button" class="btn-signin" onclick="getPwd();">送出</button>
		</div>

	</form>

	<script type="module"
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
	<script defer src="../js/signin.js"></script>
</body>
</html>