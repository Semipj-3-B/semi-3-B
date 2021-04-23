<%@page import="dto.ReviewUserJoin"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% List<ReviewUserJoin> reviewList = (List<ReviewUserJoin>) request.getAttribute("reviewList"); %>
<%@ include file="/WEB-INF/views/admin/common.jsp" %>

<style type="text/css">
table {width: 900px;}
th, td { text-align:center;}
</style>
<script type="text/javascript">
//카테고리 조회 하지 않은 경우
$(document).ready(function () {
	$("#review").css({
		'background-color': 'white'
		, 'border': '1px solid  #A48654'
		, 'color': '#EA9A56'
	})
	
	var reviewno = 0
	getReviewno()		//findno post 전송
	handleBtns()	//수정, 삭제 버튼 event
}) 
</script>	
	<form action="/admin/review" method="post">	
	<select id="sort" name="sort">
		<option value="0" selected>게시판 구분</option>
		<option value="1">스토어 후기</option>
		<option value="2">찾아준 후기</option>
		<option value="3">찾은 후기</option>
	</select>
	
	<table class="table">
	<tr>
		<th style="width: 5%"></th>
		<th style="width: 10%">글번호</th>
		<th style="width: 10%">게시글</th>
		<th style="width: 10%">조회수</th>
		<th style="width: 10%">글삭제</th>
	</tr>
	<% for(int i = 0; i < reviewList.size(); i++) { %>
	<tr>
		<td><input type="radio" name="chk" value="<%= reviewList.get(i).getReviewNo() %>" /></td>
		<td><%= reviewList.get(i).getReviewNo() %></td>
		<td><button class="funBtns revdet" type="button" disabled>상세보기</button></td>
		<td><%= reviewList.get(i).getViews() %></td>
		<td><button class="funBtns revdel" type="button" disabled>삭제</button></td>
	</tr>
	<% } %>
	</table>
	<input type="hidden" id="reviewno" name="reviewno" />
	</form>	
	<div id="paging"><%@ include file="/WEB-INF/views/layout/adminPaging.jsp" %></div>
	</div>
</div>

<script type="text/javascript">
//radio가 체크된 행의 findno 추출
function getReviewno() {
	$("input[type=radio]").change(function () {
		reviewno = $(this).val()
		$("#reviewno").attr("value", reviewno)
		$(".funBtns").attr("disabled", false)
	})
}

//수정, 삭제 버튼 이벤트
function handleBtns() {
	$(".revdet").click(function () {
		 window.open("/review/view?reviewNo="+reviewno, "_blank", "width=400px height=200px" )
	})
	
	$(".revdel").click(function () {
		if(confirm("글을 삭제하시겠습니까?")) {
			$("form").submit()
		} else {
			return false
		}
	})
}
</script>	

<script type="text/javascript">
//카테고리로 목록 조회
$(document).ready(function () {
	var sort = -1
	
	$("select").change(function () {
		sort = $("select").val()
		$.ajax({
			type: "get"
			, url: "/admin/review"
			, data: {'reviewSort': sort}
			, dataType: 'json'
			, success: function (data) {viewReview(data)}
			, error: function () {console.log("실패")}
		})
	})
})

function viewReview(data) {
	var rList = JSON.parse(JSON.stringify(data))
	console.log(rList)
	
	$("table").empty()
	$("#paging").empty()
	$(".contents").css({'border': 'none'})
	var html = ''
		html += "<tr><th style='width: 5%'></th>"
		html += "<th style='width: 10%'>글번호</th>"
		html += "<th style='width: 10%'>게시글</th>"
		html += "<th style='width: 10%'>조회수</th>"
		html += "<th style='width: 10%'>글삭제</th>"
	
	for(var i = 0; i < rList.length; i++) {
		html += "<tr><td><input type='radio' name='chk' value='" + rList[i].reviewNo + "'/></td>"
		html += "<td>" + rList[i].reviewNo + "</td>"
		html += "<td><button class='funBtns revdet' type='button' disabled>상세보기</button></td>"
		html += "<td>" + rList[i].views + "</td>"
		html += "<td><button class='funBtns revdel' type='button' disabled>삭제</button></td>"
		html += "</tr>" 
	}	
		$("table").append(html)
		getReviewno()
		handleBtns()
}
</script>
