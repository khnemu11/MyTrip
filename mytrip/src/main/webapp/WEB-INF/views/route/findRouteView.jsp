<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
	<%@ include file="/WEB-INF/views/util/head.jsp"%>
	<link rel="stylesheet" href="/css/route/findRouteView.css">
	<script type="text/javascript" src="/js/route/findRoute.js" defer></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1aedb50890494bc67a51873a9228153a"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	<c:if test="${not empty msg}">
		<script>
			alert("${msg}");
		</script>
	</c:if>
	<div class="row">
		<div class="col-md-2 col-sm-2 col-xs-2 select-tour-container">
			<div class="title-bar primary1">
				선택한 여행지
			</div>
			<div class="select-title">
				<div class="select-title-left">여행지</div>
				<div class="select-title-right"><span id="cnt">0</span> / 5</div>
			</div>
			<form action="/route/registRouteView" method="GET" id="selectForm">
				<div class="select-list">
				</div>
				<div class="select-submit">
					<button type="button" class="btn submit-btn" onclick="selectSubmit()">경로생성</button>
				</div>
			</form>
		</div>
		<div class="col-md-3 col-sm-3 col-xs-3 search-tour-container">
			<div class="title-bar primary2">
				여행지 검색
			</div>
			<div class="search-container">
				<div id="search-bar">
				<img src="/img/utill/only-logo.png" class="search-logo" />
				<p class="divider">|</p>
				<input class="form-search" id="search" type="text"
					placeholder="여행지 검색">
				<button type="button" id="btn-search">
					<i class="fa-solid fa-magnifying-glass" style="color: #3F80F8;"></i>
				</button>
			</div>
				<div class="sort-container">
					<input type="radio" id="view" class="form-check-input" name="sort"
						value="view" checked> <label for="view">검색 순</label> <input
						type="radio" id="name" class="form-check-input" name="sort"
						value="view"> <label for="name">즐겨찾기 순</label>
				</div>
			<select class="form-select select-box" id="select-city"
				onChange="onChangeCity();">
				<option selected>도시</option>
			</select> <select class="form-select select-box" id="select-gun"
				onChange="onChangeGunGu();">
				<option selected>시/군/구</option>
			</select> 
			<select class="form-select select-box" id="select-contentTypeId"
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
			<div class="sub-title-container">
				<span class="sub-title">검색 결과</span><span id="result-count">( 1,930 )</span>
			</div>
			</div>
			<div id="tour-list-wrapper" style="height:200px">
				<div id="tour-list">
					<div class="text-center loading-spinner">
						<div class="spinner-border" role="status"></div>
					</div>
				</div>
			</div> 
		</div>
		<div class="col-md-7 col-sm-7 col-xs-7 map-container">
			<div id="map">map</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>