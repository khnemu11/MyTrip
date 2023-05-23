<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/util/head.jsp"%>
	<script src="/js/review/detail.js" defer></script>
	<link rel="stylesheet" href="/css/review/detail.css" />
</head>
<body>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	<div class = "list-container row">	
		<div class="col-lg-3 col-md-3 col-sm-3"></div>
		<div class="col-lg-6 col-md-6 col-sm-6 row">
			<div class="row sub-title-wrapper">
				<div class="sub-title-container">
					<span class="sub-title-left"> 
						<span class="sub-title-label">|</span>
						<span class="sub-title">여행지 후기</span>
					</span>
					<span class="sub-title-right" onclick="window.location.href='/review/list';"> 
						<span class="back back-icon">
							<i class="fa-solid fa-arrow-left"></i>
						</span> 
						<span class="back back-text">돌아가기</span>
					</span>
				</div>
			</div>
			<div class="detail-container">
				ㅎㅇㅎㅇㅎㅇㅎㅇ
			</div>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-3"></div>
	</div>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>	
