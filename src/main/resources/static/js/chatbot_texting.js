document.getElementById('sendButton').addEventListener('click', function () {
    const input = document.getElementById('messageInput');
    const message = input.value.trim();
    if (message) {
        const messagesContainer = document.getElementById('messages');
        const newMessage = document.createElement('div');
        newMessage.className = 'chat ch2';
        newMessage.innerHTML = `
                <div class="icon"><i class="fa-solid fa-user"></i></div>
                <div class="textbox">${message}</div>
            `;
        messagesContainer.appendChild(newMessage);
        messagesContainer.scrollTop = messagesContainer.scrollHeight;
        input.value = '';
    }
});
