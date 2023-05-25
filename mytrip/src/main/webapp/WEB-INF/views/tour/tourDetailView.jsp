<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/util/head.jsp"%>
<link rel="stylesheet" href="/css/tour/tourDetail.css" />
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1aedb50890494bc67a51873a9228153a"></script>
<script type="text/javascript" src="/js/tour/tourDetail.js" defer></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	<!-- 공공데이터 관광지 출력 -->
	<div class="body-wrapper">
		<div class="row main-container">
<!-- Your share button code -->
			<div class="col-md-2 col-sm-2 col-xs-2"></div>
			<div class="col-md-8 col-sm-8 col-xs-8">
				<div class="row sub-title-wrapper">
					<div class="sub-title-container">
						<span class="sub-title-left"> <span class="sub-title-label">|</span>
							<span class="sub-title">여행지 상세</span>
						</span> <span class="sub-title-right" onclick="window.history.back();"> <span class="back back-icon"><i
								class="fa-solid fa-arrow-left"></i></span> <span class="back back-text">돌아가기</span>
						</span>
					</div>
				</div>
			</div>
			<div class="col-md-2 col-sm-2 col-xs-2"></div>
		</div>
		<div class="row main-container" >
			<div class="col-md-2 col-sm-2 col-xs-2"></div>
			<div class="col-md-8 col-sm-8 col-xs-8 row">
				<div class="col-md-6 col-sm-6 col-xs-6">
					<img src="/img/tour/no-image.png" id="tour-img">
				</div>
				<div class="col-md-6 col-sm-6 col-xs-6">
					<div class="description-container">
					<div>
						<div class="description-top">
							<span class="description-top-left"><span>${tour.title}</span></span>
							<span class="description-top-right">
								<div class="circle favorite" onclick="setFavorite('${tour.title}')"></div>
								<div class="circle share" onclick="shareFaceBook()" ><i class="fa-solid fa-share-nodes"></i></div>
							</span>
						</div>
						<div class="description-mid">
							<p>${tour.address}</p>
						</div>
					</div>
					<div class="description-bottom">
						<span class="description-bottom-context">
							<i class="fa-solid fa-phone"></i>
							<p>${tour.telephone}</p>
						</span>
						<span class="description-bottom-context">
							<i class="fa-regular fa-eye"></i>
							<p>${tour.hits}</p>
						</span>
					</div>
					</div>
				</div>
			</div>
			<div class="col-md-2 col-sm-2 col-xs-2"></div>
		</div>
		<div class="row main-container">
			<div class="col-md-2 col-sm-2 col-xs-2"></div>
			<div class="col-md-8 col-sm-8 col-xs-8 row">
				<div class="col-md-6 col-sm-6 col-xs-6 map-container">
					<div class="subsub-title">위치</div>
					<div id="map" data-lat = '${tour.latitude}' data-lng ='${tour.longitude}' data-title='${tour.title}'></div>
				</div>
				<div class="col-md-6 col-sm-6 col-xs-6 near-tour-container">
					<div class="subsub-title">
					주변 관광지
					</div>
					<div class="tour-list">
					</div>
				</div>
				<div class="col-md-2 col-sm-2 col-xs-2"></div>
			</div>
			<div class="col-md-2 col-sm-2 col-xs-2"></div>
		</div>
		<div class="row main-container">
				<div class="col-md-2 col-sm-2 col-xs-2"></div>
				<div class="col-md-8 col-sm-8 col-xs-8 ">	
					<div class="subsub-title">추천 영상</div>
						<c:choose>
							<c:when test="${youtubeList eq null}">
								<div class="none-text">
									추천 영상이 없습니다.
								</div>
							</c:when>
							<c:otherwise>
								<div class="grid-5">
									<c:forEach items="${youtubeList}" var="item">
										<div class="card-thumbnail">
											<a href="https://www.youtube.com/watch?v=${item.videoId}"><img src="${item.imgSrc}"></a>
										</div>
									</c:forEach>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				<div class="col-md-2 col-sm-2 col-xs-2"></div>
			</div>
		<div class="row main-container">
			<div class="col-md-2 col-sm-2 col-xs-2"></div>
			<div class="col-md-8 col-sm-8 col-xs-8 ">	
				<div class="subsub-title">후기</div>
				<c:if test="${empty reviewList}">
					<div class="non-div">
						작성된 후기가 없습니다.
					</div>
				</c:if>
				<c:if test="${not empty reviewList}">
					<div class="grid-5">
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
				</c:if>
				

			</div>
			<div class="col-md-2 col-sm-2 col-xs-2"></div>
		</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>