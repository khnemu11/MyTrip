<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/util/head.jsp"%>
<script src="/js/tour/tour.js" defer></script>
<link rel="stylesheet" href="/css/tour/tour.css" />
</head>
<body>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	<div class="search-container row">
		<div class="col-lg-3 col-md-3 col-sm-3"></div>
		<div class="col-lg-6 col-md-6 col-sm-6 row">
			<div id="search-bar">
				<img src="/img/utill/only-logo.png" class="search-logo" />
				<p class="divider">|</p>
				<input class="form-search" id="search" type="text"
					placeholder="여행지 검색">
				<button type="button" id="btn-search">
					<i class="fa-solid fa-magnifying-glass" style="color: #3F80F8;"></i>
				</button>

			</div>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-3"></div>
	</div>

	<div class="row">
		<div class="col-lg-3 col-md-3 col-sm-3"></div>
		<div class="col-lg-6 col-md-6 col-sm-6 row select-group">
			<select class="form-select select-box" id="select-city"
				onChange="onChangeCity();">
				<option selected>도시</option>
			</select> <select class="form-select select-box" id="select-gun"
				onChange="onChangeGunGu();">
				<option selected>시/군/구</option>
			</select> <select class="form-select select-box" id="select-contentTypeId"
				onChange="onChangeContentTypeId()">
				<option selected value="">전체</option>
				<option value="14">문화시설</option>
				<option value="15">축제공연행사</option>
				<option value="25">여행코스</option>
				<option value="32">숙박</option>
				<option value="38">쇼핑</option>
				<option value="39">음식점</option>
				<option value="28">레포츠</option>
				<option value="12">관광지</option>
			</select>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-3"></div>
	</div>

	<!-- 공공데이터 관광지 출력 -->
	<div class="row">
		<div class="col-md-2 col-sm-2 col-xs-2"></div>
		<div class="col-md-8 col-sm-8 col-xs-8">
			<div class="row sub-title-wrapper">
				<div class="sub-title-container">
					<span class="sub-title-label">|</span> <span class="sub-title">인기있는
						여행지</span>
				</div>
				<div class="sort-container">
					<input type="radio" id="view" class="form-check-input" name="sort"
						value="view" checked> <label for="view">인기 순</label> <input
						type="radio" id="name" class="form-check-input" name="sort"
						value="view"> <label for="name">이름 순</label>
				</div>

			</div>

		</div>
	</div>
	<div class="row">
		<div class="col-md-2 col-sm-2 col-xs-2"></div>
		<div class="col-md-8 col-sm-8 col-xs-8">
			<div class="grid-3 tour-container" id="location-list">
				<div class="tour-card">
					<img src="/img/tour/no-image.png">
					<div class="tour-info">
						<div class="tour-info-top">
							<span class="tour-info-title">
							엄청엄청엄청엄청엄청엄청엄청엄청엄청엄청엄청엄청엄청긴식당
							</span>
							<span class="tour-info-view">
							<i class="fa-regular fa-eye" style="color: #ffffff;"></i>111,111
							</span>
						</div>
						<!-- <div class="tour-info-bottom">
							<div class="tour-info-address">
							</div>
						</div> -->
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-2 col-sm-2 col-xs-2"></div>
	</div>

	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>