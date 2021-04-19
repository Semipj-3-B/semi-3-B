<%@page import="dto.Usertb"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<script type="text/javascript">
$(document).ready(function () {
	
 	$("#admin-user").click(function () {
		console.log("#admin-user: ", $(this))
		
		$.ajax({
			type: "get"
			,url: "/admin/user"
			,dataType: "html"
			, success: function(result) {
					console.log("성공")
					$(".contents").html(result)		
			}
			, error: function() {console.log("실패")}
			
		}) //$.ajax() END
		
 	}) //click() END
	
}) //ready() END

</script>

<style type="text/css">
.menu-grid {
	display: inline-grid;
	width: 150px;
	grid-template-rows: 50px 50px 50px;
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
} */
</style>

<div class="container">
	<div class="menu-grid" >
	<button class="btn" id="admin-user">회원관리</button>
	<button class="btn" id="admin-board">게시글 관리</button>
	<button class="btn" id="admin-prod">제품 관리</button>
	</div>
	<div class="contents">
	</div>
</div>
</body>
</html>