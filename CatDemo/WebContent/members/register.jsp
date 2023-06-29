<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&display=swap"
      rel="stylesheet"
    />

    <link rel="stylesheet" href="../css/register/register.css" />
    
    <link rel="shortcut icon" href="../img/logo.png" />
    
    <title>註冊</title>
    
  </head>
  <body>
    <div class="background">
      <div class="ball"></div>
      <div class="ball"></div>
    </div>

    <form action="../register" method="POST" class="fs-2" id="register-form">
      <h3 class="heading-primary">註冊</h3>
      
      <%
      	Date currentDate = new Date();
      	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      	String formatDate = sdf.format(currentDate);
      
      	
		String message = (String)session.getAttribute("message");
      
		if(message!=null){
			out.print("<h1 class='message_h1'>" + message + "</h1>");	
			session.removeAttribute("message");
		}
		%>
	  <!-- <h1 style="margin-top:1rem;color:rgb(255,0,43);">${ message }</h1> -->

      <input type="hidden" /> 
      
      <label for="username">帳號(8-12)</label>
      <input
        type="text"
        class="username"
        id="username"
        name="username"
        onchange="checkUserName();"
        placeholder="輸入帳號"
        pattern="^[0-9a-z]{8,12}$"
        required
      />

      <h1 class="check-date" id="checkUserName"></h1>

      <label for="password">密碼(8-12)</label>
      <input
        type="password"
        class="password"
        id="password"
        name="password"
        onchange="checkPassword();"
        placeholder="輸入密碼"
        pattern="^[0-9a-z]{8,12}$"
        required
      />
      
      <h1 class="check-date" id="checkPassword"></h1>

      <label for="name">姓名</label>
      <input
        type="text"
        class="name"
        id="name"
        name="name"
        placeholder="貓貓貓"
        required
      />

      <label for="phone">電話</label>
      <input
        type="tel"
        class="phone"
        id="phone"
        name="phone"
        onchange="checkPhone();"
        placeholder="0995275871"
        pattern="^09\d{2}-?\d{3}-?\d{3}$"
        required
      />
      <h1 class="check-date" id="checkPhone"></h1>

      <label for="birthday">出生年月日</label>
      <input
        type="date"
        class="birthday"
        id="birthday"
        name="birthday"
        min="1900-01-01"
        max="<%= formatDate %>"
        required
      />

      <label for="email">Email</label>
      <input
        type="email"
        class="email"
        id="email"
        name="email"
        onchange="checkEmail();"
        placeholder="cat@exaple.com"
       	pattern="^[A-Za-z0-9-_\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+\.[a-zA-Z0-9_-]+$"
        required 
      />
      <h1 class="check-date" id="checkEmail"></h1>
      
      <label for="gender">性別</label>
      <select name="gender" id="gender" class="gender" required>
        <option value="">請選擇性別</option>
        <option value="1">男</option>
        <option value="2">女</option>
      </select>
      
      <label for="address">地址</label>
      <input
        class="address"
        placeholder="貓貓市貓貓區貓貓路11號"
        name="address"
        id="address"
        required
      />
      
      <div class="check">
		<div class="flex check_img-box">
	        <img src="../createImage.jsp" alt="Random_Number" class="check_img" />
    	    <ion-icon name="refresh-outline" class="check_icon-refresh"></ion-icon>
		</div>
        <input type="text" name="insrand" size="8" maxlength="4" />
      </div>

      <button type="submit" class="btn-login" >送出</button>

      <div class="socail grid">
        <div>
          <a href="../index.jsp" class="btn-icon btn--full flex">
            <ion-icon class="socail-icon" name="home-outline"></ion-icon>
            <p>首頁</p>
          </a>
        </div>

        <div>
          <a href="login.jsp" class="btn-icon btn--cat flex">
            <ion-icon class="socail-icon" name="paw-outline"></ion-icon>
            <p>返回</p>
          </a>
        </div>
      </div>
    </form>

    <script
      type="module"
      src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"
    ></script>
    <script
      nomodule
      src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"
    ></script>
    
    <!-- 驗證使用者資料 -->
    <script defer src="../js/check.js"></script>
    <!-- 重置驗證圖片 -->
    <script defer src="../js/refresh.js"></script>

  </body>
</html>
