<%@page import="dto.FindImg"%>
<%@page import="dto.FindBoard"%>
<%@page import="dto.Usertb"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%  Usertb u = (Usertb) request.getAttribute("param"); %>
<%	FindBoard b = (FindBoard) request.getAttribute("viewFindBoard"); %>
<%	FindImg findImg = (FindImg) request.getAttribute("findFile"); %>

<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<script type="text/javascript">
$(document).ready(function() {
	
	//목록버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "/find/list");
	});
	
// 	//수정버튼 동작
// 	$("#btnUpdate").click(function() {
<%-- 		$(location).attr("href", "/board/update?boardno=<%=b.getBoardno() %>"); --%>
// 	});

// 	//삭제버튼 동작
// 	$("#btnDelete").click(function() {
		
// 		if( confirm("게시글을 삭제하시겠습니까?") ) {
<%-- 			$(location).attr("href", "/board/delete?boardno=<%=b.getBoardno() %>"); --%>
// 		}
		
// 	});
	
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
	border:1px solid;
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

<h1>반려동물 찾기</h1>
<hr>

<!-- <table> -->

<!-- <tr> -->
<%-- <td colspan="3"><%=b.getTitle() %></td> --%>
<%-- <td><%=request.getAttribute("nick") %></td> --%>
<%-- <td><%=b.getCreateDate() %></td> --%>
<!-- </tr> -->

<!-- <tr> -->
<!-- <td colspan="2" rowspan="6">메인사진</td> -->
<!-- <td rowspan="2">사진1</td> -->
<%-- <td>반려동물 이름 : <%=b.getPetName() %></td> --%>
<!-- </tr> -->

<!-- <tr> -->
<%-- <td>반려동물 종류 : <%=b.getPetName() %></td> --%>
<!-- </tr> -->

<!-- <tr> -->
<!-- <td rowspan="1">사진2</td> -->
<%-- <td>반려동물 나이 : <%=b.getPetName() %></td> --%>
<%-- <td>잃어버린 곳 : <%=b.getLoc() %></td> --%>
<!-- </tr> -->

<!-- <tr> -->
<!-- <td rowspan="1">사진3</td> -->
<%-- <td>이메일 : <%=u.getEmail() %></td> --%>
<!-- </tr> -->

<%-- <tr><td colspan="4"><textarea><%=b.getContent() %></textarea></td></tr> --%>

<!-- </table> -->

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
	<div id="findinfo1">이메일 : </div>
</div>


<table >
	<tr>
		<td style="border: 1px solid;">
			<img src="..." alt="main"  id="mainimg"/>
		</td>
		<td>
			<table>
				<tr>
					<td><img src="..." alt="sub1" id="subimg"/></td>
				</tr>
				<tr>
					<td><img src="..." alt="sub2" id="subimg"/></td>
				</tr>
				<tr>
					<td><img src="..." alt="sub3" id="subimg"/></td>
				</tr>
			</table>
		</td>
</table>

<p style="width: 1100px; height: 300px; border: 1px solid; margin: 10px 0 10px 0;"><%=b.getContent() %></p>



	

<div class="text-left">	
<%-- <%	if( b.getUserid().equals( session.getAttribute("userid") )) { %> --%>
	<button id="btnUpdate" class="btn btn-info">글수정</button>
	<button id="btnDelete" class="btn btn-danger">글삭제</button>
<%-- <%	} %> --%>
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
