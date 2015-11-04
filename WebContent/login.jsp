<%@ include file="/WEB-INF/jspf/page_settings.jspf"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="style/login.css"/>
</head>
<body>
	<div id="airlines_logo">
		<a href="WelcomeServlet"> <img src="style/images/airlines.png">
		</a>
		<hr>
	</div>
	<table>
		<tr>
			<th>Sign in</th>
		</tr>
		<tr>
			<td>
				<form action="controller" method="post">
					<input type="hidden" name="command" value="login" />
					<fieldset>
						<legend>Login</legend>
						<input name="login" required pattern="[A-Za-z]{5,20})" />
					</fieldset>
					<fieldset>
						<legend>Password</legend>
						<input type="password" name="password" required
							pattern="[A-Za-z\d]{5,20})" />
					</fieldset>
					<input id="send_button" type="submit" value="Login">
				</form>
			</td>
		</tr>
	</table>
</body>
</html>