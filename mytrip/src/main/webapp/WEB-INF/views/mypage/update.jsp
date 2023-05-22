<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/util/head.jsp"%>
	<script src="/js/mypage/update.js" defer></script>
	<link rel="stylesheet" href="/css/mypage/mypage.css"/>
</head>
<body>
<c:if test="${not empty mypageMsg}">
	<script>
	 alert("${mypageMsg}");
	</script>
</c:if>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	<div class = "update-container row">
		<div class="col-md-4 col-sm-4 col-xs-4"></div>
		<!-- 세션에서 꺼내서 value 값넣고, 수정 method="post" -->
		<div class="col-md-4 col-sm-4 col-xs-4">
			<h1>| 내 정보 수정</h1>
			<form action="/mypage/update" method="POST" id="update-form">
				<div class="info-row row">						
					<div class="col-lg-3 col-md-3 col-sm-3 flex">
						<span>이름</span>
					</div>
					<div class="col-lg-9 col-md-9 col-sm-9 flex">
						<input type ="text" id="name" name ="name" value="${userInfo.name}" />
					</div>
					
					<div class="col-lg-3 col-md-3 col-sm-3 flex">
						<span>이메일</span>
					</div>
					<div class="col-lg-9 col-md-9 col-sm-9 flex">
						<input type ="text" id="email" name ="email" value="${userInfo.email}" />
					</div>
					
					<div class="col-lg-3 col-md-3 col-sm-3 flex">
						<span>새 비밀번호</span>
					</div>
					<div class="col-lg-9 col-md-9 col-sm-9 flex">
						<input type ="password" id="password" name ="password"/>
					</div>
					
					<div class="col-lg-3 col-md-3 col-sm-3 flex" >
						<span>새 비밀번호 확인</span>
					</div>
					<div class="col-lg-9 col-md-9 col-sm-9 flex">
						<input type ="password" id="confirm-password" name ="confirmPassword"/>
					</div>
					
					<div id="confirm-password-result"></div>
					
					<div class="col-lg-3 col-md-3 col-sm-3 flex">
						<span>전화번호</span>
					</div>
					<div class="col-lg-9 col-md-9 col-sm-9 flex">
						<input type ="text" id="phoneNo" name ="phoneNo" value="${userInfo.phoneNo}" />
					</div>
	
					<div class="col-lg-3 col-md-3 col-sm-3 flex">
						<span>자기소개</span>
					</div>
					<div class="col-lg-9 col-md-9 col-sm-9 flex">
						<input type ="text" id="intro" name ="intro" value="${userInfo.intro}" />
					</div>
				</div>
				<button type="submit">수정하기</button>
			</form>
		</div>
		<div class="col-md-4 col-sm-4 col-xs-4"></div>
	</div>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>