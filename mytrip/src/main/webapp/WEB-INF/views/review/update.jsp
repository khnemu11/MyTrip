<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/util/head.jsp"%>
	<script src="/js/review/write.js" defer></script>
	<link rel="stylesheet" href="/css/review/write.css"/>
</head>
<body>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	<div class = "write-container row">
		<div class="col-lg-3 col-md-3 col-sm-3"></div>
		<div class="col-lg-6 col-md-6 col-sm-6">
			<div class="row sub-title-wrapper">
				<div class="sub-title-container">
					<span class="sub-title-left"> <span class="sub-title-label">|</span>
						<span class="sub-title-label">후기 수정</span>
					</span> <span class="sub-title-right" onclick="window.history.back();"> <span class="back back-icon"><i
							class="fa-solid fa-arrow-left"></i></span> <span class="back back-text">돌아가기</span>
					</span>
				</div>
			</div>
			<form action="/review/update/${review.seq}" method="POST" >
				<div class="sub-title">제목</div>
				<!-- <input type = "hidden" name="tour-title" id="tour-title"/> -->
				<input type = "text" class= "form-control title-form" id = "title" name = "title" placeholder="제목을 작성해주세요" value="${review.title}" required/>
				
				<div class="sub-title">여행지</div>
				<input type = "hidden" name="tour-address" id="tour-address" value="${tour.address}"/>
				<input type = "hidden" name="tour-longitude" id="tour-longitude" value="${tour.longitude}"/>
				<input type = "hidden" name="tour-latitude" id="tour-latitude" value="${tour.latitude}"/>
				<input type = "hidden" name="tour-telephone" id="tour-telephone" value="${tour.telephone}"/>
				
				<div class="flex">
					<input type = "text" class="form-control" name="tour-title" id="tour-title" value="${tour.title}" required readonly="readonly"/>
					<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" data-bs-whatever="@mdo">여행지 검색</button>
				</div>
					
				<div class="sub-title">사진 선택</div>
				<c:forEach var="img" items="${reviewImg}">
					<c:choose>
						<c:when test="${img.imageCode.length() lt 5}">
							<img src="/img/review/${img.imageCode}.png">
						</c:when>
						<c:otherwise>
							<img src="/img/upload/${img.imageCode}">
						</c:otherwise>
					</c:choose>
				</c:forEach>
				
				<div class="sub-title">내용</div>
				<textarea class="form-control" name="content" required>${review.content}</textarea>
				<div class="btn-container">
					<button type="submit" class="btn btn-right">등록하기</button>
				</div>			
			</form>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-3"></div>



		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h1 class="modal-title fs-5" id="exampleModalLabel">New message</h1>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
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
						<div id="search-result">
						</div>
					</div>
	<!-- 	          <div class="mb-3">
		            <label for="message-text" class="col-form-label">Message:</label>
		            <textarea class="form-control" id="message-text"></textarea>
		          </div> -->
		          	<div class="row">
						<div class="col-md-12 col-sm-12 col-xs-12" id="list-container">
	<!-- 						<div class="text-center loading-spinner">
								<div class="spinner-border" role="status">
								</div>	
							</div> -->
							<div class="tour-container" id="location-list">
							</div>
						</div>
					</div>
		      </div>
			  <div class="modal-footer">
		       		<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">확인</button>
		      </div>
		    </div>
		  </div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>