<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/page_settings.jspf"%>
<%@ include file="/WEB-INF/jspf/tagslib.jspf"%>

<jsp:useBean id="now" scope="page" class="org.joda.time.LocalDate" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="style/head.css" />
<link type="text/css" rel="stylesheet" href="style/common.css" />
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<form action="controller">
		<input type="hidden" name="command" value="createApplication">
		<table>
			<tr>
				<th>Problem description:</th>
				<th><textarea rows="10" cols="50" name="description" required></textarea></th>
			</tr>
			<tr>
				<td colspan="2">Flight details:</td>
			</tr>
			<tr>
				<td colspan="2"><select name="teamId">
						<c:forEach items="${tfbList}" var="teamFlightBean">
							<option value="${teamFlightBean.teamId}">${teamFlightBean}</option>
						</c:forEach>
						<option value="-1">Don't have enough staff to create new
							team</option>
				</select></td>
			</tr>
		</table>
		<input class="button" type="submit" value="Send application">
	</form>
</body>
</html>