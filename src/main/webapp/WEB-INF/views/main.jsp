<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>군사학 여행 지도</title>
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css">
<link rel="stylesheet" href="${ctx}/resources/css/main.css">
</head>
<body>
	<div class="container">
		<aside class="sidebar">
			<h1>장소 목록</h1>
			<div class="filters">
				<input type="search" id="search" placeholder="장소 이름 검색">

				<div class="country-buttons">
					<button type="button" class="country-btn active" data-country="ALL">전체</button>
					<button type="button" class="country-btn" data-country="KR">KR</button>
					<button type="button" class="country-btn" data-country="JP">JP</button>
				</div>

				<select id="category">
					<option value="ALL">전체 분류</option>
					<option value="MUSEUM">박물관</option>
					<option value="BATTLEFIELD">전적지</option>
					<option value="MEMORIAL">기념관</option>
					<option value="SHIP">함선</option>
				</select>
			</div>
            
            <p>결과 <span id="result-count">${places.size()}</span>곳</p>
            
			<ul class="place-list">
				<c:forEach var="place" items="${places}">
					<li class="place-item"
						data-lat="${place.latitude}"
						data-lng="${place.longitude}"
						data-name="<c:out value='${place.name}' />"
						data-country="${place.countryCode}"
						data-category="${place.category}"
						data-url="${ctx}/place/detail?id=${place.placeId}">
						<a href="${ctx}/place/detail?id=${place.placeId}">
                            <c:out value="${place.name}" />
                        </a>
                        (${place.countryCode}, ${place.category})
                    </li>
                </c:forEach>
            </ul>
        </aside>
        <div id="map"></div>
    </div>

    <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
    <script src="${ctx}/resources/js/main.js"></script>
</body>
</html>