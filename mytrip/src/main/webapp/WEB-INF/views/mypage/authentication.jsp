<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/util/head.jsp"%>
	<script src="/js/user/register.js" defer></script>
	<link rel="stylesheet" href="/css/mypage/mypage.css"/>
</head>
<body>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	<div class = "update-container row">	
		<div class="col-md-4 col-sm-4 col-xs-4"></div>
		<div class="col-md-4 col-sm-4 col-xs-4 flex">
			<div>*비밀번호를 입력해주세요</div>
			<form action="/mypage/authentication" method="POST">
				<input
				type="password" id="password" name="password"
				placeholder="비밀번호" />
				<button type="submit">확인</button>
			</form>
		</div>
		<div class="col-md-4 col-sm-4 col-xs-4"></div>
	</div>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>	