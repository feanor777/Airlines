<%@page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/page_settings.jspf"%>
<%@ include file="/WEB-INF/jspf/tagslib.jspf"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="style/head.css" />
	<link type="text/css" rel="stylesheet" href="style/common.css" />
</head>
<jsp:useBean id="flightStatus" scope="page"
	class="ua.nure.sharov.Airlines.db.FlightStatus" />

<body>
	<%@ include file="/WEB-INF/jspf/header.jspf" %>
	<form action="controller" method="post">
		<input type="hidden" name="command" value="setFlightStatus">
		<input type="hidden" name="flightId" value="${flightEditStatus.id}">
		<table>
			<tr>
				<th>Number</th>
				<td>${flightEditStatus.number}</td>
			</tr>
			<tr>
				<th>From</th>
				<td>${flightEditStatus.from}</td>
			</tr>
			<tr>
				<th>To</th>
				<td>${flightEditStatus.to}</td>
			</tr>
			<tr>
				<th>Date of departure</th>
				<td>${flightEditStatus.date_of_departure}</td>
			</tr>
			<tr>
				<th>Price</th>
				<td>${flightEditStatus.price}</td>
			</tr>
			<tr>
				<th>Flight status</th>
				<td>${flightStatus.getFlightStatusById(flightEditStatus.flight_status_id)}</td>
			</tr>
			<tr>
				<th><select name="flightStatusId">
						<c:forEach items="${flightStatusList}" var="statusId">
							<option value="${statusId}">${flightStatus.getFlightStatusById(statusId)}</option>
						</c:forEach>
				</select></th>
				<td><input type="submit" value="Set flight status"></td>
			</tr>
		</table>
	</form>
	<input type="button" class="button" value="Back" onclick="history.back()">
</body>
</html>