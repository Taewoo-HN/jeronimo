function showToast(message, duration = 1000) {
    const toast = new bootstrap.Toast(document.getElementById('toast'), {
        animation: true,
        autohide: true,
        delay: duration
    });
    document.querySelector('.toast-body').textContent = message;
    toast.show();
}

document.getElementById('maesu').addEventListener('click', () => {
    showToast("매수 주문이 전송되었습니다.");
});

document.getElementById('maedo').addEventListener('click', () => {
    showToast("매도 주문이 전송되었습니다.");
});