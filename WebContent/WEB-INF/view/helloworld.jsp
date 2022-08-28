<!DOCTYPE html>
<%@ page isELIgnored="false" %>
<html>
	<head>
		<title>Hello World</title>
	 	<script src="${pageContext.request.contextPath}/resources/js/simple-test.js"></script>
	</head>
	<body>
		
		<img src="${pageContext.request.contextPath}/resources/images/spring-logo.png" />
		Hello World of Spring!
		<br><br>
		Student name : ${param.studentName }
		<br><br>
		The message : ${message };
	</body>
</html>