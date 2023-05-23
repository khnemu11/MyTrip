<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
	<%@ include file="/WEB-INF/views/util/head.jsp"%>
	<link rel="stylesheet" href="/css/route/registRouteView.css">
	<script type="text/javascript" src="/js/route/registRoute.js" defer></script>
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
				<span class="title">여행 계획 등록</span>
			</span> <span class="title-right" onclick="window.history.back();"> <span class="back back-icon"><i
					class="fa-solid fa-arrow-left"></i></span> <span class="back back-text">돌아가기</span>
			</span>
			</div>
			<form action="/route/registRoute" method="GET">
				<c:forEach items="${tourList}" var="item">
					<div class="routeParams" data-title="${item.title}" data-longitude = "${item.longitude}" data-latitude ="${item.latitude}" >
						<input type="hidden" name="tourTitle" value="${item.title}">
						<input type="hidden" name="tourlongitude" value="${item.longitude}">
						<input type="hidden" name="tourLatitude" value="${item.latitude}">
						<input type="hidden" name="tourAddress" value="${item.address}">
						<input type="hidden" name="tourTel" value="${item.telephone}">
						<input type="hidden" name="tourDistance" value="0">
						<input type="hidden" name="tourTime" value="0">
					</div>
				</c:forEach>	
				<div class="row">
					<div class="col-md-7 col-sm-7 col-xs-7">
						<div id="map"></div>
					</div>
					<div class="col-md-5 col-sm-5 col-xs-5">
						<div class="sub-container">
							<div class="sub-title">제목</div>
							<input class="form-control" type="text" name="title" placeholder="제목">
						</div>
						<div class="sub-container">
							<div class="sub-title">세부 설명</div>
							<input class="form-control" type="text" name="content" placeholder="세부 설명">
						</div>
						<div class="sub-container">
							<div class="sub-title">여행 경로</div>
							<div id="tour-list">
							</div>
						</div>
						<div class="sub-container">
							<div class="sub-title">예상 비용</div>
							<div id="predict-time">
								<input type="hidden" name="expectedTime" id="expectedTime" value="">
								<span class="sub-sub-title" id="total-time">5시간 30분</span>
								<input type="hidden" name="expectedDistance" value="" id="expectedDistance">
								<span class="sub-context" id="total-distance">400km</span>
							</div>
							<div id="predict-cost">
								<input type="hidden" name="taxiCost" id="taxiCost" value="">
								<span class="sub-context" id="total-taxi">택시비 약 400,000원</span>
								<span class="sub-context">|</span>
								<input type="hidden" name="fuelCost" id="fuelCost" value="">
								<span class="sub-context" id="total-toll">통행비 약 400,000원</span>
							</div>
						</div>
						<div class="btn-container">
							<button type="submit" class="btn btn-right">등록하기</button>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div class="col-md-2 col-sm-2 col-xs-2">
			
		</div>
	</div>
	</div>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>