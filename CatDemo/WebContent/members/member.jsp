<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import=" java.util.List, java.util.ArrayList, java.util.Objects, com.entities.Pet"%>
<%@ page import="com.entities.Permiss, com.managers.PermissManager"%>

<!DOCTYPE html>
<html lang="zh-tw">
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet" />
	
<!-- font  awesome cdn link -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>

<!-- swiper css -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />


<link rel="stylesheet" href="css/head-nav.css" />
<link rel="stylesheet" href="css/footer.css" />
<link rel="stylesheet" href="css/user/side.css" />
<link rel="stylesheet" href="css/queries.css" />
<link rel="stylesheet" href="css/user/member.css" />
<link rel="stylesheet" href="css/pageButton.css" />
<link rel="stylesheet" href="css/shop/shop.css" />
<link rel="stylesheet" href="css/top.css" />

<link rel="shortcut icon" href="img/logo.png" />
<title>會員專區</title>
</head>
<body>
	<!-- header-nav -->
	<header class="header">
		<!-- MOBILE NAVIGATION -->
		<button class="btn__mobile-nav">
			<ion-icon class="icon__mobile-nav" name="menu-outline"></ion-icon>
			<ion-icon class="icon__mobile-nav" name="close-outline"></ion-icon>
		</button>
		
		<a href="animation.html"> <img src="img/logo.png" alt="Logo" class="logo" />
		</a>

		<nav class="nav">
			<ul class="nav__links">
				<li class="nav__item"><a href="index.jsp#section-1"
					class="nav__link">養貓好處</a></li>
				<li class="nav__item"><a href="index.jsp#section-4"
					class="nav__link">養貓需知</a></li>
				<li class="nav__item"><a href="pets/pet-form.jsp"
					class="nav__link">送養寵物</a></li>
				<li class="nav__item"><a href="pets" class="nav__link">領養寵物</a></li>

				<li class="nav__item"><a href="member"
					class="nav__link nav-cta">會員專區</a></li>
			</ul>
		</nav>
		
		<div class="icons">
        	<i class="fas fa-search search_icon" id="search-btn"></i>
      	</div>
      	
      	<form action="keyword-search" class="search-bar-container">
        	<input type="search" name="kwSearch" id="search-bar" placeholder="search here ...." />
        	<button type="submit" id="search-button" class="fas fa-search"></button>
      	</form>
	</header>

	<div class="content">
		<nav class="sidebar">
			<ul class="side-nav">
				<li class="side-nav__item side-nav__item--active user"><a
					href="#" class="side-nav__link nav__none"> <ion-icon name=""
							class="side-nav__icon user-icon"> </ion-icon>
							<span>${ username }</span>
				</a></li>

				<li class="side-nav__item"><a href="index.jsp"
					class="side-nav__link"> <ion-icon name="home-outline"
							class="side-nav__icon"> </ion-icon> <span>首頁</span>
				</a></li>

				<li class="side-nav__item"><a href="search"
					class="side-nav__link"> <ion-icon name="person-outline"
							class="side-nav__icon"></ion-icon> <span>會員資料</span>
				</a></li>

				<li class="side-nav__item"><a href="members/changepwd.jsp"
					class="side-nav__link"> <ion-icon name="key-outline"
							class="side-nav__icon"></ion-icon> <span>變更密碼</span>
				</a></li>

				<li class="side-nav__item"><a href="member"
					class="side-nav__link"> <ion-icon name="clipboard-outline"
							class="side-nav__icon"></ion-icon> <span>浪浪</span>
				</a></li>

				<li class="side-nav__item"><a href="pets/pet-form.jsp"
					class="side-nav__link"> <ion-icon name="add-circle-outline"
							class="side-nav__icon"></ion-icon> <span>新增寵物</span>
				</a></li>

				<li class="side-nav__item"><a href="pets"
					class="side-nav__link"> <ion-icon name="paw-outline"
							class="side-nav__icon"></ion-icon> <span>領養寵物</span>
				</a></li>

				<li class="side-nav__item"><a href="search-publish"
					class="side-nav__link"> <ion-icon name="build-outline"
							class="side-nav__icon"></ion-icon> <span>修改刊登</span>
				</a></li>

				<li class="side-nav__item"><a href="search-adoption"
					class="side-nav__link"> <ion-icon name="calendar-outline"
							class="side-nav__icon"></ion-icon> <span>預約訊息</span>
				</a></li>
				<% 
					int userId = (int) session.getAttribute("userId");
					// 得到使用者的權限
					Permiss permiss = null;
					permiss = PermissManager.getInstance().findByUserId(userId);
					String roleStr = "系統管理者";
				%>
				
				<% if(permiss != null && (Objects.equals(permiss.getRole(), roleStr))){ %>
					<li class="side-nav__item"><a href="role/aCatAndDog.jsp"
						class="side-nav__link"> <ion-icon name="leaf-outline" class="side-nav__icon"></ion-icon>
						<span>系統管理者模式</span>
					</a></li>
				<% } %>

				<li class="side-nav__item"><a href="logout"
					class="side-nav__link"> <ion-icon name="enter-outline"
							class="side-nav__icon"></ion-icon> <span>登出</span>
				</a></li>
			</ul>
		</nav>

		<main class="main">
			<%
			/*
					String message = (String)request.getAttribute("message");
					if(message!=null){
				out.print("<h1 style='margin-top:1rem;color:rgb(255,0,43);'>" + message + "</h1>");
					}*/
			%>
			<!-- <h1 style="margin-top:1rem;color:rgb(255,0,43);">${ message }</h1> -->

			<section class="section-card">
				<div class="grid grid--3-cols" id="card"></div>
			</section>
			
			<!-- shop -->
			<section>
				<div class="shop">
					<div class="shop__item-box">
						<h1 class="shop__item-heading">心動浪浪</h1>

						<div class="shop__btn-box">
							<button class="shop__btn-icon shop__btn-full" onclick="publish()">
								<ion-icon name="navigate-outline"></ion-icon>
								送出
							</button>
						</div>
					</div>

					<button class="btn__box">
						<ion-icon name="caret-forward-circle-outline" class="btn__icon"></ion-icon>

						<ion-icon name="caret-back-circle-outline" class="btn__icon"></ion-icon>
					</button>
					
					<!-- top -->
					<div class="shop__top-box">
						<a href="#" class="shop__top-btn"> <span>Top</span> <ion-icon
								class="btn__top" name="caret-up-circle-outline"></ion-icon>
						</a>
					</div>
				</div>
			</section>

			<!-- pageButton -->
			<section class="section-pageBtn">
				<div class="pageBtn_box">
					<div class="pagination">
						<%
						String pageTemp = (String) request.getAttribute("currentPage");
						int pageNum = Integer.parseInt(pageTemp);
						int currentPage = pageNum;

						int pageTotal = (int) request.getAttribute("pageTotal");

						for (int i = 1; i <= pageTotal; i++) {
							if (i == (currentPage)) {
								out.print("<button type='button' class='pagination_btn pagination_btn--current'>" + i + "</button>");
							} else {
								out.print("<button type='button' class='pagination_btn'>" + i + "</button>");
							}

						}
						%>
					</div>
				</div>
			</section>
		</main>
	</div>
	
	<!-- footer -->
	<footer class="footer">
		<div class="container--footer grid--footer">
			<div class="logo-col">
				<a href="animation.html"> <img src="img/logo.png" alt="Logo"
					class="logo" />
				</a>
				<ul class="footer__links">
					<li><a class="footer__link" href="#"> <ion-icon
								class="footer__icon" name="logo-twitter"></ion-icon>
					</a></li>
					<li><a class="footer__link" href="#"> <ion-icon
								class="footer__icon" name="logo-facebook"></ion-icon>
					</a></li>
					<li><a class="footer__link" href="#"> <ion-icon
								class="footer__icon" name="logo-instagram"></ion-icon>
					</a></li>
				</ul>
			</div>

			<div class="address-col">
				<p class="footer-heading">聯絡我們</p>
				<address class="contacts">
					<p class="address">貓貓市貓貓區貓貓路945號</p>
					<p>
						<a class="footer__link" href="tel:9527-5871">9527-5871</a> <br />
						<a class="footer__link" href="mailto:cat@catmeals.com">cat@catmeals.com</a>
					</p>
				</address>
			</div>
			<nav class="nav-col">
				<p class="footer-heading">貓貓相關</p>
				<ul class="foot-nav">
					<li><a class="footer__link" href="#">注意事項</a></li>

					<li><a class="footer__link" href="#">認養貓咪</a></li>

					<li><a class="footer__link" href="#">貓餐介紹</a></li>
				</ul>
			</nav>
			<p class="copyright">Copyright &copy; 2023 by Leo Fan. All right
				s reserved.</p>
		</div>
	</footer>

	<script type="module"
		src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule
		src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
		
	<!-- swiperjs -->
	<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>

	<script defer src="js/member.js"></script>
	<!-- 分頁 -->
	<script defer src="js/pagination.js"></script>
	<!-- 購物車 -->
	<script defer src="js/shop.js"></script>
	<!-- header上的search -->
	<script defer src="js/search.js"></script>
</body>
</html>