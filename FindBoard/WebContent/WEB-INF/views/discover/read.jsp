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

<script type="text/javascript">
$(document).ready(function() {
	
	//목록버튼 동작
	$("#btnList").click(function() {
		$(location).attr("href", "/discover/list");
	});
	
	//수정버튼 동작
	$("#btnUpdate").click(function() {
		$(location).attr("href", "/discover/update?DiscoverNo=<%=b.getDiscoverNo() %>");
	});

	//삭제버튼 동작
	$("#btnDelete").click(function() {		
		if( confirm("게시글을 삭제하시겠습니까?") ) {
			$(location).attr("href", "/discover/delete?DiscoverNo=<%=b.getDiscoverNo() %>");
		}		
	});
	
});
</script>

<script type="text/javascript">

$(document).ready(function () {
	
	
$("#subimages").on('click', function (){
	
	var imageLocation = this.getAttribute("src");
	
	lightboxImage.setAttribute("src", imageLocation);
	
	lightbox.style.display = "block";
	
	
})

	/* 팝업 클릭시 창닫음 */
$("#lightbox").on('click', function() {
	lightbox.style.display = "none";
});


// /* 클릭시 이미지저장경로와 display속성 부여 */
// function showPopup() {
// 	var imageLocation = this.getAttribute("src");
// 	lightboxImage.setAttribute("src", imageLocation);
// 	lightbox.style.display = "block";

// 	/* 팝업 클릭시 창닫음 */
// 	lightbox.onclick = function() {
// 		lightbox.style.display = "none";
// 	}
// }


})

</script>

<!-- 댓글 관련 -->
<script type="text/javascript">

/* 댓글목록 조회 */
$(document).ready(function() {
	commentlist();
})

// $("#commentwrap").click(

/* 댓글 목록조회 */
function commentlist() {
	
	var discoverNo = $("#DiscoverNo").val();
	
	$.ajax({
		type : 'get',
		url : '/discover/commentlist',
		data : {'discoverNo' : discoverNo},
		dataType : 'json',
		success : function(data) {
			var jsontext = JSON.stringify(data);
			var commentlist = JSON.parse(jsontext);

			for(var i=0; i<commentlist.length; i++) {
				
				var html = '';
				
				html += "<div class='commentwrap'> <span class='commnetNick'>"
				html += commentlist[i].nick
				html += "</span> <span class='commentDate'>"
				html += commentlist[i].commentDate
				html += "</span>"
				html += "<input type='button' onclick = 'commentUpdateTrans(" + commentlist[i].commentNo + ")' id='updatebtn" + commentlist[i].commentNo + "' value='수정'/>"
				html += "<input type='button' onclick = 'commentDelete(" + commentlist[i].commentNo + ")' id='deletebtn" + commentlist[i].commentNo + "' value='삭제'/>"
				html += "<br> <div id='comwrap" + commentlist[i].commentNo + "'> <span class='commentText' id='commentText" + commentlist[i].commentNo + "'> "
				html += commentlist[i].commentText
				html += "</span> </div>"
				
				$("#commentwrap").append(html);
			}/* for문 끝 */
			
		}/* success끝 */
		
	})/* ajax끝 */
	
}//click 이벤트 끝	


$(document).ready(function() {



/* 댓글 추가 */
$("#commentUpdatebtn").click(function() {
	
	
	var discoverNo = $("#DiscoverNo").val();
	var comment = $("#comment").val();
	var nick = $("#nick").val();
	var userno = $("#userno").val();

	console.log(discoverNo);
	
	$.ajax({
		type : 'post',
		url : '/discover/commentinsert',
		data : {'discoverNo' : discoverNo
				, 'comment' : comment
				, 'nick' : nick
				, 'userno' : userno},
		success : function() {
			$("#comment").val("");
			$("#commentwrap").html("");
			
			commentlist();
		}	//success
		
	})	//ajax
	
})//click

})//document

/* 댓글 삭제 */
function commentDelete(commentno) {
	
	$.ajax({
		type : 'post',
		url : '/discover/commentdelete',
		data : {'commentno' : commentno},
		success : function() {
			
			$("#commentwrap").html("");
			
			commentlist();
		}
			
	})
}

/* 댓글 수정 */
function commentUpdateTrans(commentno) {
	
	var contentText = $('#commentText' + commentno).text();
	
	$('#updatebtn' + commentno).css('display', 'none');
	$('#deletebtn' + commentno).css('display', 'none');
	
	var html = '';
	
	html += "<input type='text' id='comupdate' value='" + contentText + "' style='width:300px; height:40px;'/>";
	html += "<input type='button' id='updateConfirm' onclick='commentUpdate(" + commentno + ")' value='수정'/>"
	html += "<input type='button' id='canclebtn' onclick='updatecancle()' value='취소'/>"
	
	$('#comwrap' + commentno).html(html)
	
	commentUpdateFocus();
}


/* 수정버튼 클릭 */
function commentUpdate(commentno) {
	
	var comment = $('#comupdate').val();
	
	$.ajax({
		type: 'post',
		url: '/discover/commentupdate',
		data: { 'commentno' : commentno
				,'comment' : comment},
		success : function() {
			
			$("#commentwrap").html("");
			
			commentlist();
		}
	})
}

