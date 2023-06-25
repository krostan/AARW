/*****************/
/* 切換content頁面 */
/****************/
const btns = document.querySelectorAll(".nav_btn");
const contents = document.querySelectorAll(".content");

btns.forEach((btn, i) => {
	btn.addEventListener("click", () => {
		btns.forEach((btn) => {
			btn.classList.remove("active");
		});

		/* 切換內容 */
		contents.forEach((content) => {
			content.classList.remove("active");
		});

		btns[i].classList.add("active");
		contents[i].classList.add("active");

		contBox(i);
	});
});

function contBox(i) {
	/* 切換table頁面 */
	const sideBtns = contents[i].querySelectorAll(".side-nav__btn");
	const tableEls = contents[i].querySelectorAll(".section-table");

	sideBtns.forEach((btn, i) => {
		btn.addEventListener("click", () => {
			/* 切換按鈕 active狀態 */
			sideBtns.forEach((btn) => {
				btn.classList.remove("active");
			});

			/* 切換table */
			tableEls.forEach((table) => {
				table.classList.remove("active");
			});

			sideBtns[i].classList.add("active");
			tableEls[i].classList.add("active");
		});
	});
}

contBox(0);

/********************************/
/*            通用ajax           */
/********************************/
/* 通用ajax 傳入 url 跟要使用的function */

function loadData(url, functionName) {

	if (window.ActiveXObject) {
		XHR = new ActiveXObject("Microsoft.XMLHTTP");
	}
	else if (window.XMLHttpRequest) {
		XHR = new XMLHttpRequest();
	}

	try {
		XHR.onreadystatechange = functionName;
		XHR.open("GET", url, true);
		XHR.send();
	}
	catch (e) {
		window.alert("無法建立連線!!!");
		return false;
	}
}

/* 刪除資料後的動作 */
function get_deleteDate() {
	if (XHR.readyState == 4) {
		if (XHR.status == 200) {
			let str = XHR.responseText;

			if (str !== 'false') {
				alert("刪除成功");
				// 重新加載頁面
				location.reload();
			} else {
				alert("刪除失敗");
			}

		}
	}
}

/********************************/
/*             會員介面            */
/********************************/

/* 更新會員的 查詢編號按鈕 */

/* 會員編號 */
const updateUserNo = document.getElementById("updateUserId");
/* 點擊的按鈕 */
const updateUserIdBtn = document.querySelector("#updateUserId-btn");

updateUserIdBtn.addEventListener("click", function(e) {
	e.preventDefault();

	console.log(updateUserNo.value);

	let url = "../admin-update-user?updateUserNo=" + updateUserNo.value;

	loadData(url, get_updateDate);
})

/* 刪除會員的 查詢編號按鈕 */
const deleteUserIdBtn = document.querySelector("#deleteUserId-btn");
const deleteUserNo = document.querySelector("#deleteUserId");

deleteUserIdBtn.addEventListener("click", function() {

	console.log(deleteUserNo.value);

	let url = "../delete-member?deleteUserNo=" + deleteUserNo.value;
	loadData(url, get_deleteDate);
})

/* 查詢會員後的動作 將值填入到form 以便管理者更新 */
function get_updateDate() {
	if (XHR.readyState == 4) {
		if (XHR.status == 200) {
			let response = JSON.parse(XHR.responseText);

			/* 解構 */
			for (let [key, value] of Object.entries(response)) {
				// 找到input  ID名稱為 update + key開頭大寫 外加其他小寫
				const inputEl = document.getElementById("update" + key.charAt(0).toUpperCase() + key.slice(1));

				// 當有找到所屬input　就會進入if
				if (inputEl) {
					// 前兩個 if　判斷select的值
					if (value === "男") {
						inputEl.value = 1;
					} else if (value === "女") {
						inputEl.value = 2;
					} else {
						inputEl.value = value;
					}
				}
			}
		}
	}
}

/********************************/
/*             寵物介面            */
/********************************/
/* 顯示 updata 寵物介面的圖 */
function updataPetImage(event) {
	const form = document.querySelector("#formUpdateImg");
	const images = document.getElementById("updateImage");
	const number = images.files.length;

	form.innerHTML = "";

	for (i = 0; i < number; i++) {
		const file = event.target.files[i];
		const urls = URL.createObjectURL(file);
		form.innerHTML += '<img src="' + urls + '" >';
	}
}

/* 讀取輸入的寵物編號的資料 */
const updatePetNo = document.querySelector("#updatePetId");
const updatePetIdBtn = document.querySelector("#updatePetId-btn");

updatePetIdBtn.addEventListener("click", function(e) {
	e.preventDefault();

	console.log(updatePetNo.value);

	let url = "../search-pet?updatePetNo=" + updatePetNo.value;

	loadData(url, get_petDate);
})

