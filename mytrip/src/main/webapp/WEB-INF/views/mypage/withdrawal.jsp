<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/util/head.jsp"%>
	<script src="/js/mypage/withdrawal.js" defer></script>
	<link rel="stylesheet" href="/css/mypage/withdrawal.css"/>
</head>
<body>
<c:if test="${mypageMsg}">
	<script>
	 alert(${mypageMsg});
	</script>
</c:if>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	<div class = "update-container row">	
		<div class="col-md-4 col-sm-4 col-xs-4"></div>
		<div class="col-md-4 col-sm-4 col-xs-4 flex">
			<div class="row sub-title-wrapper">
				<div class="sub-title-container">
					<span class="sub-title-left"> <span class="sub-title-label">|</span>
						<span class="sub-title">회원 탈퇴</span>
					</span> <span class="sub-title-right" onclick="window.history.back();"> <span class="back back-icon"><i
							class="fa-solid fa-arrow-left"></i></span> <span class="back back-text">돌아가기</span>
					</span>
				</div>
			</div>
			<div class="infor-password">*비밀번호를 입력해주세요</div>
			<input
			type="password" id="password" class="form-control" name="password"
			placeholder="비밀번호 확인" required />
			<div class="button-wrapper">
				<button type="submit" class="btn" id="password-submit">확인</button>
			</div>
		</div>
		<div class="col-md-4 col-sm-4 col-xs-4"></div>
	</div>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>	