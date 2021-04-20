<%@page import="dto.FindImg"%>
<%@page import="dto.FindBoard"%>
<%@page import="dto.Usertb"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%  Usertb u = (Usertb) request.getAttribute("param"); %>
<%	FindBoard b = (FindBoard) request.getAttribute("viewFindBoard"); %>
<%	List<FindImg> findImg = (List) request.getAttribute("findImg"); %>

<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<script type="text/javascript">
$(document).ready(function() {
	
	//목록버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "/find/list");
	});
	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
		$(location).attr("href", "/find/update?FindNo=<%=b.getFindNo() %>");
	});

	//삭제버튼 동작
	$("#btnDelete").click(function() {		
		if( confirm("게시글을 삭제하시겠습니까?") ) {
			$(location).attr("href", "/find/delete?FindNo=<%=b.getFindNo() %>");
		}		
	});
	
});
</script>

<style type="text/css">

#findheader{
	border:1px solid;
	width: 700px;
	float: left;
	margin: 10px 3px 10px 3px;
	text-align: center;

}
#findheader1{
 	border:1px solid; 
	width: 200px;
	float: left;
	margin: 10px 3px 10px 3px;
	text-align: center;

}

#mainimg{
		width: 500px;
		height: 300px;
		float: left;
}

#subimg{
/* 	border:1px solid; */
	width: 100px;
	height: 100px;
	float: left;
	margin: 5px 5px 5px 5px;
}

#findinfo{
	width: 450px;
	float: right;
	margin: 2%;
}
#findinfo1{
	border:2px solid;
	margin: 10px 3px 10px 3px;
	padding: 2%;
	text-align: center;
}

#content {
	width: 80%;
	height: 200px;
	margin: 10px 0 10px 5px;
}

#button{
	float: right;
	margin: 0px 0 0px 50px;
}

</style>



<div class="container">

<h1>반려동물 찾기  </h1>
<hr>



<div>

<div id="findheader"><%=b.getTitle() %></div>
<div id="findheader1"><%=request.getAttribute("nick") %></div>
<div id="findheader1"><%=b.getCreateDate() %></div>

</div>

<div id="findinfo">		
	<div id="findinfo1">반려동물 종류 : <%=b.getPetKinds() %></div>
	<div id="findinfo1">반려동물 이름 : <%=b.getPetName() %></div>
	<div id="findinfo1">반려동물 나이 : <%=b.getPetAge() %></div>
	<div id="findinfo1">잃어버린 위치 : <%=b.getLoc() %></div>
	<div id="findinfo1">이메일 : <%=request.getAttribute("email") %></div>
</div>


<table>
<%-- <%	if( findImg != null ) { %> --%>
<% int i = findImg.size(); %>
	<%	if(i <= 0) {%>	
		<tr>			
		<td >
			<img src=".." alt="main"  id="mainimg"/>
		</td>
		<td>
			<table>
				<tr>
					<td><img src=".." alt="sub1" id="subimg"/></td>
				</tr>
				<tr>
					<td><img src=".." alt="sub2" id="subimg"/></td>
				</tr>
				<tr>
					<td><img src=".." alt="sub3" id="subimg"/></td>
				</tr>
			</table>
		</td>
		
	<%}	if(i < 2 && i > 0) {%>
			
	<tr>
		<td>
			<img src="/upload/<%=findImg.get(0).getStoredImg() %>" alt="main"  id="mainimg"/>
		</td>
		<td>
			<table>
				<tr>
					<td><img src=".." alt="sub1" id="subimg"/></td>
				</tr>
				<tr>
					<td><img src=".." alt="sub2" id="subimg"/></td>
				</tr>
				<tr>
					<td><img src=".." alt="sub3" id="subimg"/></td>
				</tr>
			</table> 
		</td>
	<%}	if(i < 3 && i > 1) {%>
			
	<tr>
		<td>
			<img src="/upload/<%=findImg.get(0).getStoredImg() %>" alt="main"  id="mainimg"/>
		</td>
		<td>
			<table>
				<tr>
					<td><img src="/upload/<%=findImg.get(1).getStoredImg() %>" alt="sub1" id="subimg"/></td>
				</tr>
				<tr>
					<td><img src=".." alt="sub2" id="subimg"/></td>
				</tr>
				<tr>
					<td><img src=".." alt="sub3" id="subimg"/></td>
				</tr>
			</table> 
		</td>
		
	<%}	if(i < 4 && i > 2) {%>
			
	<tr>
		<td>
			<img src="/upload/<%=findImg.get(0).getStoredImg() %>" alt="main"  id="mainimg"/>
		</td>
		<td>
			<table>
				<tr>
					<td><img src="/upload/<%=findImg.get(1).getStoredImg() %>" alt="sub1" id="subimg"/></td>
				</tr>
				<tr>
					<td><img src="/upload/<%=findImg.get(2).getStoredImg() %>" alt="sub2" id="subimg"/></td>
				</tr>
				<tr>
					<td><img src=".." alt="sub3" id="subimg"/></td>
				</tr>
			</table> 
		</td>
	<%}	if(i < 5 && i > 3) {%>
			
	<tr>
		<td>
			<img src="/upload/<%=findImg.get(0).getStoredImg() %>" alt="main"  id="mainimg"/>
		</td>
		<td>
			<table>
				<tr>
					<td><img src="/upload/<%=findImg.get(1).getStoredImg() %>" alt="sub1" id="subimg"/></td>
				</tr>
				<tr>
					<td><img src="/upload/<%=findImg.get(2).getStoredImg() %>" alt="sub2" id="subimg"/></td>
				</tr>
				<tr>
					<td><img src="/upload/<%=findImg.get(3).getStoredImg() %>" alt="sub3" id="subimg"/></td>
				</tr>
			</table> 
		</td>
		
	<% } %><!-- if문 끝 -->
</table>

<div style="width: 1100px; height: 300px; border: 1px solid; margin: 10px 0 10px 0;">
<!-- <p style="width: 1100px; height: 300px; border: 1px solid; margin: 10px 0 10px 0; ">123</p> -->
<p ><%=b.getContent() %></p>
</div>


	

<div class="text-left">	
<%	if( request.getAttribute("nick").equals(session.getAttribute("nick")) ) { %>
	<button id="btnUpdate" class="btn btn-info">글수정</button>
	<button id="btnDelete" class="btn btn-danger">글삭제</button>
<%	} %>
</div>

<div>
<textarea id="content" name="content"></textarea>
	<div id="button">
	<button>댓글등록</button>
	</div>
</div>

<div class="text-center">	
	<button id="btnList" class="btn btn-primary">목록</button>
</div>
</div>



<%@ include file="/WEB-INF/views/layout/footer.jsp" %>
