<%@ page pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.jsp.jstl.core.Config"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/WEB-INF/jspf/page_settings.jspf"%>
<%@ include file="/WEB-INF/jspf/tagslib.jspf"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="style/main.css">
</head>

<jsp:useBean id="now" scope="page" class="org.joda.time.LocalDate" />

<body>

	<div class="left_logo">
		<p>airlines.ua</p>
	</div>
	<div class="nav">
		<%@ include file="/WEB-INF/jspf/header.jspf"%>
		<form>
			<a href="settings.jsp"><fmt:message key="index_jsp.link.settings" /></a>
		</form>
	</div>

	<div class="jumbotron">
		<div class="container">
			<h1>
				<fmt:message key="index_jsp.label.title" />
			</h1>
			<h3>
				<fmt:message key="index_jsp.label.sign" />
			</h3>

			<div class="forms">
				<div class="form_find_flights">
					<form action="controller" method="post">
						<input type="hidden" name="command" value="find_flights" />
						<fmt:message key="index_jsp.table.from" />
						<br> <input type="text" name="from" required /> <br>
						<fmt:message key="index_jsp.table.to" />
						<br> <input type="text"  name="to" required> <br>
						<span id="lightFont"><fmt:message key="index_jsp.table.date" /></span>
						<br> <input type="date" name="date" min="${now}" required>
						<input type="submit" value="<fmt:message key="index_jsp.key"/>"
							class="btn">
					</form>
				</div>

				<div class="form_find_flights_number">
					<h2>
						<fmt:message key="index_jsp.form.label" />
					</h2>
					<form action="controller" method="post">
						<input type="hidden" name="command" value="find_flight_number" />
						<input type="text" name="number" required pattern="[0-9]+$" />
						<input type="submit" value="<fmt:message key="index_jsp.key"/>"
							class="btn" />
					</form>
				</div>
			</div>
		</div>
		<%@ include file="/WEB-INF/jspf/top5flights.jspf"%>
	</div>
</body>
</html>