/******************/
/* refresh 驗證圖片 */
/******************/

const iconRefresh = document.querySelector(".check_icon-refresh");
const checkImg = document.querySelector(".check_img");
const checkImgBox = document.querySelector(".check_img-box");
/*
iconRefresh.addEventListener("click",function(){
	// 透過 不同的時間戳記 來獲得不同的url 以獲取不同的驗證圖片
	const timestamp = new Date().getTime();
	checkImg.src = "../createImage.jsp?"+timestamp;
});*/


checkImgBox.addEventListener("click",function(){
	// 透過 不同的時間戳記 來獲得不同的url 以獲取不同的驗證圖片
	const timestamp = new Date().getTime();
	checkImg.src = "../createImage.jsp?"+timestamp;
});