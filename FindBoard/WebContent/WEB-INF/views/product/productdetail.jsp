<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>
<style type="text/css">
	a:link, a:visited{text-decoration:none; color:#fff;}

	#container{width:900px; margin:0 auto; overflow:hidden;}
	
	
	#container .product_box{width:100%; border:1px solid #ccc; height:800px;}
	
	#container .product_box .support{width:100%; margin:0; overflow:hidden;}
	#container .product_box .support li{
		float:right;
		padding:10px 90px 0 0;
		font-size:12px;
	}
	
	
	#container .product_box .product_img{width:470px; height:430px; float:left; padding:20px 20px 30px 100px;}
	#container .product_box .product_img p{width:100%; height:300px;}
	#container .product_box .product_img img{ width:100%; height:100%;}
	
	
	
	#container .product_box .product_price{width:400px; height:350px; float:left; padding:20px 20px 20px 50px;}
	#container .product_box .product_price .l_basket, #container .product_box .r_price{width:100px; 
		height:40px; 
		background-color:lightblue;
		text-align:center;
		line-height:40px;
		border-radius:10px;
		float:left;
	}
	#container .product_box .product_price .l_basket{margin-right:30px;}
	#container .product_box .product_price .l_basket a, #container .product_box .r_price a{display:inline-block; width:100%; height:100%;}
	
	#container .product_box .product_price p{font-size:20px; line-height:50px;}
	
	#container .product_box .product_txBox{width:850px; clear:both; 
		margin:0 auto;
		text-align:center; 
		background-color:lightgray;
	}
	
	#container .product_box .product_txBox p{height:50px; line-height:50px; 
		border:1px solid #000; 
		font-size:20px;	
	}
	
</style>
	<div id="container">
		<div class="product_box">
			<ul class="support">
				<li>수익금액 일부는 후원금으로 사용됩니다.</li>
			</ul>
			<div class="product_img">
				<p><img src="http://i.imgur.com/i7sW1WN.jpg" alt="제품이미지1"/></p>
				
			</div>
			<div class="product_price">
				<p>판매 금액 : 1,000원</p>
				<p>배송비 : 2,500원</p>
				<p>원산지 : 한국</p>
				<p>소재 : 면</p>
				<div class="l_basket">
					<a href="#" title="장바구니">장바구니</a>
				</div>
				<div class="r_price">
					<a href="#" title="바로 구매">바로구매</a>
				</div>
			</div>
			<div class="product_txBox">
				<p>상품정보</p>
			</div>
		</div>
	</div>
</body>
</html>