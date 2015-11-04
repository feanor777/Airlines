<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/page_settings.jspf"%>
<%@ include file="/WEB-INF/jspf/tagslib.jspf"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="style/head.css" />
<link type="text/css" rel="stylesheet" href="style/common.css" />
</head>
<jsp:useBean id="prof" scope="page"
	class="ua.nure.sharov.Airlines.db.Profession" />

<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<p>Please choose next staffers for your team and after that choose
		flight!</p>
	<p>P.S. Typical team - 2 pilots, 1 navigator, 1 operator, 3 flight
		attendants.</p>
	<form action="controller" method="post">
		<input type="hidden" name="command" value="createTeam">
		<table class="withBorder">
			<thead>
				<tr>
					<td>№</td>
					<td>First name</td>
					<td>Last name</td>
					<td>profession</td>
					<td>choose</td>
				</tr>
			</thead>

			<c:set var="i" value="0" />
			<c:forEach items="${freeStaffers}" var="staff">
				<c:set var="i" value="${i + 1}" />
				<tr>
					<td>${i}</td>
					<td>${staff.first_name}</td>
					<td>${staff.last_name }</td>
					<td>${prof.getProfession(staff)}</td>
					<td class="textCenter"><input type="checkbox" name="staffId"
						value="${staff.id}"></td>
				</tr>
			</c:forEach>
		</table>
		<select id="flightSelect" name="flightId">
			<c:forEach items="${flightsWithoutTeam}" var="flight">
				<option value="${flight.id}">${flight}</option>
			</c:forEach>
		</select>
		<div>
			<input class="button" type="submit" value="Create team">
		</div>
	</form>
</body>
</html>