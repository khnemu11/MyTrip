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
						<span class="sub-title-label">후기 작성</span>
					</span> <span class="sub-title-right" onclick="window.history.back();"> <span class="back back-icon"><i
							class="fa-solid fa-arrow-left"></i></span> <span class="back back-text">돌아가기</span>
					</span>
				</div>
			</div>
			<form action="/review/write" method="POST" >
				<div class="sub-title">제목</div>
				<input type = "text" class= "form-control title-form" id = "title" name = "title" placeholder="제목을 작성해주세요" required/>
				<!-- 검색 기능 -->
				<div class="sub-title">사진 선택</div>
				<!-- 여기에 파일업로드 넣어주세용 -->
				<div class="sub-title">내용</div>
				<textarea class="form-control" name="content" required></textarea>
				<div class="btn-container">
					<button type="submit" class="btn btn-right">등록하기</button>
				</div>			
			</form>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-3"></div>s
	</div>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>