<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix ="c" uri ="http://java.sun.com/jsp/jstl/core"%>
<c:set var = "root" value = "${pageContext.request.contextPath}" scope = "session"></c:set>
<link rel="stylesheet" href="/css/util/header.css" />
<script>
	<c:if test="${not empty loginMsg}">
		alert("${loginMsg}");
	</c:if>
</script>
    <!-- 상단 header 시작 -->
    <header>
        <nav class="navbar navbar-expand-lg bg-info bg-opacity-75" data-bs-theme="dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="/">
                    <img src="/img/utill/logo.png" class ="logo"/>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse my-nav center" id="navbarSupportedContent">
                	<div class="link-border">
                		<a class="nav-link" href="/tour/search">여행지 검색</a>
                	</div >
                	<div class="link-border">
                		 <a class="nav-link" href="#">경로 등록</a>
                	</div>
                	<div class="link-border">
                		<a class="nav-link" href="#">여행지 후기</a>
                	</div>             
                </div>
                 <div class="collapse navbar-collapse my-nav right" id="navbarSupportedContent">
                      <c:if test="${empty userInfo}">
                      	<a class="nav-link" href="/user/login">로그인</a>
                  	  </c:if>
                   	  <c:if test="${not empty userInfo}">
                   		<a class="nav-link" href="/user/logout">로그아웃</a>
                   	  </c:if>
                       <a class="nav-link" href="/user/register">회원가입</a>
                </div>
            </div>
        </nav>
    </header>