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

        const text = document.createElement('p');
        text.textContent = '로딩 중...';

        this.loadingScreen.appendChild(spinner);
        this.loadingScreen.appendChild(text);

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

    show() {
        this.loadingScreen.style.display = 'flex';
    }

    hide() {
        this.loadingScreen.style.display = 'none';
    }
}

// 전역 인스턴스 생성
const loadingManager = new LoadingManager();

// 전역 함수로 노출
window.showLoading = () => loadingManager.show();
window.hideLoading = () => loadingManager.hide();