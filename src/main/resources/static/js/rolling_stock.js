$(document).ready(function() {
    const rankings = [
        {name: '삼성전자', code: '005930'},
        {name: 'SK하이닉스', code: '000660'},
        {name: 'LG전자', code: '066570'},
        {name: '현대자동차', code: '005380'},
        {name: '네이버', code: '035420'},
        {name: '카카오', code: '035720'},
        {name: '삼성바이오로직스', code: '207940'},
        {name: '셀트리온', code: '068270'},
        {name: 'POSCO', code: '005490'},
        {name: 'LG화학', code: '051910'},
    ];
    let currentIndex = 0;

    function showNextRanking() {
        const stock = rankings[currentIndex];
        $('#ranking-display').html(`<a href="/stock-detail/${stock.code}">${currentIndex + 1}. ${stock.name}</a>`);
        currentIndex = (currentIndex + 1) % rankings.length;
    }

    showNextRanking(); // 초기 표시
    setInterval(showNextRanking, 3000); // 3초마다 갱신
});