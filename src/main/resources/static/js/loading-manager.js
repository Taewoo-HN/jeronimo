// loading-manager.js

class LoadingManager {
    constructor() {
        this.loadingScreen = document.getElementById('loading-screen');
        if (!this.loadingScreen) {
            this.createLoadingScreen();
        }
    }

    createLoadingScreen() {
        this.loadingScreen = document.createElement('div');
        this.loadingScreen.id = 'loading-screen';
        this.loadingScreen.className = 'loading-screen';
        this.loadingScreen.style.display = 'none';

        const spinner = document.createElement('div');
        spinner.className = 'loading-spinner';

        this.loadingText = document.createElement('p');
        this.loadingText.textContent = 'AI가 뉴스를 요약하는 중…';  // 기본 메시지

        this.loadingScreen.appendChild(spinner);
        this.loadingScreen.appendChild(this.loadingText);

        document.body.appendChild(this.loadingScreen);

        // 스타일 추가
        const style = document.createElement('style');
        style.textContent = `
            .loading-screen {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                display: flex;
                justify-content: center;
                align-items: center;
                flex-direction: column;
                z-index: 9999;
            }
            .loading-spinner {
                border: 5px solid #f3f3f3;
                border-top: 5px solid #3498db;
                border-radius: 50%;
                width: 50px;
                height: 50px;
                animation: spin 1s linear infinite;
            }
            @keyframes spin {
                0% { transform: rotate(0deg); }
                100% { transform: rotate(360deg); }
            }
            .loading-screen p {
                color: white;
                margin-top: 10px;
                font-size: 18px;
            }
        `;
        document.head.appendChild(style);
    }

    show(messages = ['AI가 뉴스를 요약하는 중…', 'AI가 GPU를 식히는 중…', 'AI가 데이터를 포장하는 중…']) {
        // 메시지 배열이 아니면 단일 메시지로 처리
        if (!Array.isArray(messages)) {
            messages = [messages];
        }

        this.loadingText.textContent = messages[0]; // 첫 번째 메시지로 설정
        this.loadingScreen.style.display = 'flex';

        // 메시지 타이머 설정
        this.messageIndex = 0; // 메시지 인덱스 초기화
        this.clearTimer(); // 기존 타이머 제거

        this.messageTimer = setInterval(() => {
            this.messageIndex = (this.messageIndex + 1) % messages.length;
            this.loadingText.textContent = messages[this.messageIndex];
        }, 3000); // 3초마다 메시지 변경
    }

    hide() {
        this.loadingScreen.style.display = 'none';
        this.clearTimer(); // 타이머 제거
    }

    clearTimer() {
        if (this.messageTimer) {
            clearInterval(this.messageTimer);
            this.messageTimer = null;
        }
    }
}

// 전역 인스턴스 생성
const loadingManager = new LoadingManager();

// 전역 함수로 노출
window.showLoading = () => loadingManager.show();
window.hideLoading = () => loadingManager.hide();