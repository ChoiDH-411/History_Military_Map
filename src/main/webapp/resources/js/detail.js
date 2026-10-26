/* id="detail-map"인 요소를 찾기
그 요소의 data-lat, data-lng를 숫자로 읽기
그 좌표를 중심으로 지도 만들기 (확대 수준 15)
그 좌표에 마커 1개 찍기 (팝업 없이)
*/
const detailMapElement = document.getElementById("detail-map");

if (detailMapElement) {
    const lat = parseFloat(detailMapElement.getAttribute("data-lat"));
    const lng = parseFloat(detailMapElement.getAttribute("data-lng"));

    if (!isNaN(lat) && !isNaN(lng)) {
        const map = L.map('detail-map').setView([lat, lng], 15);   // 변수에 저장

        L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 19,
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(map);

        L.marker([lat, lng]).addTo(map);                           // 저장한 map에 추가
    }
}
