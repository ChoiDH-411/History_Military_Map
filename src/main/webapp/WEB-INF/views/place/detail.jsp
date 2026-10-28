<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/init.jspf" %>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><c:out value="${place.name}" /> - <fmt:message key="site.title" /></title>
<link rel="stylesheet" href="${ctx}/resources/css/common.css">
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.4/dist/leaflet.css">
<link rel="stylesheet" href="${ctx}/resources/css/detail.css">
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jspf" %>
    <div class="detail">
        <a href="${ctx}/" class="back"><fmt:message key="detail.back" /></a>

        <div class="detail-top">
            <!-- 사진: 있으면 이미지, 없으면 안내 문구 -->
            <div class="photo">
                <c:choose>
                    <c:when test="${not empty place.imageFile}">
                        <img src="${ctx}/resources/images/places/${place.imageFile}"
                             alt="<c:out value='${place.name}' />">
                    </c:when>
                    <c:otherwise>
                        <div class="no-photo"><fmt:message key="detail.noPhoto" /></div>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="info">
                <h1><c:out value="${place.name}" /></h1>
				<p class="meta">
					<fmt:message key="country.${place.countryCode}" /> ·
					<fmt:message key="category.${place.category}" />
				</p>
                <p class="address"><c:out value="${place.address}" /></p>
                <div id="detail-map"
                     data-lat="${place.latitude}"
                     data-lng="${place.longitude}"></div>
            </div>
        </div>

        <p class="description"><c:out value="${place.description}" /></p>
    </div>

    <script src="https://unpkg.com/leaflet@1.9.4/dist/leaflet.js"></script>
    <script src="${ctx}/resources/js/detail.js"></script>
</body>
</html>