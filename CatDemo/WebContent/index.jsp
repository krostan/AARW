<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;600;700;900&display=swap"
	rel="stylesheet" />

<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet" href="css/general.css" />
<link rel="stylesheet" href="css/queries.css" />
<link rel="stylesheet" href="css/top.css" />

<link rel="shortcut icon" href="img/logo.png" />
<title>Cat Demo</title>
</head>
<body>
	<header class="header">
		<a href="animation.html"> <img src="img/logo.png" alt="Logo"
			class="logo" />
		</a>

		<nav class="nav">
			<ul class="nav__links">
				<li class="nav__item"><a href="#section-1" class="nav__link">養貓好處</a>
				</li>
				<li class="nav__item"><a href="#section-2" class="nav__link">貓咪介紹</a>
				</li>
				<li class="nav__item"><a href="#section-3" class="nav__link">貓餐介紹</a>
				</li>
				<li class="nav__item"><a href="#section-4" class="nav__link">養貓需知</a>
				</li>
				<li class="nav__item">
					<%
					String username = (String) session.getAttribute("username");
					if(username!=null){
						out.print("<a href='member' class='nav__link nav-cta'>會員專區</a>");
					}else{
						out.print("<a href='members/login.jsp' class='nav__link nav-cta'>登入</a>");
					}
					%> 


				</li>
			</ul>
		</nav>
		<!-- MOBILE NAVIGATION -->
		<button class="btn__mobile-nav">
			<ion-icon class="icon__mobile-nav" name="menu-outline"></ion-icon>
			<ion-icon class="icon__mobile-nav" name="close-outline"></ion-icon>
		</button>
	</header>

	<main>
		<section class="section-cat">
			<div class="cat">
				<div class="cat-text-box">
					<h1 class="heading-primary">One cat just leads to another.</h1>

					<p class="cat__description">Lorem ipsum dolor sit amet
						consectetur adipisicing elit. Architecto ea qui eaque inventore
						accusantium alias, quo magnam porro</p>

					<a href="#section-5" class="btn btn--full margin-right">start
						to have a cat</a> <a href="#section-3" class="btn btn--cat">了解更多
						&DownArrow;</a>
				</div>

				<div class="cat-img-box">
					<img class="cat__img" src="img/section-cat-1.jpg"
						alt="A cat is watching you" />
				</div>
			</div>
			
			<div class="shop__top-box">
			     <a href="#" class="shop__top-btn">
			         <span>Top</span>
			         <ion-icon class="btn__top" name="caret-up-circle-outline"></ion-icon>
			     </a>
      		</div>
		</section>
		

		<section class="section section-benefit" id="section-1">
			<div class="container">
				<span class="subheading">開始養貓</span>
				<h2 class="heading-secondary">養貓好處</h2>
			</div>

			<div class="container benefit-grid grid grid--2-cols grid--center-v">
				<div class="content-img-box">
					<img class="content-img" src="img/feature-1.jpg" alt="a sleep cat" />
				</div>

				<div class="content-text-box">
					<p class="content-number">01</p>
					<h3 class="heading-tertiary">身心理療癒功能開到最強</h3>
					<p class="content-description">貓咪是一種非常敏銳的動物，但當他認可你時，
						牠可以窩在你旁邊睡到作夢、翻肚肚給你摸、 跳到你腳上呼嚕嚕，我只能說這種療癒感， 真的很難用言語形容，養一隻就會知道了。</p>
				</div>

				<div class="content-text-box">
					<p class="content-number">02</p>
					<h3 class="heading-tertiary">責任心、耐心逐漸強大</h3>
					<p class="content-description">貓咪有時候很煩人，但你還是得放下手邊的事陪牠玩、處理牠闖的禍，
						你罵牠牠也不是馬上聽得懂，只能慢慢引導，慢慢的將牠塑造成你喜歡的樣子， 而你也會慢慢變成牠喜歡的樣子。</p>
				</div>

				<div class="content-img-box">
					<img class="content-img" src="img/feature-2.jpg" alt="imt" />
				</div>

				<div class="content-img-box">
					<img class="content-img" src="img/feature-3.jpg" alt="imt" />
				</div>

				<div class="content-text-box">
					<p class="content-number">03</p>
					<h3 class="heading-tertiary">探索自己內心最無私的愛</h3>
					<p class="content-description">貓咪是一種非常敏銳的動物，但當他認可你時，
						牠可以窩在你旁邊睡到作夢、翻肚肚給你摸、 跳到你腳上呼嚕嚕，我只能說這種療癒感， 真的很難用言語形容，養一隻就會知道了。</p>
				</div>
			</div>
		</section>

		<section class="section section-slider" id="section-2">
			<div class="container content-center">
				<span class="subheading">Cats</span>
				<h2 class="heading-secondary">貓咪簡介</h2>
			</div>

			<div class="slider">
				<div class="slide slide--1">
					<div class="testimonial">
						<div class="testimonial__photo-box">
							<img src="img/slide-1.jpg" alt="cat-1" class="testimonial__photo" />
						</div>

						<blockquote class="testimonial__text">
							<p class="testimonial__text-cat content-center">波斯貓</p>
							<p class="testimonial__text-content">
								性情溫和易相處，是非常適合當作寵物，毛量厚長，而且眼睛大，且圓，呈綠色，個性溫順，豐滿的尾巴，是最迷人之處</p>
						</blockquote>
					</div>
				</div>

				<div class="slide slide--2">
					<div class="testimonial">
						<div class="testimonial__photo-box">
							<img src="img/slide-2.jpg" alt="cat-1" class="testimonial__photo" />
						</div>

						<blockquote class="testimonial__text">
							<p class="testimonial__text-cat content-center">短毛貓</p>
							<p class="testimonial__text-content">
								個性沉穩，是非常親人的貓咪，對待家裡的新成員總是能很快適應，，四肢粗壯，頸部同樣又短又小，尾巴也短，耳朵也小，但有雙又大又圓的眼睛
							</p>
						</blockquote>
					</div>
				</div>

				<div class="slide slide--3">
					<div class="testimonial">
						<div class="testimonial__photo-box">
							<img src="img/slide-3.jpg" alt="cat-1" class="testimonial__photo" />
						</div>

						<blockquote class="testimonial__text">
							<p class="testimonial__text-cat content-center">美國捲耳貓</p>
							<p class="testimonial__text-content">
								有個契形的頭部，和狀似胡桃的雙眼，尾巴很長，最獨一無二的特色，就是它那往後捲耳朵，耳朵大，以至少90度的滑順弧形往後彎</p>
						</blockquote>
					</div>
				</div>

				<button class="slider__btn slider__btn--left">
					<ion-icon class="slide__icon" name="chevron-back-outline"></ion-icon>
				</button>

				<button class="slider__btn slider__btn--right">
					<ion-icon class="slide__icon" name="chevron-forward-outline"></ion-icon>
				</button>

				<div class="dots">
					<!-- <div class="dots__dot dots__dot--active">&nbsp</div>
            <div class="dots__dot">&nbsp</div>
            <div class="dots__dot">&nbsp</div>
            <div class="dots__dot">&nbsp</div>
            <div class="dots__dot">&nbsp</div> -->
				</div>
			</div>
		</section>

		<section class="section section-pricing" id="section-3">
			<div class="container">
				<span class="subheading">價格</span>
				<h2 class="heading-secondary">給貓吃得更健康</h2>
			</div>

			<div class="container grid grid--2-cols margin-botton">
				<figure class="pricing__eat pricing__eat-starter">
					<img src="img/pricing-1.jpg" alt="a cat" class="pricing__photo" />

					<div class="pricing__text-box">
						<h3 class="heading-tertiary">每日</h3>

						<ul class="pricing__lists">
							<li class="pricing__list"><ion-icon name="paw"
									class="pricing__icon"></ion-icon> <span>每天一餐</span></li>
							<li class="pricing__list"><ion-icon name="alarm"
									class="pricing__icon"></ion-icon> <span>9:00 ~ 21:00</span></li>
							<li class="pricing__list"><ion-icon name="cart"
									class="pricing__icon"></ion-icon> <span>免費送貨</span></li>
							<li class="pricing__list"><ion-icon name="restaurant"
									class="pricing__icon"></ion-icon> <span></span></li>
						</ul>
					</div>

					<div class="eat__price">
						<strong class="eat__price-text">$299</strong> <a href="#section-5"
							class="btn btn--full">預約</a>
					</div>
				</figure>

				<figure class="pricing__eat pricing__eat-complete">
					<img src="img/pricing-2.jpg" alt="a cat" class="pricing__photo" />

					<div class="pricing__text-box">
						<h3 class="heading-tertiary">每月</h3>

						<ul class="pricing__lists">
							<li class="pricing__list"><ion-icon name="paw"
									class="pricing__icon"></ion-icon> <span>每天兩餐</span></li>
							<li class="pricing__list"><ion-icon name="alarm"
									class="pricing__icon"></ion-icon> <span>9:00 ~ 21:00</span></li>
							<li class="pricing__list"><ion-icon name="cart"
									class="pricing__icon"></ion-icon> <span>免費送貨</span></li>
							<li class="pricing__list"><ion-icon name="restaurant"
									class="pricing__icon"></ion-icon> <span>獲得最新食譜</span></li>
						</ul>
					</div>

					<div class="eat__price">
						<strong class="eat__price-text">$6990</strong> <a
							href="#section-5" class="btn btn--full">預約</a>
					</div>
				</figure>
			</div>

			<div class="container grid grid--center-v margin-botton">
				<aside class="plan-details">你可以在任何時候取消。這兩個計劃都包括以下內容：</aside>
			</div>

			<div class="container grid grid--4-cols">
				<!-- feature-1 -->
				<div class="feature">
					<ion-icon class="feature__icon" name="person-outline"></ion-icon>
					<p class="feature__title">隨時供應</p>
					<p class="feature__text">計畫涵蓋每年365天，甚至重大節日</p>
				</div>

				<!-- feature-2 -->
				<div class="feature">
					<ion-icon class="feature__icon" name="home-outline"></ion-icon>
					<p class="feature__title">本地有機</p>
					<p class="feature__text">使用新鮮雞、牛、魚肉，搭配有機蔬果，依照營養師建議比例，準備貓餐</p>
				</div>

				<!-- feature-3 -->
				<div class="feature">
					<ion-icon class="feature__icon" name="water-outline"></ion-icon>
					<p class="feature__title">沒有浪費</p>
					<p class="feature__text">只使用可重複使用的容器來包裝您的所有餐點</p>
				</div>

				<!-- feature-4 -->
				<div class="feature">
					<ion-icon class="feature__icon" name="pause-outline"></ion-icon>
					<p class="feature__title">隨時暫停</p>
					<p class="feature__text">只需暫停計畫，我們就會退還未使用的天數</p>
				</div>
			</div>
		</section>

		<section class="section section-question" id="section-4">
			<div class="container">
				<span class="subheading">養貓評估</span>
				<h2 class="heading-secondary">養貓要知道的幾件事</h2>
			</div>
			<div class="container">
				<div class="accordion">
					<!-- item-1 -->
					<div class="accordion__item">
						<span class="accordion__number">01</span>

						<h3 class="accordion__text">養貓該準備什麼？</h3>

						<button class="btn__accordion">
							<ion-icon class="accordion__icon" name="chevron-up-outline"></ion-icon>

							<ion-icon class="accordion__icon" name="chevron-down-outline">
							</ion-icon>
						</button>

						<div class="accordion__hidden">
							<ul class="accordion__hidden-lists">
								<li class="accordion__hidden-list">食物：飼料、主食罐、鮮食</li>

								<li class="accordion__hidden-list">居家用品：貓砂盆、貓砂、貓抓板、跳台、舒適的窩
								</li>

								<li class="accordion__hidden-list">生活所需：毛梳、指甲剪、排毛粉或化毛膏</li>

								<li class="accordion__hidden-list">外出用品：牽繩、胸背帶、外出籠、溼紙巾</li>

								<li class="accordion__hidden-list">娛樂：逗貓棒、玩具等</li>
							</ul>
						</div>
					</div>

					<!-- item-2 -->
					<div class="accordion__item">
						<span class="accordion__number">02</span>

						<h3 class="accordion__text">養成貓還是幼貓？新手養貓推薦哪一種？</h3>

						<button class="btn__accordion">
							<ion-icon class="accordion__icon" name="chevron-up-outline"></ion-icon>

							<ion-icon class="accordion__icon" name="chevron-down-outline">
							</ion-icon>
						</button>

						<div class="accordion__hidden">
							<p class="accordion__hidden-text">
								幼貓嬌小、活潑的可愛姿態固然十分吸引人，然而幼貓的身體機能和免疫系統都非常脆弱，因此養小貓要花費的心力及時間也相對較多。 <br />
								與幼貓相比，成貓是相對好養的貓，並且只要耐心培養感情，成貓也可以非常親人，因此十分推薦沒經驗的新手飼養。
							</p>
						</div>
					</div>

					<!-- item-3 -->
					<div class="accordion__item">
						<span class="accordion__number">03</span>

						<h3 class="accordion__text">品種貓、米克斯，我適合養哪種貓？</h3>

						<button class="btn__accordion">
							<ion-icon class="accordion__icon" name="chevron-up-outline"></ion-icon>

							<ion-icon class="accordion__icon" name="chevron-down-outline">
							</ion-icon>
						</button>

						<div class="accordion__hidden">
							<p class="accordion__hidden-text">
								純種貓是透過選擇性雜交來培育出獨一無二的生理特徵，這樣選擇性培育的方式也讓每一個特定品種的貓都有著類似的個性，例如布偶貓個性安靜、溫和；挪威森林貓活潑、親人；美國短毛貓貪玩、好動等等，這些鮮明的個性有助於飼主更準確的選定符合自身喜好的貓咪。
								<br /> 與幼貓相比，成貓是相對好養的貓，並且只要耐心培養感情，成貓也可以非常親人，因此十分推薦沒經驗的新手飼養。
							</p>
						</div>
					</div>
				</div>
			</div>
		</section>

		<section class="section section-cta" id="section-5">
			<div class="container">
				<div class="cta">
					<div class="cta__img-box" role="img" aria-label="a very cool cat"></div>

					<div class="cta__text-box">
						<h2 class="heading-secondary">
							領養你的第一隻 Mr.Cat
							<!-- Start adopting your first Mr.Cat -->
						</h2>

						<p class="cta__text">Lorem ipsum dolor sit amet, consectetur
							adipisicing elit. Eum voluptas facere est quas? Est, alias
							officiis rerum illum pariatur iure. Sapiente repellat earum
							aliquam modi debitis sed accusantium minima dolores!</p>

						<form class="cta__form" name="sign-up">
							<div>
								<label class="cta__form-label" for="fullname">姓名</label> <input
									class="cta__form-input" type="text" placeholder="貓貓貓"
									name="fullname" id="fullname" required />
							</div>

							<div>
								<label class="cta__form-label" for="phone">電話</label> <input
									class="cta__form-input" type="tel" placeholder="0995275871"
									name="phone" id="phone" pattern="[0-9]{4}[0-9]{3}[0-9]{3}"
									required />
							</div>

							<div>
								<label class="cta__form-label" for="email">信箱</label> <input
									class="cta__form-input" type="email"
									placeholder="cat@exaple.com" name="email" id="email" required />
							</div>
							<div>
								<label class="cta__form-label" for="choice">想養貓的原因</label>
								<select class="cta__form-select" name="choice" id="choice"
									required>
									<option value="">請選擇一個選項</option>
									<option value="cute">貓很可愛</option>
									<option value="cute">貓很可愛</option>
									<option value="cute">貓很可愛</option>
									<option value="friend">生活中很想要有個陪伴</option>
									<option value="cost">貓的照顧成本較低</option>
								</select>
							</div>

							<button class="btn btn--form">預約</button>
						</form>
					</div>
				</div>
			</div>
		</section>
	</main>

	<footer class="footer">
		<div class="container grid--footer">
			<div class="logo-col">
				<a href="animation.html"> <img src="img/logo.png"
					alt="Logo" class="logo" />
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
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>

	<script nomodule
		src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

	<script defre
		src="https://unpkg.com/smoothscroll-polyfill@0.4.4/dist/smoothscroll.min.js"></script>
	<script defer src="js/script.js"></script>

</body>
</html>