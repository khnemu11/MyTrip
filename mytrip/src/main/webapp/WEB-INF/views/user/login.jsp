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
	<div class = "container">
	<div>
		<h1>| 로그인</h1>
        <div class="col-lg-8 col-md-10 col-sm-12">
			<form action="/user/login" method="post" id="form-login">
				<input type="hidden" name="action" value="login">
				<div class = "mb-3">
				<label for="userid" class = "form-label">아이디</label>
				<input type = "text" class= "form-control" id = "userid" name = "id"/>
				</div>
				<div class = "mb-3">
					<label for= "userpassword" class ="from-label">비밀번호</label>
					<input type ="password" class = "form-control" id="password" name ="password" />
				</div>
				<div class = "col-auto text-center">
					<button type="submit" id="btn-login" class="btn btn-outline-primary mb-3" style="
    margin-right: 16px;">
						로그인
					</button>
					<button type="reset" class="btn btn-outline-success mb-3 ">취 소</button>
				</div>
			</form>
		</div>
	</div>
	</div>
</body>
</html>