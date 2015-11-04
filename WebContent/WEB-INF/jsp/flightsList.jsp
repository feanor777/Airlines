<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/page_settings.jspf"%>
<%@ include file="/WEB-INF/jspf/tagslib.jspf"%>

<jsp:useBean id="usd" scope="page"
	class="ua.nure.sharov.Airlines.currency.Currency" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="style/head.css" />
<link type="text/css" rel="stylesheet" href="style/common.css" />
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<c:choose>
		<c:when test="${fn:length(flights) > 0 }">
			<form action="controller">
				<input type="hidden" name="command" value="sort">
				<p>
					<fmt:message key="flightListNumber_jsp.sort" />
				</p>
				<input type=submit name="order"
					value="<fmt:message key="flightListNumber_jsp.table.number" />" />
					<input
					type=submit name="order" value="<fmt:message key="flightListNumber_jsp.table.price" />" />
			</form>
			<table class="withBorder">
				<thead>
					<tr>
						<td>№</td>
						<td><fmt:message key="flightListNumber_jsp.table.number" /></td>
						<td><fmt:message key="index_jsp.table.from" /></td>
						<td><fmt:message key="index_jsp.table.to" /></td>
						<td><fmt:message key="index_jsp.table.date" /></td>
						<td><fmt:message key="flightListNumber_jsp.table.price" /></td>
					</tr>
				</thead>

				<c:set var="i" value="0" />
				<c:forEach var="item" items="${flights}">
					<c:set var="i" value="${i+1}" />
					<tr>
						<td><c:out value="${i}" /></td>
						<td>${item.number}</td>
						<td>${item.from}</td>
						<td>${item.to}</td>
						<td>${item.date_of_departure}</td>
						<td><c:choose>
								<c:when test="${currentLocale eq 'en'}">
									<fmt:setLocale value="en_US" />
									<fmt:formatNumber type="currency"
										value="${item.price / usd.getUSDCurrency()}" />
									<br />
								</c:when>
								<c:otherwise>
									<fmt:setLocale value="uk_UA" />
									<fmt:formatNumber type="currency" value="${item.price}" />
									<fmt:setLocale value="ru_RU" />
								</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<p>
				<fmt:message key="flightList_jsp.notFind" />
			</p>
		</c:otherwise>
	</c:choose>
	<form action="WelcomeServlet">
		<input type="submit"
			value="<fmt:message key='flightListNumber_jsp.button.menu'/>">
	</form>
</body>
</html>