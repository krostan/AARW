const btnIconEl = document.querySelector(".btn__box");
const shopEl = document.querySelector(".shop");

btnIconEl.addEventListener("click", function() {
	shopEl.classList.toggle("shop-open");
});

document.addEventListener("keydown", function(e) {
	if (e.key === "ArrowRight" || e.key === "Escape" && shopEl.classList.contains("shop-open")) {
		shopEl.classList.remove("shop-open");
	}
	if (e.key === "ArrowLeft" && !shopEl.classList.contains("shop-open")) {
		shopEl.classList.add("shop-open");
	}
});

// //點擊 下面以外的地方 會將 shop 縮回
// window.addEventListener("click", function (e) {
//   if (
//     !shopEl.contains(e.target) &&
//     e.target !== btnIconEl &&
//     !cardBtnEl.contains(e.target)
//   ) {
//     shopEl.classList.remove("shop-open");
//   }
// });

/**********************************/
/*               shop             */
/**********************************/
// 監聽的html
const cardEl = document.getElementById("card");
// 主容器
const itemBoxEl = document.querySelector(".shop__item-box");
// 主容器裡的 第一個children
const head = document.querySelector(".shop__item-heading");
const shopBtn = document.querySelector(".shop__btn-icon");

// 預約按鈕
let catdBtnEL;
let cardBtns = [];
//物件陣列
let pets = [];

function handleClickEvent(e) {
	// 點擊的物件圖片
	let cardImgEl;
	// 點擊的物件名稱 class = petName, breed, age
	let petName;
	let breed;
	let age;
	// 點擊物件的編號
	let petId;

	if (e.target.id === "card-btn") {
		e.preventDefault();

		// 找到該物件
		let ee = e.target.parentNode.parentNode.children;
		//petId
		petId = ee[0].value;
		//cardBtnEl = ee[4].children[0];
		// img src
		cardImgEl = ee[1].src;
		// pet名稱
		petName = ee[2].children[0].textContent;
		// pet 品種
		breed = ee[3].children[0].children[0].children[1].textContent;
		// pet 年齡
		age = ee[3].children[3].children[0].children[1].textContent;

		// 將值傳到物件 再儲存到陣列
		let obj1 = {
			petId: petId,
			petName: petName,
			breed: breed,
			age: age
		};
		
		// 比對陣列裡面物件的 petId ,如果不存在,則回傳-1,如果存在,則回傳該物件的位置
		let result = pets.map((pet) => pet.petId).indexOf(petId);

		// 當陣列裡面已有值 則不加入
		if (result === -1) {
			pets.push(obj1);
		} else {
			return;
		}

		// 將點擊按鈕 傳給變數
		// 隱藏按鈕
		e.target.style.display = "none";
		catdBtnEL = e.target;

		let obj2 = {
			petId: petId,
			target: catdBtnEL
		};
		cardBtns.push(obj2);

		create_date(petId, cardImgEl, petName, breed, age);

	}
}

cardEl.addEventListener("click", handleClickEvent);

/**********************************/
/*             建立物件html          */
/**********************************/

function create_date(petId, cardImgEl, petName, breed, age) {
	
	// 創建父容器
	let shopItemEl = document.createElement("div");
	shopItemEl.setAttribute("class", "shop__item");

	// 創建子容器
	// 1.img
	let img = document.createElement("img");
	let src = cardImgEl;
	img.setAttribute("class", "item__img");
	img.setAttribute("src", src);

	// 2.ul
	let textBoxEl = document.createElement("ul");
	let liEl0 = document.createElement("li");
	let liEl1 = document.createElement("li");
	let liEl2 = document.createElement("li");
	let liEl3 = document.createElement("li");

	textBoxEl.setAttribute("class", "text__box");
	liEl0.append(petId);
	liEl1.append(petName);
	liEl2.append(breed);
	liEl3.append(age);

	liEl0.setAttribute("style", "display:none");

	textBoxEl.appendChild(liEl0);
	textBoxEl.appendChild(liEl1);
	textBoxEl.appendChild(liEl2);
	textBoxEl.appendChild(liEl3);

	// 3. btn
	let itemBtnEl = document.createElement("button");
	let iconEl = document.createElement("ion-icon");

	itemBtnEl.setAttribute("class", "item__icon-box");
	iconEl.setAttribute("class", "item__icon");
	iconEl.setAttribute("name", "close-outline");

	itemBtnEl.appendChild(iconEl);

	// 將子容器 加入到父容器
	shopItemEl.appendChild(img);
	shopItemEl.appendChild(textBoxEl);
	shopItemEl.appendChild(itemBtnEl);
	
	// 將新增的父容器 插入到 主容器的第二個子元素 之前
	itemBoxEl.insertBefore(shopItemEl,itemBoxEl.children[1]);
}


