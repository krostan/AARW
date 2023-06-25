const ulEl = document.querySelector(".side-nav");
const liEls = document.querySelectorAll(".side-nav li:nth-child(n + 2)");

const userEl = document.querySelector(".user");

userEl.addEventListener("click", function () {
  liEls.forEach((li) => {
    li.classList.toggle("active");
  });

  // 得到當前ul的背景顏色
  const currentColor = window.getComputedStyle(ulEl).backgroundColor;
  // 判斷要交換的顏色
  if (currentColor === "rgb(110, 105, 100)") {
    ulEl.style.backgroundColor = "#fff";
  } else {
    ulEl.style.backgroundColor = "#6e6964";
  }
});
