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
	
	//사진업로드 버튼 클릭 -> input file 클릭으로 이벤트 생성
	const upBtn = document.querySelector('.browse')
	const realupInput = document.querySelector('#upfile')

	upBtn.addEventListener('click', function () {
		realupInput.click()
	})

	//파일을 첨부했을 때
	$("#upfile").on('change', uploadImg)
	
	//작성 버튼 눌렀을 때
	$("#write").click(function () {
		
		//NOT NULL CHECK
		if(!checkInfo()) {
			alert('필수 사항을 모두 입력해주세요.')
			return false
		}
		
		submitContents($("#write"))
		
		//첨부 파일이 없는 경우
		var imges = $("#mainimg").children("img").attr('src')
		if( imges == null || imges == '') {
			if(confirm("사진 첨부 없이 글을 등록하시겠습니까?")){
				$("form").submit();
			}
		} else	$("form").submit();
	})
	
})

function getByteLength(name) {
	var b = 0	//byte
	var c = 0	//char
	
	for(j = 0; j < name.length; j++) {
		c = name.charCodeAt(j)
		if(c >> 11) c = 3
		else if(c >> 7) c = 2
		else c = 1
		b+=c	
	}
	return b
}

function uploadImg(e) {
	//이미지 영역 초기화
 	$("#mainimg").empty()	
	$("#subimg1").empty()	
	$("#subimg2").empty()	
	$("#subimg3").empty()
	
	var files = null
	if(e.target.files != null) {
		files = e.target.files
	}
	
	//파일 개수 검사
	if(files.length > 4 ) {
		alert('최대 4장까지 업로드 할 수 있습니다.')
		return false
	}
	
	for(var i = 0; i < files.length; i++) {
		//확장자 검사
		if(!files[i].type.match("image/jpeg")) {
			alert('jpg 또는 jpeg 확장자만 업로드 가능합니다.')
			return false
		}
		
		//확장자 제거한 파일명
		var lastDot = files[i].name.lastIndexOf('.')
		var fileName = files[i].name.substring(0, lastDot)
		
		//파일명 길이 검사	
		var byteLen = getByteLength(fileName)
		if(byteLen > 30) {
			alert('파일명은 한글 10자 또는 영문 30자 이하로 가능합니다.')
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
					$("<img>").attr({
						"src" : ev.target.result
						, "width" : "397px"
						, "height" : "307px"
					}).appendTo($("#mainimg"))
				}
				break
			case 1:
				reader.onload = function(ev){
					$("<img>").attr({
						"src" : ev.target.result
						, "width" : "147px"
						, "height" : "97px"
					}).appendTo($("#subimg1"))
				}
				break
			case 2:
				reader.onload = function(ev){
					$("<img>").attr({
						"src" : ev.target.result
						, "width" : "147px"
						, "height" : "97px"
					}).appendTo($("#subimg2"))
				}
				break
			case 3:
				reader.onload = function(ev){
					$("<img>").attr({
						"src" : ev.target.result
						, "width" : "147px"
						, "height" : "97px"
					}).appendTo($("#subimg3"))
				}
				break
			default: return false
			
			} //switch END
			
		reader.readAsDataURL(files[i])
		
	} //for() END
	
} //uploadImg() END

//NOT NULL CHECK: petname, petkinds, title, loc
function checkInfo() {
	var pname = $("#petname").val()
	var pkinds = $("#petkinds").children("input").val()
	var title = $("#title").val()
	var loc = $("#loc").val()
	var flag = true
	
	if(pname == null || pname == '') flag = false
	if(pkinds == null || pkinds == '') flag = false
	if(title == null || title == '') flag = false
	if(loc == null || loc == '') flag = false
	
	return flag
}
</script>
<style type="text/css">
input[type=text]:focus{outline: none;}
input[type=text] {border: none;}

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

.petinfo-grid {
	display: grid;
	grid-template-rows: 50px 40px 50px 50px 50px 50px;
	margin: 0 5% 0 0;
	/* border: 1px solid; */
	width: 450px;
	height: 240px;
	float: right;
	align-items: center;
	font-size: 14px;
	text-align: center;
}

input.pat {
	font-size: 14px;
	width: 270px;
	border-bottom: 2px solid #EBAA5F;
	text-align: center;
}

#detail-loc {border-bottom: 1px solid #EBAA5F;}

.browse {
	margin: 5px 0 10px 150px;
	border-color: #A48654;
	background-color: white;
	transition-duration: 0.4s;
}

.browse:hover {
  background-color: #A48654;
  color: white;
}

textarea {
	width: 1000px;
	height: 400px;
}

#write { background-color: #EBC680; }
#subBtn button {transition-duration: 0.4s;}
#subBtn button:hover{
	box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24), 0 17px 50px 0 rgba(0,0,0,0.19);
}	

</style>

<div class="container">
<form action="/discover/add" method="post" enctype="multipart/form-data">

<input type ="hidden" name ="DiscoverNo" id ="DiscoverNo" value="<%=request.getParameter("DiscoverNo") %>" />
<input type="hidden" id="nick" value="<%=session.getAttribute("nick") %>">
<input type="hidden" id="userno" value="<%=session.getAttribute("userno")%>">

	<h1><input type="text" id="title" name="title" placeholder="제목입력" /></h1>
	<hr> <!-- style="border: 3px solid #EBC680;" -->
	
	<!-- img -->
	<div id="mainimg" class="mainimg"></div>
	<div class="subimg-grid">
		<div id="subimg1"></div>
		<div id="subimg2"></div>
		<div id="subimg3"></div>
	</div>
	
	<div class="petinfo-grid">
		<div id=msg style='color:#EBAA5F;'><h5>※제목 / 이름 / 동물 종류 / 지역은 필수 입력사항입니다.※</h5></div>
		<div>
			<label for="petname">반려동물이름</label>
			<input class="pat" type="text" id="petname" name="petname" />
		</div>
		<div style="text-align: center;" id="petkinds">
			<input type="radio" value="dog" name="petkinds" />강아지
			<input type="radio" value="cat" name="petkinds" />고양이
			<input type="radio" value="etc" name="petkinds" />기타 동물
		</div>
		<div>
			<label for="petage">반려동물나이</label>
			<input class="pat" type="text" id="petage" name="petage"/>
		</div>
		<div>
			<label for="detail-loc">잃어버린 곳</label>
			<select id="loc" name="loc">
				<option value="0"  selected = "selected" style="height: 26px;">지역</option>
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
			<input type="text" id="detail-loc" name="detail-loc" placeholder="상세주소 입력"/>
		</div>
		<div style="font-weight: 700;">이메일 <%= session.getAttribute("email") %></div>
	</div>
	
	<input type="file" id="upfile" name="upfile" multiple="multiple" accept="image/jpeg" style="display:none;"/><br>
	<button class="browse btn" type="button">사진 첨부</button>
	
	<div><textarea id="content" name="content"></textarea></div>

	<div id="subBtn" style="width: 120px; margin: 5px 45%">
		<button id="write" class="btn" type="button">작성</button>
		<button id="cancle" class="btn" onclick="history.go(-1)">취소</button>
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