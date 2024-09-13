$(document).ready(function() {
    // 요약 버튼 클릭 이벤트
    $(".summary-btn").click(function () {
        let newsId = $(this).closest('.news-text').attr('id'); // 뉴스 ID 가져오기

        $.ajax({
            url: '/summarize',  // 요약 경로
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({news_id: newsId}),  // JSON 형식으로 데이터 전송
            success: function (response) {
                let parsedResponse;
                try {
                    parsedResponse = JSON.parse(response);
                } catch (e) {
                    parsedResponse = response;  // 이미 JSON 객체인 경우
                }

                // 요약 내용 처리
                let summaryContent = document.getElementById('summaryContent');
                summaryContent.textContent = parsedResponse.summary || '요약 없음';

                // 요약 모달 띄우기
                document.getElementById('summaryModal').style.display = 'block';
                document.getElementById('keywordModal').style.display = 'none'; // 키워드 모달 닫기
            },
            error: function (error) {
                console.error("요약 요청 중 에러 발생:", error);
            }
        });
    });
});