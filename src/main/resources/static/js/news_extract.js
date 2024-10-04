$(document).ready(function() {
    // 요약 버튼 클릭 이벤트
    $(".keywords-btn").click(function() {
        var newsId = $(this).closest('.news-text').attr('id'); // 뉴스 ID 가져오기

        // 로딩 화면 표시
        window.showLoading();

        $.ajax({
            url: '/summarize',  // Spring Boot에서 매핑된 경로
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ news_id: newsId }),  // JSON 형식으로 데이터 전송
            success: function(response) {

                window.hideLoading();
                // 서버에서 반환된 요약 결과 처리
                let parsedResponse;
                try {
                    parsedResponse = JSON.parse(response);
                } catch (e) {
                    parsedResponse = response;  // 이미 JSON 객체인 경우
                }
                let keywords = parsedResponse.keywords;
                let recommand = parsedResponse.recommand_keywords;

                if ($(this).hasClass('keywords-btn')) {
                    // 키워드 모달 띄우기
                    let keywordContent = document.getElementById('keywordContent');
                    keywordContent.innerHTML = '<h4>키워드:</h4><p>' + (keywords || '키워드 없음') + '</p>';

                    // 추천 데이터가 있으면 함께 표시
                    if (recommand) {
                        keywordContent.innerHTML += '<h4>추천 테마:</h4><p>' + recommand + '</p>';
                    }

                    document.getElementById('keywordModal').style.display = 'block';
                    document.getElementById('summaryModal').style.display = 'none'; // 요약 모달 닫기
                }
            }.bind(this),  // 'this'를 바인딩하여 버튼에 따라 모달을 띄우도록 함
            error: function(error) {
                window.hideLoading();
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