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
	<c:choose>
		<c:when test="${fn:length(teamStaffBeanList) > 0}">
			<table class="withBorder">
				<thead>
					<tr>
						<th>№</th>
						<th>team number</th>
						<th>flight number</th>
						<th>from</th>
						<th>to</th>
						<th>staff</th>
						<th>date of departure</th>
						<th>dispatcher last name</th>
					</tr>
				</thead>

				<c:set var="i" value="0" />
				<c:forEach var="teamStaffBean" items="${teamStaffBeanList}">
					<c:set var="i" value="${i + 1}" />
					<tr>
						<td>${i}</td>
						<td>${teamStaffBean.teamId}</td>
						<td>${teamStaffBean.flightsNumber}
						<td>${teamStaffBean.from}
						<td>${teamStaffBean.to}
						<td><c:forEach var="staff" items="${teamStaffBean.staffList}">
								${staff.first_name}
								${staff.last_name}:
								${prof.getProfession(staff)}
								<br>
							</c:forEach></td>
						<td>${teamStaffBean.dateOfDeparture}</td>
						<td>${teamStaffBean.dispatcherLastName}</td>
					</tr>
				</c:forEach>

			</table>
		</c:when>
		<c:otherwise>
			<p>There is no any teams</p>
		</c:otherwise>
	</c:choose>
</body>
</html>