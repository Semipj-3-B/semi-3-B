<%@page import="dto.FindBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% List<FindBoard> findList = (List<FindBoard>) request.getAttribute("findList"); %>
<%@ include file="/WEB-INF/views/admin/common.jsp" %>

<style type="text/css">
table {width: 900px;}
th, td { text-align:center;}
</style>
<script type="text/javascript">
//카테고리 조회 하지 않은 경우
$(document).ready(function () {
	$("#find").css({
		'background-color': 'white'
		, 'border': '1px solid  #A48654'
		, 'color': '#EA9A56'
	})
	
	var findno = 0
	getFindno()		//findno post 전송
	handleBtns()	//수정, 삭제 버튼 event
}) 
</script>	
	<form action="/admin/find" method="post">	
	<select id="pet" name="pet">
		<option value="0" selected>종류</option>
		<option value="1">강아지</option>
		<option value="2">고양이</option>
		<option value="3">기타</option>
	</select>
	<select id="loc" name="loc">
		<option value="0" selected>지역</option>
		<option value="1">서울특별시</option>
		<option value="2">경기도</option>
		<option value="3">강원도</option>
		<option value="4">충청북도</option>
		<option value="5">충청남도</option>
		<option value="6">경상북도</option>
		<option value="7">경상남도</option>
		<option value="8">전라북도</option>
		<option value="9">전라남도</option>
		<option value="10">대전광역시</option>
		<option value="11">광주광역시</option>
		<option value="12">인천광역시</option>
		<option value="13">부산광역시</option>
		<option value="14">대구광역시</option>
		<option value="15">울산광역시</option>
		<option value="16">세종시</option>
		<option value="17">제주시</option>
	</select>
	<button type="button" id="sel">조회</button>
	<table class="table">
	<tr>
		<th style="width: 5%"></th>
		<th style="width: 10%">글번호</th>
		<th style="width: 10%">게시글</th>
		<th style="width: 10%">조회수</th>
		<th style="width: 10%">글삭제</th>
	</tr>
	<% for(int i = 0; i < findList.size(); i++) { %>
	<tr>
		<td><input type="radio" name="chk" value="<%= findList.get(i).getFindNo() %>" /></td>
		<td><%= findList.get(i).getFindNo() %></td>
		<td><button class="funBtns finddet" type="button" disabled>상세보기</button></td>
		<td><%= findList.get(i).getViews() %></td>
		<td><button class="funBtns finddel" type="button" disabled>삭제</button></td>
	</tr>
	<% } %>
	</table>
	<input type="hidden" id="findno" name="findno" />
	</form>	
	<div id="paging"><%@ include file="/WEB-INF/views/layout/adminPaging.jsp" %></div>
	</div>
</div>

<script type="text/javascript">
//radio가 체크된 행의 findno 추출
function getFindno() {
	$("input[type=radio]").change(function () {
		findno = $(this).val()
		$("#findno").attr("value", findno)
		$(".funBtns").attr("disabled", false)
	})
}

//수정, 삭제 버튼 이벤트
function handleBtns() {
	$(".finddet").click(function () {
		 window.open("/find/read?FindNo="+findno)
	})
	
	$(".finddel").click(function () {
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
	var pet = ''
	var loc = ''
	
	$("#pet").change(function () { pet = $("#pet").val()})
	$("#loc").change(function () { loc = $("#loc").val()})
	
	$("#sel").click(function () {
		$.ajax({
			type: "get"
			, url: "/admin/find"
			, data: {'pet': pet, 'loc': loc}
			, dataType: "json"
			, success: function (data) {viewFind(data)}
			, error: function () {console.log("실패")}
		})
	})
})

function viewFind(data) {
	var fList = JSON.parse(JSON.stringify(data))
	console.log(fList)
	
	$("table").empty()
	$("#paging").empty()
	$(".contents").css({'border': 'none'})
	var html = ''
		html += "<tr><th style='width: 5%'></th>"
		html += "<th style='width: 10%'>글번호</th>"
		html += "<th style='width: 10%'>게시글</th>"
		html += "<th style='width: 10%'>조회수</th>"
		html += "<th style='width: 10%'>글삭제</th>"
	
	for(var i = 0; i < fList.length; i++) {
		html += "<tr><td><input type='radio' name='chk' value='" + fList[i].findNo + "'/></td>"
		html += "<td>" + fList[i].findNo + "</td>"
		html += "<td><button class='funBtns finddet' type='button' disabled>상세보기</button></td>"
		html += "<td>" + fList[i].views + "</td>"
		html += "<td><button class='funBtns finddel' type='button' disabled>삭제</button></td>"
		html += "</tr>" 
	}	
		$("table").append(html)
		getFindno()
		handleBtns()
}
</script>
