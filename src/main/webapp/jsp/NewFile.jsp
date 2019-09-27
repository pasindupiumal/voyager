<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored ="false" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="../css/RouteManagement.css" type="text/css">
<title>Insert title here</title>
</head>
<body style="background-color:black">
	<c:forEach var="route" items="${routeList}">
		routeID ${route.routeID} <br/>
		routeName ${route.routeName} <br/>
	</c:forEach>
</body>
</html>