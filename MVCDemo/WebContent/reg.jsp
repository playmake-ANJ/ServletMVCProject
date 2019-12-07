<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户注册</title>
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
	<br>
	<br>
	<c:set var="regErro" value="${requestScope.regErro }" />
	<c:if test="${not empty regErro }">
		<p style="margin-left: 550px;color: red">
		 	<strong>${regErro}</strong>
		</p>
	</c:if>
	<c:if test="${not empty requestScope.regUserNameErro }">
		<p style="margin-left: 550px;color: red">
			<strong>${requestScope.regUserNameErro}</strong>
		</p>
	</c:if>
	
	
	<div id="div1" style="border: 1px red solid;width:600px;height:400px;margin-left:400px">
	<br>
	<h2 >填写信息</h2>
    <form action="${pageContext.request.contextPath }/reg.anj" method="post" style="width:500px;height:400px">
    	<table style="padding: 20px;border: 1px blue solid;width:470px;height:300px;margin-left:70px;margin-top:10px">
    		<tr>
    			<td style="text-align: right">
    				<p>输入账号：</p>
    			</td>
    			<td style="text-align: left">
    				<p><input type="text" name="regUserName"></p>
    			</td>
    		</tr>
    		<tr>
    			<td style="text-align: right">
    				<p>输入密码：</p>
    			</td>
    			<td style="text-align: left">
    				<p><input type="password" name="regPassword"></p>
    				
    			</td>
    		</tr>
    		<tr>
    			<td style="text-align: right">
    				<p>输入住址：</p>
    			</td>
    			<td style="text-align: left">
    				<p><input type="text" name="regAddress"></p>
    				
    			</td>
    		</tr>
    		<tr>
    			<td style="text-align: right">
    				<p>输入电话：</p>
    			</td>
    			<td style="text-align: left">
    				<p><input type="text" name="regPhone"></p>
    				
    			</td>
    		</tr>
    	
    	</table>
    	<p style="margin-left: 100px">
    	<br>
    		<input type="submit" value="注      册">
    	</p>
    </form>
</div>	
</body>
</html>