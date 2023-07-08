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
				<c:if test="${review.userId == userInfo.id}">
					<div class="util-container">
						<a class="btn edit" href="/review/update/${review.seq}">수정</a>
						<span class="btn delete-btn delete" >삭제</span>
					</div>
				</c:if>
				<div data-seq="${review.seq}" id="seq"></div>
				
				<div class="flex-between">
					<div class="userName">작성자 : ${review.userName}</div>
					<div class="createdDate">${review.createdDate}</div>
				</div>
				
				<div class="title">${review.title}</div>
				<c:if test="${not empty review.tourTitle}">
					<div class="place">● 장소: ${review.tourTitle}</div> <!-- 장소 누르면 상세페이지로 이동 -->
				</c:if>

				<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
				  <div class="carousel-indicators">
				  	 <c:forEach var="img" items="${reviewImg}" varStatus="status">
				  	 	<c:choose>
					  	 	<c:when test="${status.index == 0}">
					  	 	  <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
					  	 	</c:when>
					  	 	<c:otherwise>
					  	 	   <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="${status.index}" aria-label="Slide 2"></button>
					  	 	</c:otherwise>
				  	 	</c:choose>
				    </c:forEach>
				  </div>
				  <div class="carousel-inner">
				    <c:forEach var="img" items="${reviewImg}" varStatus="status">
					  	 <c:choose>
					  	 	<c:when test="${status.index == 0}">
		    				    <c:choose>
									<c:when test="${img.imageCode.length() lt 5}">
										<div class="carousel-item active">
											<img class="d-block" src="https://dwv4yecgxdd1b.cloudfront.net/upload/${img.imageCode}">	
										</div>
									</c:when>
									<c:otherwise>
										<div class="carousel-item active">
											<img class="d-block" src="https://dwv4yecgxdd1b.cloudfront.net/upload/${img.imageCode}">
										</div>
									</c:otherwise>
								</c:choose>
					    	</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${img.imageCode.length() lt 5}">
										<div class="carousel-item">
											<img class="d-block" src="https://dwv4yecgxdd1b.cloudfront.net/upload/${img.imageName}">	
									   </div>
									</c:when>
									<c:otherwise>
										<div class="carousel-item">
											<img class="d-block" src="https://dwv4yecgxdd1b.cloudfront.net/upload/${img.imageName}">
										</div>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
				    </c:forEach>
			   	  </div>
			   	   <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
				    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
				    <span class="visually-hidden">Previous</span>
				  </button>
				  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
				    <span class="carousel-control-next-icon" aria-hidden="true"></span>
				    <span class="visually-hidden">Next</span>
				  </button>
				  </div>
				</div>
				
				<div class="content">${review.content}</div>
				<div id="reply-container">
					<div class="row">
							<span class="reply-title">댓글</span><span class="reply-count">${replyCnt}</span>
					</div>
					
					<div class="input-reply-container">
						<div class="reply-profile"><i class="fa-solid fa-user" style="color: #000000;"></i></div>
						<div class="reply-context" contenteditable="true" placeholder="내용을 입력해주세요."></div>
						<i class="fa-regular fa-paper-plane regist-reply" style="color: #000000;"></i>
					</div>
					<div id="reply-list-container"></div>
				</div>
				<div>
			</div>

		</div>
		<div class="col-lg-3 col-md-3 col-sm-3"></div>
	</div>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>	
