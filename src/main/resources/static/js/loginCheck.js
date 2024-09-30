document.addEventListener("DOMContentLoaded", function() {
    // 서버에서 전달된 오류 메시지가 있을 경우 이를 처리
    fetch('/login')  // 서버에서 오류 메시지를 전달하는 경로
        .then(response => response.json())
        .then(data => {
            if (data && !data.success) {
                alert(data.message); // 오류 메시지를 사용자에게 알림
            }
        })
        .catch(error => {
            console.error('Error fetching error message:', error);
        });
});

document.addEventListener("DOMContentLoaded", function() {
    // 성공 메시지 처리
    var successMessage = "{{success}}";
    if (successMessage) {
        alert(successMessage);  // 회원가입 성공 메시지 알림
    }

    // 오류 메시지 처리
    var errorMessage = "{{error}}";
    if (errorMessage) {
        alert(errorMessage);  // 오류 메시지 알림
    }
});
