<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/util/head.jsp"%>
	<script src="/js/review/list.js" defer></script>
	<link rel="stylesheet" href="/css/review/list.css" />
</head>
<body>
<c:if test="${not empty reviewMsg}">
	<script>
		alert("${reviewMsg}");
	</script>
</c:if>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	<div class = "list-container row">	
		<div class="col-lg-2 col-md-2 col-sm-2"></div>
		<div class="col-lg-8 col-md-8 col-sm-8 row">
			<div class="row sub-title-wrapper">
				<div class="sub-title-container">
					<span class="sub-title-left"> 
						<span class="sub-title-label">|</span>
						<span class="sub-title">여행지 후기</span>
					</span>
					<span class="sub-title-right" onclick="window.history.back();"> 
						<span class="back back-icon">
							<i class="fa-solid fa-arrow-left"></i>
						</span> 
						<span class="back back-text">돌아가기</span>
					</span>
				</div>
			</div>
			
			<!-- 검색바 요기에 넣어주세여어엉 -->
			<div class="function-container">
				<div class="search-container">
					<div id="search-bar">
					<img src="/img/utill/only-logo.png" class="search-logo" />
					<p class="divider">|</p>
					<input class="form-search" id="search" type="text"
						placeholder="후기 검색">
					<button type="button" id="btn-search">
						<i class="fa-solid fa-magnifying-glass" style="color: #3F80F8;"></i>
					</button>
					</div>
				</div>
				
				<c:if test="${not empty userInfo}">
					<div class="btn-wrapper">
						<button type="button" class="btn" onclick="window.location.href='/review/write';">후기 작성</button>
					</div>
				</c:if>
			</div>
			
			<div class="review-wrapper">
				<div class="no-match">해당 조건에 맞는 관광지가 없습니다.</div>
				<div class="review-container">
					<c:forEach var="review" items="${reviewList}">	
						<form class="review-card" action="/review/detail/${review.seq}" method="get" onclick="submit()">		
							<c:if test="${not empty review.imageCode}">
								<c:choose>
									<c:when test="${review.imageCode.length() lt 5}">
										<img class="review-img" src="/img/review/${review.imageCode}.png">
									</c:when>
									<c:otherwise>
										<img class="review-img" src="/img/upload/${review.imageCode}">
									</c:otherwise>
								</c:choose>
							</c:if>					
							<c:if test="${empty review.imageCode}">
								<img class="review-img" src="/img/review/no-image.png">
							</c:if>	
							<span class="review-title">&nbsp; ${review.title}</span>
						</form>
					</c:forEach>
				</div>
			</div>
			
		</div>
		<div class="col-lg-2 col-md-2 col-sm-2"></div>
	</div>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>	