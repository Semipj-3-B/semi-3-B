<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<script type="text/javascript">
	
	$(function(){
		
		
		// 이전 버튼을 해결하기 위하여 이미지 한장을 빼놓음
		$("#film").prepend( $(".scene:last") );
		$("#film").css({"marginLeft":"-100%"});
		

		/* 다음 버튼 누르기 */
		$(".nextBtn").click(function(){
			$("#film:not(:animated)").stop().animate({"marginLeft":"-=100%"},500,"swing",function(){
				$("#film").append( $(".scene:first") );
				$("#film").css({"marginLeft":"-100%"});
			});
		});


		/* 이전 버튼 누르기 */
		/* 
			문제점: 빈칸이 나옴
			
			다음 버튼과 다른점?
				다음버튼의 경우 이미지가 줄지어있음
				이전버튼의 경우 처음에 빈공간이 있음
		*/
		
		$(".prevBtn").click(function(){
			$("#film:not(:animated)").stop().animate({"marginLeft":"+=100%"},500,"swing",function(){
				$("#film").prepend( $(".scene:last") );
				$("#film").css({"marginLeft":"-100%"});
			});
		});
	
	
	});


</script>
	
<style>
		*{margin:0; padding:0;}
		li{list-style:none;}
		a:link, a:visited{text-decoration:none; color:#777;}
		
		
		#container{width:900px; margin:0 auto;}
		
		#screen{max-width:800px; width:100%; height:300px; border:5px solid #ccc; margin:0 auto; position:relative; 
			overflow:hidden;
		}
		#film{width:400%;}
		/* 부모요소가 크던 작던 100%로 인식함*/
		.scene{float:left; width:25%;}
		.scene p{ width:100%; }
	
		#film:after{ content:""; display:block; clear:both; }

		#btn p{ position:absolute; top:60%; width:50px; height:100px; margin-top:-50px;}
		#btn p span{ width:100%; height:100%;}

		#btn .nextBtn{right:0; text-align:right;}
		#btn .prevBtn{left:0;}
		
		hr{ width:880px; 
			margin:30px auto; 
			border:solid 10px #B2EBF4;
			border-radius:10px 10px 10px 10px;
		}
		

		#container #sponsor{width:100%; height:40px; margin:0;}
		#container #sponsor li{float:right; list-style:disc;}
		
		#container #product{width:100%; overflow:hidden;}
		#container #pro_cate{width:850px; /*padding-left:50px;*/ margin-bottom:30px; height:26px;}
		#container #pro_cate li{float:left; text-align:center;} 
		#container #pro_cate li h3, h4{margin:0; line-height:26px;}
		#container #pro_cate .fir{width:120px;}
		#container #pro_cate .sec{width:100px;}
		
		
		#container #product_img{width:100%; height:720px; border:1px solid #ccc; margin:0 auto;}
		
		#container #product_img ul{width:100%; height:100%; }
		#container #product_img ul li{width:200px; height:200px; float:left; margin:20px 0 0 20px;}
		#container #product_img li img{width:100%; height:100%;}
  </style>
<body>
	<div id="container">
		<div id="screen">
			<ul id="film">
				<li class="scene">
					<p><img src="http://i.imgur.com/i7sW1WN.jpg" alt="제품이미지1"/></p>
				</li>
				<li class="scene">
					<p><img src="http://i.imgur.com/i7sW1WN.jpg" alt="제품이미지1"/></p>
				</li>
				<li class="scene">
					<p><img src="http://i.imgur.com/i7sW1WN.jpg" alt="제품이미지1"/></p>
				</li>
				<li class="scene">
					<p><img src="http://i.imgur.com/i7sW1WN.jpg" alt="제품이미지1"/></p>
				</li>
			</ul>
			<div id="btn">
				<p class="nextBtn"><span>&gt;</span></p>
				<p class="prevBtn"><span>&lt;</span></p>
			</div>
		</div>
		<hr>
			<ul id="sponsor">
				<li>수익금액 일부는 후원금으로 사용됩니다.</li>
			</ul>
		<div id="product">
			<ul id="pro_cate">
				<li class="fir"><h3><a href="#" title="전체보기">전체보기</a></h3></li>
				<li class="sec"><h4><a href="#" title="반려동물">반려동물</a></h4></li>
				<li class="sec"><h4><a href="#" title="악세서리">악세서리</a></h4></li>
				<li class="sec"><h4><a href="#" title="폰케이스">폰케이스</a></h4></li>
			</ul>
			
			<div id="product_img">
				<ul class="img_box">
					<li><a href="#" title="제품이미지1"><img src="http://i.imgur.com/i7sW1WN.jpg" alt="제품이미지1"/></a></li>
					<li><a href="#" title="제품이미지1"><img src="http://i.imgur.com/i7sW1WN.jpg" alt="제품이미지1"/></a></li>
					<li><a href="#" title="제품이미지1"><img src="http://i.imgur.com/i7sW1WN.jpg" alt="제품이미지1"/></a></li>
					<li><a href="#" title="제품이미지1"><img src="http://i.imgur.com/i7sW1WN.jpg" alt="제품이미지1"/></a></li>
					<li><a href="#" title="제품이미지1"><img src="http://i.imgur.com/i7sW1WN.jpg" alt="제품이미지1"/></a></li>
					<li><a href="#" title="제품이미지1"><img src="http://i.imgur.com/i7sW1WN.jpg" alt="제품이미지1"/></a></li>
					<li><a href="#" title="제품이미지1"><img src="http://i.imgur.com/i7sW1WN.jpg" alt="제품이미지1"/></a></li>
					<li><a href="#" title="제품이미지1"><img src="http://i.imgur.com/i7sW1WN.jpg" alt="제품이미지1"/></a></li>
					<li><a href="#" title="제품이미지1"><img src="http://i.imgur.com/i7sW1WN.jpg" alt="제품이미지1"/></a></li>
					<li><a href="#" title="제품이미지1"><img src="http://i.imgur.com/i7sW1WN.jpg" alt="제품이미지1"/></a></li>
					<li><a href="#" title="제품이미지1"><img src="http://i.imgur.com/i7sW1WN.jpg" alt="제품이미지1"/></a></li>
					<li><a href="#" title="제품이미지1"><img src="http://i.imgur.com/i7sW1WN.jpg" alt="제품이미지1"/></a></li>
				</ul>
			</div>
			
		</div>
	</div>
</body>
</html>