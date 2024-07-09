$(document).ready(function() {
    let currentSlide = 0;
    const $carousel = $('.carousel');
    const slideCount = $('.carousel-item').length;

    $('.carousel-control.next').click(function(e) {
        e.preventDefault();
        currentSlide = (currentSlide + 1) % slideCount;
        updateCarousel();
    });

    $('.carousel-control.prev').click(function(e) {
        e.preventDefault();
        currentSlide = (currentSlide - 1 + slideCount) % slideCount;
        updateCarousel();
    });

    function updateCarousel() {
        $carousel.css('transform', `translateX(-${currentSlide * 100}%)`);
    }

    // 자동 슬라이드 (옵션)
    setInterval(function() {
        currentSlide = (currentSlide + 1) % slideCount;
        updateCarousel();
    }, 5000);
});