<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
	<%@ include file="/WEB-INF/views/util/head.jsp"%>
	<script src="/js/user/login.js" defer></script>
	<link rel="stylesheet" href="/css/user/login.css"/>
</head>
<body>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	<div class = "login-container row">
	<div class="col-lg-1 col-md-1 col-sm-1"></div>
	<div class="col-lg-10 col-md-10 col-sm-10 row">
        <div class="col-lg-5 col-md-5 col-sm-5">
        	<h1>| 로그인</h1>
			<form action="/user/login" method="post" id="form-login">
				<input type="hidden" name="action" value="login">
				<div class = "mb-3">
				<label for="userid" class = "form-label">아이디</label>
				<input type = "text" class= "form-control" id = "id" name = "id"/>
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
				</div>
			</form>
		</div>
		<div class="col-lg-2 col-md-2 col-sm-2"></div>
		<div class="col-lg-5 col-md-5 col-sm-5"></div> <!-- 요기에 사진 넣어주세용 -->
	</div>
	<div class="col-lg-1 col-md-1 col-sm-1"></div>
	</div>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>