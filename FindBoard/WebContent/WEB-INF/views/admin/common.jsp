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

.btns {
	transition-duration: 0.4s;
	background-color: #EBC680;
	color: white;
}

.btns:hover {
	border: 2px solid #A48654;
	background-color: white;
	color: #A48654;
}

.funBtns {
	border: none;
	background-color: white;
	transition-duration: 0.4s;
	color: #A48654;
}
.funBtns:hover {
	background-color: #A48654;
	color: white;
</style>

<script type="text/javascript">
$(document).ready(function () {
	//열려 있는 리스트 페이지의 form을 해제하고 이동
	$("#user").click(function () {
		$("form").attr("onsubmit", "return false")
		location.href="/admin/user"
	})
	$("#find").click(function () {
		$("form").attr("onsubmit", "return false")
		location.href="/admin/find"
	})
	$("#discover").click(function () {
		$("form").attr("onsubmit", "return false")
		location.href="/admin/discover"
	})
	$("#review").click(function () {
		$("form").attr("onsubmit", "return false")
		location.href="/admin/review"
	})
	$("#product").click(function () {
		$("form").attr("onsubmit", "return false")
		location.href="/admin/product"
	})
})
</script>
<div class="container">
	<div class="menu-grid" >
	<button class="btns btn" id="user">회원 관리</button>
	<button class="btns btn" id="find">찾기 게시글 관리</button>
	<button class="btns btn" id="discover">발견 게시글 관리</button>
	<button class="btns btn" id="review">후기 게시글 관리</button>
	<button class="btns btn" id="product">제품 관리</button>
	</div>
	<div class="contents">