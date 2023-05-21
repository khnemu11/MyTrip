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
	<div class = "register-container row">
		<div class="col-lg-1 col-md-1 col-sm-1"></div>
		<div class="col-lg-10 col-md-10 col-sm-10 row">
			<div class="col-lg-7 col-md-7 col-sm-7" id="form">
				<h1>| 회원가입</h1>
				<form id="register-form" method="POST" action="/user/register">
					<div class="mb-3">
						<input
							type="text" class="form-control" id="id" name="id"
							placeholder="아이디" maxlength="20"/>
					</div>
					<div id="idcheck-result"></div>
					<div class="mb-3">
						<input
							type="text" class="form-control" id="name"
							name="name" placeholder="이름" />
					</div>
					<div id="nicknamecheck-result"></div>
					<div class="mb-3">
						<input
							type="password" class="form-control" id="password" name="password"
							placeholder="비밀번호" />
					</div>
					<div class="mb-3">
						<input
							type="password" class="form-control" id="pwcheck"
							placeholder="비밀번호 확인" />
					</div>
					<div id="pwdcheck-result"></div>
		
					<div class="mb-3">
							<div class="input-group">
								<input type="text" class="form-control" id="email"
									name="email" placeholder="이메일" /> <span
									class="input-group-text">@</span> <select class="form-select"
									id="emaildomain" name="emaildomain" aria-label="이메일 도메인 선택">
									<option selected>선택</option>
									<option value="ssafy.com">ssafy.com</option>
									<option value="gmail.com">gmail.com</option>
									<option value="naver.com">naver.com</option>
									<option value="daum.net">daum.net</option>
								</select>
							</div>
					</div>
					<div class="col-auto text-center">
						<button type="submit" id="btn-join"
							class="btn btn-outline-primary mb-3">회원가입</button>
					</div>
				</form>
			</div>
			<div class="col-lg-5 col-md-5 col-sm-5"></div> <!-- 여기에 사진 넣어주세용 -->
		</div>
		<div class="col-lg-1 col-md-1 col-sm-1"></div>
	</div>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>