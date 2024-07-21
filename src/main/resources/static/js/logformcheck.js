// 이름 입력 필드에 한글만 입력 가능하도록 설정
    const nameInput = document.querySelector('#r_user_name');
    // 한글 이외의 문자를 제거하는 함수
    function onlyKorean() {
    // 한글 이외의 문자를 찾는 정규식
    const pattern = /[a-z0-9]|[ \[\]{}()<>?|`~!@#$%^&*-_+=,.;:\"'\\]/g;
    // 입력된 값에서 한글 이외의 문자를 제거
    this.value = this.value.replace(pattern, '');
}
    // 입력 이벤트에 onlyKorean 함수를 연결
    nameInput.addEventListener('input', onlyKorean);

    // 전화번호 입력 필드에 11자리 숫자만 입력 가능하도록 설정
    const phoneInput = document.querySelector('#r_phone_number');
    phoneInput.addEventListener('input', function() {
    this.value = this.value.replace(/\D/g, '').slice(0, 11);
});

//     // 계좌 입력 필드에 12자리 숫자만 입력 가능하도록 설정
//     const accountInput = document.querySelector('#stock_account');
//     accountInput.addEventListener('input', function() {
//     this.value = this.value.replace(/\D/g, '').slice(0, 12);
// });
//
//     // 시크릿키 입력 필드에 180자까지 입력 가능하도록 설정
//     const secretKeyInput = document.querySelector(' #secret_key');
//     secretKeyInput.addEventListener('input', function() {
//     this.value = this.value.slice(0, 180);
// });
