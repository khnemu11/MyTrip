<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
	<%@ include file="/WEB-INF/views/util/head.jsp"%>
	<link rel="stylesheet" href="/css/route/registRouteView.css">
	<script type="text/javascript" src="/js/route/registRoute.js" defer></script>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1aedb50890494bc67a51873a9228153a"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	<c:forEach items="${tourList}" var="item">
		<div class="routeParams" data-title="${item.title}" data-longitude = "${item.longitude}" data-latitude ="${item.latitude}" >
		</div>
	</c:forEach>	
	<div class="row">
	
	</div>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>