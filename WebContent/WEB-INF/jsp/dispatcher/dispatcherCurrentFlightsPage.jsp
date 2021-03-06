<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/page_settings.jspf"%>
<%@ include file="/WEB-INF/jspf/tagslib.jspf"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="style/head.css" />
	<link type="text/css" rel="stylesheet" href="style/withoutBorderBackground.css" />
</head>
<jsp:useBean id="status" scope="page"
	class="ua.nure.sharov.Airlines.db.FlightStatus" />

<body>
	<%@ include file="/WEB-INF/jspf/header.jspf" %>
	<c:choose>
		<c:when test="${fn:length(currentFlightsList) > 0 }">
			<table class="withBorder">
				<thead>
					<tr>
						<td>№</td>
						<td>flight number</td>
						<td>from</td>
						<td>to</td>
						<td>date of departure</td>
						<td>flight status</td>
					</tr>
				</thead>

				<c:set var="i" value="0" />
				<c:forEach var="flight" items="${currentFlightsList}">
					<c:set var="i" value="${i + 1}" />
					<tr>
						<td>${i}</td>
						<td>${flight.number}</td>
						<td class="textLeft">${flight.from}</td>
						<td class="textLeft">${flight.to}</td>
						<td>${flight.date_of_departure}</td>
						<td>${status.getFlightStatus(flight)}</td>
						<td><a href="controller?command=editFlightStatus&flightId=${flight.id}"><img
								src="style\images\button.jpg" /></a></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<p>There is no any flights</p>
		</c:otherwise>
	</c:choose>
</body>
</html>