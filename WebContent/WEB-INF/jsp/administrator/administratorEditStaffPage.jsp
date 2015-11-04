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
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<form action="controller" method="post">
		<input type="hidden" name="staffId" value="${changeStaff.id}"> 
	<table>
		<tr>
			<th>First name</th>
			<td><input type="text" name="first_name" value="${changeStaff.first_name}" required pattern="[A-Za-zА-Яа-яЁё]+"></td>
		</tr>
		<tr>
			<th>Last name</th>
			<td><input type="text" name="last_name" value="${changeStaff.last_name}" required pattern="[A-Za-zА-Яа-яЁё]+"></td>
		</tr>
		<tr>
			<th>Profession</th>
			<td>
				<select name="profession">
					<c:forEach begin="0" end="3" varStatus="loop">
						<option value="${loop.index}">${prof.getProfessionById(loop.index)}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
	</table>
	<c:if test="${not empty changeStaff}">
		<button type="submit" name="command" value="editStaff" class="button">Save</button>
	</c:if>
	<c:if test="${empty changeStaff}">
		<button type="submit" name="command" value="addStaff" class="button">Add</button>
	</c:if>
	<input type="button" value="Cancel" class="button" onclick="window.location.replace('http://127.0.0.1:8080/Airlines/controller?command=showStaffPage')">
		
</form>
</body>
</html>