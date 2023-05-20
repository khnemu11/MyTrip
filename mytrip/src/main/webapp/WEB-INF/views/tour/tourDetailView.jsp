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
								<div class="circle"><i class="fa-regular fa-heart"></i></div>
								<div class="circle"><i class="fa-solid fa-share-nodes"></i></div>
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
					<div id="map" data-lat = '${tour.latitude}' data-lng ='${tour.longitude}'></div>
				</div>
				<div class="col-md-6 col-sm-6 col-xs-6 near-tour-container">
					<div class="subsub-title">
					주변 관광지
					</div>
					<div class="tour-list">
						<div class="tour-card row">
							<div class="col-md-6 col-sm-6 col-xs-6">
								<img src="/img/tour/no-image.png">
							</div>
							<div class="col-md-6 col-sm-6 col-xs-6">
								<div class="tour-description-wrapper">
									<div class="tour-description-top">
										<div class="tour-title">가나 해수욕장</div>
									</div>
									<div class="tour-description-bottom">
										<span>
											<span><i class="fa-solid fa-location-dot"></i>
											<span class="tour-address">아주아주아주아주아주아주아주아주아주아주아주아주긴아주아주아주아주아주아주아주아주아주아주아주아주긴아주아주아주아주아주아주아주아주아주아주아주아주긴 주소</span>
											</span>
										</span>
										<span><i class="fa-solid fa-phone"></i><span class="tour-tel">010-9220-0441</span></span>
										</div>
								</div>
								
							</div>
						</div>
						<div class="tour-card row">
							<div class="col-md-6 col-sm-6 col-xs-6">
								<img src="/img/tour/no-image.png">
							</div>
							<div class="col-md-6 col-sm-6 col-xs-6">
								<div class="tour-description-wrapper">
									<div class="tour-description-top">
										<div class="tour-title">가나 해수욕장</div>
									</div>
									<div class="tour-description-bottom">
										<span>
											<span><i class="fa-solid fa-location-dot"></i>
											<span class="tour-address">아주아주아주아주아주아주아주아주아주아주아주아주긴아주아주아주아주아주아주아주아주아주아주아주아주긴아주아주아주아주아주아주아주아주아주아주아주아주긴 주소</span>
											</span>
										</span>
										<span><i class="fa-solid fa-phone"></i><span class="tour-tel">010-9220-0441</span></span>
										</div>
								</div>
								
							</div>
						</div>
						<div class="tour-card row">
							<div class="col-md-6 col-sm-6 col-xs-6">
								<img src="/img/tour/no-image.png">
							</div>
							<div class="col-md-6 col-sm-6 col-xs-6">
								<div class="tour-description-wrapper">
									<div class="tour-description-top">
										<div class="tour-title">가나 해수욕장</div>
									</div>
									<div class="tour-description-bottom">
										<span>
											<span><i class="fa-solid fa-location-dot"></i>
											<span class="tour-address">아주아주	아주아주아주아주아주아주아주아주아주아주긴아주아주아주아주아주아주아주아주아주아주아주아주긴아주아주아주아주아주아주아주아주아주아주아주아주긴 주소</span>
											</span>
										</span>
										<span><i class="fa-solid fa-phone"></i><span class="tour-tel">010-9220-0441</span></span>
										</div>
								</div>
								
							</div>
						</div>
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
				<div class="grid-5">
					<c:choose>
						<c:when test="${youtubeList eq null}">
							<div class="none-text">
								추천 영상이 없습니다.
							</div>
						</c:when>
						<c:otherwise>
							<c:forEach items="${youtubeList}" var="item">
								<div class="card-thumbnail">
									<img src="${item}">
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>

				</div>

			</div>
			<div class="col-md-2 col-sm-2 col-xs-2"></div>
		</div>
		<div class="row main-container">
			<div class="col-md-2 col-sm-2 col-xs-2"></div>
			
			<div class="col-md-8 col-sm-8 col-xs-8 ">	
				<div class="subsub-title">후기</div>
				<div class="grid-5">
					<div class="card-thumbnail">
						<img src="/img/tour/no-image.png">
					</div>
					<div class="card-thumbnail">
						<img src="/img/tour/no-image.png">
					</div>
					<div class="card-thumbnail">
						<img src="/img/tour/no-image.png">
					</div>
					<div class="card-thumbnail">
						<img src="/img/tour/no-image.png">
					</div>
					<div class="card-thumbnail">
						<img src="/img/tour/no-image.png">
					</div>
				</div>

			</div>
			<div class="col-md-2 col-sm-2 col-xs-2"></div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>