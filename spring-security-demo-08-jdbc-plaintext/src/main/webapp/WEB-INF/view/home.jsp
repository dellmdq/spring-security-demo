<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
	<title>luv2code Company Home Page</title>
</head>

<body>
	<h2>luv2code Company Home Page</h2>
	<hr>
	
	<p>
	Welcome to the luv2code company home page!!
	</p>
	
	<hr>
	
	<!-- display user name and role -->
	<p>
		User: <security:authentication property="principal.username"/>	
		<br><br>
		Role(s): <security:authentication property="principal.authorities"/>
	</p>

	<security:authorize access="hasRole('MANAGER')">
	<!-- content for leaders only -->
		<p>
			<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
			(Only for manager peeps)
		</p>
	</security:authorize>

	<security:authorize access="hasRole('ADMIN')">
	
	<!-- add link to point to /systems... this is for admins -->
		<p>
			<a href="${pageContext.request.contextPath}/admins">Admins Meeting</a>
			(Only for admin peeps)
		</p>
		
	</security:authorize>
	<hr>
	
	
	<!-- add logout button -->
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout"/>	
	</form:form>
</body>

</html>