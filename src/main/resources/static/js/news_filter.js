document.addEventListener('DOMContentLoaded', function () {
    const newsItems = document.querySelectorAll('.news-item');

    // 필터링할 키워드들
    const filterKeywords = [
        '<b>',
        'ⓒ SBS & SBS i',
        'RSS 피드는',
        '▶ SBS 뉴스 앱 다운로드',
        '▶ 뉴스에 지식을 담다',
        '무단복제 및 재배포 금지',
        '이 기사의 전체 내용 확인하기',
        '스브스프리미엄 앱 다운로드',
        '개인 리더 이용 목적으로 허용',
        '피드를 이용한 게시 등의 무단 복제는 금지'
    ];

    function cleanArticleContent(content) {
        if (!content) return '';
        let lines = content.split('\n');
        lines = lines.filter(line =>
            !filterKeywords.some(keyword => line.includes(keyword)) &&
            line.trim() !== ''
        );
        return lines.map(line => line.trim()).join('\n');
    }

    // 뉴스 콘텐츠 초기화
    document.querySelectorAll('.news-content').forEach(contentElement => {
        contentElement.innerHTML = cleanArticleContent(contentElement.innerHTML);
    });
});
