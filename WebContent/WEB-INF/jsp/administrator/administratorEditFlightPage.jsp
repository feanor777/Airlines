<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/page_settings.jspf"%>
<%@ include file="/WEB-INF/jspf/tagslib.jspf"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<link type="text/css" rel="stylesheet" href="style/head.css" />
	<link type="text/css" rel="stylesheet" href="style/common.css" />
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<form action="controller" method="post">
		<table>
			<tr>
				<th>Number</th>
				<td><input type="text" name="number"
					value="${changeFlight.number}" required pattern="[0-9]+$">
				</td>
			</tr>
			<tr>
				<th>From(city)</th>
				<td><input type="text" name="from" value="${changeFlight.from}"
					required pattern="[A-Za-zА-Яа-яЁё]+"></td>
			</tr>
			<tr>
				<th>To(city)</th>
				<td><input type="text" name="to" value="${changeFlight.to}"
					required pattern="[A-Za-zА-Яа-яЁё]+"></td>
			</tr>
			<tr>
				<th>Date of departure</th>
				<td><input type="date" name="date"
					value="${changeFlight.date_of_departure}" required min="${now}">
				</td>
			</tr>
			<tr>
				<th>Price</th>
				<td><input type="text" name="price"
					value="${changeFlight.price}" required pattern="^[1-9]+[0-9]{0,}$">
				</td>
			</tr>
		</table>
		<input type="hidden" name="command" value="editFlight">
		<input type="hidden" name="flightId" value="${changeFlight.id}"> 
		<input type="submit" value="edit flight">
		<input type="button" value="back" onclick="history.back()">
	</form>
</body>
</html>