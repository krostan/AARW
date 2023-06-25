let jsonData = '<%=request.getAttribute("jsonData")%>';
  	
let json =JSON.parse(jsonData);

////////////////////////////////
// 製作btn
function showBtn() {
  const page = document.querySelector(".pagination");

  let pageNum = Math.ceil(json.length / 10);
  let str = "";

  for (let i = 0; i < pageNum; i++) {
    str += `<button class="pagination_btn">${i + 1}</button>`;
  }
  page.innerHTML = str;
}

showBtn();
//////////////////////////////
// 監聽點擊事件
function test() {
  const btns = document.querySelectorAll(".pagination_btn");

  // for (let i = 0; i < btns.length; i++) {
  //   btns[i].addEventListener("click", changePage.bind(this, i, arg));
  // }

  btns.forEach((btn, i) => {
    btn.addEventListener("click", changePage.bind(this, i + 1, json));
  });
}

test();

function changePage(page, data) {
  let items = 10;
  let pageIndexStart = (page - 1) * items;
  let pageIndexEnd = page * items;
  let str;
  for (let i = pageIndexStart; i < pageIndexEnd; i++) {
    if (i >= data.length) {
      break;
    }
    str += `<div class="box" style="width:500px;height:20vh;background-color:yellow;margin-left:20px">${data[i]}</div>`;
  }

  document.querySelector(".page").innerHTML = str;
}
