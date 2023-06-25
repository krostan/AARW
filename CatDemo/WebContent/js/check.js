let XHR;

/****************/
/*   驗證使用者名稱  */
/****************/

function checkUserName() {
	let url;
	let data;
	
	let nameInput = document.getElementById("username").value;

	if (window.ActiveXObject) {
		XHR = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) {
		XHR = new XMLHttpRequest();
	}

	url = "../check-name";

	XHR.open("POST", url, true);
	XHR.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	XHR.onreadystatechange = getNameData;

	data = "username=" + nameInput;

	XHR.send(data);

}

function getNameData() {
	const result = document.getElementById("checkUserName");

	if (XHR.readyState == 4 && XHR.status == 200) {
		str = XHR.responseText;
		result.innerHTML = str;
	}
}

/****************/
/*    驗證密碼     */
/****************/

function checkPassword() {
	let url;
	let data;
	
	let passwordInput = document.getElementById("password").value;

	if (window.ActiveXObject) {
		XHR = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) {
		XHR = new XMLHttpRequest();
	}

	url = "../check-password";

	XHR.open("POST", url, true);
	XHR.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	XHR.onreadystatechange = getPasswordData;

	data = "password=" + passwordInput;

	XHR.send(data);

}

function getPasswordData() {
	const result = document.getElementById("checkPassword");

	if (XHR.readyState == 4 && XHR.status == 200) {
		str = XHR.responseText;
		result.innerHTML = str;
	}
}

/****************/
/*    驗證電話     */
/****************/
function checkPhone() {
	let url;
	let data;
	
	let phoneInput = document.getElementById("phone").value;

	if (window.ActiveXObject) {
		XHR = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) {
		XHR = new XMLHttpRequest();
	}

	url = "../check-phone";

	XHR.open("POST", url, true);
	XHR.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	XHR.onreadystatechange = getPhoneData;

	data = "phone=" + phoneInput;

	XHR.send(data);

}

function getPhoneData() {
	const result = document.getElementById("checkPhone");

	if (XHR.readyState == 4 && XHR.status == 200) {
		str = XHR.responseText;
		result.innerHTML = str;
	}
}

/****************/
/*    驗證信箱     */
/****************/
function checkEmail() {
	let url;
	let data;
	
	let emailInput = document.getElementById("email").value;

	if (window.ActiveXObject) {
		XHR = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) {
		XHR = new XMLHttpRequest();
	}
	console.log(emailInput);

	url = "../check-email";

	XHR.open("POST", url, true);
	XHR.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
	XHR.onreadystatechange = getEmailData;

	data = "email=" + emailInput;

	XHR.send(data);

}

function getEmailData() {
	const result = document.getElementById("checkEmail");

	if (XHR.readyState == 4 && XHR.status == 200) {
		str = XHR.responseText;
		result.innerHTML = str;
	}
}
/*
function check() {
	
	let nameInput = document.getElementById("username").value;
	
	data = "username=" + nameInput;
	url = "../check-name?"+data;
	console.log(data);
	
	fetch(url,{
		method:"POST",
		header:{
			"Content-type":"application/x-www-form-urlencoded"
		},

	})
	.then(response => response.text())
	.then(str=>{
		const result = document.getElementById("checkUsername");
		result.innerHTML = str;
	})
	.catch(error=>{
		console.error("Error:", error);
	});

}
*/