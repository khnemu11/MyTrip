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
<c:if test="${not empty loginMsg}">
	<script>
		alert("${loginMsg}");
	</script>
</c:if>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	<div class = "login-container row">
	<div class="col-lg-3 col-md-3 col-sm-3"></div>
	<div class="col-lg-6 col-md-6 col-sm-6 row">
        <div class="col-lg-5 col-md-5 col-sm-5">
			<div class="row sub-title-wrapper">
				<div class="sub-title-container">
					<span class="sub-title-left"> <span class="sub-title-label">|</span>
						<span class="sub-title">로그인</span>
					</span> <span class="sub-title-right" onclick="window.history.back();"> <span class="back back-icon"><i
							class="fa-solid fa-arrow-left"></i></span> <span class="back back-text">돌아가기</span>
					</span>
				</div>
			</div>
	
			<form action="/user/login" method="POST" id="form-login">
				<div class = "mb-3">
				<input type = "text" class= "form-control" id = "id" name = "id" placeholder="아이디" required/>
				</div>
				<div class = "mb-3">
					<input type ="password" class = "form-control" id="password" name ="password" placeholder="비밀번호" required/>
				</div>
				<div class = "text-center">
					<button type="submit" id="btn-login" class="btn btn-outline-primary mb-3" style="
    margin-right: 16px;">
						로그인
					</button>
				</div>
			</form>
			
		</div>
		<div class="col-lg-2 col-md-2 col-sm-2"></div>
		<div class="col-lg-5 col-md-5 col-sm-5">
			<img class="right-img" src="/img/utill/travel.jpg">
		</div>
	</div>
	<div class="col-lg-3 col-md-3 col-sm-3"></div>
	</div>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>