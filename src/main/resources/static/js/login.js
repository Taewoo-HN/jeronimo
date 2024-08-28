$(document).ready(function () {
    $("form").submit(function (e) {
        e.preventDefault(); // 기본 폼 제출 동작 방지

        let formData = new FormData(this);

        $.ajax({
            type: "POST",
            url: "/logging",
            data: formData,
            processData: false,
            contentType: false,
            beforeSend: function(xhr) {
                // Mustache 템플릿에서 제공된 CSRF 토큰 사용
                xhr.setRequestHeader(csrfHeader, csrfToken);
            },
            success: function (response) {
                console.log("로그인 성공:", response);
                if (typeof response === 'string') {
                    try {
                        response = JSON.parse(response);
                    } catch (e) {
                        console.error("응답을 JSON으로 파싱할 수 없습니다:", e);
                    }
                }

                if (response && response.success) {
                    alert("로그인 성공");
                    window.location.href = "/main";
                } else {
                    alert("로그인 실패: " + (response.message || "알 수 없는 오류가 발생했습니다."));
                }
            },
            error: function (xhr, status, error) {
                console.log("로그인 실패:", xhr.responseText);
                let errorMessage = "로그인 실패";

                try {
                    let responseJson = JSON.parse(xhr.responseText);
                    if (responseJson && responseJson.message) {
                        errorMessage += ": " + responseJson.message;
                    }
                } catch (e) {
                    console.error("오류 응답을 JSON으로 파싱할 수 없습니다:", e);
                    errorMessage += ": " + xhr.responseText;
                }

                alert(errorMessage);
            }
        });
    });
});