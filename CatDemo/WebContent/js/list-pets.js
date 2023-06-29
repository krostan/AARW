"use strict";

/**********************************/
/*          pagination.js         */
/**********************************/

/* 根據資料總數 產生分頁按鈕 監聽每個按鈕 顯示不同的資料 */
const pageBtn = document.querySelectorAll(".pagination_btn");
let currentPage;
let XHR;

function check() {
	pageBtn.forEach((btn, i) => {
		btn.addEventListener("click", () => {
			currentPage = i + 1;

			document.querySelector(".pagination .pagination_btn--current").classList.remove("pagination_btn--current");
			btn.classList.add("pagination_btn--current");

			window.scrollTo({ top: 0, behavior: "smooth" });

			loadPage(currentPage);
		});
	});
}

function loadPage(pageNum) {

	let url;
	url = "do-page?page=" + pageNum;

	if (window.ActiveXObject) {
		XHR = new ActiveXObject("Microsoft.XMLHTTP");
	}
	else if (window.XMLHttpRequest) {
		XHR = new XMLHttpRequest();
	}

	try {
		XHR.onreadystatechange = get_info;
		XHR.open("GET", url, true);
		XHR.send();
	}
	catch (e) {
		window.alert("無法建立連線!!!");
		return false;
	}
}

function get_info() {
	if (XHR.readyState == 4) {
		if (XHR.status == 200) {
			let val = XHR.responseText;
			document.getElementById("card").innerHTML = val;
		}
	}
}

if (pageBtn.length != 0) {
	loadPage(1);
}

check();

/**********************************/
/*              end               */
/**********************************/


const btnNavEl = document.querySelector(".btn__mobile-nav");
const headerEl = document.querySelector(".header");

const btnIconEls = document.querySelectorAll(".btn__accordion");
const accItemEls = document.querySelectorAll(".accordion__item");

const allLinks = document.querySelectorAll("a:link");
const sectionCatEl = document.querySelector(".section-cat");
const nav = document.querySelector(".nav");

const headerHeight = headerEl.getBoundingClientRect().height;

// sort
const sortBox = document.querySelector(".sort_box");
const menuBox = document.querySelector(".menu_box");
const menuLi = document.querySelectorAll(".menu-li");
const sortMenu = document.querySelector(".sort-menu");


/**********************************/
/*         MOBILE NAVIGATION      */
/**********************************/
btnNavEl.addEventListener("click", function() {
	headerEl.classList.toggle("nav-open");
});

/**********************************/
/*        section-question        */
/**********************************/
btnIconEls.forEach(function(btnIconEl, index) {
	btnIconEl.addEventListener("click", function(e) {
		accItemEls[index].classList.toggle("item-open");
	});
});

/**********************************/
/*         scroll-behavior        */
/**********************************/
allLinks.forEach(function(link) {
	link.addEventListener("click", function(e) {
		e.preventDefault();
		const href = this.getAttribute("href");
		console.log("123");

		if (href === "#") {
			window.scrollTo({
				top: 0,
				behavior: "smooth",
			});
		}

		if (href !== "#" && href.startsWith("#")) {
			const sectionEl = document.querySelector(href);
			sectionEl.scrollIntoView({
				behavior: "smooth",
			});
		} else {
			window.location.href = href;
		}

		if (link.classList.contains("nav__link")) {
			headerEl.classList.toggle("nav-open");
		}
	});
});

/**********************************/
/*          Menu animation        */
/**********************************/
const handleHover = function(e) {
	if (e.target.classList.contains("nav__link")) {
		const link = e.target;
		const siblings = link.closest(".nav").querySelectorAll(".nav__link");
		const logo = link.closest(".header").querySelector(".logo");

		siblings.forEach((el) => {
			if (el !== link) el.style.opacity = this;
		});
		logo.style.opacity = this; //logo的圖變透明
	}
};

nav.addEventListener("mouseover", handleHover.bind(0.5));
nav.addEventListener("mouseout", handleHover.bind(1));

/**********************************/
/*        sort排序事前準備js          */
/**********************************/
menuBox.addEventListener("click", function() {
	/* icon 顯示 */
	sortBox.classList.toggle("item-open");

	/* 隱藏 li */
	menuLi.forEach((li) => {
		li.classList.toggle("active");
	});

	/* 隱藏ul */
	sortMenu.classList.toggle("active");
});

/* 選取後傳送到後端 */
const sortItem = document.querySelectorAll(".sort-item");
const menuHeader = document.querySelector(".menu-header");

sortItem.forEach((item) => {
	item.addEventListener("click", function() {
		// 取得 button上的字
		let header = item.textContent;
		menuHeader.textContent = header;
	});
});

/**********************************/
/*         觀察排序選項變動            */
/**********************************/

