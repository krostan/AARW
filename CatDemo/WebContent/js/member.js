"use strict";
const btnNavEl = document.querySelector(".btn__mobile-nav");
const headerEl = document.querySelector(".header");

const btnIconEls = document.querySelectorAll(".btn__accordion");
const accItemEls = document.querySelectorAll(".accordion__item");

const allLinks = document.querySelectorAll("a:link");

const nav = document.querySelector(".nav");
/**********************************/
/* MOBILE NAVIGATION */
/**********************************/
btnNavEl.addEventListener("click", function () {
  headerEl.classList.toggle("nav-open");
});

/**********************************/
/*        section-question        */
/**********************************/
btnIconEls.forEach(function (btnIconEl, index) {
  btnIconEl.addEventListener("click", function (e) {
    accItemEls[index].classList.toggle("item-open");
  });
});

/**********************************/
/*         scroll-behavior        */
/**********************************/
allLinks.forEach(function (link) {
  link.addEventListener("click", function (e) {
    e.preventDefault();
    const href = this.getAttribute("href");
    console.log(href);

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
const handleHover = function (e) {
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
