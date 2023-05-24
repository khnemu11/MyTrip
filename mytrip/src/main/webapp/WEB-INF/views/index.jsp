<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
	<%@ include file="/WEB-INF/views/util/head.jsp"%>
	<link rel="stylesheet" href="/css/util/index.css"/>
</head>
<body>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	<img src="/img/utill/main-background.png" class="main-background">
	<div class="main-wrapper">
		<div class="main-container row">
			<div class="col-md-1 col-sm-1 col-xs-1"></div>
			<div class="col-md-9 col-sm-9 col-xs-9">
				<div class="context-row ">
					<div class="title">인기 있는 관광지</div>
					<div class="card-grid">
						<c:forEach items="${tourList}" var="item">
							<div class="card" onclick="window.location.href=''">
								<img src="/img/review/1.png">
								<div class="card-description-container">
									<div class="card-description">
										${item.title}
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<div class="context-row">
					<div class="title">관심 있는 관광지</div>
					<div class="card-grid">
						<div class="card">
							<img src="/img/review/1.png">
							<div class="card-description-container">
								<div class="card-description">
									매우매우매우매우매우매우매우매우매우긴이름
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="context-row">
					<div class="title">최근 등록된 후기</div>
					<div class="card-grid">
						<div class="card">
							<img src="/img/review/1.png">
							<div class="card-description-container">
								<div class="card-description">
									매우매우매우매우매우매우매우매우매우긴이름
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>	
			<div class="col-md-1 col-sm-1 col-xs-1"></div>	
		</div>
	</div>
		
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>	