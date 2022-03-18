<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index</title>
</head>
<body>
<h1>Hello Index</h1>
<h2><%=new Date() %></h2>
<br>
<h2>Case01</h2>
<ol>
	<li>
		<a href="mvc/case01/hello/welcome">範例一</a>
	</li>
	<li>
		<a href="mvc/case01/hello/sayhi?who=Webster&age=30">範例二</a>
	</li>
	<li>
		<a href="mvc/case01/hello/bmi?h=170.0&w=60.0">範例三</a>
	</li>
	<li>
		<a href="mvc/case01/hello/exam/75">範例四</a>
	</li>
	<li>
		<a href="mvc/case01/hello//calc/add?x=30&y=20">範例五</a>
	</li>
	<li>
		<a href="mvc/case01/hello//calc/sub?x=30&y=20">範例五之一</a>
	</li>
	<li>
		<a href="mvc/case01/hello/nameTaipei/java7">範例六</a>
	</li>
	<li>
		<a href="mvc/case01/hello/age?age=18&age=19&age=20">範例七</a>
	</li>
	<li>
		<a href="mvc/case01/hello/javaexam?score=80&score=70&score=90&score=60">範例八</a>
	</li>
	<li>
		<a href="mvc/case01/hello/user?name=John&age=18">範例九</a>
	</li>
	<li>
		<a href="mvc/case01/hello/person?name=Mary&score=100&age=18&pass=true">範例十</a>
	</li>
	<li>
		<a href="mvc/case01/hello/sessioninfo">範例十一</a>
	</li>
</ol>
<br>
<h2>Case02</h2>
<ol>
	<li>
		<a href="mvc/case02/time/now">範例一</a>
	</li>
	<li>
		<a href="mvc/case02/arraydata/showdata/">範例二</a>
	</li>
	<li>
		<a href="mvc/case02/redirect/demo1">範例三</a>
	</li>
	<li>
		<a href="mvc/case02/redirect/demo2">範例四</a>
	</li>
	<li>
		<a href="mvc/case02/redirect/demo3">範例五</a>
	</li>
	<li>
		<a href="mvc/case02/redirect/demo4">範例六</a>
	</li>
	<li>
		<a href="mvc/case02/redirect/demo5">範例七</a>
	</li>
	<li>
		<a href="mvc/case02/redirect/saveorder?name=iPhone&price=25000&qty=5">範例八</a>
	</li>
	<li>
		<a href="mvc/case02/lotto/showlotto">範例九</a>
	</li>
</ol>
<br/>
<h2>Case03</h2>
<ol>
	<li><a href="mvc/case03/exam/">範例一</a></li>
</ol>
<h2>Case04</h2>
<ol>
	<li><a href="mvc/case04/person/">範例一</a></li>
	<li><a href="mvc/case04/stock/">範例二</a></li>
</ol>
<h2>Lab</h2>
<ol>
	<li><a href="mvc/lab/fund/">範例一</a></li>
	<li><a href="mvc/lab/fundstock/">範例二</a></li>	
	<li><a href="mvc/lab/fundstock/1">範例三</a></li>
	<li><a href="html/fund.html">範例四</a></li>	
</ol>
</body>
</html>