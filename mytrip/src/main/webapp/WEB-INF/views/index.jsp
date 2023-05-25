<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
	<%@ include file="/WEB-INF/views/util/head.jsp"%>
	<link rel="stylesheet" href="/css/util/index.css"/>
	<script src="/js/util/index.js" defer></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	<img src="/img/utill/main-background.png" class="main-background">
	<div class="main-wrapper">
		<div class="main-container row">
			<div class="col-md-1 col-sm-1 col-xs-1"></div>
			<div class="col-md-9 col-sm-9 col-xs-9">
				<div class="context-row ">
					<div class="title">사람들이 자주 보는 관광지</div>
					<div class="card-grid">
						<c:forEach items="${tourList}" var="item">
							<div class="card tour-card" onclick="window.location.href='/tour/detail?title=${item.title}&latitude=${item.latitude}&longitude=${item.longitude}&telephone=${item.telephone}&address=${item.address}'">
								<img src="/img/utill/loading.png" style="object-fit:fill;">
								<div class="card-description-container">
									<div class="card-description">
										<p class="card-title">${item.title}</p>
									</div>
									<div class="card-hits">
										<span>
										<i class="fa-regular fa-eye"></i>
										${item.hits}
										</span>
									</div>	
								</div>
							</div>
						</c:forEach>
					</div>
					<c:forEach items="${tourList}" var="item">
							<div class="tour-data" data-title="${item.title}"></div>
					</c:forEach>
				</div>
				<div class="context-row ">
					<div class="title">사람들이 좋아하는 관광지</div>
					<div class="card-grid">
						<c:forEach items="${favoriteList}" var="item">
							<div class="card favorite-card" onclick="window.location.href='/tour/detail?title=${item.title}&latitude=${item.latitude}&longitude=${item.longitude}&telephone=${item.telephone}&address=${item.address}'">
								<img src="/img/utill/loading.png">
								<div class="card-description-container">
									<div class="card-description">
										<p class="card-title">${item.title}</p>
									</div>
									<div class="card-hits">
										<span>
										<i class="fa-regular fa-heart"></i>
										${item.hits}
										</span>
									</div>								
								</div>
							</div>
						</c:forEach>
					</div>
					<c:forEach items="${favoriteList}" var="item">
							<div class="favorite-data" data-title="${item.title}"></div>
					</c:forEach>
				</div>
				<div class="context-row">
					<div class="title">최근 등록된 후기</div>
					<div class="card-grid">
						<c:forEach items="${reviewList}" var="item">
							<div class="card" onclick="window.location.href='/review/detail/${item.seq}'">
							<c:if test="${not empty item.imageCode}">
								<c:choose>
									<c:when test="${item.imageCode.length() lt 5}">
										<img src="/img/review/${item.imageCode}.png">
									</c:when>
									<c:otherwise>
										<img src="/img/upload/${item.imageCode}">
									</c:otherwise>
								</c:choose>
							</c:if>					
							<c:if test="${empty item.imageCode}">
								<img class="review-img" src="/img/review/no-image.png">
							</c:if>	
							<div class="card-description-container">
								<div class="card-description">
									<p class="card-title">${item.title}</p>
								</div>
							</div>
							</div>
						</c:forEach>
						
					</div>
				</div>
			</div>	
			<div class="col-md-1 col-sm-1 col-xs-1"></div>	
		</div>
	</div>
		
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>	