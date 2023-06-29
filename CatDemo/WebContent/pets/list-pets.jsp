<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List,java.util.ArrayList ,com.entities.Pet"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet" />

<!-- font  awesome cdn link -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" />

<!-- swiper css -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />

<link rel="stylesheet" href="css/head-nav.css" />
<link rel="stylesheet" href="css/footer.css" />
<link rel="stylesheet" href="css/queries.css" />
<link rel="stylesheet" href="css/listpets/list-pets.css" />
<link rel="stylesheet" href="css/pageButton.css" />
<link rel="stylesheet" href="css/shop/shop.css" />
<link rel="stylesheet" href="css/top.css" />
<link rel="stylesheet" href="css/swiper.css" />

<link rel="shortcut icon" href="img/logo.png" />
<title>領養寵物</title>
</head>
<body>
<!-- header-nav -->
	<header class="header">

		<!-- MOBILE NAVIGATION -->
		<button class="btn__mobile-nav">
			<ion-icon class="icon__mobile-nav" name="menu-outline"></ion-icon>
			<ion-icon class="icon__mobile-nav" name="close-outline"></ion-icon>
		</button>

		<a href="animation.html"> <img src="img/logo.png" alt="Logo"
			class="logo" />
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

		<!-- search -->
		<form action="keyword-search" class="search-bar-container">
			<input type="search" name="kwSearch" id="search-bar" placeholder="search here ...." />
			<button type="submit" id="search-button" class="fas fa-search"></button>
		</form>
	</header>

	<main class="main">
	<!-- swiper -->
		<section class="session-swiper">
			<div class="swiper__text-box">
				<h1 class="swiper-primary">
					<span class="swiper-primary--main">Be Their Voice</span> <span
						class="swiper-primary--sub">Adopt a Stray Today</span>
				</h1>
				<a href="#section-card" class="btn btn--swiper">Explore our
					tours</a>
			</div>

			<!-- Swiper -->
			<div class="swiper mySwiper">
				<div class="swiper-wrapper">
					<div class="swiper-slide">
						<img src="img/cat1.jpg" alt="" />
					</div>

					<div class="swiper-slide">
						<img src="img/cat2.jpg" alt="" />
					</div>

					<div class="swiper-slide">
						<img src="img/dog1.jpg" alt="" />
					</div>

					<div class="swiper-slide">
						<img src="img/cat3.jpg" alt="" />
					</div>

					<div class="swiper-slide">
						<img src="img/dog2.jpg" alt="" />
					</div>
				</div>
				<div class="swiper-scrollbar"></div>
			</div>
		</section>
		
		<!-- list-pets -->
		<section class="section-sort">
	        <div class="sort_box">
	          <div class="sor_box-text">Sort by :</div>
	
	          <div class="menu_box">
	            <div class="menu-text">
	              <div class="menu-header ">默認排序</div>
	              
	              <ul class="sort-menu">
	                <li class="menu-li">
	                  <button class="sort-item" type="button">默認排序</button>
	                </li>
	
	                <li class="menu-li">
	                  <button class="sort-item" type="button">最新發布</button>
	                </li>
	
	                <li class="menu-li">
	                  <button class="sort-item" type="button">亂數</button>
	                </li>
	
	                <!--  
	                <li class="menu-li">
	                  <button class="sort-item" type="button">亂數</button>
	                </li> 
	                -->
	              </ul>
	            </div>
	
	            <div class="icons_box">
	              <ion-icon name="chevron-down-outline" class="icons_sort"></ion-icon>
	              
	              <ion-icon name="chevron-up-outline" class="icons_sort"></ion-icon>
	            </div>
	          </div>
	        </div>
	      </section>
		<%
		/*
		String message = (String) request.getAttribute("message");
		if (message != null) {
			out.print("<h1 style='margin-top:1rem;color:rgb(255,0,43);'>" + message + "</h1>");
		}
		*/
		%>
		<section class="section-card">
			<div class="grid grid--3-cols" id="card"></div>
		</section>

		<!-- shop -->
		<section>
			<div class="shop">
				<div class="shop__item-box">
					<h1 class="shop__item-heading">心動浪浪</h1>


					<div class="shop__btn-box">
						<div>
							<button class="shop__btn-icon shop__btn-full"
								onclick="adoption()">
								<ion-icon name="cart-outline"></ion-icon>
								送出
							</button>
						</div>
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
	
	<!-- Swiper JS -->
    <script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>

    <!-- Initialize Swiper -->
    <script defer src="js/myswiper.js"></script>

	<!-- sticky nav button-->
	<script type="module"
		src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule
		src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>

	<!-- swiperjs -->
	<script
		src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>

	<!-- 自定義JS-->
	<script defer src="js/list-pets.js"></script>
	
	<!-- <script defer src="js/pagination.js"></script> -->
	
	<script defer src="js/shop.js"></script>

</body>
</html>