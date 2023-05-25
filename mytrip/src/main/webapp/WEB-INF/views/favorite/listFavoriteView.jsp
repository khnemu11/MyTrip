<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
<head>
	<%@ include file="/WEB-INF/views/util/head.jsp"%>
	<link rel="stylesheet" href="/css/route/listPlan.css">
	<!-- <script type="text/javascript" src="/js/route/findRoute.js" defer></script> -->
</head>
<body>
	<%@ include file="/WEB-INF/views/util/header.jsp"%>
	<div class="body-wrapper">
		<div class="row main-container">
			<div class="col-md-2 col-sm-2 col-xs-2"></div>
			<div class="col-md-8 col-sm-8 col-xs-8">
				<div class="row sub-title-wrapper">
					<div class="sub-title-container">
						<span class="sub-title-left"> <span class="sub-title-label">|</span>
							<span class="sub-title">여행 계획 목록</span>
						</span> <span class="sub-title-right" onclick="window.history.back();"> <span class="back back-icon"><i
								class="fa-solid fa-arrow-left"></i></span> <span class="back back-text">돌아가기</span>
						</span>
					</div>
				</div>
				<form action="/review/listReview" method="get">
					<div class="search-container">
						<input type="hidden" name="pageSize" value="10">
						<div id="search-bar">
							<img src="/img/utill/only-logo.png" class="search-logo" />
							<p class="divider">|</p>
							<input class="form-search" id="search" type="text"
								placeholder="즐겨찾기 검색" name ="keyword">
							<button type="submit" id="btn-search">
								<i class="fa-solid fa-magnifying-glass" style="color: #3F80F8;"></i>
							</button>
						</div>
					</div>
				</form>
				<table class="table">
				  <thead>
				    <tr>
				      <th class="no">번호</th>
				      <th class="title">제목</th>
				    </tr>
				  </thead>
				  <tbody>
				  <c:forEach items="${list}" var="item" varStatus="idx" begin="0" >
					  	<tr>
				  	 		<td class="no">${idx.count}</td>
						    <td class="title"><a href="/tour/detail?title=${item.title}&address=${item.address}&longitude=${item.longitude}&latitude=${item.latitude}&telephone=${item.latitude}">${item.title}</a></td>
				    	</tr>
				  </c:forEach>
				  </tbody>
				</table>
			</div>
			<div class="col-md-2 col-sm-2 col-xs-2"></div>
		</div>
		<div class="row main-container">
			<div class="col-md-2 col-sm-2 col-xs-2"></div>
			<div class="col-md-8 col-sm-8 col-xs-8">
			<div class="center-container">
		<nav aria-label="Page navigation example" id="pagination-nav">
		  <ul class="pagination">
		     <c:if test="${pagination.before}">
		      <li class="page-item">
				<form action="/favorite/favoriteList" method="get" id="move${pagination.start - 1}">
					<input type="hidden" name="pageSize" value="10">
					<input type="hidden" name="keyword" value="${pagination.keyword}">
					<input type="hidden" name="pageNo" value="${pagination.start - 1}">	    
				      <a class="page-link" href="#" aria-label="Previous" onclick="document.getElementById('move${pagination.start - 1}')submit()">
				        <span aria-hidden="true">&laquo;</span>
				      </a>
				</form>
				</li>
			</c:if>
		    <c:forEach var="page" begin="${pagination.start}" end="${pagination.end}">
				<li class="page-item">
					<form action="/favorite/favoriteList" method="get" id="move${page}">
						<input type="hidden" name="pageSize" value="10">
						<input type="hidden" name="keyword" value="${pagination.keyword}">
						<input type="hidden" name="pageNo" value="${page}">
						<a class="page-link" type="submit" href="#" aria-label="Previous" onclick="document.getElementById('move${page}').submit()">
				        	<span aria-hidden="true">${page}</span>
				      	</a>
					</form>
				</li>
			</c:forEach>
			<c:if test="${pagination.next}">
			<li class="page-item">
				<form action="/favorite/favoriteList" method="get" id="move${pagination.end + 1}">
					<input type="hidden" name="pageSize" value="10">
					<input type="hidden" name="keyword" value="${pagination.keyword}">
					<input type="hidden" name="pageNo" value="${page.end + 1}">
			      <a class="page-link" href="#" aria-label="Next" onclick="document.getElementById('move${pagination.end + 1}').submit()">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
				</form>
				 </li>
			</c:if>
		  </ul>
		</nav>
		</div>
			</div>
			<div class="col-md-2 col-sm-2 col-xs-2"></div>
		</div>
	</div>
	<%@ include file="/WEB-INF/views/util/footer.jsp"%>
</body>
</html>