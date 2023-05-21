<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
	<%@ include file="/WEB-INF/views/util/head.jsp"%>
	<link rel="stylesheet" href="/css/route/findRouteView.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	
	<div class="row">
		<div class="col-md-2 col-sm-2 col-xs-2">
			<div class="title-bar">
				선택한 여행지
			</div>
			<div class="select-title">
				<div class="select-title-left">여행지</div>
				<div class="select-title-right">1 / 5</div>
			</div>
			<div class="select-list">
				<span class="select-tour">
					<span class="tour-left"><i class="fa-sharp fa-solid fa-location-dot"></i></span>
					<span class="tour-right">
						<span class="tour-title">가니안 약국</span>
						<span class="tour-delete"><i class="fa-solid fa-trash"></i></span>
					</span>
				</span>
			</div>
			<div class="select-submit">
				<button>경로생성</button>
			</div>
		</div>
		<div class="col-md-3 col-sm-3 col-xs-3">
			<div class="title-bar">
				여행지 검색
			</div>
		</div>
		<div class="col-md-7 col-sm-7 col-xs-7">
			map
		</div>
	</div>
	
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>