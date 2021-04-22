<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/layout/header.jsp" %>

<!-- iamport 1.1.5 라이브러리 추가 -->
<script src="https://service.iamport.kr/js/iamport.payment-1.1.5.js" type="text/javascript"></script>

<!-- jQuery 2.2.4 라이브러리 추가 -->
<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript">

	$(document).ready(function() {
		var chk = $("#chk")
		
		// iamport 변수 초기화
		var IMP = window.IMP;
		IMP.init('imp21893920');	// 가맹점 식별코드, 회원가입해서 직접 넣어야합니다
	
		// 결제 모듈 불러오기
		$(".pay").click(function() {
			
				requestPayment();
		});
	});
	
	// 결제 요청 - 결제 모듈 불러오기
	function requestPayment() {
		IMP.request_pay({
		    pg : 'html5_inicis', //PG사 - 'kakao':카카오페이, 'html5_inicis':이니시스(웹표준결제), 'nice':나이스페이, 'jtnet':제이티넷, 'uplus':LG유플러스, 'danal':다날, 'payco':페이코, 'syrup':시럽페이, 'paypal':페이팔
		    pay_method : 'card', //결제방식 - 'samsung':삼성페이, 'card':신용카드, 'trans':실시간계좌이체, 'vbank':가상계좌, 'phone':휴대폰소액결제
		    merchant_uid : 'merchant_' + new Date().getTime(), //고유주문번호 - random, unique
		    name : '주문명:결제테스트', //주문명 - 선택항목, 결제정보 확인을 위한 입력, 16자 이내로 작성
		    amount : 1000, //결제금액 - 필수항목
		    buyer_email : 'iamport@siot.do', //주문자Email - 선택항목
		    buyer_name : '구매자이름', //주문자명 - 선택항목
		    buyer_tel : '010-1234-5678', //주문자연락처 - 필수항목, 누락되면 PG사전송 시 오류 발생
		    buyer_addr : '서울특별시 강남구 삼성동', //주문자주소 - 선택항목
		    buyer_postcode : '123-456', //주문자우편번호 - 선택항목
		    m_redirect_url : 'https://www.yourdomain.com/payments/complete' //모바일결제후 이동페이지 - 선택항목, 모바일에서만 동작
		    
		}, function(rsp) { // callback - 결제 이후 호출됨, 이곳에서 DB에 저장하는 로직을 작성한다
		    if ( rsp.success ) { // 결제 성공 로직
		        var msg = '결제가 완료되었습니다.';
		        msg += '고유ID : ' + rsp.imp_uid;
		        msg += '상점 거래ID : ' + rsp.merchant_uid;
		        msg += '결제 금액 : ' + rsp.paid_amount;
		        msg += '카드 승인번호 : ' + rsp.apply_num;
		        msg += '[rsp.success]';
	
		        
		        // 결제 완료 처리 로직
				//[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
				jQuery.ajax({
					url: "/pay/complete", //cross-domain error가 발생하지 않도록 동일한 도메인으로 전송
					type: 'POST',
					dataType: 'json',
					data: {
						// rsp객체를 통해 전달된 데이터를 DB에 저장할 때 사용한다
						imp_uid : rsp.imp_uid
					}
				
				}).done(function(data) {
					//[2] 서버에서의 응답 처리
					if ( data == 'success' ) {
						var msg = '결제가 완료되었습니다.';
						msg += '\n고유ID : ' + rsp.imp_uid;
						msg += '\n상점 거래ID : ' + rsp.merchant_uid;
						msg += '\n결제 금액 : ' + rsp.paid_amount;
						msg += '\n카드 승인번호 : ' + rsp.apply_num;
				        msg += '\n[done]';
	
						alert(msg);
						
		    		} else {
		    			//[3] 아직 제대로 결제가 되지 않았습니다.
		    			//[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
		    		}
		    	});
		        
		    } else { // 결제 실패 로직
		        var msg = '결제에 실패하였습니다.';
		        msg += '에러내용 : ' + rsp.error_msg;
		    }
		    alert(msg);
		});
	}

