<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Data</title>
</head>
<body>
<h2>Name : ${names}</h2><hr/>
	<c:forEach items="${names}" varStatus="status" var="name">
		${status.index} ${name}<br/>
	</c:forEach>
<h2>Fruits : ${fruits}</h2>
	<c:forEach items="${fruits}" varStatus="status" var="fruit">
		${status.index} ${fruit} ${fruit.key} ${fruit.value}<br/>
	</c:forEach>
</body>
</html>