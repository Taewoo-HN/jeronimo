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
                let keywords = response.keywords;
                let recommand = response.recommand_keywords;
                // 클릭한 버튼에 따라 모달 띄우기
                if ($(this).hasClass('summary-btn')) {
                    // 요약 모달 띄우기
                    document.getElementById('summaryModal').style.display = 'block';
                    document.getElementById('keywordModal').style.display = 'none'; // 키워드 모달 닫기
                }

                if ($(this).hasClass('keywords-btn')) {
                    // 키워드 모달 띄우기
                    document.getElementById('keywordContent').textContent = keywords;

                    // 추천 데이터가 있으면 함께 표시
                    if (recommand) {
                        document.getElementById('keywordContent').textContent += "\n추천 테마 :" + recommand;
                    }

                    document.getElementById('keywordModal').style.display = 'block';
                    document.getElementById('summaryModal').style.display = 'none'; // 요약 모달 닫기
                }
            }.bind(this),  // 'this'를 바인딩하여 버튼에 따라 모달을 띄우도록 함
            error: function(error) {
                console.error("요약 요청 중 에러 발생:", error);
            }
        });
    });

    // 모달 닫기 버튼 이벤트
    document.querySelectorAll('.close').forEach(function(closeBtn) {
        closeBtn.addEventListener('click', function() {
            closeBtn.closest('.modal').style.display = 'none';
        });
    });
});