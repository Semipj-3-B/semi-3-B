<%@page import="dto.DiscoverImg"%>
<%@page import="dto.DiscoverBoard"%>
<%@page import="dto.Usertb"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<% List<DiscoverImg> discoverImg = (List) request.getAttribute("discoverImg"); %>  
<%  Usertb u = (Usertb) request.getAttribute("param"); %>
<%	DiscoverBoard b = (DiscoverBoard) request.getAttribute("viewDiscoverBoard"); %>


<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<!-- 스마트에디터 2 -->
<script type="text/javascript"
 src="/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<!-- <form>태그의 submit을 수행하면 editor에 작성한 내용을 <textarea>에 반영 -->
<script type="text/javascript">
function submitContents( elClickedObj ) {
	
	//에디터의 내용을 #content에 반영한다
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", []);
	
	try {
		// <form>태그의 submit 수행
		elClickedObj.form.submit();
	} catch(e) {}
	
}
</script>

<script type="text/javascript">

$(document).ready(function () {

	const upBtn = document.querySelector('.browse')
	const realupInput = document.querySelector('#upfile')

	upBtn.addEventListener('click', function () {
		realupInput.click()
	})

	$("#upfile").on('change', uploadImg)
	
// 	$("#upfile").on('click', function() {
		
// 		 
// 		 $("#subimages").replaceWith("<img id="subimages" / >");
		
// 	}
	
	$("#write").click(function () {
		submitContents($("#write"))
		$("form").submit();
	})
	
})

function uploadImg(e) {
	
	 changeimg = 1;

	 
//	$("#mainimg").empty()	
//	$("#subimg1").empty()	
//	$("#subimg2").empty()	
//	$("#subimg3").empty()				 
			 
			 

	 $("img").replaceWith("<img>");

	
	var files = null
	if(e.target.files != null) {
		files = e.target.files
	}
	
	//파일 개수 검사
	if(files.length > 4 ) {
		alert('최대 4장까지 업로드 할 수 있습니다.')
		e.target.value = null	/* 파일 업로드 초기화 */
		return false
	}
	
	for(var i = 0; i < files.length; i++) {
		//확장자 검사
		if(!files[i].type.match("image/jpeg")) {
			alert('jpg 또는 jpeg 확장자만 업로드 가능합니다.')
			e.target.value = null
			return false
		}
		
		//크기 검사
		var fileSize = files[i].size
		var sizeKb = fileSize / 1024
		var sizeMb = sizeKb / 1024
		var totalMaxSize = 10 * 1024 * 1024
		var totalFileSize = 0
		
		//파일이 1KB 미만일 때
		if(fileSize < 1024) {
			alert('1KB 이상의 사진을 업로드 할 수 있습니다.')
			return false
		}
		
		//파일이 10MB 이하일 때
		if(fileSize < totalMaxSize) {
			totalFileSize += fileSize
		}
		
		//모든 파일 합의 크기가 10MB 초과일 때
		if(totalFileSize > totalMaxSize) {
			alert('10MB까지 업로드 할 수 있습니다.')
			return false
		}
		
		//이미지 미리보기
		var reader = new FileReader()
		switch(i) {
			case 0:
				reader.onload = function(ev){
					$("#mainimg").children().attr({
						"src" : ev.target.result
						, "width" : "400px"
						, "height" : "310px"
					})
					//이미지 영역 초기화
// 					$("#mainimg").empty()	
// 					$("#subimg1").empty()	
// 					$("#subimg2").empty()	
// 					$("#subimg3").empty()	
				}
				break
			case 1:
				reader.onload = function(ev){
					$("#subimg1").children().attr({
						"src" : ev.target.result
						, "width" : "148px;"
						, "height" : "97px"
					})
					//이미지 영역 초기화
// 					$("#mainimg").empty()	
// 					$("#subimg1").empty()	
// 					$("#subimg2").empty()	
// 					$("#subimg3").empty()	
				}
				break
			case 2:
				reader.onload = function(ev){
					$("#subimg2").children().attr({
						"src" : ev.target.result
						, "width" : "148px;"
						, "height" : "97px"
					})
					//이미지 영역 초기화
// 					$("#mainimg").empty()	
// 					$("#subimg1").empty()	
// 					$("#subimg2").empty()	
// 					$("#subimg3").empty()	
				}
				break
			case 3:
				reader.onload = function(ev){
					$("#subimg3").children().attr({
						"src" : ev.target.result
						, "width" : "148px;"
						, "height" : "97px"
					})
				}
				break
			default: return false
			
			} //switch END
			
		reader.readAsDataURL(files[i])
		
	} //for() END
	
} //uploadImg() END
</script>

