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