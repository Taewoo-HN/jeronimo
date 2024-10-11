const socket = new SockJS('/ws');  // WebSocket 연결을 위한 엔드포인트 설정
const stompClient = Stomp.over(socket);

stompClient.connect({}, function(frame) {
//    console.log('Connected: ' + frame);  // WebSocket 연결 성공 시 로그 출력

    // WebSocket 연결이 성공한 후 주제를 구독
    stompClient.subscribe('/topic/stocks', function(messageOutput) {
        const stockData = JSON.parse(messageOutput.body);
        updateStockData(stockData);
    });});


function updateStockData(stockData) {
    // 주식 코드에 해당하는 HTML 요소를 찾음
    const element = document.querySelector(`[data-stock-code="${stockData.code}"]`);
    if (element) {
        // 소수점 2자리까지 포맷 처리
        const percentageChange = stockData.percentageChange.toFixed(2);
        const priceDifference = stockData.priceDifference.toFixed(0);

        // 가격과 등락률 업데이트
        element.querySelector('.current-price').textContent = `${stockData.currentPrice.toFixed(0)}원`;
        element.querySelector('.price-difference').textContent = `${priceDifference}원 (${percentageChange}%)`;

        // 배경 색상 업데이트 (등락률에 따라 변경)
        if (stockData.percentageChange > 0) {
            element.classList.add('bg-red-100');
            element.classList.remove('bg-blue-100');
            element.querySelector('.price-difference').classList.add('text-red-500');
            element.querySelector('.price-difference').classList.remove('text-blue-500');
        } else {
            element.classList.add('bg-blue-100');
            element.classList.remove('bg-red-100');
            element.querySelector('.price-difference').classList.add('text-blue-500');
            element.querySelector('.price-difference').classList.remove('text-red-500');
        }
        element.style.transition = 'transform 0.5s ease-in-out, background-color 0.5s ease-in-out';
        element.style.transform = 'scale(1.1)';

        if(stockData.priceDifference > 0){
            element.style.backgroundColor = '#E30D71';
        }else{
            element.style.backgroundColor = '#0D0ADF';
        }

        // 일정 시간 후 원래 상태로 돌아가도록 설정
        setTimeout(() => {
            element.style.transform = 'scale(1)';
            element.style.backgroundColor = '';
        }, 500);
    } else {
        console.warn(`Stock element for code ${stockData.code} not found.`);
    }
}