<style type="text/css">

/* #mainimg{ */
/* 		width: 500px; */
/* 		height: 300px; */
/* 		float: left; */
/* } */

/* #subimg1{ */
/* 	border:1px solid; */
/* 	width: 100px; */
/* 	height: 100px; */
/* 	float: left; */
/* 	margin: 5px 5px 5px 5px; */
/* } */
/* #subimg2{ */
/* 	border:1px solid; */
/* 	width: 100px; */
/* 	height: 100px; */
/* 	float: left; */
/* 	margin: 5px 5px 5px 5px; */
/* } */
/* #subimg3{ */
/* 	border:1px solid; */
/* 	width: 100px; */
/* 	height: 100px; */
/* 	float: left; */
/* 	margin: 5px 5px 5px 5px; */
/* } */

#findheader{
	border:1px solid;
	width: 700px;
	float: left;
	margin: 10px 3px 10px 3px;
	text-align: center;

}

#title{

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

#subimages{
	width: 148px;
	height: 97px;
	border-radius: 5px;
}
#mainimages{
	width: 398px;
	height: 307px;
	border-radius: 5px;
}

.mainimg{
	position: absolute;
	width: 400px;
	height: 310px;
	border: 1px solid #A48654;
	border-radius: 5px;
}

.subimg-grid {
	display: inline-grid;
	grid-template-rows: 100px 100px 100px;
	grid-row-gap: 3px;
	margin: 0 0 0 405px;
	/* border: 1px solid; */
	width: 150px;
	height: 309px;
}

.subimg-grid div {
	border: 1px solid #A48654;
	border-radius: 5px;
}


table table tr td img{
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
	width: 100%;
	height: 250px;
}

#button{
	float: right;
	margin: 0px 0 0px 50px;
}

</style>


<div class="container">

<h3>반려동물 발견</h3>
<hr>

<!-- //enctype="multipart/form-data" 파일 업로드 처리-->
<form action="/discover/update" method="post" enctype="multipart/form-data" >

<input type ="hidden" name ="DiscoverNo" id ="DiscoverNo" value="<%=request.getParameter("DiscoverNo") %>" />
<%-- <input type="hidden" id="nick" value="<%=request.getAttribute("nick") %>"> --%>
<%-- <input type="hidden" id="userno" value="<%=session.getAttribute("userno")%>"> --%>
	
<div>
<div id="findheader"><input type ="text" name = "title" id="title" value="<%=b.getTitle() %>"/></div>
<div id="findheader1"><%=request.getAttribute("nick") %></div>
<div id="findheader1"><%=b.getUpdateDate() %></div>
</div>

<div id="findinfo">		
	<div id="findinfo1">반려동물 종류 : <%=b.getPetKinds() %></div>
	<div id="findinfo1">반려동물 이름 : <%=b.getPetName() %></div>
	<div id="findinfo1">반려동물 나이 : <%=b.getPetAge() %></div>
	<div id="findinfo1">발견한 위치 : <%=b.getLoc() %></div>
	<div id="findinfo1">이메일 : <%=request.getAttribute("email") %></div>
</div>




<%-- <% int changeimg = 0; %> --%>

<% int i = discoverImg.size(); %>
<%-- <% int i = 0; %> --%>

