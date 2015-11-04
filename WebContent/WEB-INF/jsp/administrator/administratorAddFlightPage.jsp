<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/page_settings.jspf"%>
<%@ include file="/WEB-INF/jspf/tagslib.jspf"%>

<jsp:useBean id="now" scope="page" class="org.joda.time.LocalDate"/>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="style/head.css" />
	<link type="text/css" rel="stylesheet" href="style/common.css" />
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf" %>
	<form action="controller">
		<fieldset>
			<legend>New flight</legend>
			<input type="hidden" name="command" value="addFlight">
			<table>
				<tr>
					<th>Number</th>
					<td><input type="text" name="number" required pattern="[0-9]+$"></td>
				</tr>
				<tr>
					<th>From(city)</th>
					<td><input type="text" name="from" required
						pattern="[A-Za-zА-Яа-яЁё]+"></td>
				</tr>
				<tr>
					<th>To(city)</th>
					<td><input type="text" name="to" required
						pattern="[A-Za-zА-Яа-яЁё]+"></td>
				</tr>
				<tr>
					<th>Price</th>
					<td><input type="text" name="price" required
						pattern="^[1-9]+[0-9]{0,}$"></td>
				</tr>
				<tr>
					<th>Date of departure</th>
					<td><input type="date" name="date" required min="${now}"></td>
				</tr>
	
			</table>
			<input type="submit" value="Save" class="button">
			<input type="button" value="Back" onclick="history.back()" class="button">
		</fieldset>
	</form>
</body>
</html>