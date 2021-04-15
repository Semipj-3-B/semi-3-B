<%@page import="dto.FindBoard"%>
<%@page import="java.util.List"%>
<%@page import="dto.FindImg" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	List<FindBoard> list = (List) request.getAttribute("findList"); %>
    
    
<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<!-- jQuery 2.2.4 -->
<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script> -->


<script type="text/javascript">

// 	function mainSiseMove(code) {
// 		if (code=='KONEX') {
// 			document.location.href='/sise/konex.nhn';
// 		} else if(code=='ETF') {
// 			document.location.href='/sise/etf.nhn';
// 	    } else if(code=='ETN') {
// 	        document.location.href='/sise/etn.nhn';
// 		} else {
// 			document.location.href='/sise/sise_index.nhn?code='+code;
// 		}
// 	}
</script>

<style type="text/css">
	a:link, a:visited{text-decoration:none; color:#777;}
	
	

	#container{width:900px; height:850px; margin:0 auto; }
	#container .click_box{width:100%; 
		height:40px; 
		line-height:40px; 
		background-color:#EBC680;
		border-radius:5px 5px 5px 5px;
	}
	#container .click_box .left_box{width:300px; float:left; text-align:center;}
	#container .click_box .right_box{width:90px; float:right; text-align:center;}
	#container .click_box .right_box button{display:block; 
		width:80px; 
		height:25px; 
		background-color:green;
		margin-top:7px;
		line-height:20px;
		border-radius:7px 7px 7px 7px;
	}
	
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
				<select name="pet">
					<option value="" selected>반려동물</option>
					<option value="dog">강아지</option>
					<option value="cat">고양이</option>
					<option value="etc">기타</option>
				</select>
				<select name="lc"">
					<option value="" selected>지역선택</option>
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
			</div>
			<div class="right_box">
				<button>등록하기</button>
			</div>
		</div>
		<%	for(int i=0; i<list.size(); i++) { %>
			<div class="pet_list">
				<p class="img_box" ><a href="/find/read?FindNo=<%=list.get(i).getFindNo() %>">img</a></p>
				<p><%=list.get(i).getTitle() %></p>
				<p><%=list.get(i).getLoc() %></p>
				<p><%=list.get(i).getPetKinds() %></p>
			</div>
		<% } %>
	</div>
<%@ include file="/WEB-INF/views/layout/paging.jsp" %>
<%@ include file="/WEB-INF/views/layout/footer.jsp" %>