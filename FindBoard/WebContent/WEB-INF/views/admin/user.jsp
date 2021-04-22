<%@page import="dto.Usertb"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% List<Usertb> userList = (List<Usertb>) request.getAttribute("userList"); %>
<%@ include file="/WEB-INF/views/admin/common.jsp" %>

<script type="text/javascript">
$(document).ready(function () {
	$("#user").css({
		'background-color': 'white'
		, 'border': '1px solid  #A48654'
		, 'color': '#EA9A56'
	})
	
	$("input[type=radio]").change(function () {
		console.log("0. $this: " , $(this))
			
		var userno = $(this).val()
		console.log("1. 라디오 val(userno) 저장")
			
		$("#userno").attr("value", userno)
		console.log("2. 라디오 val(userno) hidden에 삽입")
			
		$(".funBtns").attr("disabled", false)
		console.log("3. 버튼 활성화")
	})
	
	$(".userdel").click(function () {
		if(confirm("회원을 삭제하시겠습니까?")) {
			$("form").submit()
		} else {
			return false
		}
	})
})
</script>	
	<form action="/admin/user" method="post">
	<table class="table">
	<tr>
		<th style="width: 5%"></th>
		<th style="width: 10%">회원번호</th>
		<th style="width: 20%">아이디</th>
		<th style="width: 20%">닉네임</th>
		<th style="width: 15%">탈퇴요청</th>
		<th style="width: 15%">DB삭제</th>
	</tr>
	<% for(int i = 0; i < userList.size(); i++) { %>
	<tr>
		<td><input type="radio" name="chk" value="<%= userList.get(i).getUserNo() %>" /></td>
		<td><%= userList.get(i).getUserNo() %></td>
		<td><%= userList.get(i).getUserId() %></td>
		<td><%= userList.get(i).getNick() %></td>
		<td>-</td>
		<td><button class="funBtns userdel" type="button" disabled>삭제</button></td>
	</tr>
	<% } %>
	</table>
	<input type="hidden" id="userno" name="userno" />
	</form>
	<div><%@ include file="/WEB-INF/views/admin/paging.jsp" %></div>
	</div>
</div>