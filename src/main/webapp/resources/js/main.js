// 1. 지도 만들기: id="map"인 div에 지도를 그림
// [위도, 경도] = 한국과 일본이 함께 보이는 중심, 5 = 확대 수준(숫자가 클수록 확대)
const map = L.map('map').setView([36.0, 132.0], 5);

// 2. 지도 배경 그림(타일) - OpenStreetMap
// attribution(출처 표시)은 OSM 이용 조건이라 지우면 안 됨
L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
	maxZoom: 19,
	attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

// 3. 목록의 장소마다 마커 만들기
const items = document.querySelectorAll(".place-item");

items.forEach(item => {
	const lat  = Number(item.getAttribute("data-lat"));
	const lng  = Number(item.getAttribute("data-lng"));
	const name = item.getAttribute("data-name");
	
	const url = item.dataset.url;

    // 팝업 내용을 HTML 요소로 직접 만듦
    const popup = document.createElement('div');
    const title = document.createElement('strong');
    title.textContent = name;
    const link = document.createElement('a');
    link.href = url;
    link.textContent = '상세보기';
    popup.append(title, document.createElement('br'), link);

    L.marker([lat, lng]).addTo(map).bindPopup(popup);
});