// 워드 클라우드 이미지를 서버에서 새 창에 띄우는 함수
function openWordCloudInNewWindow() {
    // 이미지가 제공되는 Spring 서버의 엔드포인트 URL
    const imageUrl = '/download-wordcloud';  // Spring 서버의 이미지 URL

    // 새 창 열기
    const newWindow = window.open("", "WordCloudImage", "width=800,height=600");

    // 새 창이 정상적으로 열렸을 때
    if (newWindow) {
        // 새 창의 내용을 이미지로 채움
        newWindow.document.write(`
                <html>
                <head>
                    <title>Word Cloud Image</title>
                </head>
                <body>
                    <h1>워드 클라우드 이미지</h1>
                    <img src="${imageUrl}" alt="Word Cloud Image" style="width:640px; height: 480px;">
                </body>
                </html>
            `);

        // 새 창의 문서를 업데이트하여 이미지 렌더링
        newWindow.document.close();
    } else {
        // 새 창이 열리지 않았을 경우 (팝업 차단 등의 이유로)
        alert("창을 열 수 없습니다. 설정을 확인해주세요");
    }
}

// 버튼 클릭 시 새 창으로 이미지 열기
document.getElementById('open-wordcloud-btn').addEventListener('click', function() {
    openWordCloudInNewWindow();  // 버튼 클릭 시 새 창에 이미지 로드
});