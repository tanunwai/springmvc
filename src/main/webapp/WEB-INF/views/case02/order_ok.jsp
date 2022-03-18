<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Save Order OK</title>
</head>
<body>
Name :<h2>${requestScope.name}</h2>
Price :<h2>${sessionScope.price}</h2>
Quantity :<h2>${applicationScope.qty}</h2>
</body>
</html>