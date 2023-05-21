<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/util/head.jsp"%>
	<script src="/js/user/register.js" defer></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
		<div>*비밀번호를 입력해주세요</div>
		<form action="/mypage/authentication" method="POST">
			<input
			type="password" id="password" name="password"
			placeholder="비밀번호" />
			<button type="submit">확인</button>
		</form>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>	