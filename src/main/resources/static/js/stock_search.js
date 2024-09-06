document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('stock-search');
    const stockList = document.getElementById('stock-list');
    let debounceTimer;

    searchInput.addEventListener('input', function() {
        clearTimeout(debounceTimer);
        debounceTimer = setTimeout(() => {
            const searchTerm = this.value.trim();
            if (searchTerm === '') {
                fetchStocks('');
            } else {
                fetchStocks(searchTerm);
            }
        }, 300);
    });

    async function fetchStocks(searchTerm) {
        try {
            const response = await fetch(`/api/stocks/search?term=${encodeURIComponent(searchTerm)}`);
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            const stocks = await response.json();
            displayStocks(stocks);
        } catch (error) {
            console.error('Failed to fetch stocks:', error);
        }
    }

    function displayStocks(stocks) {
        stockList.innerHTML = '';
        if (stocks.length > 0) {
            stocks.forEach(stock => {
                const div = document.createElement('div');
                div.textContent = `${stock.name} (${stock.code})`;
                div.classList.add('p-2', 'hover:bg-gray-100', 'cursor-pointer');
                div.addEventListener('click', () => {
                    navigateToStockDetail(stock.code);
                });
                stockList.appendChild(div);
            });
            stockList.classList.remove('hidden');
        } else {
            stockList.classList.add('hidden');
        }
    }

    function navigateToStockDetail(stockCode) {
        window.location.href = `/stock-detail/${stockCode}`;
    }

    // 검색창 외부 클릭 시 목록 숨기기
    document.addEventListener('click', function(event) {
        if (!searchInput.contains(event.target) && !stockList.contains(event.target)) {
            stockList.classList.add('hidden');
        }
    });

    // 검색창 포커스 시 목록 표시
    searchInput.addEventListener('focus', function() {
        if (this.value.trim() === '') {
            fetchStocks('');
        }
    });
});