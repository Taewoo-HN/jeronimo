$(document).ready(function() {
    // sendButton 클릭 이벤트와 Enter 키 입력 이벤트 설정
    $('#sendButton').on('click', sendMessage);
    $('#messageInput').on('keypress', function (e) {
        if (e.which == 13) sendMessage();
    });

    showMessage("AI 주식 도우미 Jeronimo입니다." , 'bot');
    showMessage("종료를 원하시면 종료를 입력해주세요." , 'bot');

    // 메시지 전송 함수
    function sendMessage() {
        var messageContent = $('#messageInput').val().trim();
        if (messageContent) {
            // 사용자가 입력한 메시지 출력
            showMessage(messageContent, 'user');
            
            /** 종료를 입력하면 닫히는 코드*/
            if (messageContent === "종료") {
                setInterval(function() {
                    showMessage("챗봇과의 대화를 종료합니다. 감사합니다.", 'bot');
                    window.close();
                }, 3000);
            }

            // 서버에 보낼 메시지 형식
            var chatMessage = {
                sender: 'User',
                content: messageContent
            };

            // Ajax를 사용하여 서버에 메시지 전송
            $.ajax({
                url: '/chatBot',  // 서버의 엔드포인트 URL
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(chatMessage),  // JSON 형식으로 메시지 전송
                success: function (data) {
                    showMessage(data, 'bot');
                },
                error: function (xhr, status, error) {
                    console.error('Error:', error);
                }
            });

            // 메시지 입력창 비우기
            $('#messageInput').val('');
        }
    }

// 메시지를 화면에 출력하는 함수
    function showMessage(message, type) {
        const cleanedMessage = message.replace(/response/g, '').trim();
        const messageClass = type === 'user' ? 'user-message' : 'bot-message';
        $('#messages').append(
            $('<div>').text(type === 'user' ? 'User: ' + cleanedMessage : 'Chatbot: ' + cleanedMessage)
                .addClass(`message ${messageClass}`)
        );
    }
});
