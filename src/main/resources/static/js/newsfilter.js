document.addEventListener('DOMContentLoaded', function () {
    const newsItems = document.querySelectorAll('.news-item');
    const summaryModal = document.getElementById("summaryModal");
    const keywordsModal = document.getElementById("keywordsModal");
    const closeBtns = document.getElementsByClassName("close");

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

    function summarizeContent(content) {
        const cleanedContent = cleanArticleContent(content);
        const sentences = cleanedContent.match(/[^．。！？\n]+[．。！？\n]/g) || [];
        const sentenceScores = sentences.map(sentence => ({
            text: sentence.trim(),
            score: calculateSentenceImportance(sentence)
        }));
        sentenceScores.sort((a, b) => b.score - a.score);
        const summaryLength = Math.max(1, Math.min(3, Math.ceil(sentences.length * 0.3)));
        const topSentences = sentenceScores.slice(0, summaryLength);
        topSentences.sort((a, b) => sentences.indexOf(a.text) - sentences.indexOf(b.text));
        return topSentences.map(sentence => sentence.text).join(' ');
    }

    function calculateSentenceImportance(sentence) {
        const words = sentence.toLowerCase().match(/[a-z가-힣]+/g) || [];
        const wordFrequency = {};
        words.forEach(word => {
            wordFrequency[word] = (wordFrequency[word] || 0) + 1;
        });
        return Object.values(wordFrequency).reduce((sum, freq) => sum + freq, 0);
    }

    function extractKeywords(content) {
        const cleanedContent = cleanArticleContent(content);
        const words = cleanedContent.toLowerCase().match(/[a-z가-힣]+/g) || [];
        const stopWords = new Set(['이', '그', '저', '것', '수', '들', '더', '는', '을', '를', '에', '의', 'the', 'a', 'an', 'in', 'on', 'at', 'for', 'to', 'of', 'and', 'or', 'but']);
        const filteredWords = words.filter(word => !stopWords.has(word) && word.length > 1);
        const wordFrequency = {};
        filteredWords.forEach(word => {
            wordFrequency[word] = (wordFrequency[word] || 0) + 1;
        });
        return Object.entries(wordFrequency)
            .sort((a, b) => b[1] - a[1])
            .slice(0, 10)
            .map(entry => entry[0]);
    }

    newsItems.forEach(item => {
        const title = item.querySelector('.news-title');
        const content = item.querySelector('.news-content');
        const summaryBtn = item.querySelector('.summary-btn');
        const keywordsBtn = item.querySelector('.keywords-btn');

        if (title && content) {
            title.addEventListener('click', () => {
                content.style.display = content.style.display === 'none' ? 'block' : 'none';
            });
        }

        if (summaryBtn && content) {
            summaryBtn.addEventListener('click', () => {
                const summary = summarizeContent(content.innerText);
                document.getElementById("summaryContent").innerHTML = summary;
                summaryModal.style.display = "block";
            });
        }

        if (keywordsBtn && content) {
            keywordsBtn.addEventListener('click', () => {
                const keywords = extractKeywords(content.innerText);
                document.getElementById("keywordsContent").innerHTML = keywords.join(", ");
                keywordsModal.style.display = "block";
            });
        }
    });

    Array.from(closeBtns).forEach(btn => {
        btn.addEventListener('click', () => {
            summaryModal.style.display = "none";
            keywordsModal.style.display = "none";
        });
    });

    window.addEventListener('click', (event) => {
        if (event.target === summaryModal) {
            summaryModal.style.display = "none";
        }
        if (event.target === keywordsModal) {
            keywordsModal.style.display = "none";
        }
    });

    // 초기 콘텐츠 정제
    document.querySelectorAll('.news-content').forEach(contentElement => {
        contentElement.innerHTML = cleanArticleContent(contentElement.innerHTML);
    });
});
