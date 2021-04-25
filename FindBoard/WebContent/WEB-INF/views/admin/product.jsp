<%@page import="dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% List<Product> pList = (List<Product>) request.getAttribute("productList"); %>
<%@ include file="/WEB-INF/views/admin/common.jsp" %>
<style type="text/css">
table {width: 900px;}
th, td { text-align:center;}
</style>
<script type="text/javascript">
$(document).ready(function () {
	var prodId = 0
	
	//카테고리 미선택시 동작
	getProdId()
	btnsEvent()
	
	$("#product").css({
		'background-color': 'white'
		, 'border': '1px solid  #A48654'
		, 'color': '#EA9A56'
	})
	
	//카테고리 별 조회
	$("select").change(function () {
		$("table").empty()
		
		var categoryId = $("select").val()
		$.ajax({
			type: "get"
			, url: "/admin/product"
			, data: {'categoryId': categoryId}
			, dataType: 'json'
			, success: function (data) {viewProducts(data)}
			, error: function () {console.log("실패")}
		})
	})
	
})

//체크된 radio의 상품 ID 얻어오기
function getProdId() {
	$("input[type=radio]").change(function () {
		prodId = $(this).val()
		$("#prodId").attr("value", prodId)
		$(".funBtns").attr("disabled", false)
	})
}

//버튼 이벤트
function btnsEvent() {
	//수정하기
	$(".prodmod").click(function () {
		 window.open("/product/edit?productId="+prodId, "_blank", "width=400px height=200px")
	})
	
	//삭제하기
	$(".proddel").click(function () {
		if(confirm("상품을 삭제하시겠습니까?")) {
			$("form").submit()
		} else {
			return false
		}
	})
	
	//작성하기
	$("#write").click(function () {
		window.open("/product/write", "_blank")
	})
}
//카테고리별 목록 조회 
function viewProducts(data) {
	var pList = JSON.parse(JSON.stringify(data))
	console.log(pList)
	
	$("table").empty()
	$("#paging").empty()
	$(".contents").css({'border': 'none'})
	var html = ''
		html += "<tr><th style='width: 5%'></th>"
		html += "<th style='width: 10%'>상품ID</th>"
		html += "<th style='width: 20%'>상품명</th>"
		html += "<th style='width: 15%'>단가</th>"
		html += "<th style='width: 15%'>상세페이지</th>"
		html += "<th style='width: 10%'>DB</th></tr>"
	
	for(var i = 0; i < pList.length; i++) {
		html += "<tr><td><input type='radio' name='chk' value='" + pList[i].productId + "'/></td>"
		html += "<td>" + pList[i].productId + "</td>"
		html += "<td>" + pList[i].productName + "</td>"
		html += "<td>" + pList[i].price + "</td>"
		html += "<td><button class='funBtns prodmod' type='button' disabled>수정</button></td>"
		html += "<td><button class='funBtns proddel' type='button' disabled>삭제</button></td>"
		html += "</tr>" 
	}	
		$("table").append(html)
		getProdId()
		btnsEvent()
}
</script>	
	<form action="/admin/product" method="post">
	<select name="category">
		<option value="0" selected>카테고리 선택</option>
		<option value="1">반려동물 용품</option>
		<option value="2">악세서리</option>
		<option value="3">폰케이스</option>
	</select>
	<button type="button" id="write">제품 등록</button>
	<table class="table">
	<tr>
		<th style="width: 5%"></th>
		<th style="width: 10%">상품ID</th>
		<th style="width: 20%">상품명</th>
		<th style="width: 15%">단가</th>
		<th style="width: 10%">상세페이지</th>
		<th style="width: 10%">DB</th>
	</tr>
	<% for(int i = 0; i < pList.size(); i++) { %>
	<tr>
		<td><input type="radio" name="chk" value="<%= pList.get(i).getProductId() %>" /></td>
		<td><%= pList.get(i).getProductId() %></td>
		<td><%= pList.get(i).getProductName() %></td>
		<td><%= pList.get(i).getPrice() %></td>
		<td><button class="funBtns prodmod" type="button" disabled>수정</button></td>
		<td><button class="funBtns proddel" type="button" disabled>삭제</button></td>
	</tr>
	<% } %>
	</table>
	<input type="hidden" id="prodId" name="prodId" />
	</form>
	<div><%@ include file="/WEB-INF/views/layout/adminPaging.jsp" %></div>
	</div>
</div>