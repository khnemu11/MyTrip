<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/util/head.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	<form action="/upload" method="POST" enctype="multipart/form-data">
		<input type="file" name="file">
		<input type="text" name="title">
		<input type="submit" value="전송">
		
	</form>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>