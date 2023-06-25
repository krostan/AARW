/***********/
/* 重設密碼  */
/**********/
const iconBox = document.querySelectorAll(".icon__box");
const inputBox = document.querySelectorAll(".input_box");

iconBox.forEach(function (icon, i) {
  icon.addEventListener("click", function (e) {
    inputBox[i].classList.toggle("item-open");
    
    if (inputBox[i].classList.contains("item-open")) {
      inputBox[i].children[0].type = "text";
    } else {
      inputBox[i].children[0].type = "password";
    }
  });
});