// 排序選項
let sortData;
// 監視 內容變化 或是 class變化
const observer = new MutationObserver(function(mutationsList) {
	for (let mutation of mutationsList) {
		// 子節點 或 文字內容 發生變化 觸發
		if (mutation.type === "childList" || mutation.type === "characterData") {
			sortData = menuHeader.textContent;
			getSort();
		}
	}
});

// 設定觀察選項
const config = {
	childList: true,
	subtree: true,
	characterData: true,
};

// 開始觀察 <div> 內容變化
observer.observe(menuHeader, config);

// ajax function
function getSort() {

	if (window.ActiveXObject) {
		XHR = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) {
		XHR = new XMLHttpRequest();
	}

	let url = "new-sort-pet";

	XHR.open("POST", url, true);
	XHR.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	XHR.onreadystatechange = getData;

	let data = "sortData=" + sortData;

	XHR.send(data);
}

// 得到回傳值
function getData() {
	if (XHR.readyState == 4 && XHR.status == 200) {

		/* 取得值後 再次呼叫 取得新的html 非常重要!!!*/
		loadPage(1);
	}
}

/**********************************/
/*             彈出視窗              */
/**********************************/
// 彈出視窗

// 父容器
const windParent = document.getElementById("card");
// 保存當前滾動位置
let scrollPosition;

// 暫存 index
let index = 0;

/* 跳出視窗 */
const openWin = function(i) {
	scrollPosition =
		window.scrollY ||
		document.documentElement.scrollTop ||
		document.body.scrollTop;


	card.children[i].classList.add("itme-open");
	/* 設定body */
	document.body.style.position = "fixed";
	/* 設定到目前高度 */
	document.body.style.top = -scrollPosition + "px";
};

/* 將彈出視窗縮回 */
const closeWin = function(i) {
	card.children[i].classList.remove("itme-open");
	/*重置 */
	document.body.style.position = "";
	document.body.style.top = "";
	/* 滑動回原本位置 */
	window.scrollTo({ top: scrollPosition, behavior: "instant" });
};
/* 事件委託  */
windParent.addEventListener("click", function(e) {
	// card 上的btn 開
	const btnChecks = document.querySelectorAll(".btn-check");
	// 關
	const iconBtns = document.querySelectorAll(".icon_detail");

	btnChecks.forEach((btn, i) => {
		if (e.target === btn) {
			index = i;
			openWin(i);
		}
	})

	iconBtns.forEach((btn, i) => {
		if (e.target === btn) {
			closeWin(i);
		}
	})


})

/* 點擊周圍縮回 */
document.addEventListener("click", function(e) {
	if (e.target.classList == 'item_detail') {
		closeWin(index);
	}
});

/* 按下esc 縮回 */
document.addEventListener("keydown", function(e) {
	if (e.key === "Escape") {
		closeWin(index);
	}
});

/*
以下兩個const 是靜態頁面可以使用的
顯示詳細上的 btn
const btnDetail = document.querySelector(".btn_detail");
顯示詳細的容器
const itemDetail = document.querySelector(".item_detail");

btnCheck.addEventListener("click", openWin);
btnDetail.addEventListener("click", closeWin);
*/


/**********************************/
/*            search              */
/**********************************/
let searchBtn = document.querySelector("#search-btn");
let searchContainer = document.querySelector(".search-bar-container");
// input 物件
const searchB = document.querySelector("#search-bar");

searchBtn.addEventListener("click", function() {
	searchBtn.classList.toggle("fa-times");
	searchContainer.classList.toggle("active");

	if (searchContainer.classList.contains("active")) {
		searchB.focus();
	}
});

/**********************************/
/*            關鍵字搜尋             */
/**********************************/
/* 以下不需要了 現在直接提交表單資料 跳轉後 顯示新的資料

const searchBar = document.getElementById("search-bar");
const searchButton = document.getElementById("search-button");
let kwSearch;

searchButton.addEventListener("click",function(e){
	handleSearch();
});

searchBar.addEventListener("keydown",function(e){
	if(e.key ==='Enter'){
		
		handleSearch();
	}
})

function handleSearch(){
	kwSearch = searchBar.value;
	getKWSearch();
}

function getKWSearch() {

  if (window.ActiveXObject) {
	XHR = new ActiveXObject("Microsoft.XMLHTTP");
  } else if (window.XMLHttpRequest) {
	XHR = new XMLHttpRequest();
  }

  let url = "keyword-search";

  XHR.open("POST", url, true);
  XHR.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  XHR.onreadystatechange = get;

  let data = "kwSearch=" + kwSearch;

  XHR.send(data);
}

// 得到回傳值
function get() {
  if (XHR.readyState == 4 && XHR.status == 200) {
	console.log("get");
	window.scrollTo({ top: 600, behavior: "smooth" });
  }
}*/