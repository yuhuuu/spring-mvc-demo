<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Teacher Form</title>
	</head>
	<body>
		<form:form action="processForm" modelAttribute="teacher">
			Name : <form:input path="name" />
			<br/>
			Subject : 
			<form:select path="subject">
				<form:options items="${teacher.subjectOptions}"/>
			</form:select>
			

			<br/>
			<input type="submit" value="Submit"/>
		</form:form>
	</body>
</html>