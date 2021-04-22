<%@page import="dto.Usertb"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<style type="text/css">
.menu-grid {
	display: inline-grid;
	width: 150px;
	grid-template-rows: 50px 50px 50px 50px 50px;
	position: absolute;
}

.contents {
	border: 1px solid;
	border-radius: 5px;
	border-color: #EBC680;
	width: 900px;
	height: 450px;
	margin: 0 0 0 150px;
}

.bts {
	transition-duration: 0.4s;
	background-color: #EBC680;
	color: white;
}

.bts:hover {
	border: 2px solid #A48654;
	background-color: white;
	color: #A48654;
}
</style>

<div class="container">
	<div class="menu-grid" >
	<button class="bts btn" onclick="location.href='/admin/user'">회원 관리</button>
	<button class="bts btn" onclick="location.href='/admin/find'">찾기 게시글 관리</button>
	<button class="bts btn" onclick="location.href='/admin/discover'">발견 게시글 관리</button>
	<button class="bts btn" onclick="location.href='/admin/review'">후기 게시글 관리</button>
	<button class="bts btn" onclick="location.href='/admin/product'">제품 관리</button>
	</div>
	<div class="contents"></div>
</div>