</script>
<style type="text/css">
	li{list-style:none;}
	
	#container{width:100%;}
	
	#container h3{width:800px; 
		height:50px; 
		line-height:50px;
		margin:0 auto;
		text-align:center;
		background-color:navy;
		color:#fff;
	}
	
	#payment{max-width:800px;
		width:100%;
		overflow:hidden;
		border:1px solid #ccc;
		margin:0 auto;
		padding:30px 50px 30px 50px;f
	}
	
	#payment .product_box{width:100%; height:265px;}
	
	
	#payment .product_img{width:200px; height:265px; float:left;}
	#payment .product_img p{width:100%;
		height:30px; 
		line-height:30px; 
		margin-top:10px; 
		text-align:center; 
		background-color:lightblue;
		border-radius:10px;
	
	}
	#payment .product_img .img_box{width:100%; height:200px; }
	#payment .product_img p img{width:100%; height:100%;}
	#payment .product_img p a{color:#fff;}
	
	#payment .product_text{float:left;
		margin-left:30px;
		 height:260px;
	}
	#payment .product_text:after{clear:both;}
	
	
	#payment .product_text table{width:460px; overflow:hidden; margin-top:30px;}
	#payment .product_text table tr{text-indent:20px;}
	#payment .product_text .br_bot{width:100%; border-bottom:1px solid #000;}
	#payment .product_text .price{margin-top:15px;}
	
	#payment .product_info{width:100%; height:250px; margin-top:50px;}
	#payment .product_info h5{font-size:18px; text-indent:30px; padding-bottom:10px;}
	#payment .product_info p{border:1px solid #000; 
		line-height:25px;
		padding:10px;
	}
	
	#payment .product_info form{text-align:right;}
	
	#payment .info_box{width:100%; height:300px;
		border:1px solid #ccc;
	}
	
	#payment .info_box h5{ font-size:18px; text-indent:20px;}
	#payment .info_box form{padding:15px 0 0 50px;}
	#payment .info_box form label{width:10%;}
	#payment .info_box form input{width:70%; margin-bottom:20px;}
	
	
	#container #pay_box{width:150px; height:50px;
		margin:30px auto 0;
	}
	
	#container .pay{width:100%; height:100%;
		line-height:50px;
		text-align:center;
		border-style:none;
		background-color:blue;
		border-radius:10px;
		color:#fff;
	}
</style>
	<div id="container">
		<h3>결제하기</h3>
		<div id="payment">
			<div class="product_box">
				<div class="product_img" >
			      <p class="img_box"><img src="http://i.imgur.com/viuPHoS.gif" /></p>
			      <p><a href="#" title="상세보기">상품 상세보기</a></p>
		   		</div>
		   		
		   		<div class="product_text">
		   			<table>
		   				<tr class="br_bot">
		   					<th>상품/옵션 정보</th>
		   					<th>수량</th>
		   					<th>금액</th>
		   					<th>배송비</th>
		   				</tr>
		   				
		   				<tr>
		   					<td>상품이름</td>
		   					<td>n개</td>
		   					<td>0원</td>
		   					<td>0원</td>
		   				</tr>
		   			</table>
		   			<div class="price">
		   				<p>상품금액 </p>
		   				<p>배송비</p>
		   				<p>총 결제 금액</p>
		   			</div>
		   		</div>	
		   	</div>
	   		<div class="product_info">
				<h5>주문 동의 사항</h5>	
				<p>
					구매한 상품의 취소/반품은 구매내역에서 신청 가능합니다.<br/>
					상품문의 및 후기게시판을 통해 취소나 환불, 반품 등은 처리되지 않습니다.<br/>
					가격, 판매자, 교환/환불 및 배송 등 해당 상품 자체와 관련 없는 문의는 고객센터 내 1:1 문의하기를 이용해주세요.<br/>
					"해당 상품 자체"와 관계없는 글, 양도, 광고성, 욕설, 비방, 도배 등의 글은 예고 없이 이동, 노출제한, 삭제 등의 조치가 취해질 수 있습니다.<br/>
					공개 게시판이므로 전화번호, 메일 주소 등 고객님의 소중한 개인정보는 절대 남기지 말아주세요.<br/>
				</p>
				<form action="#" method="get">
					<input type="radio" id="chk" name="chk"/> 동의 사항 확인
				</form>
			</div>
			<div class="info_box">
				<h5>배송 정보</h5>
				<form action="#" method="get">
					<label name="recipient">받으실 분</label>
					<input type="text" name="recipient"/><br/>
					
					<label name="phone">연락처&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<input type="text" name="phone"/><br/>
					
					<label name="loc">주소&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<input type="text" name="loc"/><br/>
					
					<label name="email">이메일&nbsp;&nbsp;&nbsp;&nbsp;</label>
					<input type="text" name="email"/><br/>
					
					<label name="requests">요청사항</label>
					<input type="text" name="requests"/>
					
				</form>
			</div>
		</div>
		<div id="pay_box">
			<button class="pay">결제하기</button>
		</div>
	</div>
</body>
</html>