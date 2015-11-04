<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/page_settings.jspf"%>
<%@ include file="/WEB-INF/jspf/tagslib.jspf"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="style/head.css" />
	<link type="text/css" rel="stylesheet" href="style/common.css" />
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<c:choose>
		<c:when test="${fn:length(applicationList) > 0 }">
			<form action="controller">
				<input type="hidden" name="command" value="resolveApplication">
				<table class="withBorder">
					<thead>
						<tr>
							<th>№</th>
							<th>problem description</th>
							<th>team id</th>
							<th>choose</th>
						</tr>
					</thead>

					<c:set var="i" value="0" />
					<c:forEach var="application" items="${applicationList}">
						<c:set var="i" value="${i + 1}" />
						<tr>
							<td>${i}</td>
							<td>${application.problem_description}</td>
							<td>
								<c:if test="${application.team_id != 0}">
									${application.team_id}
								</c:if>
							</td>
							<td><input type="radio" name="choose"
								value="${application.id}" checked="checked"></td>
						</tr>
					</c:forEach>
				</table>
				<p>Solution:</p>
				<textarea name="solution" cols="30" rows="5" required></textarea>
				<div>
					<button type="submit" class="button" name="applicationStatus" value="0">Perform</button>
					<button type="submit" class="button" name="applicationStatus" value="1">Deny</button>
				</div>
			</form>
		</c:when>
		<c:otherwise>
			<p>There is no any application</p>
		</c:otherwise>
	</c:choose>
</body>
</html>