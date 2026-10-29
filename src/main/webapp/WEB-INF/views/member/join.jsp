<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/init.jspf" %>
<!DOCTYPE html>
<html lang="${lang}">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><fmt:message key="join.title" /> - <fmt:message key="site.title" /></title>
<link rel="stylesheet" href="${ctx}/resources/css/common.css">
</head>
<body>
<%@ include file="/WEB-INF/views/common/header.jspf" %>

<main class="form-page">
    <h1><fmt:message key="join.title" /></h1>

    <!-- 오류가 있으면 표시 -->
    <c:if test="${not empty error}">
        <p class="form-error"><fmt:message key="${error}" /></p>
    </c:if>

    <form action="${ctx}/member/join" method="post" class="form">
        <label>
            <fmt:message key="member.loginId" />
            <input type="text" name="loginId" value="<c:out value='${loginId}' />"
                   maxlength="20" required>
            <small><fmt:message key="join.hint.loginId" /></small>
        </label>

        <label>
            <fmt:message key="member.password" />
            <input type="password" name="password" required>
            <small><fmt:message key="join.hint.password" /></small>
        </label>

        <label>
            <fmt:message key="member.passwordConfirm" />
            <input type="password" name="passwordConfirm" required>
        </label>

        <label>
            <fmt:message key="member.nickname" />
            <input type="text" name="nickname" value="<c:out value='${nickname}' />"
                   maxlength="20" required>
        </label>

        <fieldset>
            <legend><fmt:message key="member.lang" /></legend>
            <label class="inline">
                <input type="radio" name="lang" value="ko" ${empty joinLang or joinLang == 'ko' ? 'checked' : ''}> 한국어
            </label>
            <label class="inline">
                <input type="radio" name="lang" value="ja" ${joinLang == 'ja' ? 'checked' : ''}> 日本語
            </label>
        </fieldset>

        <button type="submit"><fmt:message key="join.submit" /></button>
    </form>
</main>
</body>
</html>