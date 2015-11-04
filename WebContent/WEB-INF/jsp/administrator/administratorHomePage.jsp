<%@ page pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/page_settings.jspf"%>
<%@ include file="/WEB-INF/jspf/tagslib.jspf"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="style/head.css" />
<link href="style/bootstrap-3.3.2-dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<%@ include file="/WEB-INF/jspf/header.jspf" %>
	<ul class="nav nav-tabs">
		<li><a data-toggle="tab" href="controller?command=showFlightsPage" class="active">Change flights</a></li>
		<li><a data-toggle="tab" href="controller?command=showStaffPage">Change staff</a></li>
		<li><a data-toggle="tab" href="controller?command=showApplicationPage">Application</a></li>
	</ul>
	<div class="tab-content">
		<div id="panel1" class="tab-pane fade in active"></div>
		<div id="panel2" class="tab-pane fade"></div>
		<div id="panel3" class="tab-pane fade"></div>
	</div>
</body>
</html>