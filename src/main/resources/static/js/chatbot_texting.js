const messageInput = document.getElementById('message-input');
const sendButton = document.getElementById('send-button');
const chatMessages = document.getElementById('chat-messages');

sendButton.addEventListener('click', () => {
    const message = messageInput.value;
    if (message) {
        const messageElement = document.createElement('div');
        messageElement.classList.add('bg-purple-500', 'text-white','flex','item-smart' , 'rounded-lg', 'p-3');
        messageElement.textContent = message;
        chatMessages.appendChild(messageElement);
        messageInput.value = '';
    }
});