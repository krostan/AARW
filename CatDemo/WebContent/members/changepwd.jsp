<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="../css/resetpwd.css" />
<link rel="shortcut icon" href="../img/logo.png" />
<title>變更密碼</title>
</head>
<body>
	<div class="background">
		<div class="ball"></div>
		<div class="ball"></div>
	</div>

	<!-- resetpwd -->
	<form action="../change-pwd" method="POST" id="pwd_form">
		<h3 class="heading-primary">變更密碼</h3>
		<label for="oldpassword">原本密碼</label>
		<div class="input_box">
			<input type="password" class="oldpassword" id="oldpassword"
				name="oldpassword" placeholder="Enter password"
				pattern="^[0-9a-z]{8,12}$" required />

			<div class="icon__box">
				<ion-icon name="eye-outline" class="input-icon"></ion-icon>
				<ion-icon name="eye-off-outline" class="input-icon"></ion-icon>
			</div>
		</div>

		<label for="password1">新密碼(8~12)</label>
		<div class="input_box">
			<input type="password" class="password" id="password1"
				name="password1" placeholder="Enter password"
				pattern="^[0-9a-z]{8,12}$" required />

			<div class="icon__box">
				<ion-icon name="eye-outline" class="input-icon"></ion-icon>
				<ion-icon name="eye-off-outline" class="input-icon"></ion-icon>
			</div>
		</div>

		<label for="password2">再輸入新密碼</label>
		<div class="input_box">
			<input type="password" class="password" id="password2"
				name="password2" placeholder="Enter password"
				pattern="^[0-9a-z]{8,12}$" required />

			<div class="icon__box">
				<ion-icon name="eye-outline" class="input-icon"></ion-icon>
				<ion-icon name="eye-off-outline" class="input-icon"></ion-icon>
			</div>
		</div>
		<%
		String message = (String) session.getAttribute("message");
		if (message != null) {
			out.print("<h1 class='message_h1'>" + message + "</h1>");
		}

		session.removeAttribute("message");
		%>
		<div class="socail reset_pwd grid">
			<div>
				<a href="../member" class="btn-icon btn--full">返回</a>
			</div>
			<button class="btn-signin">送出</button>
		</div>


	</form>

	<script type="module"
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
	<script defer src="../js/resetpwd.js"></script>
</body>
</html>