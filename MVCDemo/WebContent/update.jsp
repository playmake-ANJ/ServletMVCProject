<%@page import="java.util.Date"%>
<%@page import="zsc.ysh.mvc.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新数据</title>

<style type="text/css">
  *{
  padding:0px;
  margin:0px;
  }
#div1{
margin:0px;
text-align: center;
}
</style>
</head>

<body>
<div id="div1" style="border: 1px red solid;width:600px;height:430px;margin-left:400px">
	<br>
	<h2 >填写信息</h2>
	<c:if test="${not empty requestScope.repeadUserName }">
		<p style="color: red">
			<strong>警告：${requestScope.repeadUserName}!</strong>
		</p>
	</c:if>
    <form action="${pageContext.request.contextPath}/update.anj?id=${requestScope.id}" method="post" style="width:500px;height:400px">
    	<table style="padding: 20px;border: 1px blue solid;width:470px;height:300px;margin-left:70px;margin-top:10px">
    		<c:set var="user" value="${requestScope.user }" />
    		<tr>
    			<td style="text-align: right">
    				<p>用户名：</p>
    			</td>
    			<td style="text-align: left">
    				<p><input type="text" name="updateUserName" value="${user.userName }"></p>
    			</td>
    		</tr>
    		<tr>
    			<td style="text-align: right">
    				<p>密 码：</p>
    			</td>
    			<td style="text-align: left">
    				<p><input type="text" name="updatePassword" value="${user.passwd}"></p>
    				
    			</td>
    		</tr>
    		<tr>
    			<td style="text-align: right">
    				<p>住 址：</p>
    			</td>
    			<td style="text-align: left">
    				<p><input type="text" name="updateAddress" value="${user.address}"></p>
    				
    			</td>
    		</tr>
    		<tr>
    			<td style="text-align: right">
    				<p>电 话：</p>
    			</td>
    			<td style="text-align: left">
    				<p><input type="text" name="updatePhone" value="${user.phone }"></p>
    				
    			</td>
    		</tr>
    	
    	</table>
    	<p style="margin-left: 100px">
    	<br>
    		<input type="submit" value="更  新">
    	</p>
    </form>
</div>	
	

</body>
</html>