function showToast(message, duration = 3000) {
    const toast = document.getElementById('toast');
    toast.textContent = message;
    toast.style.opacity = 1;

    setTimeout(() => {
        toast.style.opacity = 0;
    }, duration);
}

document.getElementById('maesu').addEventListener('click', () => {
    showToast("매수 주문이 전송되었습니다.");
});

document.getElementById('maedo').addEventListener('click', () => {
    showToast("매도 주문이 전송되었습니다.");
});