/* 취소버튼 클릭 */
function updatecancle() {
	
	$("#commentwrap").html("");
	
	commentlist();
}


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
	width: 150px;
	height: 309px;
}

.subimg-grid div {
	border: 1px solid #A48654;
	border-radius: 5px;
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

#comment {
width : 500px;
height : 70px;
}

#button{
	float: right;
	margin: 0px 0 0px 50px;
}

#lightbox {
  position: fixed;
  width:50%;
  height:50%;
  background-color:rgba(0,0,0,0.7); 
  top:20%;
  left:20%;
  display:none;
}

#lightbox img {
  position:absolute; 
  top:50%;
  left:50%;
  width:100%;
  height:100%;
  transform:translate(-50%, -50%);
  border:1px solid #eee;      
}

</style>



<div class="container">

<h1>반려동물 찾기</h1>
<hr>

<input type ="hidden" name ="DiscoverNo" id ="DiscoverNo" value="<%=request.getParameter("DiscoverNo") %>" />
<input type="hidden" id="nick" value="<%=session.getAttribute("nick") %>">
<input type="hidden" id="userno" value="<%=session.getAttribute("userno")%>">
	
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



<%-- <%	if( discoverImg != null ) { %> --%>
<% int i = discoverImg.size(); %>
	<%	if(i <= 0) {%>	
	
	<div id="mainimg" class="mainimg"></div>
	<div class="subimg-grid">
		<div id="subimg1"></div>
		<div id="subimg2"></div>
		<div id="subimg3"></div>
	</div>
		<div id="lightbox">
		<img alt="" src="" id="lightboxImage">
	</div>
	<%}	if(i < 2 && i > 0) {%>
	
	<div id="mainimg" class="mainimg"><img src="/upload/<%=discoverImg.get(0).getStoredImg() %>" id="mainimages"/></div>
	<div class="subimg-grid">
<!-- 		<div id="subimg1"></div> -->
<!-- 		<div id="subimg2"></div> -->
<!-- 		<div id="subimg3"></div> -->
	</div>
		<div id="lightbox">
		<img alt="" src="" id="lightboxImage" />
	</div>
	<%}	if(i < 3 && i > 1) {%>

	<div id="mainimg" class="mainimg"><img src="/upload/<%=discoverImg.get(0).getStoredImg() %>" id="mainimages"/></div>
	<div class="subimg-grid">
		<div id="subimg1"><img src="/upload/<%=discoverImg.get(1).getStoredImg() %>" id="subimages" class="subimages" /></div>
<!-- 		<div id="subimg2"></div> -->
<!-- 		<div id="subimg3"></div> -->
	</div>
		<div id="lightbox">
		<img alt="" src="" id="lightboxImage">
	</div>
	<%}	if(i < 4 && i > 2) {%>
		
	<div id="mainimg" class="mainimg"><img src="/upload/<%=discoverImg.get(0).getStoredImg() %>" id="mainimages"/></div>
	<div class="subimg-grid">
		<div id="subimg1"><img src="/upload/<%=discoverImg.get(1).getStoredImg() %>" id="subimages" class="subimages" /></div>
		<div id="subimg2"><img src="/upload/<%=discoverImg.get(2).getStoredImg() %>" id="subimages" class="subimages" /></div>
<!-- 		<div id="subimg3"></div> -->
	</div>	
		<div id="lightbox">
		<img alt="" src="" id="lightboxImage">
	</div>
	<%}	if(i < 5 && i > 3) {%>
	<div id="mainimg" class="mainimg"><img src="/upload/<%=discoverImg.get(0).getStoredImg() %>" id="mainimages"/></div>
	<div class="subimg-grid">
		<div id="subimg1"><img src="/upload/<%=discoverImg.get(1).getStoredImg() %>"  id="subimages" class="subimages" /></div>
		<div id="subimg2"><img src="/upload/<%=discoverImg.get(2).getStoredImg() %>"  id="subimages" class="subimages" /></div>
		<div id="subimg3"><img src="/upload/<%=discoverImg.get(3).getStoredImg() %>"  id="subimages" class="subimages" /></div>
	</div>	
	<div id="lightbox">
		<img alt="" src="" id="lightboxImage" />
	</div>

	<% } %><!-- if문 끝 -->


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
<hr>

<div>
	<h3>댓글</h3>
	
	<input type="text" id=comment name="comment" />
	<input type="button" id="commentUpdatebtn" value="댓글 등록"/>
	
	<div id="commentwrap">
<!-- 	<h1>댓글목록</h1> -->
	</div>
</div>

<div class="text-center">	
	<button id="btnList" class="btn btn-primary">목록</button>
</div>
</div>



<%@ include file="/WEB-INF/views/layout/footer.jsp" %>








<!-- 	<div class="imgbox"> -->
<%-- 		<% for( int i=0; i<file.size(); i++) { %> --%>
<%-- 			<img src="<%=request.getContextPath()%>/upload/<%=file.get(i).getStoredName() %>" --%>
<%-- 				data-src="<%=request.getContextPath()%>/upload/<%=file.get(i).getStoredName() %>" --%>
<!-- 				 class="pic"> -->
<%-- 		<% } %> --%>
<!-- 		<br> -->
<!-- 	</div> -->
<!-- 	<div id="lightbox"> -->
<!-- 		<img alt="" src="" id="lightboxImage"> -->
<!-- 	</div> -->










