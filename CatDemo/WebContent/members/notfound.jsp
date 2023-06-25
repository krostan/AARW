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
	
<!-- font  awesome cdn link -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"/>

<!-- swiper css -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.css" />


<link rel="stylesheet" href="../css/head-nav.css" />
<link rel="stylesheet" href="../css/footer.css" />
<link rel="stylesheet" href="../css/user/side.css" />
<link rel="stylesheet" href="../css/queries.css" />
<link rel="stylesheet" href="../css/user/member.css" />
<link rel="stylesheet" href="../css/pageButton.css" />
<link rel="stylesheet" href="../css/shop/shop.css" />
<link rel="stylesheet" href="../css/top.css" />
<link rel="stylesheet" href="../css/swiper.css" />

<link rel="stylesheet" href="../css/notfound.css" />

<link rel="shortcut icon" href="../img/logo.png" />

<title>搜尋不到</title>
</head>
<body>
	<!-- header-nav -->
	<header class="header">
		<!-- MOBILE NAVIGATION -->
		<button class="btn__mobile-nav">
			<ion-icon class="icon__mobile-nav" name="menu-outline"></ion-icon>
			<ion-icon class="icon__mobile-nav" name="close-outline"></ion-icon>
		</button>
		
		<a href="../animation.html"> <img src="../img/logo.png" alt="Logo" class="logo" />
		</a>

		<nav class="nav">
			<ul class="nav__links">
				<li class="nav__item"><a href="../index.jsp#section-1"
					class="nav__link">養貓好處</a></li>
				<li class="nav__item"><a href="../index.jsp#section-4"
					class="nav__link">養貓需知</a></li>
				<li class="nav__item"><a href="../pets/pet-form.jsp"
					class="nav__link">送養寵物</a></li>
				<li class="nav__item"><a href="../pets" class="nav__link">領養寵物</a></li>

				<li class="nav__item"><a href="../member"
					class="nav__link nav-cta">會員專區</a></li>
			</ul>
		</nav>
		
		<div class="icons">
        	<i class="fas fa-search search_icon" id="search-btn"></i>
      	</div>
      	
      	<form action="../keyword-search" class="search-bar-container">
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
						<img src="../img/cat1.jpg" alt="" />
					</div>

					<div class="swiper-slide">
						<img src="../img/cat2.jpg" alt="" />
					</div>

					<div class="swiper-slide">
						<img src="../img/dog1.jpg" alt="" />
					</div>

					<div class="swiper-slide">
						<img src="../img/cat3.jpg" alt="" />
					</div>

					<div class="swiper-slide">
						<img src="../img/dog2.jpg" alt="" />
					</div>
				</div>
				<div class="swiper-scrollbar"></div>
			</div>
		</section>
		
		<section class="section-notfound">
			<div class="card-section">
		<%
		String keyword = (String)session.getAttribute("kwSearch");
		if(keyword!=null){
			out.print("<p>找不到符合搜尋字詞「" + keyword + "」的文件</p>");	
			session.removeAttribute("kwSearch");
		}
		%>
				<p>建議:</p>
				<ul>
					<li>請檢查有無錯別字</li>
					<li>試試以其他關鍵字搜尋。</li>
					<li>試試以較籠統的關鍵字搜尋。</li>
				</ul>
			</div>
		</section>
	
	</main>
	
	<!-- footer -->
	<footer class="footer">
		<div class="container--footer grid--footer">
			<div class="logo-col">
				<a href="../animation.html"> <img src="../img/logo.png" alt="Logo"
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
	<script
		src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>
		
	<!-- Initialize Swiper -->
    <script defer src="../js/myswiper.js"></script>
	
	<!-- header上的search -->
	<script defer src="../js/search.js"></script>
</body>
</html>