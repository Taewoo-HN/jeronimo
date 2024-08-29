$(document).ready(function () {
    $("#login_form").submit(function (e) {
        e.preventDefault(); // 기본 폼 제출 동작 방지

        // 폼 필드의 데이터를 수집
        let formData = {
            username: $("#l_user_id").val(),
            password: $("#l_user_pw").val()
            // 필요한 다른 필드들도 여기에 추가
        };

        $.ajax({
            type: "POST",
            url: "/logging",
            data: $.param(formData), // 데이터를 쿼리 문자열로 변환
            contentType: "application/x-www-form-urlencoded; charset=UTF-8", // 콘텐츠 타입 설정
            processData: false, // 데이터를 쿼리 문자열로 변환하지 않음
            success: function (response) {
                console.log("로그인 성공:", response);
            },
            error: function (xhr, status, error) {
                console.log("로그인 실패:", xhr.responseText);
                alert("로그인 실패: " + xhr.responseText);
            }
        });
    });
});
