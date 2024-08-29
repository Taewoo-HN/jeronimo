$(document).ready(function () {
    $("#login_form").submit(function (e) {
        e.preventDefault(); // 기본 폼 제출 동작 방지

        let formData = new FormData(this);

        $.ajax({
            type: "POST",
            url: "/logging",
            data: formData,
            processData: false,
            contentType: false,
            success: function (response) {
                console.log("로그인 성공:", response);
                // 응답 처리
            },
            error: function (xhr, status, error) {
                console.log("로그인 실패:", xhr.responseText);
                alert("로그인 실패: " + xhr.responseText);
            }
        });
    });
});
