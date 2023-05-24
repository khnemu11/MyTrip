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
					<div class="row sub-title-wrapper">
						<div class="sub-title-container">
							<span class="sub-title-left"> <span class="sub-title-label">|</span>
								<span class="sub-title">마이페이지</span>
							</span> <span class="sub-title-right" onclick="window.history.back();"> <span class="back back-icon"><i
								class="fa-solid fa-arrow-left"></i></span> <span class="back back-text">돌아가기</span>
							</span>
						</div>
					</div>
					<div class="col-lg-5 col-md-5 col-sm-5">
<!-- 						<div class="util-container">
							<span class="sub-title">내정보</span>
							<span>
								<a href="/mypage/authentication" class="edit">수정</a>
								<a href="/mypage/withdrawal" class="delete">탈퇴</a>
							</span>
						</div> -->
					<div class="info-table">
						<div class="info-row row">
							<div class="col-lg-3 col-md-3 col-sm-3 flex">
								<span class="sub-title">내정보</span>
							</div>
							<div class="col-lg-9 col-md-9 col-sm-9 util-container">
								<span class="util-container">
									<a href="/mypage/authentication" class="edit">수정</a>
									<a href="/mypage/withdrawal" class="delete">탈퇴</a>
								</span>
							</div>
						</div>
						<div class="info-row row">
							<div class="col-lg-3 col-md-3 col-sm-3 flex">
								<span>아이디</span>
							</div>
							<div class="col-lg-9 col-md-9 col-sm-9 flex">
								<span>${userInfo.id}</span>
							</div>
						</div>
						<div class="info-row row">
							<div class="col-lg-3 col-md-3 col-sm-3 flex">
								<span>이름</span>
							</div>
							<div class="col-lg-9 col-md-9 col-sm-9 flex">
								<span>${userInfo.name}</span>
							</div>
						</div>
						<div class="info-row row">
							<div class="col-lg-3 col-md-3 col-sm-3 flex">
								<span>이메일</span>
							</div>
							<div class="col-lg-9 col-md-9 col-sm-9 flex">
								<span>${userInfo.email}</span>
							</div>
						</div>
						<div class="info-row row">
							<div class="col-lg-3 col-md-3 col-sm-3 flex">
								<span>전화번호</span>
							</div>
							<div class="col-lg-9 col-md-9 col-sm-9 flex">
								<span>${userInfo.phoneNo}</span>
							</div>
						</div>
						<div class="info-row row">
							<div class="col-lg-3 col-md-3 col-sm-3 flex">
								<span>자기소개</span>
							</div>
							<div class="col-lg-9 col-md-9 col-sm-9 flex">
								<span>${userInfo.intro}</span>
							</div>
						</div>
						<div class="info-row row">
							<div class="col-lg-3 col-md-3 col-sm-3 flex">
								<span>가입일</span>
							</div>
							<div class="col-lg-9 col-md-9 col-sm-9 flex">
								<span>${userInfo.joinDateStr}</span>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-7 col-md-7 col-sm-7">
					<div class="right-row">
						<div class="right-title-row">
							<div class="sub-title">나의 여행 계획</div>
							<i class="fa-solid fa-plus"></i>
						</div>
						<div class="right-context-row">
							<div class="card-grid" id="plan-list">
								<div class="card">
									<img src="/img/review/1.png">
									<div class="card-description-container">
										<div class="card-description">
											엄청엄청엄청엄청엄청엄청엄청엄청엄청긴 제목
										</div>
									</div>
								</div>
									<div class="card">
									<img src="/img/review/1.png">
									<div class="card-description-container">
										<div class="card-description">
											엄청엄청엄청엄청엄청엄청엄청엄청엄청긴 제목
										</div>
									</div>
								</div>
									<div class="card">
									<img src="/img/review/1.png">
									<div class="card-description-container">
										<div class="card-description">
											엄청엄청엄청엄청엄청엄청엄청엄청엄청긴 제목
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="right-row">
						<div class="right-title-row">
							<div class="sub-title">나의 후기</div>
							<i class="fa-solid fa-plus"></i>
						</div>
						<div class="right-context-row">
							<div class="card-grid" id="review-list">
								<div class="card">
									<img src="/img/review/1.png">
									<div class="card-description-container">
										<div class="card-description">
										</div>
									</div>
								</div>
								<div class="card">
									<img src="/img/review/1.png">
									<div class="card-description-container">
										<div class="card-description">
										</div>
									</div>
								</div>
								<div class="card">
									<img src="/img/review/1.png">
									<div class="card-description-container">
										<div class="card-description">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="right-row">
						<div class="right-title-row">
							<div class="sub-title">나의 즐겨찾기</div>
							<i class="fa-solid fa-plus"></i>
						</div>
							<div class="right-context-row">
							<div class="card-grid" id="favorite-list">
								<div class="card">
									<img src="/img/review/1.png">
									<div class="card-description-container">
										<div class="card-description">
										</div>
									</div>
								</div>
								<div class="card">
									<img src="/img/review/1.png">
									<div class="card-description-container">
										<div class="card-description">
										</div>
									</div>
								</div>
								<div class="card">
									<img src="/img/review/1.png">
									<div class="card-description-container">
										<div class="card-description">
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>