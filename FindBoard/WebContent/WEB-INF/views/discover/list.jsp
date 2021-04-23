<%@page import="dto.DiscoverBoard"%>
<%@page import="java.util.List"%>
<%@page import="dto.DiscoverImg" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% List<DiscoverBoard> list = (List) request.getAttribute("discoverList"); %>  
    
<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script> -->


<script type="text/javascript">

	
function search(){
		
		console.log($('#pet').val());
		
// 		if($('#pet').val() = null ){
		
// 			console.log("loc 가능");
			
// 		location.href="/find/list?loc=" + $('#Loc').val();			
			
// 		}else if(  $('#Loc').val() = null ){
			
// 			console.log("pet 가능");
			
// 		location.href="/find/list?pet="  + $('#pet').val();
			
// 		} else{
			
		
// 		}
		
		location.href="/discover/list?pet=" + $('#pet').val() + "&loc=" + $('#Loc').val();
		
}
</script>

<style type="text/css">
	a:link, a:visited{text-decoration:none; color:#777;}
	
	

	#container{width:900px; /* height:850px; */ overflow:hidden; margin:0 auto; }
	#container .click_box{width:100%; 
		height:40px; 
		line-height:40px; 
		background-color:#EBC680;
		border-radius:5px 5px 5px 5px;
	}
	#container .click_box .left_box{width:370px; height:40px; float:left; text-align:center;}
	#container .click_box .left_box input{width:50px; height:20px; 
		background-color:green; 
		border-style:none;
		border-radius:10px;
		line-height:18px;
		color:#eee;
		margin-left:10px;
	}
	#container .click_box .right_box{width:90px; height:40px; float:right; text-align:center;}
	#container .click_box .right_box p{display:block; 
		width:80px; 
		height:25px; 
		background-color:green;
		margin-top:7px;
		line-height:25px;
		border-radius:6px 6px 6px 6px;
	}
	#container .click_box .right_box p a{color:#eee;}
	
	#container .pet_list{width:150px; 
	

	margin:40px 15px 0 15px;
	float:left;
	cursor:pointer;
	}
	
	#container .pet_list p{text-align:center; margin:0; }
	
	#container .pet_list p a{display:block;}
	
	#container .pet_list .img_box{width:150px; height:150px; line-height:150px; border:1px solid #ccc;}
</style>
	
	<div id="container">
		<div class="click_box">
			<div class="left_box">
				<span>반려동물선택</span>
<!-- 				<INPUT TYPE="HIDDEN" NAME="PET_KINDS"/> -->
				<select id="pet">
					<option value="">반려동물</option>
					<option value="dog">강아지</option>
					<option value="cat">고양이</option>
					<option value="etc">기타</option>
				</select>
				<select id="Loc">
					<option value="">지역선택</option>
					<option value="서울특별시">서울특별시</option>
					<option value="경기도">경기도</option>
					<option value="강원도">강원도</option>
					<option value="충청북도">충청북도</option>
					<option value="충청남도">충청남도</option>
					<option value="경상북도">경상북도</option>
					<option value="경상남도">경상남도</option>
					<option value="전라북도">전라북도</option>
					<option value="전라남도">전라남도</option>
					<option value="대전광역시">대전광역시</option>
					<option value="광주광역시">광주광역시</option>
					<option value="인천광역시">인천광역시</option>
					<option value="부산광역시">부산광역시</option>
					<option value="대구광역시">대구광역시</option>
					<option value="울산광역시">울산광역시</option>
					<option value="세종시">세종시</option>
					<option value="제주시">제주시</option>
				</select>
				<input type="button" id="search" onclick="search();" value="조회"/>
			</div>
			<div class="right_box">
				<p><a href="/find/add" title="글 등록하기">등록하기</a></p>
			</div>
		</div>
		<%	for(int i=0; i<list.size(); i++) { %>
			<div class="pet_list">
				<p class="img_box" ><a href="/discover/read?DiscoverNo=<%=list.get(i).getDiscoverNo() %>">img</a></p>
				<p><%=list.get(i).getTitle() %></p>
				<p><%=list.get(i).getLoc() %></p>
				<p><%=list.get(i).getPetKinds() %></p>
			</div>
		<% } %>
	</div>
<%@ include file="/WEB-INF/views/layout/paging.jsp" %>
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>