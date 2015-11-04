<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/page_settings.jspf"%>
<%@ include file="/WEB-INF/jspf/tagslib.jspf"%>

<jsp:useBean id="prof" scope="page"
	class="ua.nure.sharov.Airlines.db.Profession" />

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="style/head.css" />
	<link type="text/css" rel="stylesheet" href="style/common.css" />
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf" %>
	<form action="controller">
		<input type="hidden" name="command" value="addStaff">
		<input type="text" name="first_name" required pattern="[A-Za-zА-Яа-яЁё]+">First name<br>
		<input type="text" name="last_name" required pattern="[A-Za-zА-Яа-яЁё]+">Last name<br>
		<select name="profession">
			<c:forEach begin="0" end="3" varStatus="loop">
				<option value="${loop.index}">${prof.getProfession(loop.index)}</option>
			</c:forEach>
		</select> <br> 
		<input type="submit" value="save">
		<input type="button" value="back" onclick="history.back()">
	</form>
</body>
</html>