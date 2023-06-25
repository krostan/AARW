<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.entities.Member,java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet" />

<link rel="stylesheet" href="css/register/register.css" />
<link rel="shortcut icon" href="img/logo.png" />

<title>會員資料</title>
</head>
<body>
<div class="background">
      <div class="ball"></div>
      <div class="ball"></div>
    </div>
    <% 
    Member member = (Member)request.getAttribute("member");
    
    String name = member.getName();
    String phone = member.getPhone();
    Date birthday = member.getBirthday();
    String email = member.getEmail();
    
    String tempDender = member.getGender();
	int gender = tempDender.equals("男") ? 1 : 2;

    String address = member.getAddress();
    %>

    <form action="update"
          method="POST"
          class="fs-2"
          >
      <h3 class="heading-primary">基本資料</h3>

      <label for="name">姓名</label>
      <input
        type="text"
        class="name"
        id="name"
        name="name"
        value=<%= name %>
        placeholder="貓貓貓"
      />

      <label for="phone">Phone</label>
      <input
        type="tel"
        class="phone"
        id="phone"
        name="phone"
        value=<%= phone %>
        placeholder="0995275871"
        pattern="[0-9]{4}[0-9]{3}[0-9]{3}"
      />

      <label for="birthday">出生年月日</label>
      <input
        type="date"
        class="birthday"
        id="birthday"
        name="birthday"
        value=<%= birthday %>
        min="1900-01-01"
        max="2019-01-01"
      />

      <label for="email">Email</label>
      <input
        type="email"
        class="email"
        id="email"
        name="email"
        value=<%= email %>
        placeholder="cat@exaple.com"
        pattern="^[A-Za-z0-9-_\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$"
      />
      
      <label for="gender">性別</label>
      <select name="gender" id="gender">
        <option value="0">請選擇性別</option>
        
        <% if(gender==1){ %>
        	<option value="1" selected>男</option>
        	<option value="2" >女</option>
        <% }else{ %>
        	<option value="1" >男</option>
        	<option value="2" selected>女</option>
        <% } %>
      </select>
      
      <label for="address">地址</label>
      <input
        type="address"
        class="address"
        id="address"
        name="address"
        value=<%= address %>
        placeholder="貓貓市貓貓區貓貓路11號"
      />

      <button class="btn-login">儲存</button>

      <div class="socail grid">
        <div>
          <a href="../index.jsp" class="btn-icon btn--full flex">
            <ion-icon class="socail-icon" name="home-outline"></ion-icon>
            <p>首頁</p>
          </a>
        </div>

        <div>
          <a href="member" class="btn-icon btn--cat flex">
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
    

</body>
</html>