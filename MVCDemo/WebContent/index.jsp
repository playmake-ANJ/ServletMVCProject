<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户登录界面</title>
</head>
<body>
     <br><br><br><br><br>
     <h2 style="margin-left: 550px">信息管理系统案例</h2>
     <form action="${pageContext.request.contextPath}/login.anj" method="post">
     	<table style="margin-left: 450px;padding: 80px;border: 1px #ccc solid;width:420px">
     	  <tr>
     	  	<c:set var="erro" value="${requestScope.erro }"/>
     	  	<c:if test="${not empty erro }">
	     	  	<td style="color: red">
	     	  		<strong>${requestScope.erro}</strong>
	     	  	</td>
     	  	</c:if>
     	  
     	 </tr>
     	  <tr>
     	    <td>
     	    	<p>
     	    		账号：<input type="text" name="userName">
     	    	</p>
     	    </td>
     	  </tr>
     	  <tr>
     	    <td >
     	    	<p>
     	    		密码：<input type="password" name="passwd">
     	    	</p>
     	    </td>
     	  </tr>
     	  <tr>
     	    <td >
     	    	
     	    		验证码：<input style="width: 80px" type="text" name="checkCode"> 
     	    		<img alt="" src="${pageContext.request.contextPath}/getCheckCode.anj">
     	    	
     	    </td>
     	  </tr>
     	   <tr>
     	    <td >
     	    	
     	    		<font size="2">
     	    			<a href="${pageContext.request.contextPath}/index.jsp" style="margin-left: 170px;">换一张</a>
     	    		</font>
     	    	
     	    </td>
     	  </tr>
     	  <tr>
     	    <td>
     	    	<p>
     	    		记住我一周<input type="radio" name="keep" value="7">
     	                               记住我一个月<input type="radio" name="keep" value="30">
     	       </p>
     	    </td>
     	    
     	  </tr>
     	  <tr>
     	  	<td>
     	  		<a style="margin-left: 80px" href="${pageContext.request.contextPath}/reg.jsp">用户注册</a>
     	  	</td>
     	  </tr>
     	  <tr>
     	     <td>
     	     	<p style="margin-left: 80px">
     	     		<input type="submit" value="登    录">
     	     	</p>
     	     </td>
     	  
     	  </tr>
     	
     	</table>
     
     
     </form>

</body>
</html>