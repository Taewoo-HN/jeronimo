document.addEventListener('DOMContentLoaded', function () {
    function updatePriceDifference(id) {
        const priceDifferenceElement = document.querySelector(`#${id} .text-sm`);
        const priceDifferenceText = parseFloat(priceDifferenceElement.textContent.split(' ')[0]); // 가격 차이를 추출
        const percentageChangeText = priceDifferenceElement.textContent.split(' ')[1]; // 퍼센트 변화 추출

        if (priceDifferenceText < 0) {
            priceDifferenceElement.textContent = `${priceDifferenceText.toFixed(2)} ${percentageChangeText}`;
            priceDifferenceElement.classList.remove('text-red-500');
            priceDifferenceElement.classList.add('text-indigo-500');
        } else if (priceDifferenceText > 0) {
            priceDifferenceElement.textContent = `+${priceDifferenceText.toFixed(2)} ${percentageChangeText}`;
            priceDifferenceElement.classList.remove('text-green-500');
            priceDifferenceElement.classList.add('text-red-500');
        }
    }

    updatePriceDifference('dollar'); // 원/달러
    updatePriceDifference('oil'); // 석유
    updatePriceDifference('snp');// snp
    updatePriceDifference('gold'); // 금
    updatePriceDifference('nasdaq'); // 나스닥
    updatePriceDifference('kospi'); // 코스피
    updatePriceDifference('kosdaq'); // 코스닥
    // 다른 id에 대해서도 동일하게 추가 가능
});
