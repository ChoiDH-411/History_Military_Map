// ===== 1. 지도 =====
const map = L.map('map').setView([36.0, 132.0], 5);

L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
    maxZoom: 19,
    attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

// 국가 버튼별 지도 위치: [위도, 경도, 확대 수준]
const VIEWS = {
    ALL: [36.0, 132.0, 5],
    KR: [36.3, 127.8, 7],
    JP: [36.2, 138.0, 5]
};

// ===== 2. 마커 만들기 (목록 항목과 같은 순서로 저장) =====
const items = document.querySelectorAll('.place-item');
const markers = [];   // 마커 번호

items.forEach(item => {
    const lat = Number(item.dataset.lat);
    const lng = Number(item.dataset.lng);
    const name = item.dataset.name;
    const url = item.dataset.url;

    const popup = document.createElement('div');
    const title = document.createElement('strong');
    title.textContent = name;
    const link = document.createElement('a');
    link.href = url;
    link.textContent = document.getElementById('map').dataset.detailLabel;
    popup.append(title, document.createElement('br'), link);

    const marker = L.marker([lat, lng]).addTo(map).bindPopup(popup);
    markers.push(marker);
	item.addEventListener('mouseenter', () => marker.openPopup());
	item.addEventListener('mouseleave', () => marker.closePopup());
	
	marker.on('click', () => {
	    items.forEach(other => other.classList.remove('selected'));       // ① 모두에서 빼기
	    item.classList.add('selected');                                    // ② 이 항목만 추가
	    item.scrollIntoView({ behavior: 'smooth', block: 'center' });      // ③ 그 위치로 스크롤
	});
});

// ===== 3. 필터에 쓰는 요소들 =====
let selectedCountry = 'ALL';   // 현재 선택된 국가 버튼
const searchInput = document.getElementById('search');
const categorySelect = document.getElementById('category');
const countEl = document.getElementById('result-count');

// ===== 4. 필터 적용 =====
function applyFilter() {
    const keyword = searchInput.value.trim().toLowerCase();   // 검색어 (앞뒤 공백 제거)
    const category = categorySelect.value;                     // 선택된 분류
    let count = 0;

    items.forEach((item, i) => {
        // 조건 3개: 모두 true여야 표시
        const matchCountry = selectedCountry === 'ALL' || item.dataset.country === selectedCountry;
        const matchCategory = category === 'ALL' || item.dataset.category === category;
        const matchKeyword = item.dataset.name.toLowerCase().includes(keyword);
        const visible = matchCountry && matchCategory && matchKeyword;

        item.classList.toggle('hidden', !visible);   // visible이 false면 hidden 클래스 추가

        if (visible) {
            markers[i].addTo(map);   // 지도에 마커 표시
            count++;
        } else {
            markers[i].remove();     // 지도에서 마커 제거
        }
    });

    countEl.textContent = count;
}

// ===== 5. 이벤트 연결 =====
// 국가 버튼
document.querySelectorAll('.country-btn').forEach(btn => {
    btn.addEventListener('click', () => {
        // 모든 버튼에서 active를 빼고, 누른 버튼에만 active
        document.querySelectorAll('.country-btn').forEach(b => b.classList.remove('active'));
        btn.classList.add('active');

        selectedCountry = btn.dataset.country;

        const [lat, lng, zoom] = VIEWS[selectedCountry];   // 배열 구조 분해
        map.setView([lat, lng], zoom);
		
		btn.classList.add('active');

        applyFilter();
    });
});

// 분류 select 값이 바뀌면 applyFilter 실행
categorySelect.addEventListener('change', applyFilter);
// 검색창에 글자를 입력할 때마다 applyFilter 실행
searchInput.addEventListener('input', applyFilter);
