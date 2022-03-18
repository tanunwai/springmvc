<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spform" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="include/header2.jspf" %>
</head>
<body class="fundstockbody">
<br/>
<table class="fundstocktable">
	<tr>
		<!-- stock Form -->
		<td valign="top">
			<%@include file="stock_form.jspf"%>
		</td>
		<!-- Fundstock List -->
		<td valign="top">
			<%@include file="stock_list.jspf"%>
		</td>
		
	</tr>
</table>
${people}
<%@include file="include/footer2.jspf" %>
</body>
</html>