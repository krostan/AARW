/* get password */
function getPwd() {
	let username = document.getElementById("username");
	let email = document.getElementById("email");
	let form = document.getElementById("pwd_form");

	if (username.value.length == 0) {
		alert("請輸入帳號");
		return false;
	}

	if (email.value.length == 0) {
		alert("請輸入電子郵件地址");
		return false;
	}

	localStorage.setItem("username", username.value);
	localStorage.setItem("email", email.value);

	form.submit();
}