/**
 * header 的 search
 */
 
 /**********************************/
/*            search              */
/**********************************/
const searchBtn = document.querySelector("#search-btn");
const searchContainer = document.querySelector(".search-bar-container");
// input 物件
const searchB = document.querySelector("#search-bar");

searchBtn.addEventListener("click", function () {
  searchBtn.classList.toggle("fa-times");
  searchContainer.classList.toggle("active");
  
  if(searchContainer.classList.contains("active")){
  	searchB.focus();
  }
});