<%-- <%	if(  changeimg <= 1 ) { %> --%>
	<%	if(i <= 0) {%>	
	
	<div id="mainimg" class="mainimg"><img id="mainimages"></div>
	<div class="subimg-grid">
		<div id="subimg1"><img id="subimages"></div>
		<div id="subimg2"><img id="subimages"></div>
		<div id="subimg3"><img id="subimages"></div>
	</div>
		
	<%}	if(i < 2 && i > 0) {%>
	
	<div id="mainimg" class="mainimg"><img src="/upload/<%=discoverImg.get(0).getStoredImg() %>" id="mainimages"/></div>
	<div class="subimg-grid">
		<div id="subimg1"><img id="subimages"></div>
		<div id="subimg2"><img id="subimages"></div>
		<div id="subimg3"><img id="subimages"></div>
	</div>
	
	<%}	if(i < 3 && i > 1) {%>

	<div id="mainimg" class="mainimg"><img src="/upload/<%=discoverImg.get(0).getStoredImg() %>" id="mainimages"/></div>
	<div class="subimg-grid">
		<div id="subimg1"><img src="/upload/<%=discoverImg.get(1).getStoredImg() %>" id="subimages"/></div>
		<div id="subimg2"><img id="subimages"></div>
		<div id="subimg3"><img id="subimages"></div>
	</div>
	
	<%}	if(i < 4 && i > 2) {%>
		
	<div id="mainimg" class="mainimg"><img src="/upload/<%=discoverImg.get(0).getStoredImg() %>" id="mainimages"/></div>
	<div class="subimg-grid">
		<div id="subimg1"><img src="/upload/<%=discoverImg.get(1).getStoredImg() %>" id="subimages" /></div>
		<div id="subimg2"><img src="/upload/<%=discoverImg.get(2).getStoredImg() %>" id="subimages" /></div>
		<div id="subimg3"><img><img id="subimages"></div>
	</div>	
	
	<%}	if(i < 5 && i > 3) {%>
	<div id="mainimg" class="mainimg"><img src="/upload/<%=discoverImg.get(0).getStoredImg() %>" id="mainimages"/></div>
	<div class="subimg-grid">
		<div id="subimg1"><img src="/upload/<%=discoverImg.get(1).getStoredImg() %>"  id="subimages"/></div>
		<div id="subimg2"><img src="/upload/<%=discoverImg.get(2).getStoredImg() %>"  id="subimages"/></div>
		<div id="subimg3"><img src="/upload/<%=discoverImg.get(3).getStoredImg() %>"  id="subimages"/></div>
	</div>	

	<% } %><!-- if문 끝 -->
	
<%-- <% }  else {%><!-- 버튼 클릭 IF문 끝 --> --%>
<!-- 	바뀌어서 이 말이 들어가나 -->
<!-- 왜 인지 모르겠는데 글쓰기 시 이미지 하나만 들어가서 -->
<!-- 제대로 초기화 되고 바뀌는지 알수 없음 -->
<!-- 하지만 안바뀌는거 같음 -->
<!-- ajax가 아니기때문에 화면 전환은 안될꺼같음 -->
<!-- if문의 모든이미지를 이걸로 처리하면? -->
<!-- if문을 쓰는 의미가 있나 -->
<!-- 	<div id="mainimg" class="mainimg"></div> -->
<!-- 	<div class="subimg-grid"> -->
<!-- 		<div id="subimg1"></div> -->
<!-- 		<div id="subimg2"></div> -->
<!-- 		<div id="subimg3"></div> -->
<!-- 	</div> -->
	
<%-- <% } %><!-- if문 끝 --> --%>


<input type="file" id="upfile" name="upfile" multiple="multiple" accept="image/jpeg" style="display:none;"/><br>
<button class="btn browse" type="button">사진 업로드</button>

<div><textarea id="content" name="content"><%=b.getContent() %></textarea></div>




<div style="width: 120px; margin: 5px 45%">
	<button id="write" class="btn btn-info">작성</button>
	<button id="cancle" class="btn btn-danger" onclick="history.go(-1)">취소</button>
</div>

</form>


</div>

<!-- .container -->


<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content", //에디터가 적용될 <textarea>의 id를 입력
	sSkinURI: "/resources/se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2"
})
</script>

<%@ include file="/WEB-INF/views/layout/footer.jsp" %>














