<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/init.jspf" %>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><fmt:message key="login.title" /> - <fmt:message key="site.title" /></title>
<link rel="stylesheet" href="${ctx}/resources/css/common.css">
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jspf" %>

<main class="form-page">
    <h1><fmt:message key="login.title" /></h1>

    <c:if test="${not empty error}">
        <p class="form-error"><fmt:message key="${error}" /></p>
    </c:if>

    <form action="${ctx}/member/login" method="post" class="form">
        <label>
            <fmt:message key="member.loginId" />
            <input type="text" name="loginId" value="<c:out value='${loginId}' />" required>
        </label>
        <label>
            <fmt:message key="member.password" />
            <input type="password" name="password" required>
        </label>
        <button type="submit"><fmt:message key="login.submit" /></button>
    </form>

    <p class="form-footer">
        <fmt:message key="login.noAccount" />
        <a href="${ctx}/member/join"><fmt:message key="header.join" /></a>
    </p>
</main>
</body>
</html>