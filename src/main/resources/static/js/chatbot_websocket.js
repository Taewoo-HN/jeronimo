document.addEventListener("DOMContentLoaded", function() {
    const socket = new SockJS('/ws');
    const stompClient = Stomp.over(socket);

    // 초기화
    initializeWebSocketConnection();
    initializeEventListeners();

    function initializeWebSocketConnection() {
        // 페이지 로드 후 로딩 애니메이션 표시
        displayLoading(true);

        stompClient.connect({}, onConnected, onError);
    }

    function onConnected(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', onMessageReceived);
        displayLoading(false); // 연결이 성공적으로 완료되면 로딩 애니메이션 제거
    }

    function onError(error) {
        console.error('Error connecting: ', error);
        displayLoading(false); // 오류 발생 시 로딩 애니메이션 제거
    }

    function onMessageReceived(messageOutput) {
        console.log("Received message: " + messageOutput.body);
        try {
            const message = JSON.parse(messageOutput.body);
            showMessageOutput(message);
        } catch (e) {
            console.error("Error parsing or displaying the message: ", e);
        }
    }

    function sendMessage() {
        const messageInput = document.getElementById('messageInput');
        const message = messageInput.value.trim();
        if (message) {
            stompClient.send("/app/chat", {}, JSON.stringify({
                'content': message,
                'sender': 'User'
            }));
            messageInput.value = '';
        }
    }

    function showMessageOutput(message) {
        const messageList = document.getElementById('messages');
        const messageElement = document.createElement('div');
        messageElement.className = 'chat ch2';

        const icon = message.sender === 'Server' ? 'fa-robot' : 'fa-user';
        messageElement.innerHTML = `
            <div class="icon"><i class="fa-solid ${icon}"></i></div>
            <div class="textbox"><strong>${message.sender}:</strong> ${message.content}</div>
        `;

        messageList.appendChild(messageElement);
        messageList.scrollTop = messageList.scrollHeight;
    }

    function displayLoading(show) {
        const loadingElement = document.getElementById('loading');
        loadingElement.style.display = show ? 'block' : 'none';
    }

    function initializeEventListeners() {
        document.getElementById('sendButton').addEventListener('click', sendMessage);
        document.getElementById('messageInput').addEventListener('keypress', function(event) {
            if (event.key === 'Enter') {
                sendMessage();
            }
        });
    }
});
