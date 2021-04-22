<%@page import="dto.FindBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% List<FindBoard> findList = (List<FindBoard>) request.getAttribute("findList"); %>
<%@ include file="/WEB-INF/views/admin/common.jsp" %>

<script type="text/javascript">
$(document).ready(function () {
	
	$("#find").css({
		'background-color': 'white'
		, 'border': '1px solid  #A48654'
		, 'color': '#EA9A56'
	})
	
	var findno = 0
	
	//findno를 post로 보내기 위한 코드
	$("input[type=radio]").change(function () {
		findno = $(this).val()
		$("#findno").attr("value", findno)
		$(".funBtns").attr("disabled", false)
	})
	
	$(".finddet").click(function () {
		 window.open("/find/read?FindNo="+findno, "_blank", "width=400px height=200px" )
	})
	
	$(".finddel").click(function () {
		if(confirm("글을 삭제하시겠습니까?")) {
			$("form").submit()
		} else {
			return false
		}
	})
}) 
</script>	
	<form action="/admin/find" method="post">	
	<table class="table">
	<tr>
		<th style="width: 5%"></th>
		<th style="width: 5%">글번호</th>
		<th style="width: 5%">게시글</th>
		<th style="width: 5%">조회수</th>
		<th style="width: 5%">글삭제</th>
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
	<div><%@ include file="/WEB-INF/views/admin/paging.jsp" %></div>
	</div>
</div>