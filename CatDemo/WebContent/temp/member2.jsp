<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import=" java.util.List ,java.util.ArrayList ,com.entities.Pet"%>

<!DOCTYPE html>
<html lang="zh-tw">
<head>
<meta charset="UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@100;300;400;500;700;900&display=swap"
	rel="stylesheet" />

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0"
	crossorigin="anonymous" />

<link rel="stylesheet" href="css/head-nav.css" />
<link rel="stylesheet" href="css/footer.css" />
<link rel="stylesheet" href="css/user/side.css" />
<link rel="stylesheet" href="css/queries.css" />
<link rel="stylesheet" href="css/user/member.css" />


<title>Member</title>
</head>
<body style="font-family: Arial; font-size: 20px;">

	<header class="header">
		<a href="#"> <img src="img/logo.png" alt="Logo" class="logo" />
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

		<!-- MOBILE NAVIGATION -->
		<button class="btn__mobile-nav">
			<ion-icon class="icon__mobile-nav" name="menu-outline"></ion-icon>
			<ion-icon class="icon__mobile-nav" name="close-outline"></ion-icon>
		</button>
	</header>

	<div class="content">
		<nav class="sidebar">
			<ul class="side-nav">
				<li class="side-nav__item side-nav__item--active"><a href="#"
					class="side-nav__link nav__none"> <ion-icon name=""
							class="side-nav__icon"> </ion-icon> 
							<%
 							String username = (String) session.getAttribute("user");

 							out.print("<span>" + username + "</span>");
 							%>
				</a></li>
				<li class="side-nav__item"><a href="index.jsp"
					class="side-nav__link"> <ion-icon name="home-outline"
							class="side-nav__icon"> </ion-icon> <span>home</span>
				</a></li>

				<li class="side-nav__item"><a href="#" class="side-nav__link">
						<ion-icon name="person-outline" class="side-nav__icon"></ion-icon>

						<span>upDate Member</span>
				</a></li>

				<li class="side-nav__item"><a href="pets/pet-form.jsp"
					class="side-nav__link"> <ion-icon name="add-circle-outline"
							class="side-nav__icon"></ion-icon> <span>AddPet</span>
				</a></li>

				<li class="side-nav__item"><a href="pets"
					class="side-nav__link"> <ion-icon name="paw-outline"
							class="side-nav__icon"></ion-icon> <span>Pets</span>
				</a></li>

				<li class="side-nav__item"><a
					href="http://localhost:8080/CatDemo/logout" class="side-nav__link">
						<ion-icon name="enter-outline" class="side-nav__icon"></ion-icon>
						<span>sign out</span>
				</a></li>
			</ul>
		</nav>

		<main class="main">
			<section class="container section section-cat">
				<div class="row row-cols-1 row-cols-md-3 g-5 page">
				
				
				</div>
			</section>

			<section class="container section">
				<div class="d-flex justify-content-center">
					<div class="pagination">
					</div>
				</div>
			</section>
		</main>
	</div>

	<footer class="footer">
		<div class="container grid--footer">
			<div class="logo-col">
				<a href="#"> <img src="img/logo.png" alt="Logo" class="logo" />
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

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8"
		crossorigin="anonymous"></script>

	<script type="module"
		src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule
		src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>

	<script defer src="js/script.js"></script>
	
	<script>

let jsonData = '<%=request.getAttribute("jsonData")%>';
  	
let json =JSON.parse(jsonData);
  	
////////////////////////////////
// 製作btn
const page = document.querySelector(".pagination");
  	
function showBtn() {

  let pageNum = Math.ceil(json.length / 10);
  let str = "";
  let tmp ="";
  for (let i = 1; i <= pageNum; i++) {
	  tmp="<a href='#' class='pagination_btn page-link'>"+i+"</a>";
	  str +=tmp;
  }
  page.innerHTML = str;
  
}

showBtn();

//////////////////////////////
// 監聽點擊事件
function test() {
  const btns = document.querySelectorAll(".pagination_btn");

   //for (let i = 0; i < btns.length; i++) {
     //btns[i].addEventListener("click", changePage.bind(this, i+1, json));
   //}

  btns.forEach((btn, i) => {
    btn.addEventListener("click", changePage.bind(this, i + 1, json));
  });
}
changePage(1,json);
test();

function changePage(page, data) {
  let items = 10;
  let pageIndexStart = (page - 1) * items;
  let pageIndexEnd = page * items;
  let str="";
  let temp="";
  let dname;
  for (let i = pageIndexStart; i < pageIndexEnd; i++) {
	  
    if (i >= data.length) {
      break;
    }
    
    //if(!data[i].petName){
    	//data[i].petName="";
    //}
    
    dname= !data[i].petName?"":data[i].petName;
    
    temp="<div class='box' style='width:500px;height:20vh;background-color:yellow;margin-left:20px'>"+dname+"</div>"
    //temp =" <div class='col'><div class='card h-100'><ul class='list-group list-group-flush'><li class='list-group-item ps-5'><div class='d-flex flex-row justify-content-start fs-3'><div class='p-2 bd-highlight'>寵物品種:</div><div class='p-2 bd-highlight'>" + !data[i].breed?'':data[i].breed + "</div></div></li></ul></div></div>";
    
    str += temp;
  }

  document.querySelector(".page").innerHTML = str;
}
  	
</script>


</body>
</html>