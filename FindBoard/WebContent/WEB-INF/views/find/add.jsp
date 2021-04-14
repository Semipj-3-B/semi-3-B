<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<!-- 네이버 스마트 에디터2 -->
<script type="text/javascript" src="/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>

<script type="text/javascript">
function submitContents(elClickedObj) {
	oEditors.getById["content"].exec("UPDATE_CONTENTS_FIELD", [])
	
	try {
		elClickedObj.form.submit();
	} catch(e) {}
}
</script>
<script type="text/javascript">
$(document).ready(function () {
	$("#write").click(function () {
		submitContents( $("#write"))
		$("form").submit();
	})
	
	$("#cancle").click(function() {
		history.go(-1)
	})
	
})

window.onload = function () {
	
	var petkinds = document.getElementsByName("petkinds")
	console.log(petkinds)
}

</script>
<style type="text/css">
.container {
	width: 1200px;
	margin: 0 auto;
}

div#title {
	padding: 0 292.5px;
}


input#title { 
	/* border: none; */ 
	width: 585px;
	font-size: 20px;
	text-align: center;
	margin: 0 0 10px 0;
}

ul li { list-style: none; }


.pet-info .test1 .test2 {
    overflow: hidden;
    position: relative;
}

img.img-lg {
	border: 1px solid black;
	width: 300px;
	height: 300px;
	margin: 5px 0 0 8%;
	position: absolute
}

img.img-sm {
	border: 1px solid black;
	width: 150px;
	height: 100px;
	margin: 0 5px 5px 370px ;
}

input.pat {
	font-size: 14px;
	width: 270px;
}

textarea {
	width: 1000px;
	margin: 0 0 0 5%;
	height: 400px;
}

</style>

<div class="container">
<form action="/find/add" method="post" enctype="multipart/form-data">

<div id="title"><input id="title" name="title" /></div>

<div class="pet-info">
	<img class="img-lg">
<ul class="test1">
	<li class="test2">
	<img class="img-sm">
		<input class="pat" type="text" id="petname" name="petname" placeholder="반려동물 이름"/>
		<label><input type="radio" value="dog" name="petkinds" />강아지</label>
		<label><input type="radio" value="cat" name="petkinds" />고양이</label>
		<label><input type="radio" value="etc" name="petkinds" />기타 동물</label>
	</li>
	<li>
	<img class="img-sm">
		<input class="pat" type="text" id="petage" name="petage" placeholder="반려동물 나이"/>
		<select name="loc">
			<option value="0"  selected = "selected">지역</option>
			<option value="1">서울</option>
			<option value="2">경기</option>
		</select>
			<input type="text" id="detail-loc" name="detail-loc" placeholder="상세주소 입력"/>
	</li>
	<li>
	<img class="img-sm">
		<input type="text" id="detail-loc" name="detail-loc" placeholder="연락받으실 이메일번호"/>
		<input type="file" name="file"/>
	</li>
</ul>
</div>

<div style="padding: 0 0 0 40px;">
	<textarea id="content" name="content">본문</textarea>
</div>

<div>
	<button id="write">작성</button>
	<button id="cancle">취소</button>
</div>
</form>
</div><!-- div.container end -->

<script type="text/javascript">
var oEditors = []
nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "content",
	sSkinURI: "/resources/se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2"
})
</script>
</body>
</html>