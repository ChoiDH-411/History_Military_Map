<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/init.jspf" %>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><fmt:message key="site.title" /></title>
<link rel="stylesheet" href="${ctx}/resources/css/common.css">
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css">
<link rel="stylesheet" href="${ctx}/resources/css/main.css">
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
	<div class="container">
		<aside class="sidebar">
			<h1><fmt:message key="main.heading" /></h1>
			<div class="filters">
				<input type="search" id="search" placeholder="<fmt:message key='main.search' />">

				<div class="country-buttons">
					<button type="button" class="country-btn active" data-country="ALL"><fmt:message key="filter.all" /></button>
					<button type="button" class="country-btn" data-country="KR"><fmt:message key="country.KR" /></button>
					<button type="button" class="country-btn" data-country="JP"><fmt:message key="country.JP" /></button>
				</div>

				<select id="category">
					<option value="ALL"><fmt:message key="filter.category.all" /></option>
					<option value="MUSEUM"><fmt:message key="category.MUSEUM" /></option>
					<option value="BATTLEFIELD"><fmt:message key="category.BATTLEFIELD" /></option>
					<option value="MEMORIAL"><fmt:message key="category.MEMORIAL" /></option>
					<option value="SHIP"><fmt:message key="category.SHIP" /></option>
				</select>
			</div>
            
			<p>
				<fmt:message key="main.result">
				<fmt:param><span id="result-count">${places.size()}</span></fmt:param>
				</fmt:message>
			</p>
            
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
                        (<fmt:message key="country.${place.countryCode}" />,
 							<fmt:message key="category.${place.category}" />)
                    </li>
                </c:forEach>
            </ul>
        </aside>
                <div id="map" data-detail-label="<fmt:message key='popup.detail' />"></div>
    </div>
    <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
    <script src="${ctx}/resources/js/main.js"></script>
</body>
</html>