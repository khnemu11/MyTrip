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
	<%@ include file="/WEB-INF/views/util/header.jsp"%>\
	<div class="search-container">
		<div class="col-lg-3 col-md-3 col-sm-3"></div>
		<div class="col-lg-6 col-md-6 col-sm-6">
			<div id="search-bar">
				<input class="form-control" id="search" type="text"
								placeholder="검색어 입력">
				<button type="button" class="btn btn-info text-white"
								id="btn-search">검색</button>
			</div>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-3"></div>
	</div>
	<div class="main-container">
		<div class="col-lg-1 col-md-1 col-sm-1">
			
		</div>
		<div class="col-lg-10 col-md-10 col-sm-10">
				
		</div>
		<div class="col-lg-1 col-md-1 col-sm-1">
				
		</div>
	</div>
	
	
	
	
	
	
	
	
	
	<div class="container p-1">
		<div class="container p-1">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12">
					<h2 class="my-3 py-3 shadow-sm bg-light text-center">관광지 목록</h2>
				</div>
			</div>
			<div class="row select-row">
				<div class=" col-md-2 col-sm-12 col-xs-12">
					<select class="form-select" id="select-city"
						onChange="onChangeCity();">
						<option selected>도시</option>
					</select>
				</div>
				<div class="col-md-2 col-sm-12 col-xs-12">
					<select class="form-select" id="select-gun"
						onChange="onChangeGunGu();">
						<option selected>시/군/구</option>
					</select>
				</div>
				<div class="col-md-2 col-sm-12 col-xs-12">
					<select class="form-select" id="select-contentTypeId"
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
				<div class="col-md-6 col-sm-12 col-xs-12">
					<div id="search-bar">
						<input class="form-control" id="search" type="text"
							placeholder="검색어 입력">
						<button type="button" class="btn btn-info text-white"
							id="btn-search">검색</button>
					</div>
				</div>
			</div>



			<!-- 공공데이터 관광지 출력 -->
			<div class="row" id="location-container">
				<div class="col-md-4 col-sm-4 col-xs-4" id="search-result">
					<div id="location-list" class="row"></div>
				</div>
				<div class="col-md-8 col-sm-8 col-xs-8">
					<%@ include file="/WEB-INF/views/tour/map.jsp"%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>