function get_petDate() {
	if (XHR.readyState == 4) {
		if (XHR.status == 200) {
			let response = JSON.parse(XHR.responseText);
			const photosImg = document.querySelector("#formUpdateImg img");
			/* 解構 */
			for (let [key, value] of Object.entries(response)) {
				console.log(key);
				// 找到input  ID名稱為 update + key開頭大寫 外加其他小寫
				const inputEl = document.getElementById("update" + key.charAt(0).toUpperCase() + key.slice(1));
				console.log(inputEl);
				// 當有找到所屬input　就會進入if
				if (inputEl) {
					if (key.includes("photos")) {
						photosImg.src = "../" + value;
					} else {
						inputEl.value = value;
					}
				}
			}
		}
	}
}

/* 刪除寵物的 查詢編號按鈕 */
const deletePetIdBtn = document.querySelector("#deletePetId-btn");
const deletePetNo = document.querySelector("#deletePetId");

deletePetIdBtn.addEventListener("click", function() {

	console.log(deletePetNo.value);

	let url = "../delete?deletePetNo=" + deletePetNo.value;
	loadData(url, get_deleteDate);
})

/********************************/
/*             刊登介面            */
/********************************/

/* 讀取輸入的刊登編號的資料 */
const updatePublishNo = document.querySelector("#updatePublishId");
const updatePublishIdBtn = document.querySelector("#updatePublishId-btn");

updatePublishIdBtn.addEventListener("click", function(e) {
	e.preventDefault();

	console.log(updatePublishNo.value);

	let url = "../admin-search-publish?updatePublishNo=" + updatePublishNo.value;

	loadData(url, get_publishDate);
})

function get_publishDate() {
	if (XHR.readyState == 4) {
		if (XHR.status == 200) {
			let response = JSON.parse(XHR.responseText);

			for (let [key, value] of Object.entries(response)) {

				const inputEl = document.getElementById("updatePublish" + key.charAt(0).toUpperCase() + key.slice(1));

				if (inputEl) {
					inputEl.value = value;
				}
			}
		}
	}
}

/* 刪除刊登的 查詢編號按鈕 */
const deletePublishNo = document.querySelector("#deletePublishId");
const deletePublishIdBtn = document.querySelector("#deletePublishId-btn");

deletePublishIdBtn.addEventListener("click", function() {

	let url = "../delete-publish?deletePublishNo=" + deletePublishNo.value;

	loadData(url, get_deleteDate);
})

/********************************/
/*             預約介面            */
/********************************/
/* 讀取輸入的預約編號的資料 */
const updateAdoptionNo = document.querySelector("#updateAdoptionId");
const updateAdoptionIdBtn = document.querySelector("#updateAdoptionId-btn");
const updateAdoptionInputEl = document.querySelector("#updateAdoptionResult");
updateAdoptionIdBtn.addEventListener("click", function(e) {
	e.preventDefault();

	let url = "../admin-search-adoption?updateAdoptionNo=" + updateAdoptionNo.value;

	loadData(url, get_adoptionDate);
})

function get_adoptionDate() {
	if (XHR.readyState == 4) {
		if (XHR.status == 200) {
			let str = XHR.responseText;

			if (str === 'false') {
				alert("查無此預約 或 此預約已送養");
				updateAdoptionNo.value = "";
				updateAdoptionInputEl.value = "";
			} else {
				
				updateAdoptionInputEl.value = str;
			}

		}
	}
}

/* 刪除預約的 查詢編號按鈕 */
const deleteAdoptionNo = document.querySelector("#deleteAdoptionId");
const deleteAdoptionIdBtn = document.querySelector("#deleteAdoptionId-btn");

deleteAdoptionIdBtn.addEventListener("click", function() {

	let url = "../delete-adoption?deleteAdoptionNo=" + deleteAdoptionNo.value;

	loadData(url, get_deleteDate);
})

/********************************/
/*             權限介面            */
/********************************/
/* 讀取輸入的預約編號的資料 */
const updatePermissUserNo = document.querySelector("#updatePermissUserId");
const updatePermissUserIBtn = document.querySelector("#updatePermissUserId-btn");
const updatePermissInputEl = document.querySelector("#updateRole");
updatePermissUserIBtn.addEventListener("click", function(e) {
	e.preventDefault();

	let url = "../search-permiss?updatePermissUserNo=" + updatePermissUserNo.value;

	loadData(url, get_PermissDate);
})

function get_PermissDate() {
	if (XHR.readyState == 4) {
		if (XHR.status == 200) { 
			let str = XHR.responseText;
			
			if (str === 'false') {
				alert("查無此會員 或 此會員沒有任何權限");
				updatePermissUserNo.value = "";
				updatePermissInputEl.value = "";
			} else {
				updatePermissInputEl.value = str;
			}
		}
	}
}

/* 刪除權限的 查詢編號按鈕 */
const deletePermissUserNo = document.querySelector("#deletePermissUserId");
const deletePermissIdBtn = document.querySelector("#deletePermissId-btn");

deletePermissIdBtn.addEventListener("click", function() {

	let url = "../delete-permiss?deletePermissUserNo=" + deletePermissUserNo.value;

	loadData(url, get_deleteDate);
})