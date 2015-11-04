<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/page_settings.jspf"%>
<%@ include file="/WEB-INF/jspf/tagslib.jspf"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="style/head.css" />
<link type="text/css" rel="stylesheet" href="style/common.css" />
</head>
<jsp:useBean id="profession" scope="page"
	class="ua.nure.sharov.Airlines.db.Profession" />

<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<c:choose>
		<c:when test="${fn:length(changeStaffList) > 0 }">
			<form action="controller">
				<table class="withBorder">
					<thead>
						<tr>
							<th>№</th>
							<th>first name</th>
							<th>last name</th>
							<th>profession</th>
							<th>choose</th>
						</tr>
					</thead>

					<c:set var="i" value="0" />
					<c:forEach var="staff" items="${changeStaffList}">
						<c:set var="i" value="${i + 1}" />
						<tr>
							<td>${i}</td>
							<td>${staff.first_name}</td>
							<td>${staff.last_name}</td>
							<td>${profession.getProfession(staff)}</td>
							<td class="textCenter"><input type="checkbox" name="checks"
								value="${staff.id}"></td>
						</tr>
					</c:forEach>
				</table>
				<button type="submit" name="command" value="showStaffPage">Refresh</button>
				<button type="submit" name="command" value="editStaffPage">Edit</button>
				<button type="submit" name="command" value="deleteStaff">Delete</button>
			</form>
		</c:when>
		<c:otherwise>
			<p>There is no any staff</p>
		</c:otherwise>
	</c:choose>
</body>
</html>