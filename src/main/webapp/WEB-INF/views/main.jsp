<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>군사학적 여행 지도</title>
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css">
<link rel="stylesheet" href="${ctx}/resources/css/main.css">
</head>
<body>
    <div class="container">
        <aside class="sidebar">
            <h1>장소 목록</h1>
            <p>결과 ${places.size()}곳</p>
            <ul class="place-list">
                <c:forEach var="place" items="${places}">
                    <li class="place-item"
                        data-lat="${place.latitude}"
                        data-lng="${place.longitude}"
                        data-name="<c:out value='${place.name}' />">
                        <c:out value="${place.name}" />
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