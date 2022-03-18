<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="spform" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="include/header.jspf" %>
</head>
<body class="personbody">
<br/>
<table class="persontable">
	<tr>
		<!-- Person Form -->
		<td valign="top">
			<%@include file="person_form.jspf"%>
		</td>
		<!-- Person List -->
		<td valign="top">
			<%@include file="person_list.jspf"%>
		</td>
		
	</tr>
</table>
${people}
<%@include file="include/footer.jspf" %>
</body>
</html>