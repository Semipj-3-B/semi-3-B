<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<style type="text/css">
.menu-grid {
	display: inline-grid;
	width: 150px;
	grid-template-rows: 50px 50px 50px 50px 50px;
	border-radius: 5px;
	position: absolute;
}

.contents {
	border: 1px solid;
	border-radius: 5px;
	width: 900px;
	height: 450px;
	margin: 0 0 0 150px;
}

.btn {
	transition-duration: 0.4s;
}

.btn:hover {
	border: 2px solid #A48654;
	color: #A48654;
}

table {width: 900px;}
th, td { text-align:center;}
</style>

<div class="container">
	<div class="menu-grid" >
	<button class="btn" id="test1" onclick="location.href='/admin/user'">회원 관리</button>
	<button class="btn" id="test2" onclick="location.href='/admin/find'">찾기 게시글 관리</button>
	<button class="btn" id="test3" onclick="location.href='/admin/findout'">발견 게시글 관리</button>
	<button class="btn" id="test3" onclick="location.href='/admin/review'">후기 게시글 관리</button>
	<button class="btn" id="test3" onclick="location.href='/admin/product'">제품 관리</button>
	</div>
	<div class="contents">