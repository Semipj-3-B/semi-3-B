$(document).ready(function(){
	var $slider_list = $(".imgslide li");


	$slider_list.css("left", $(".sliderbox").css("width"));

	$slider_list.eq(0).css("left", 0)


	var preClick = function() {

		//#sliderbox에 클릭이벤트 발생시키기
		$(".pre").click()
	}

	//3초마다 #sliderbox에 클릭 이벤트 발생시키기
	var tid = setInterval( preClick, 5000 );


	var curSlide = 0;
	$(".pre").click(function() {

		clearInterval( tid )
		tid = setInterval( preClick, 5000)

		//다음보여질 슬라이드의 인덱스
		var nextSlide = curSlide + 1;

		//이미지 개수만큼으로 인덱스 보정
		//	->nextSlide
		nextSlide %= $slider_list.length;

		//*** .animate() 함수로 효과 넣기 ***
		//.animate({"변경할속성": "속성값 변화량"})


		console.log(curSlide + ":" + nextSlide)

		//현재 슬라이드 숨기기 - curSlide
		//	-> <div> 태그의 왼쪽으로 밀어내기 
		$slider_list.eq(curSlide).animate({"left": "+=" + $(".sliderbox").css("width")})


		//다음 보여줄 슬라이드 준비시키기 - nextSlide
		//	-> <div>태그의 오른쪽으로 보내기
		$slider_list.eq(nextSlide).css("left", "-"+$(".sliderbox").css("width"))

		//다음 슬라이드 보여주기 - nextSlide
		//	-> <div> 태그의 안쪽으로 보내기 
		$slider_list.eq(nextSlide).animate({"left": "+=" + $(".sliderbox").css("width")})


		//증가식 (순환구조 만들기)
		curSlide++;

		//이미지 개수만큼으로 인덱스 보정
		//	->curSlide
		curSlide %= $slider_list.length;

	})

	$(".next").click(function() {

		clearInterval( tid )
		tid = setInterval( preClick, 5000)

		var nextSlide = curSlide + $slider_list.length - 1;

		nextSlide %= $slider_list.length;

		console.log(curSlide + ":" + nextSlide)

		$slider_list.eq(curSlide).animate({"left": "-=" + $(".sliderbox").css("width")})

		$slider_list.eq(nextSlide).css("left", $(".sliderbox").css("width"))

		$slider_list.eq(nextSlide).animate({"left": "-=" + $(".sliderbox").css("width")})

		curSlide += $slider_list.length - 1;

		curSlide %= $slider_list.length;


	})
	

})