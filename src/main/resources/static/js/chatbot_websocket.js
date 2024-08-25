const socket = new SockJS('/ws');
const stompClient = Stomp.over(socket);

stompClient.connect({}, function(frame) {
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/messages', function(messageOutput) {
        showMessageOutput(JSON.parse(messageOutput.body));
    });
});

function sendMessage() {
    const message = messageInput.value.trim();
    if (message) {
        stompClient.send("/app/chat", {},
            JSON.stringify({'content': message, 'sender': 'User'}));
        messageInput.value = '';
    }
}

function showMessageOutput(messageOutput) {
    const li = document.createElement('li');
    li.innerHTML = `<strong>${messageOutput.sender}:</strong> ${messageOutput.content}`;
    li.className = 'bg-gray-100 p-2 rounded';
    messages.appendChild(li);
    messages.scrollTop = messages.scrollHeight;
}

document.getElementById('sendButton').addEventListener('click', function() {
    const input = document.getElementById('messageInput');
    const message = input.value.trim();
    if (message) {
        const messageList = document.getElementById('messages');
        const messageElement = document.createElement('div');
        messageElement.classList.add('message', 'user-message', 'ml-auto');
        messageElement.textContent = message;
        messageList.appendChild(messageElement);
        input.value = '';
        messageList.scrollTop = messageList.scrollHeight;
    }
});