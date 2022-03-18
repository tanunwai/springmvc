<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://unpkg.com/purecss@2.0.6/build/pure-min.css">
<meta charset="UTF-8">
<title>Show Lottos</title>
</head>
<body style="padding: 15px">
<form class="pure-form" method="post" action="./add">
    <fieldset>
        <legend>539樂透大猜選</legend>        
        <button type="submit" class="pure-button pure-button-primary">電腦選號</button>
    </fieldset>
</form>
<table class="pure-table pure-table-horizontal">
    <thead>
        <tr>
            <th>Index</th>
            <th>Lottos</th>
            <th>updateLotto</th>
            <th>deleteLotto</th>
        </tr>
    </thead>
    <tbody>
    	<c:forEach items="${lottos}" varStatus="status" var="lotto">
	        <tr>
	            <td>${status.index}</td>
	            <td>${lotto}</td>
	            <td>
	            	<button type="submit" onclick="window.location.href='./update/${status.index}'" class="pure-button pure-button-primary">修改</button>
	            </td>
	            <td>
	            	<button type="submit" onclick="window.location.href='./remove/${status.index}'" class="pure-button pure-button-primary">刪除</button>
	            </td>
	        </tr>
        </c:forEach>
    </tbody>
</table>
<form class="pure-form" method="post" action="./statistics">
    <fieldset>
        <legend>樂透號碼統計</legend>        
        <button type="submit" class="pure-button pure-button-primary">統計</button>
    </fieldset>
</form>
<table class="pure-table pure-table-horizontal">
    <thead>
        <tr>
            <th>Number</th>
            <th>Count</th>
        </tr>
    </thead>
    <tbody>
    	<c:forEach items="${numerical}" varStatus="status" var="stat">
	        <tr>
	            <td>${stat.key}</td>
	            <td>${stat.value}</td>           
	        </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>