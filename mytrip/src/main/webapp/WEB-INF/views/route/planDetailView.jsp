<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
	<%@ include file="/WEB-INF/views/util/head.jsp"%>
	<link rel="stylesheet" href="/css/route/registRouteView.css">
	<script type="text/javascript" src="/js/route/planDetail.js" defer></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1aedb50890494bc67a51873a9228153a&libraries=clusterer"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	<div class="main-wrapper">
	<div class="row main-container">
		<div class="col-md-2 col-sm-2 col-xs-2"></div>
		<div class="col-md-8 col-sm-8 col-xs-8">
			<div class="title-container">
			<span class="title-left"> <span class="title-label">|</span>
				<span class="title">여행 계획 상세 페이지</span>
			</span> <span class="title-right" onclick="window.history.back();"> <span class="back back-icon"><i
					class="fa-solid fa-arrow-left"></i></span> <span class="back back-text">돌아가기</span>
			</span>
		</div>
			<div class="row">
				<div class="col-md-7 col-sm-7 col-xs-7">
					<div id="map"></div>
				</div>
				<div class="col-md-5 col-sm-5 col-xs-5">
					<div class="sub-container">
						<div class="sub-title">제목</div>
						<div class="sub-context">${plan.title}</div>
					</div>
					<div class="sub-container">
						<div class="sub-title">세부 설명</div>
						<div class="sub-context">${plan.content}</div>
					</div>
					<div class="sub-container">
						<div class="sub-title">여행 경로</div>
						<c:forEach items="${list}" var="item" varStatus="status">
							<div class="data" data-lat = "${item.tourDto.latitude}" data-lng="${item.tourDto.longitude}" data-title="${item.tourDto.title}">
							</div>
							<span class="select-tour" id="${item.tourDto.title}">	
								<span class="tour-left">
								<c:choose>
									<c:when test="${status.first}">
										<i class="fa-sharp fa-solid fa-location-dot"></i>
									</c:when>
									<c:when test="${status.last}">
										<i class="fa-solid fa-location-pin"></i>
									</c:when>
									<c:otherwise>
										<span class="circle"></span>
									</c:otherwise>
								</c:choose>
								</span>
								<span class="tour-right">
									<span class="tour-title">${item.tourDto.title}</span>
									<span class="data-container"><p class="distance"></p><p class="duration"></p></span>
								</span>
							</span>
						</c:forEach>
					</div>
					<div class="sub-container">
						<div class="sub-title">예상 비용</div>
						<div id="predict-time">
							<span class="sub-sub-title" id="total-time"></span>
							<span class="sub-context" id="total-distance"></span>
						</div>
						<div id="predict-cost">
							<span class="sub-context" id="total-taxi"></span>
							<span class="sub-context">|</span>
							<span class="sub-context" id="total-toll"></span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-2 col-sm-2 col-xs-2">
			
		</div>
	</div>
	</div>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>