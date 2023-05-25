<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/util/head.jsp"%>
	<!-- <script src="/js/user/register.js" defer></script> -->
	<link rel="stylesheet" href="/css/user/register.css"/>
</head>
<body>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	<div class = "register-container row">
		<div class="col-lg-2 col-md-2 col-sm-2"></div>
		<div class="col-lg-8 col-md-8 col-sm-8 row">
			<div class="col-lg-6 col-md-6 col-sm-6" id="form">
				<div class="row sub-title-wrapper">
					<div class="sub-title-container">
						<span class="sub-title-left"> <span class="sub-title-label">|</span>
							<span class="sub-title">회원가입</span>
						</span> <span class="sub-title-right" onclick="window.history.back();"> <span class="back back-icon"><i
								class="fa-solid fa-arrow-left"></i></span> <span class="back back-text">돌아가기</span>
						</span>
					</div>
				</div>
				<form id="register-form" method="POST" action="/user/register">
					<div class="mb-3">
						<input
							type="text" class="form-control" id="id" name="id"
							placeholder="아이디" maxlength="20" required/>
					</div>
					<div id="idcheck-result"></div>
					<div class="mb-3">
						<input
							type="text" class="form-control" id="name"
							name="name" placeholder="이름" required/>
					</div>
					<div id="nicknamecheck-result"></div>
					<div class="mb-3">
						<input
							type="password" class="form-control" id="password" name="password"
							placeholder="비밀번호" required/>
					</div>
					<div class="mb-3">
						<input
							type="password" class="form-control" id="confirm-password"
							placeholder="비밀번호 확인" required/>
					</div>
					<div id="confirm-password-result"></div>
		
					<div class="mb-3">
							<div class="input-group">
								<input type="email" class="form-control" id="email"
									name="email" placeholder="이메일" />
							</div>
					</div>
					<div class="col-auto text-center">
						<button type="submit" id="btn-register"
							class="btn btn-outline-primary mb-3">회원가입</button>
					</div>
				</form>
			</div>
			<div class="col-lg-1 col-md-1 col-sm-1">
			</div> 
			<div class="col-lg-5 col-md-5 col-sm-5">
				<img class="right-img" src="/img/utill/travel.jpg">
			</div> <!-- 여기에 사진 넣어주세용 -->
		</div>
		<div class="col-lg-2 col-md-2 col-sm-2"></div>
	</div>
	
	
	<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
	  <div class="carousel-indicators">
	    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
	    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
	  </div>
	  <div class="carousel-inner">
	    <div class="carousel-item active">
	      <img src="/img/utill/travel.jpg" class="d-block" alt="...">
	    </div>
	    <div class="carousel-item">
	      <img src="/img/utill/main-background.png" class="d-block" alt="...">
	    </div>
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
	
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>