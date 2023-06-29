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
<link rel="shortcut icon" href="../img/logo.png" />

<title>登入</title>
</head>
<body>

	<div class="background">
		<div class="ball"></div>
		<div class="ball"></div>
	</div>


	<form action="../login" method="POST">
		<h3 class="heading-primary">Login</h3>

		<label for="username">帳號</label> 
		<input type="text" class="username" id="username" name="username" placeholder="Enter username" /> 
		
		<label for="password">密碼</label>
		<input type="password" class="password" id="password" name="password"
			placeholder="Enter password" />
			
		<div class="check">
			<div class="flex check_img-box">
	        	<img src="../createImage.jsp" alt="Random_Number" class="check_img" />
    	    	<ion-icon name="refresh-outline" class="check_icon-refresh"></ion-icon>
			</div>
        	<input type="text" name="insrand" size="8" maxlength="4" />
      	</div>

		<%
		String message = (String)session.getAttribute("message");
		if(message!=null){
			out.print("<h1 class='message_h1'>" + message + "</h1>");	
			session.removeAttribute("message");
		}
		%>
		<!-- <h1 style="margin-top:1rem;color:rgb(255,0,43);"> ${ message }</h1> -->
		
		<a href="forgetPassword.jsp" class="pwd_link">忘記密碼?</a>

		<button class="btn-login">登入</button>

		<div class="socail grid">
			<div>
				<a href="../index.jsp" class="btn-icon btn--full flex"> 
				<ion-icon class="socail-icon" name="home-outline"></ion-icon>
					<p>返回</p>
				</a>
			</div>

			<div>

				<a href="register.jsp" class="btn-icon btn--cat flex"> <ion-icon
						class="socail-icon" name="paw-outline"></ion-icon>
					<p>註冊</p>
				</a>
			</div>

		</div>
	</form>

	<script type="module"
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
		
	<script defer src="../js/refresh.js"></script>
</body>
</html>