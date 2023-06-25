"use strict";

const btnNavEl = document.querySelector(".btn__mobile-nav");
const headerEl = document.querySelector(".header");

const btnIconEls = document.querySelectorAll(".btn__accordion");
const accItemEls = document.querySelectorAll(".accordion__item");

const allLinks = document.querySelectorAll("a:link");

const sectionCatEl = document.querySelector(".section-cat");

const nav = document.querySelector(".nav");

const headerHeight = headerEl.getBoundingClientRect().height;
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
/*全部的link */
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
/*         Sticky navigation      */
/**********************************/
const options = {
  root: null,
  threshold: 0,
  rootMargin: "100px",
};

const stickyNav = function (entries) {
  const [entry] = entries;
  if (!entry.isIntersecting) document.body.classList.add("sticky");
  else document.body.classList.remove("sticky");
};

const headerObs = new IntersectionObserver(stickyNav, options);

headerObs.observe(sectionCatEl);
/*
const stickyNav = function (entries) {
  const [entry] = entries;
  if (!entry.isIntersecting) document.body.classList.add("sticky");
  else document.body.classList.remove("sticky");
};
const headerObs = new IntersectionObserver(stickyNav, {
  root: null,
  threshold: 0,
  rootMargin: `-${headerHeight}px`,
});

headerObs.observe(sectionCatEl);*/
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
/**********************************/
/*         section-animation      */
/**********************************/
const allSections = document.querySelectorAll(".section");
const revealSection = function (entries, observer) {
  const [entry] = entries;

  if (!entry.isIntersecting) return;
  entry.target.classList.remove("section__hidden");

  observer.unobserve(entry.target);
};

const sectionObserver = new IntersectionObserver(revealSection, {
  root: null,
  threshold: 0.15,
});

allSections.forEach(function (section) {
  sectionObserver.observe(section);
  section.classList.add("section__hidden");
});

/**********************************/
/*            Slider              */
/**********************************/
const slider = function () {
  const slides = document.querySelectorAll(".slide");
  const btnLeft = document.querySelector(".slider__btn--left");
  const btnRight = document.querySelector(".slider__btn--right");
  const dotContainer = document.querySelector(".dots");

  let curSlide = 0;
  const maxSlide = slides.length;

  const goToSlide = function (slide) {
    slides.forEach((s, i) => {
      s.style.transform = `translateX(${100 * (i - slide)}%)`;
    });
  };

  const creadeDots = function () {
    slides.forEach(function (_, i) {
      dotContainer.insertAdjacentHTML(
        "beforeend",
        `<div class="dots__dot" data-slide="${i}">&nbsp</div>`
      );
    });
  };

  const activateDot = function (slide) {
    document
      .querySelectorAll(".dots__dot")
      .forEach((dot) => dot.classList.remove("dots__dot--active"));

    document
      .querySelector(`.dots__dot[data-slide="${slide}"]`)
      .classList.add("dots__dot--active");
  };

  const nextSlide = function () {
    if (curSlide === maxSlide - 1) {
      curSlide = 0;
    } else {
      curSlide++;
    }

    goToSlide(curSlide);
    activateDot(curSlide);
  };

  const preSlide = function () {
    if (curSlide === 0) {
      curSlide = maxSlide - 1;
    } else {
      curSlide--;
    }

    goToSlide(curSlide);
    activateDot(curSlide);
  };

  const init = function () {
    goToSlide(0);
    creadeDots();
    activateDot(0);
  };
  init();

  btnRight.addEventListener("click", nextSlide);
  btnLeft.addEventListener("click", preSlide);

  document.addEventListener("keydown", function (e) {
    if (e.key === "ArrowLeft") preSlide();
    e.key === "ArrowRight" && nextSlide();
  });

  dotContainer.addEventListener("click", function (e) {
    console.log(e.target);
    if (e.target.classList.contains("dots__dot")) {
      const { slide } = e.target.dataset;
      goToSlide(slide);
      activateDot(slide);
    }
  });
};
slider();
