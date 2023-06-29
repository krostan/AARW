<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="java.util.List, java.util.ArrayList"%>
<%@ page import="com.entities.Member, com.managers.MemberManager"%>
<%@ page import="com.entities.Pet, com.managers.PetManager"%>
<%@ page import="com.entities.Publish, com.managers.PublishManager"%>
<%@ page import="com.entities.Adoption, com.managers.AdoptionManager"%>
<%@ page import="com.entities.Permiss, com.managers.PermissManager"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/admin.css" />

<link rel="shortcut icon" href="../img/logo.png" />
<title>管理</title>
</head>
<body>
	<header class="slider-navigation">
		<div class="nav_btn active">會員管理</div>
		<div class="nav_btn">寵物管理</div>
		<div class="nav_btn">刊登管理</div>
		<div class="nav_btn">預約管理</div>
		<div class="nav_btn">權限管理</div>
	</header>

	<!-- 會員管理 -->
	<div class="content active">
		<nav class="sidebar">
			<ul class="side-nav">
				<li class="side-nav__item">
					<button class="side-nav__btn active">瀏覽會員</button>
				</li>
				<li class="side-nav__item">
					<button class="side-nav__btn">
						<span>新增會員</span>
					</button>
				</li>

				<li class="side-nav__item">
					<button class="side-nav__btn">
						<span>修改會員</span>
					</button>
				</li>

				<li class="side-nav__item">
					<button class="side-nav__btn">
						<span>刪除會員</span>
					</button>
				</li>

				<li class="side-nav__item"><a href="../member"
					class="side-nav__link"> <span>退出模式</span>
				</a></li>
			</ul>
		</nav>
		<%
		// 全部會員
		List<Member> members = MemberManager.getInstance().findAll();

		members.remove(0);
		request.setAttribute("members", members);

		// 全部寵物
		List<Pet> pets = PetManager.getInstance().findAll();
		request.setAttribute("pets", pets);

		// 全部刊登
		List<Publish> publishes = PublishManager.getInstance().findAll();
		request.setAttribute("publishes", publishes);

		// 全部預約
		List<Adoption> adoptions = AdoptionManager.getInstance().findAll();
		request.setAttribute("adoptions", adoptions);

		// 全部權限
		List<Permiss> permissions = PermissManager.getInstance().findAll();
		request.setAttribute("permissions", permissions);
		%>
		<main class="main-table">

			<!-- 瀏覽會員 -->
			<section class="section-table active">
				<table>
					<thead>
						<tr>
							<th>編號</th>
							<th>使用者名稱</th>
							<th>姓名</th>
							<th>電話</th>
							<th>電子郵件</th>
							<th>性別</th>
							<th>地址</th>
							<!--  <th>設定</th>
							<th>設定</th>-->
						</tr>
					</thead>
					<tbody>
						<c:forEach var="member" items="${members}">
							<tr>
								<th>${ member.userId }</th>
								<td>${ member.username }</td>
								<td>${ member.name }</td>
								<td>${ member.phone }</td>
								<td>${ member.email }</td>
								<td>${ member.gender }</td>
								<td>${ member.address }</td>
								<!--  
								<td class="td-btn">
									<button class="btn btn--full">重置密碼</button>
								</td>
								<td class="td-btn">
									<button class="btn btn--cat">禁用</button>
								</td>
								-->
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</section>

			<!-- 新增會員 -->
			<section class="section-table">
				<form action="../register" method="POST" class="section-form">
					<h3 class="form-heading">新增會員</h3>

					<div class="display-flex">
						<label for="username">帳號(8-12)</label> <input type="text"
							class="username" id="username" name="username"
							placeholder="Enter username" pattern="^[0-9a-z]{8,12}$" />

						<h1 id="checkUsername"></h1>
					</div>

					<div class="display-flex">
						<label for="password">密碼(8-12)</label> <input type="password"
							class="password" id="password" name="password"
							placeholder="Enter password" pattern="^[0-9a-z]{8,12}$" />
					</div>

					<div class="display-flex">
						<label for="name">姓名</label> <input type="text" class="name"
							placeholder="貓貓貓" id="name" name="name" />
					</div>

					<div class="display-flex">
						<label for="phone">電話</label> <input type="tel" class="phone"
							id="phone" name="phone" placeholder="0995275871"
							pattern="^09\d{2}-?\d{3}-?\d{3}$" />
					</div>

					<div class="display-align">
						<label for="birthday">出生年月日</label> <input type="date"
							class="birthday" id="birthday" name="birthday" min="1900-01-01"
							max="2019-01-01" />
					</div>

					<div class="display-flex">
						<label for="email">Email</label> <input class="email" type="email"
							placeholder="cat@exaple.com" id="email" name="email"
							pattern="^[A-Za-z0-9-_\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$" />
					</div>

					<div class="display-align">
						<label for="gender" class="label-other">性別</label> <select
							name="gender" id="gender" required>
							<option value="0">請選擇性別</option>
							<option value="1">男</option>
							<option value="2">女</option>
						</select>
					</div>

					<div class="display-flex">
						<label for="address">地址</label> <input class="address"
							id="address" name="address" placeholder="貓貓市貓貓區貓貓路11號" />
					</div>

					<div class="btn-box">
						<button type="submit" class="form-btn">送出</button>
					</div>
				</form>
			</section>

			<!-- 修改會員 -->
			<section class="section-table">
				<!-- 查詢會員 -->
				<form action="" method="POST" class="section-form margin-bottom-10">
					<h3 class="form-heading">修改會員</h3>

					<div class="display-flex">
						<label for="updateUserId">編號</label> <input type="text"
							class="userid" id="updateUserId" name="userid"
							placeholder="輸入使用者編號" pattern="^[0-9]{0,}$" />
					</div>

					<div class="btn-box">
						<button type="button" class="form-btn update-btn" id="updateUserId-btn">查詢</button>
					</div>
				</form>

				<!-- 準備更新的會員資料 -->
				<form action="../update" method="POST" class="section-form update-form">
					<h3 class="form-heading">更新會員</h3>
					<div class="display-flex">
						<label for="updateName">姓名</label> <input type="text" class="name"
							placeholder="貓貓貓" id="updateName" name="name" required />
					</div>

					<div class="display-flex">
						<label for="updatePhone">電話</label> <input type="tel"
							class="phone" id="updatePhone" name="phone"
							placeholder="0995275871" pattern="^09\d{2}-?\d{3}-?\d{3}$"  required/>
					</div>

					<div class="display-align">
						<label for="updateBirthday">出生年月日</label> <input type="date"
							class="birthday" id="updateBirthday" name="birthday"
							min="1900-01-01" max="2019-01-01" required />
					</div>

					<div class="display-flex">
						<label for="updateEmail">Email</label> <input class="email"
							type="email" placeholder="cat@exaple.com" id="updateEmail"
							name="email"
							pattern="^[\w\.-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)*\.[a-zA-Z]{2,}$" 
							required />
					</div>

					<div class="display-align">
						<label for="updateGender" class="label-other">性別</label> <select
							name="gender" id="updateGender" required>
							<option value="0">請選擇性別</option>
							<option value="1">男</option>
							<option value="2">女</option>
						</select>
					</div>

					<div class="display-flex">
						<label for="updateAddress">地址</label> <input class="address"
							id="updateAddress" name="address" placeholder="貓貓市貓貓區貓貓路11號" required />
					</div>

					<div class="btn-box">
						<button type="submit" class="form-btn">送出</button>
					</div>
				</form>

			</section>

			<!-- 刪除會員 -->
			<section class="section-table">
				<!-- 查詢會員 -->
				<form action="" method="POST" class="section-form">
					<h3 class="form-heading">刪除會員</h3>

					<div class="display-flex">
						<label for="deleteUserId">編號</label> <input type="text"
							class="userid" id="deleteUserId" name="userid"
							placeholder="輸入使用者編號" />
					</div>

					<div class="btn-box">
						<button type="submit" class="form-btn" id="deleteUserId-btn">送出</button>
					</div>
				</form>
			</section>
		</main>
	</div>

	<!-- 寵物管理 -->
	<div class="content">
		<nav class="sidebar">
			<ul class="side-nav">
				<li class="side-nav__item">
					<button class="side-nav__btn active">
						<span>瀏覽寵物</span>
					</button>
				</li>
				<li class="side-nav__item">
					<button class="side-nav__btn">
						<span>新增寵物</span>
					</button>
				</li>

				<li class="side-nav__item">
					<button class="side-nav__btn">
						<span>修改寵物</span>
					</button>
				</li>

				<li class="side-nav__item">
					<button class="side-nav__btn">
						<span>刪除寵物</span>
					</button>
				</li>
			</ul>
		</nav>

		<main class="main-table">

			<!-- 瀏覽寵物 -->
			<section class="section-table active">
				<table>
					<thead>
						<tr>
							<th>編號</th>
							<th>寵物名稱</th>
							<th>品種</th>
							<th>性別</th>
							<th>毛色</th>
							<th>年齡</th>
							<th>地址</th>
							<th>種類</th>
							<th>體型</th>
							<th>所屬會員</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="pet" items="${pets}">
							<tr>
								<th>${ pet.petId }</th>
								<td>${ pet.petName }</td>
								<td>${ pet.breed }</td>
								<td>${ pet.gender }</td>
								<td>${ pet.coatColor }</td>
								<td>${ pet.age }</td>
								<td>${ pet.location }</td>
								<td>${ pet.species }</td>
								<td>${ pet.size }</td>
								<td>${ pet.member.userId }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</section>

			<!-- 新增寵物 -->
			<section class="section-table">
				<form action="../add-pet" method="POST" class="section-form"
					enctype="multipart/form-data">
					<h3 class="form-heading">新增寵物</h3>

					<div class="display-flex">
						<label for="petName">寵物名稱</label> <input type="text"
							class="petName" id="petName" name="petName" placeholder="貓貓貓" />
					</div>

					<div class="display-flex">
						<label for="breed">品種</label> <input type="text" class="breed"
							id="breed" name="breed" placeholder="金吉拉" />
					</div>

					<div class="display-flex">
						<label for="petGender">性別</label> <input type="text"
							class="gender" id="petGender" name="gender" placeholder="性別" />
					</div>

					<div class="display-flex">
						<label for="coatColor">毛色</label> <input type="text"
							class="coatColor" id="coatColor" name="coatColor"
							placeholder="毛色" />
					</div>

					<div class="display-flex">
						<label for="age">年齡</label> <input type="text" class="age"
							id="age" name="age" placeholder="1" />
					</div>

					<div class="display-flex">
						<label for="location">所在地</label> <input type="text"
							class="location" id="location" name="location" placeholder="桃園" />
					</div>

					<div class="display-flex">
						<label for="species">種類</label> <input type="text" class="species"
							id="species" name="species" placeholder="種類" />
					</div>

					<div class="display-flex">
						<label for="species">體型</label> <input type="text" class="size"
							id="size" name="size" placeholder="體型" />
					</div>

					<div class="display-flex">
						<label for="userid">會員編號</label> <input type="text" class="userid"
							id="userid" name="userid" placeholder="會員編號" />
					</div>

					<div class="display-align grid-first">
						<label for="quest" class="label-other">條件</label>
						<textarea class="input-textarea" placeholder="" id="quest"
							name="quest"></textarea>
					</div>

					<div class="display-align">
						<span class="label-other">圖片</span> <label for="image"
							class="lab__img">
							<div id="formFile" class="formFile"></div> <ion-icon
								name="images-outline" class="lab__img-icon"></ion-icon>
						</label>
						<!-- multiple -->
						<input type="file" class="input-text input_img" id="image"
							name="image" accept=".jpg" onchange="previewMultiple(event)" />
					</div>

					<div class="btn-box">
						<button type="submit" class="form-btn">送出</button>
					</div>
				</form>
			</section>

			<!-- 更新寵物 -->
			<section class="section-table">
				<!-- 查詢寵物 -->
				<form action="" method="POST" class="section-form margin-bottom-10">
					<h3 class="form-heading">修改寵物</h3>

					<div class="display-flex">
						<label for="updatePetId">編號</label> <input type="text"
							class="petid" id="updatePetId" name="petid" placeholder="輸入寵物編號" pattern="^[0-9]{0,}$" />
					</div>

					<div class="btn-box">
						<button type="button" class="form-btn update-btn" id="updatePetId-btn">查詢</button>
					</div>
				</form>

				<!-- 準備更新的寵物資料 -->
				<form action="../update-pet" method="POST" class="section-form update-form"
					enctype="multipart/form-data">
					<h3 class="form-heading">更新寵物</h3>

					<div class="display-flex">
						<label for="updatePetName">寵物名稱</label> <input type="text"
							class="petName" id="updatePetName" name="petName"
							placeholder="貓貓貓" required/>
					</div>

					<div class="display-flex">
						<label for="updateBreed">品種</label> <input type="text"
							class="breed" id="updateBreed" name="breed" placeholder="金吉拉" required />
					</div>

					<div class="display-flex">
						<label for="updatePetGender">性別</label> <input type="text"
							class="gender" id="updatePetGender" name="gender"
							placeholder="性別" required />
					</div>

					<div class="display-flex">
						<label for="updateCoatColor">毛色</label> <input type="text"
							class="coatColor" id="updateCoatColor" name="coatColor"
							placeholder="毛色" required />
					</div>

					<div class="display-flex">
						<label for="updateAge">年齡</label> <input type="text" class="age"
							id="updateAge" name="age" placeholder="1" required />
					</div>

					<div class="display-flex">
						<label for="updateLocation">所在地</label> <input type="text"
							class="location" id="updateLocation" name="location"
							placeholder="桃園" required />
					</div>

					<div class="display-flex">
						<label for="updateSpecies">種類</label> <input type="text"
							class="species" id="updateSpecies" name="species"
							placeholder="種類" required/>
					</div>

					<div class="display-flex">
						<label for="updateSize">體型</label> <input type="text" class="size"
							id="updateSize" name="size" placeholder="體型" required />
					</div>

					<div class="display-align grid-first">
						<label for="updateQuest" class="label-other">條件</label>
						<textarea class="input-textarea" placeholder="" id="updateQuest"
							name="quest"></textarea>
					</div>

					<div class="display-align">
						<span class="label-other">圖片</span> <label for="updatePhotos"
							class="lab__img">
							<div id="formUpdateImg" class="formFile">
								<img src="" class="">
							</div> <ion-icon name="images-outline" class="lab__img-icon"></ion-icon>
						</label>
						<!-- multiple -->
						<input type="file" class="input-text input_img" id="updatePhotos"
							name="image" accept=".jpg" onchange="updataPetImage(event)" />
					</div>

					<div class="btn-box">
						<button type="submit" class="form-btn">送出</button>
					</div>
				</form>
			</section>

			<!-- 刪除寵物 -->
			<section class="section-table">
				<form action="" method="POST" class="section-form">
					<h3 class="form-heading">刪除寵物</h3>

					<div class="display-flex">
						<label for="deletePetId">編號</label> <input type="text"
							class="petid" id="deletePetId" name="petid" placeholder="輸入寵物編號" />
					</div>

					<div class="btn-box">
						<button type="submit" class="form-btn" id="deletePetId-btn">送出</button>
					</div>
				</form>
			</section>
		</main>
	</div>

	<!-- 刊登管理 -->
	<div class="content">
		<nav class="sidebar">
			<ul class="side-nav">
				<li class="side-nav__item">
					<button class="side-nav__btn active">
						<span>瀏覽刊登</span>
					</button>
				</li>
				<li class="side-nav__item">
					<button class="side-nav__btn">
						<span>新增刊登</span>
					</button>
				</li>

				<li class="side-nav__item">
					<button class="side-nav__btn">
						<span>修改刊登</span>
					</button>
				</li>

				<li class="side-nav__item">
					<button class="side-nav__btn">
						<span>刪除刊登</span>
					</button>
				</li>
			</ul>
		</nav>

		<main class="main-table">

			<!-- 瀏覽刊登 -->
			<section class="section-table active">
				<table>
					<thead>
						<tr>
							<th>編號</th>
							<th>刊登日期</th>
							<th>刊登者</th>
							<th>刊登的寵物</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="publish" items="${publishes}">
							<tr>
								<th>${ publish.publishId }</th>
								<td>${ publish.publishDate }</td>
								<td>${ publish.member.userId }</td>
								<td>${ publish.pet.petId }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</section>

			<!-- 新增刊登 -->
			<section class="section-table">
				<form action="../do-publish" method="POST" class="section-form">
					<h3 class="form-heading">新增刊登</h3>

					<div class="display-flex">
						<label for="publishUserId">刊登者編號</label> <input type="text"
							class="userid" id="publishUserId" name="userid"
							placeholder="使用者編號" />
					</div>

					<div class="display-flex">
						<label for="publishPetId">要刊登的寵物</label> <input type="text"
							class="petid" id="publishPetId" name="petid" placeholder="寵物編號" />
					</div>

					<div class="btn-box">
						<button type="submit" class="form-btn">送出</button>
					</div>
				</form>
			</section>

			<!-- 修改刊登 -->
			<section class="section-table">
				<!-- 查詢刊登 -->
				<form action="" method="POST" class="section-form margin-bottom-10">
					<h3 class="form-heading">修改刊登</h3>

					<div class="display-flex">
						<label for="updatePublishId">編號</label> <input type="text"
							class="publishid" id="updatePublishId" name="publishid"
							placeholder="輸入刊登編號" pattern="^[0-9]{0,}$" />
					</div>

					<div class="btn-box">
						<button type="button" class="form-btn update-btn" id="updatePublishId-btn">查詢</button>
					</div>
				</form>

				<!-- 準備更新的刊登資料 -->
				<form action="../do-publish" method="POST" class="section-form update-form">
					<h3 class="form-heading">更新刊登</h3>

					<div class="display-flex">
						<label for="updatePublishUserId">刊登者編號</label> <input type="text"
							class="userid" id="updatePublishUserId" name="userid"
							placeholder="使用者編號" required />
					</div>

					<div class="display-flex">
						<label for="updatePublishPetId">要刊登的寵物</label> <input type="text"
							class="petid" id="updatePublishPetId" name="petid"
							placeholder="寵物編號" required/>
					</div>

					<div class="btn-box">
						<button type="submit" class="form-btn">送出</button>
					</div>
				</form>
			</section>

			<!-- 刪除刊登 -->
			<section class="section-table">
				<form action="" method="POST" class="section-form">
					<h3 class="form-heading">刪除刊登</h3>

					<div class="display-flex">
						<label for="deletePublishId">編號</label> <input type="text"
							class="publishid" id="deletePublishId" name="publishid"
							placeholder="輸入刊登編號" />
					</div>

					<div class="btn-box">
						<button type="submit" class="form-btn" id="deletePublishId-btn">送出</button>
					</div>
				</form>
			</section>
		</main>
	</div>

	<!-- 預約管理 -->
	<div class="content">
		<nav class="sidebar">
			<ul class="side-nav">
				<li class="side-nav__item">
					<button class="side-nav__btn active">
						<span>瀏覽預約</span>
					</button>
				</li>
				<li class="side-nav__item">
					<button class="side-nav__btn">
						<span>新增預約</span>
					</button>
				</li>

				<li class="side-nav__item">
					<button class="side-nav__btn">
						<span>修改預約</span>
					</button>
				</li>

				<li class="side-nav__item">
					<button class="side-nav__btn">
						<span>刪除預約</span>
					</button>
				</li>
			</ul>
		</nav>

		<main class="main-table">

			<!-- 瀏覽預約 -->
			<section class="section-table active">
				<table>
					<thead>
						<tr>
							<th>編號</th>
							<th>預約日期</th>
							<th>預約者編號</th>
							<th>預約寵物編號</th>
							<th>預約寵物</th>
							<th>刊登者編號</th>
							<th>答覆日期</th>
							<th>預約結果</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="adoption" items="${adoptions}">
							<tr>
								<th>${ adoption.adoptionId }</th>
								<td>${ adoption.adoptionDate }</td>
								<td>${ adoption.member.userId }</td>
								<td>${ adoption.pet.petId }</td>
								<td>${ adoption.pet.petName }</td>
								<td>${ adoption.pet.member.userId }</td>
								<td>${ adoption.recordDate }</td>
								<td>${ adoption.adoptionResult }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</section>

			<!-- 新增預約 -->
			<section class="section-table">
				<form action="../do-adoption" method="POST" class="section-form">
					<h3 class="form-heading">新增預約</h3>

					<div class="display-flex">
						<label for="adoptionUserId">預約者編號</label> <input type="text"
							class="userid" id="adoptionUserId" name="userid"
							placeholder="預約者編號" />
					</div>

					<div class="display-flex">
						<label for="adoptionPetId">要預約的寵物</label> <input type="text"
							class="petid" id="adoptionPetId" name="petid" placeholder="寵物編號" />
					</div>

					<div class="btn-box">
						<button type="submit" class="form-btn">送出</button>
					</div>
				</form>
			</section>

			<!-- 修改預約 -->
			<section class="section-table">
				<!-- 查詢預約 -->
				<form action="" method="POST" class="section-form margin-bottom-10">
					<h3 class="form-heading">修改預約</h3>

					<div class="display-flex">
						<label for="updateAdoptionId">編號</label> <input type="text"
							class="adoptionid" id="updateAdoptionId" name="adoptionid"
							placeholder="輸入預約編號" />
					</div>

					<div class="btn-box">
						<button type="button" class="form-btn update-btn" id="updateAdoptionId-btn">查詢</button>
					</div>
				</form>

				<!-- 準備更新的預約資料 -->
				<form action="../update-adoption" method="POST" class="section-form update-form">
					<h3 class="form-heading">更新預約</h3>

					<div class="display-flex">
						<label for="updateAdoptionResult">預約結果</label> <input type="text"
							class="adoptionResult" id="updateAdoptionResult"
							name="adoptionResult" placeholder="同意 or 不同意" required />
					</div>

					<div class="btn-box">
						<button type="submit" class="form-btn">送出</button>
					</div>
				</form>
			</section>

			<!-- 刪除預約 -->
			<section class="section-table">
				<form action="" method="POST" class="section-form">
					<h3 class="form-heading">刪除預約</h3>

					<div class="display-flex">
						<label for="deleteAdoptionId">編號</label> <input type="text"
							class="adoptionid" id="deleteAdoptionId" name="adoptionid"
							placeholder="輸入預約編號" />
					</div>

					<div class="btn-box">
						<button type="submit" class="form-btn" id="deleteAdoptionId-btn">送出</button>
					</div>
				</form>
			</section>
		</main>
	</div>

	<!-- 權限管理 -->
	<div class="content">
		<nav class="sidebar">
			<ul class="side-nav">
				<li class="side-nav__item">
					<button class="side-nav__btn active">
						<span>瀏覽權限</span>
					</button>
				</li>
				<li class="side-nav__item">
					<button class="side-nav__btn">
						<span>新增權限</span>
					</button>
				</li>

				<li class="side-nav__item">
					<button class="side-nav__btn">
						<span>修改權限</span>
					</button>
				</li>

				<li class="side-nav__item">
					<button class="side-nav__btn">
						<span>刪除權限</span>
					</button>
				</li>
			</ul>
		</nav>

		<main class="main-table">

			<!-- 瀏覽權限 -->
			<section class="section-table active">
				<table>
					<thead>
						<tr>
							<th>會員編號</th>
							<th>會員權限</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="permission" items="${permissions}">
							<tr>
								<th>${ permission.userId }</th>
								<td>${ permission.role }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</section>

			<!-- 新增權限 -->
			<section class="section-table">
				<form action="../do-permiss" method="POST" class="section-form">
					<h3 class="form-heading">新增權限</h3>

					<div class="display-flex">
						<label for="roleUserId">使用者編號</label> <input type="text"
							class="userid" id="roleUserId" name="userid" placeholder="使用者編號" />
					</div>

					<div class="display-flex">
						<label for="role">權限</label> <input type="text" class="role"
							id="role" name="role" placeholder="系統管理者, 管理者, 使用者" />
					</div>

					<div class="btn-box">
						<button type="submit" class="form-btn">送出</button>
					</div>
				</form>
			</section>

			<!-- 更新權限 -->
			<section class="section-table">
				<!-- 查詢權限 -->
				<form action="" method="POST" class="section-form margin-bottom-10">
					<h3 class="form-heading">修改權限</h3>

					<div class="display-flex">
						<label for="updatePermissUserId">會員編號</label> <input type="text"
							class="permissionsUserid" id="updatePermissUserId"
							name="permissionsUserid" placeholder="輸入會員編號" />
					</div>

					<div class="btn-box">
						<button type="button" class="form-btn update-btn"
							id="updatePermissUserId-btn">查詢</button>
					</div>
				</form>

				<!-- 準備更新的權限資料 -->
				<form action="../do-update-permiss" method="POST"
					class="section-form update-form">
					<h3 class="form-heading">更新權限</h3>

					<div class="display-flex">
						<label for="updateRole">權限</label> <input type="text" class="role"
							id="updateRole" name="role" placeholder="系統管理者, 管理者, 使用者" required/>
					</div>

					<div class="btn-box">
						<button type="submit" class="form-btn">送出</button>
					</div>
				</form>
			</section>

			<!-- 刪除權限 -->
			<section class="section-table">
				<form action="" method="POST" class="section-form">
					<h3 class="form-heading">刪除權限</h3>

					<div class="display-flex">
						<label for="deletePermissUserId">編號</label> <input type="text"
							class="permissionsid" id="deletePermissUserId"
							name="permissionsid" placeholder="輸入權限編號" />
					</div>

					<div class="btn-box">
						<button type="submit" class="form-btn" id="deletePermissId-btn">送出</button>
					</div>
				</form>
			</section>
		</main>
	</div>

	<!-- icon -->
	<script type="module"
		src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.esm.js"></script>
	<script nomodule
		src="https://unpkg.com/ionicons@7.1.0/dist/ionicons/ionicons.js"></script>


	<script defer src="../js/admin.js"></script>

	<!-- 顯示圖片js -->
	<script defer src="../js/displayFilesImages.js"></script>

</body>
</html>