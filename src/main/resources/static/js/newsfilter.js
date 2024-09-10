$(document).ready(function() {
    // 요약 버튼 클릭 이벤트
    $(".summary-btn").click(function() {
        var newsId = $(this).closest('.news-text').attr('id'); // 뉴스 ID 가져오기

        $.ajax({
            url: '/summarize',  // Spring Boot에서 매핑된 경로
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ news_id: newsId }),  // JSON 형식으로 데이터 전송
            success: function(response) {
                // 서버에서 반환된 요약 결과 처리
                alert("요약 결과: " + response);
            },
            error: function(error) {
                console.error("요약 요청 중 에러 발생:", error);
            }
        });
    });
});