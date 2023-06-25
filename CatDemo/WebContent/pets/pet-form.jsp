<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
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
<link rel="stylesheet" href="../css/footer.css"  />
<link rel="stylesheet" href="../css/addpet/add-pet.css" />
<link rel="stylesheet" href="../css/queries.css" />
<link rel="stylesheet" href="../css/general.css" />

<link rel="shortcut icon" href="../img/logo.png" />
<title>新增寵物</title>
</head>
<body>
<!-- head-nav -->
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
				<li class="nav__item"><a href="pets/pet-form.jsp"
					class="nav__link">送養寵物</a></li>
				<li class="nav__item"><a href="../pets" class="nav__link">領養寵物</a></li>

				<li class="nav__item"><a href="../member"
					class="nav__link nav-cta">會員專區</a></li>
			</ul>
		</nav>
		
		<div class="icons">
        	<i class="fas fa-search search_icon" id="search-btn"></i>
      	</div>
      	
      	<!-- search -->
      	<form action="../keyword-search" class="search-bar-container">
        	<input type="search" name="kwSearch" id="search-bar" placeholder="search here ...." />
        	<button type="submit" id="search-button" class="fas fa-search"></button>
      	</form>
	</header>

	<main>
		<section class="section-pet">
			<div class="container">
				<h2 class="heading-pet">寵物資料</h2>
				<hr />
				<form action="../add-pet" method="POST"
					enctype="multipart/form-data" class="pet__form">
					<div class="grid form-grid">

						<div class="form-flex">
							<label for="petName" class="pet__form-lab">寵物名稱</label> 
							<input type="text" class="input-text" id="petName" name="petName" placeholder="貓貓貓" required/>
						</div>

						<div class="form-flex">
							<label for="breed" class="pet__form-lab">品種</label> 
							<input type="text" class="input-text" id="breed" name="breed" placeholder="金吉拉" required />
						</div>

						<div class="form-flex">
							<label for="gender" class="pet__form-lab">性別</label> 
							<input type="text" class="input-text" id="gender" name="gender" placeholder="性別" required />
						</div>

						<div class="form-flex">
							<label for="coatColor" class="pet__form-lab">毛色</label> 
							<input type="text" class="input-text" id="coatColor" name="coatColor" placeholder="毛色" required />
						</div>

						<div class="form-flex">
							<label for="age" class="pet__form-lab">年齡</label> 
							<input type="text" class="input-text" id="age" name="age" placeholder="1" required />
						</div>

						<div class="form-flex">
							<label for="location" class="pet__form-lab">所在地</label> 
							<input type="text" class="input-text" id="location" name="location" placeholder="桃園" required />
						</div>

						<div class="form-flex">
							<label for="species" class="pet__form-lab">種類</label> 
							<input type="text" class="input-text" id="species" name="species" placeholder="種類" required />
						</div>

						<div class="form-flex">
							<label for="size" class="pet__form-lab">體型</label> 
							<input type="text" class="input-text" id="size" name="size" placeholder="體型" required />
						</div>

						<div class="form-flex">
							<span class="pet__form-lab">圖片</span>
							<label for="image" class="lab__img">
								<div id="formFile" class=""></div> 
								<ion-icon name="images-outline" class="lab__img-icon"></ion-icon>

							</label>
							<!-- multiple -->
							<input
								type="file" class="input-text input_img" id="image" name="image"
								accept=".jpg" 
								onchange="previewMultiple(event)" required />
							
						</div>

						<div class="form-flex">
							<label for="quest" class="pet__form-lab">條件</label>

							<textarea class="input-textarea" placeholder="" id="quest"
								name="quest"></textarea>
						</div>
					</div>

					<div
						class="form__btn-box">
						<button type="submit" class="form__btn btn--cat">儲存</button>
						<a href="../member" class="form__link nav-cta margin-left">返回</a>
					</div>
				</form>
			</div>
		</section>
	</main>

	<!-- footer -->
	<footer class="footer">
		<div class="container grid--footer">
			<div class="logo-col">
				<a href="animation.html"> <img src="../img/logo.png" alt="Logo"
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

	<!-- sticky nav button-->
	<script type="module"
		src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule
		src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>
		
	<!-- swiperjs -->
	<script src="https://cdn.jsdelivr.net/npm/swiper@9/swiper-bundle.min.js"></script>

	<!-- 自定義JS-->
	<script defer src="../js/script.js"></script>
	<script defer src="../js/displayFilesImages.js"></script>
	<script defer src="../js/search.js"></script>

</body>
</html>