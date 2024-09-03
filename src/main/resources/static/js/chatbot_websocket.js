const socket = new SockJS('/ws');
const stompClient = Stomp.over(socket);

document.addEventListener("DOMContentLoaded", function() {
    // 페이지 로드 후 로딩 애니메이션 표시
    document.getElementById('loading').style.display = 'block';

    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function(messageOutput) {
            console.log("Received message: " + messageOutput.body);
            showMessageOutput(JSON.parse(messageOutput.body));
        });

        // 연결이 완료되면 로딩 애니메이션 숨김
        document.getElementById('loading').style.display = 'none';
    });
});

function sendMessage() {
    const messageInput = document.getElementById('messageInput');
    const message = messageInput.value.trim();
    if (message) {
        stompClient.send("/app/chat", {}, JSON.stringify({'content': message, 'sender': 'User'}));
        messageInput.value = '';
    }
}

function showMessageOutput(messageOutput) {
    const messageList = document.getElementById('messages');
    const messageElement = document.createElement('div');
    messageElement.className = 'chat ch2';
    console.log(messageOutput);

    if (messageOutput.sender === 'Server') {
        messageElement.innerHTML = `<div class="icon"><i class="fa-solid fa-robot"></i></div><div class="textbox"><strong>${messageOutput.sender}:</strong> ${messageOutput.content}</div>`;
    } else {
        messageElement.innerHTML = `<div class="icon"><i class="fa-solid fa-user"></i></div><div class="textbox"><strong>${messageOutput.sender}:</strong> ${messageOutput.content}</div>`;
    }

    messageList.appendChild(messageElement);
    messageList.scrollTop = messageList.scrollHeight;
}

document.getElementById('sendButton').addEventListener('click', function() {
    sendMessage();
});

document.getElementById('messageInput').addEventListener('keypress', function(event) {
    if (event.key === 'Enter') {
        sendMessage();
    }
});
