// 챗봇 띄우는 자바스크립트 코드
$(document).ready(function() {
    $('#floatingBtn').click(function() {
        $('#modalOverlay').fadeIn();
        $('#rightModal').css('right', '0');
    });

    $('#closeModalBtn, #modalOverlay').click(function() {
        $('#modalOverlay').fadeOut();
        $('#rightModal').css('right', '-100%');
    });
});