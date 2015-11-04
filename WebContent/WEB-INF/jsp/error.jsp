<%@ page isErrorPage="true"%>
<%@ page import="java.io.PrintWriter"%>
<%@ include file="/WEB-INF/jspf/page_settings.jspf"%>
<%@ include file="/WEB-INF/jspf/tagslib.jspf"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="style/head.css" />
	<link type="text/css" rel="stylesheet" href="style/common.css" />
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<h2 class="error">The following error occurred</h2>

	<c:set var="code"
		value="${requestScope['javax.servlet.error.status_code']}" />
	<c:set var="message"
		value="${requestScope['javax.servlet.error.message']}" />
	<c:set var="exception"
		value="${requestScope['javax.servlet.error.exception']}" />
	<c:if test="${not empty code}">
		<h3>Error code: ${code}</h3>
	</c:if>
	<c:if test="${not empty message}">
		<h3>${message}</h3>
	</c:if>
	<c:if test="${not empty exception}">
		<%
			exception.printStackTrace(new PrintWriter(out));
		%>
	</c:if>

	<c:if test="${not empty requestScope.errorMessage}">
		<h3>${requestScope.errorMessage}</h3>
	</c:if>
	<input type="button" value="back" onclick="history.back()">
</body>
</html>