/**********************************/
/*       delete shop-item         */
/**********************************/
itemBoxEl.addEventListener("click", function(e) {
	if (e.target.classList.contains("item__icon")) {

		// 找到 該按鈕所在物件的petId 
		let ee = e.target.parentNode.parentNode.children;
		let id = ee[1].children[0].textContent;

		// 透過id 得到 該物件在陣列中的索引
		let result = pets.map((pet) => pet.petId).indexOf(id);

		//透過索引 刪除該物件
		// splice(開始index ,刪除數量); 
		pets.splice(result, 1);

		// 將預約按鈕重新顯示
		let aa = cardBtns.map((btn) => btn.petId).indexOf(id);
		cardBtns[aa].target.style.display = "block";

		// icon -> btn(item__icon-box) ->div(shop__item)
		e.target.parentNode.parentNode.remove();
	}
});

/**********************************/
/*         刊登浪浪表 publish        */
/**********************************/
function publish() {

	let click = "publish()";
	
	let petId = "";
	pets.forEach(function(pet) {
		//console.log(pet.petId);
		petId += pet.petId + ",";
	});

	petId = petId.substring(0, petId.length - 1);

	let url;
	url = "do-publish?pet=" + petId;
	
	sendData(url);
	init_data(click);

}

/**********************************/
/*         領養申請表 adoption       */
/**********************************/
function adoption() {

	let click = "adoption()";

	let petId;
	
	if(pets.length > 1){
		alert("每次預約只能預約一隻浪浪");
		return;
	}
	

	petId = pets[0].petId;
	console.log(petId);

	let url;
	url = "do-adoption?pet=" + petId;

	
	sendData(url);
	init_data(click);

}

/**********************************/
/*                Ajax            */
/**********************************/
let xhr;
function sendData(url) {

	if (window.ActiveXObject) {
		xhr = new ActiveXObject("Microsoft.XMLHTTP");
	}
	else if (window.XMLHttpRequest) {
		xhr = new XMLHttpRequest();
	}

	try {
		xhr.onreadystatechange = get_print;
		xhr.open("GET", url, true);
		xhr.send();
	}
	catch (e) {
		window.alert("無法建立連線!!!");
		return false;
	}
}

function get_print() {

	if (xhr.readyState == 4 && xhr.status == 200) {
		console.log("推送成功");
	}
}

/**********************************/
/*               初始化             */
/**********************************/
function init_data(click) {
	// 初始化主容器
	itemBoxEl.innerHTML="";
	// 初始化陣列
	pets.length = 0;
	
	let h1 = "心動浪浪";
	let icon = "送出";
	let h1El = document.createElement("h1");
	let divEl = document.createElement("div");
	let btnEl = document.createElement("button");
	let iconEl = document.createElement("ion-icon");
	
	// 標題
	h1El.setAttribute("class", "shop__item-heading");
	h1El.append(h1);

	// 按鈕容器
	divEl.setAttribute("class", "shop__btn-box");
	//按鈕
	btnEl.setAttribute("class", "shop__btn-icon shop__btn-full");
	btnEl.setAttribute("onclick", click);
	//按鈕圖案
	iconEl.setAttribute("name", "navigate-outline");

	btnEl.append(iconEl);
	btnEl.append(icon);
	divEl.append(btnEl);

	// itemBoxEl
	itemBoxEl.append(h1El);
	itemBoxEl.append(divEl);

}

