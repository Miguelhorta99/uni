<%@ page language="java" session="true" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
</head>
<body>
	<div align="center">
		<h1>${title} at registration</h1>
		<h2>${message}, at registration</h2>                
                <p><a href="<c:url value='login'/>">Login</a></p>
                <p><%=(new java.util.Date()).toString()%>  </p>
	</div>
</body>
</html>