<%@page import="dto.DiscoverBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% List<DiscoverBoard> discList = (List<DiscoverBoard>) request.getAttribute("discList"); %>
<%@ include file="/WEB-INF/views/admin/common.jsp" %>

<style type="text/css">
table {width: 900px;}
th, td { text-align:center;}
</style>
<script type="text/javascript">
//카테고리 조회 하지 않은 경우
$(document).ready(function () {
	$("#discover").css({
		'background-color': 'white'
		, 'border': '1px solid  #A48654'
		, 'color': '#EA9A56'
	})
	
	var discoverno = 0
	getDiscoverno()	//discoverno post 전송
	handleBtns()	//수정, 삭제 버튼 event
}) 
</script>	
	<form action="/admin/discover" method="post">	
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
	<% for(int i = 0; i < discList.size(); i++) { %>
	<tr>
		<td><input type="radio" name="chk" value="<%= discList.get(i).getDiscoverNo() %>" /></td>
		<td><%= discList.get(i).getDiscoverNo() %></td>
		<td><button class="funBtns discdet" type="button" disabled>상세보기</button></td>
		<td><%= discList.get(i).getViews() %></td>
		<td><button class="funBtns discdel" type="button" disabled>삭제</button></td>
	</tr>
	<% } %>
	</table>
	<input type="hidden" id="discoverno" name="discoverno" />
	</form>	
	<div><%@ include file="/WEB-INF/views/layout/adminPaging.jsp" %></div>
	</div>
</div>

<script type="text/javascript">
//radio가 체크된 행의 findno 추출
function getDiscoverno() {
	$("input[type=radio]").change(function () {
		discoverno = $(this).val()
		$("#discoverno").attr("value", discoverno)
		$(".funBtns").attr("disabled", false)
	})
}

//수정, 삭제 버튼 이벤트
function handleBtns() {
	$(".discdet").click(function () {
		 window.open("/discover/read?DiscoverNo="+discoverno)
	})
	
	$(".discdel").click(function () {
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
			, url: "/admin/discover"
			, data: {'pet': pet, 'loc': loc}
			, dataType: "json"
			, success: function (data) {viewDiscover(data)}
			, error: function () {console.log("실패")}
		})
	})
})

function viewDiscover(data) {
	var dList = JSON.parse(JSON.stringify(data))
	console.log(dList)
	
	$("table").empty()
	$("#paging").empty()
	$(".contents").css({'border': 'none'})
	var html = ''
		html += "<tr><th style='width: 5%'></th>"
		html += "<th style='width: 10%'>글번호</th>"
		html += "<th style='width: 10%'>게시글</th>"
		html += "<th style='width: 10%'>조회수</th>"
		html += "<th style='width: 10%'>글삭제</th>"
	
	for(var i = 0; i < dList.length; i++) {
		html += "<tr><td><input type='radio' name='chk' value='" + dList[i].discoverNo + "'/></td>"
		html += "<td>" + dList[i].discoverNo + "</td>"
		html += "<td><button class='funBtns discdet' type='button' disabled>상세보기</button></td>"
		html += "<td>" + dList[i].views + "</td>"
		html += "<td><button class='funBtns discdel' type='button' disabled>삭제</button></td>"
		html += "</tr>" 
	}	
		$("table").append(html)
		getDiscoverno()
		handleBtns()
}
</script>
