const socket = new SockJS('/ws');
const stompClient = Stomp.over(socket);

stompClient.connect({}, function(frame) {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/messages', function(messageOutput) {
        showMessageOutput(JSON.parse(messageOutput.body));
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
    messageElement.innerHTML = `<div class="icon"><i class="fa-solid fa-user"></i></div><div class="textbox"><strong>${messageOutput.sender}:</strong> ${messageOutput.content}</div>`;
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