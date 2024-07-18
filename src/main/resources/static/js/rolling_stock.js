$(document).ready(function() {
    const rankings = [
        '삼성전자',
        'SK하이닉스',
        'LG전자',
        '현대자동차',
        '네이버',
        '카카오',
        '삼성바이오로직스',
        '셀트리온',
        'POSCO',
        'LG화학',
    ];
    let currentIndex = 0;

    function showNextRanking() {
        $('#ranking-display').text(`${currentIndex + 1}. ${rankings[currentIndex]}`);
        currentIndex = (currentIndex + 1) % rankings.length;
    }

    showNextRanking(); // 초기 표시
    setInterval(showNextRanking, 3000); // 3초마다 갱신
});