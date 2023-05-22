<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/util/head.jsp"%>
	<link rel="stylesheet" href="/css/mypage/authentication.css"/>
</head>
<c:if test="${not empty mypageMsg}">
	<script>
	 alert("${mypageMsg}");
	</script>
</c:if>
<body>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	<div class = "update-container row">	
		<div class="col-md-4 col-sm-4 col-xs-4"></div>
		<div class="col-md-4 col-sm-4 col-xs-4 flex">
			<div class="row sub-title-wrapper">
				<div class="sub-title-container">
					<span class="sub-title-left"> <span class="sub-title-label">|</span>
						<span class="sub-title">내 정보 수정</span>
					</span> <span class="sub-title-right" onclick="window.history.back();"> <span class="back back-icon"><i
							class="fa-solid fa-arrow-left"></i></span> <span class="back back-text">돌아가기</span>
					</span>
				</div>
			</div>
			<div>*비밀번호를 입력해주세요</div>
			<form action="/mypage/authentication" method="POST">
				<input
				type="password" id="password" name="password"
				placeholder="비밀번호" required/>
				<button type="submit">확인</button>
			</form>
		</div>
		<div class="col-md-4 col-sm-4 col-xs-4"></div>
	</div>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>	