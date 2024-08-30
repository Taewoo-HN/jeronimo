document.addEventListener("DOMContentLoaded", function() {
    // 서버에서 전달된 오류 메시지가 있을 경우 이를 처리
    const errorMessage = /*[[${error}]]*/ '';
    if (errorMessage) {
        alert(errorMessage); // 오류 메시지를 사용자에게 알림
    }
});