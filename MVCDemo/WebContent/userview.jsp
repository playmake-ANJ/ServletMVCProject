<%@page import="zsc.ysh.mvc.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户信息</title>
</head>
<body>
	<c:set var="flag" value="${sessionScope.user }" />
	<c:set var="str" value=""/>
	<c:if test="${empty flag || flag == str}">
		<c:redirect url="${pageContext.request.contextPath}/index.jsp" />
	</c:if>
     <br><br><br>
     <form action="${pageContext.request.contextPath}/userView.anj" method="post">
        <p style="margin-left: 500px;">
        	用户账号：<input type="text" name="seUserName">
        </p>
         <p style="margin-left: 500px;">
        	家庭住址：<input type="text" name="seAddress">
        </p>
         <p style="margin-left: 500px;">
        	联系电话：<input type="text" name="sePhone">
        	<input type="submit" value="查询">
        	<strong><a href="${pageContext.request.contextPath}/loginOut.anj">注销登录</a></strong>
        </p>
        
        
     </form>
     <table style="margin-left: 400px; padding: 50px;" border="1">
     		<tr>
     			<td>用户编号</td>
     			<td>用户名</td>
     			<td>用户密码</td>
     			<td>家庭住址</td>
     			<td>联系电话</td>
     			<td>注册时间</td>
     			<td>操作记录</td>
     		</tr>
		<c:set var="users" value="${requestScope.userList }" />
		<c:if test="${not empty users}">
			<c:forEach var="us" items="${users}">
				<tr>
					<td>${us.userId }</td>
					<td>${us.userName }</td>
					<td>${us.passwd }</td>
					<td>${us.address }</td>
					<td>${us.phone }</td>
					<td><fmt:formatDate value="${us.regDate }" pattern="yyyy-MM-dd"/></td>
					<td><a
						href="${pageContext.request.contextPath}/updateParpe.anj?userId=${us.userId}">编辑</a>
						|<a
						href="${pageContext.request.contextPath}/delete.anj?userId=${us.userId}">删除</a>
					</td>
				</tr>
			</c:forEach>

		</c:if>
	</table>
	

</body>
</html>