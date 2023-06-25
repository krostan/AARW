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

