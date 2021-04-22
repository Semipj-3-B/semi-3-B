<%@page import="dto.FindBoard"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% List<FindBoard> findList = (List<FindBoard>) request.getAttribute("findList"); %>

<%@ include file="/WEB-INF/views/admin/common.jsp" %>
<style type="text/css">
.btns {
	border: none;
	background-color: white;
	transition-duration: 0.4s;
	color: #A48654;
}
.btns:hover {
	background-color: #A48654;
	color: white;
}
</style>
<script type="text/javascript">
$(document).ready(function () {
	
	var findno = 0
	
	//findno를 post로 보내기 위한 코드
	$("input[type=radio]").change(function () {
		console.log("0. $this: " , $(this))
			
		findno = $(this).val()
		console.log("1. 라디오 val(findno) 저장")
			
		$("#findno").attr("value", findno)
		console.log("2. 라디오 val(findno) hidden에 삽입")
			
		$(".btns").attr("disabled", false)
		console.log("3. 버튼 활성화")
	})
	
	$(".detail").click(function () {
		 window.open("/find/read?FindNo="+findno, "_blank", "width=400px height=200px" )
	})
	
	$(".del").click(function () {
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
		<th style="width: 10%">글번호</th>
		<th style="width: 15%">게시글</th>
		<th style="width: 10%">조회수</th>
		<th style="width: 15%">글삭제</th>
	</tr>
	<% for(int i = 0; i < findList.size(); i++) { %>
	<tr>
		<td><input type="radio" name="chk" value="<%= findList.get(i).getFindNo() %>" /></td>
		<td><%= findList.get(i).getFindNo() %></td>
		<td><button class="btns detail" type="button" id="detail<%=i+1 %>" disabled>상세보기</button></td>
		<td><%= findList.get(i).getViews() %></td>
		<td><button class="btns del" type="button" id="del<%=i+1 %>" disabled>삭제</button></td>
	</tr>
	<% } %>
	</table>
	<input type="hidden" id="findno" name="findno" />
	</form>	
	<div><%@ include file="/WEB-INF/views/admin/paging.jsp" %></div>
	</div>
</div>