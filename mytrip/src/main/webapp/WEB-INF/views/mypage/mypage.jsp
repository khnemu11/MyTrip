<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/util/head.jsp"%>
	<script src="/js/mypage/mypage.js" defer></script>
	<link rel="stylesheet" href="/css/mypage/mypage.css"/>
</head>
<body>
<c:if test="${not empty mypageMsg}">
	<script>
	 alert("${mypageMsg}");
	</script>
</c:if>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	<div class="body-wrapper">
		<div class = "mypage-container row">
			<div class="col-lg-1 col-md-1 col-sm-1"></div>
			<div class="col-lg-10 col-md-10 col-sm-10 row">
				<div class="col-lg-5 col-md-5 col-sm-5">
					<div class="row sub-title-wrapper">
						<div class="sub-title-container">
							<span class="sub-title-left"> <span class="sub-title-label">|</span>
								<span class="sub-title">마이페이지</span>
							</span> <span class="sub-title-right" onclick="window.history.back();"> <span class="back back-icon"><i
								class="fa-solid fa-arrow-left"></i></span> <span class="back back-text">돌아가기</span>
							</span>
						</div>
					</div>
					<a href="/mypage/authentication">수정</a>
					<a href="/mypage/withdrawal">탈퇴</a>
					<div class="info-row row">	
						<div class="col-lg-3 col-md-3 col-sm-3 flex">
							<span>아이디</span>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-9 flex">
							<span>${userInfo.id}</span>
						</div>
						
						<div class="col-lg-3 col-md-3 col-sm-3 flex">
							<span>이름</span>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-9 flex">
							<span>${userInfo.name}</span>
						</div>
		
						<div class="col-lg-3 col-md-3 col-sm-3 flex">
							<span>이메일</span>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-9 flex">
							<span>${userInfo.email}</span>
						</div>
						
						<div class="col-lg-3 col-md-3 col-sm-3 flex">
							<span>전화번호</span>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-9 flex">
							<span>${userInfo.phoneNo}</span>
						</div>
		
						<div class="col-lg-3 col-md-3 col-sm-3 flex">
							<span>자기소개</span>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-9 flex">
							<span>${userInfo.intro}</span>
						</div>
						
						<div class="col-lg-3 col-md-3 col-sm-3 flex">
							<span>가입일</span>
						</div>
						<div class="col-lg-9 col-md-9 col-sm-9 flex">
							<span>${userInfo.joinDate}</span>
						</div>
					</div>
				</div>
				<div class="col-lg-7 col-md-7 col-sm-7">
					<!-- 여기에 나의 경로, 나의 후기 넣어주세용 -->
				</div>
			</div>
			<div class="col-lg-1 col-md-1 col-sm-1"></div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>