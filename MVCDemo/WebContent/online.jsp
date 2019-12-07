<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p style="margin-left: 500px;"><strong>用户在线统计</strong></p>
    <table style="margin-left: 400px; padding: 50px;" border="1">
    	<tr>
    		<td>用户的ssid</td>
    		<td>在线的用户名</td>
    		<td>访问的页面</td>
    		<td>ip地址</td>
    		<td>访问时间</td>
    	</tr>
    	<c:set var="onlieMaps" value="${onlie}"/>
    	<c:if test="${not empty onlieMaps }">
    		<c:forEach var="userOnlie" items="${onlie}">
    	<tr>
    		<td>${userOnlie.ssid}</td>
    		<td>${userOnlie.userName}</td>
    		<td>${userOnlie.page }</td>
    		<td>${userOnlie.ip }</td>
    		<td><fmt:formatDate value="${userOnlie.time }" pattern="yyyy-MM-dd"/></td>
    	</tr>
    		</c:forEach>
    	</c:if>
    
    </table>
</body>
</html>