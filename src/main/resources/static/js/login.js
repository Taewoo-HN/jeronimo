$(document).ready(function () {
    $("#sign_in").click(function (e) {
        e.preventDefault(); // 기본 버튼 동작 방지

        let formData = {
            _csrf: $("#l_csrf").val(),
            user_id: $("l_user_id").val(),
            user_pw: $("l_user_pw").val()
        };

        $.ajax({
            type: "POST",
            url: "/logging",
            data: formData,
            success: function (response) {
                console.log("로그인 성공:", response);
                // 성공 시 추가 동작
                alert("로그인 성공");
                window.location.href = "/main";
            },
            error: function (error) {
                console.log("로그인 실패:", error);
                // 실패 시 에러 처리
                alert("로그인 실패");
            }
        });
    });
});
