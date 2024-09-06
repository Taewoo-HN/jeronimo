var stompClient = null;

function connect() {
    // 로딩 표시 활성화
    document.getElementById('loading').style.display = 'block';

    // WebSocket 연결
    var socket = new SockJS('/ws');  // 서버 측 WebSocket 엔드포인트 '/ws'와 연결
    stompClient = Stomp.over(socket);

    // STOMP 연결 성공 시 콜백
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);

        // 로딩 표시 숨김
        document.getElementById('loading').style.display = 'none';

        // "/topic/public" 구독
        stompClient.subscribe('/topic/public', function(messageOutput) {
            showMessage(JSON.parse(messageOutput.body));
        });
    });
}

// 서버로 메시지 전송
function sendMessage() {
    var messageContent = document.getElementById('messageInput').value;
    if (messageContent && stompClient) {
        var chatMessage = {
            sender: 'User',  // 사용자 이름
            content: messageContent
        };

        // 서버로 메시지 전송. @MessageMapping("/chat.sendMessage")로 연결
        stompClient.send("/app/chat.sendMessage", {}, JSON.stringify(chatMessage));

        // 입력 필드 초기화
        document.getElementById('messageInput').value = '';
    }
}

// 메시지를 화면에 표시하는 함수
function showMessage(message) {
    var messagesDiv = document.getElementById('messages');
    var messageElement = document.createElement('div');
    messageElement.classList.add('message-row');  // CSS 클래스 추가

    // 사용자 메시지 스타일
    if (message.sender === 'User') {
        messageElement.classList.add('user-message');
    } else {
        messageElement.classList.add('bot-message');  // 챗봇 메시지 스타일
    }

    messageElement.innerHTML = '<span class="sender">' + message.sender + ':</span> <span class="message-content">' + message.content + '</span>';
    messagesDiv.appendChild(messageElement);

    // 스크롤 하단으로 이동
    messagesDiv.scrollTop = messagesDiv.scrollHeight;
}

// 페이지 로딩 시 WebSocket 연결 시작
window.onload = function() {
    connect();
};
