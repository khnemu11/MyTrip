<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/util/head.jsp"%>
	<script src="/js/mypage/update.js" defer></script>
	<link rel="stylesheet" href="/css/mypage/edit.css"/>
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
			<div class="row sub-title-wrapper">
				<div class="sub-title-container">
					<span class="sub-title-left"> <span class="sub-title-label">|</span>
						<span class="sub-title">내 정보 수정</span>
					</span> <span class="sub-title-right" onClick="window.location.href = '/mypage/mypage';"/> <span class="back back-icon"><i
							class="fa-solid fa-arrow-left"></i></span> <span class="back back-text">돌아가기</span>
					</span>
				</div>
			</div>
			<form action="/mypage/update" method="POST" id="update-form">
					<div class="row info-row">
						<div class="col-lg-3 col-md-3 col-sm-3 flex">
							<span class="row-title">이름</span>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-9 flex">
							<input type ="text" id="name" class="form-control" name ="name" value="${userInfo.name}" />
						</div>
					</div>
					<div class="row info-row">
						<div class="col-lg-3 col-md-3 col-sm-3 flex">
							<span class="row-title">이메일</span>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-9 flex">
							<input type ="text" id="email" class="form-control" name ="email" value="${userInfo.email}" />
						</div>
					</div>
					<div class="row info-row">
						<div class="col-lg-3 col-md-3 col-sm-3 flex">
							<span class="row-title"> 새 비밀번호</span>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-9 flex">
							<input type ="password" class="form-control" id="password" name ="password"/>
						</div>
					</div>
					<div class="row info-row">
						<div class="col-lg-3 col-md-3 col-sm-3 flex" >
							<span class="row-title">비밀번호 확인</span>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-9 flex">
							<input type ="password" class="form-control" id="confirm-password" name ="confirmPassword"/>
						</div>
					</div>
						<div class="row info-row">
					<div id="confirm-password-result"></div>
					</div>
						<div class="row info-row">
						<div class="col-lg-3 col-md-3 col-sm-3 flex">
							<span class="row-title">전화번호</span>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-9 flex">
							<input type ="text" id="phoneNo" class="form-control" name ="phoneNo" value="${userInfo.phoneNo}" />
						</div>
					</div>
					<div class="row info-row">
						<div class="col-lg-3 col-md-3 col-sm-3 flex">
							<span class="row-title">자기소개</span>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-9 flex">
							<input type ="text" id="intro" class="form-control" name ="intro" value="${userInfo.intro}" />
						</div>
					</div>
					<div class="button-container">
						<button type="submit" class="btn primary-btn">수정하기</button>
					</div>
			</form>
		</div>
		<div class="col-md-4 col-sm-4 col-xs-4"></div>
	</div>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>