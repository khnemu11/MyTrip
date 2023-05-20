<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/util/head.jsp"%>
<script src="/js/tour/tour.js" defer></script>
<link rel="stylesheet" href="/css/tour/tour.css" />
</head>
<body>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	${tour.title}
	${tour.address}

	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>