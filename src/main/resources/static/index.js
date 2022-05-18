$(document).ready(function(){  // 제이쿼리 사용시 시작 선언

	
$("#searchTwit").click(function(){  // 버튼 클릭 이벤트
	$("#foo-table").DataTable().destroy(); // 테이블 초기화 (여러번 클릭시 화면 초기화)
	$("#maxCount").empty();  // 가장 많은 조아요,지역 언어 초기화

	$("#foo-table").DataTable(); //데이터 테이블 그려준다.
	$("#twitTable").show();
	
	var timeLabel = [];
	var langs = [];
	var hashtag = [];
	var location = [];
	var transferLang = [];
	
	var text = $("#text").val();
	var geoType = $('#geoType').val();

	$.ajax({ //제이쿼리 ajax 통신
		url:"/api/twitter/searchList",   // controller 만든 api 호출
		data: "text="+text+"&geoType="+geoType,  // 서버로 보낼 파라미터
		type: "GET",  // get 방식 url에 파라미터 보임
		async: false,   // 동기화 (자바스크립트 배열에 정확한 데이터를 담기위해)
		success:function(data){
			console.log(data);
			
			var maxFavorite =data.map(function(v){ // 서버에서 만든 오브젝트 데이터중 .map을 사용하여 좋아요 수 데이터만 추출
				return v.favorite;
			});
			
			maxFavorite = Math.max.apply(null,maxFavorite);   // 추출된거 중에 가장 많은 좋아요 수
			
			var maxTimeline = data.map(function(v){   // 가장 많은 좋아요 수 받은 타임라인
					if(v.favorite == maxFavorite){ //가장 많은 좋아요수를 가진 favorite를 찾아 그에 대한 타임라인 추출
						return v.text;
					}
			})
			
		 data.map(function(v){
				langs.push(v.lang); //위에 선언한 langs 배열에 서버에서 만든 오브젝트 데이터중 lang만 담음
		 });
			
		 var langUniq = langs.filter((val,idx)=>{   // 같은 사용한 언어 중복 제거로 유니크한것만
				 return langs.indexOf(val) === idx;
		})	
			
		 data.map(function(v){   // 자바스크립트 이중 for 문(해시태그 배열중 안빈것을 찾아 위에 선언한 hashtag 배열에 해시태그 값 담음)
			 if(v.hashtag.length > 0){
				 v.hashtag.map(function(w){
					 hashtag.push(w.text);
				 })
			 }
		 });
		 
		 var hashtagUniq = hashtag.filter((val,idx)=>{   // 같은 해시태그 중복 제거로
															// 유니크한것만
			 return hashtag.indexOf(val) === idx;
		 })
			
		 data.map(function(v){ // 위에서 선언한 location 배열에 서버에서 불러온 오브젝트 데이터중 location값 담음
				 location.push(v.location);
		 });
			
		 var locationUniq = location.filter((val,idx)=>{   // 같은 지역 중복 제거로
															// 유니크한것만
			 return location.indexOf(val) === idx;
		 })
		 
			console.log("maxFavorite :" + maxFavorite );
			console.log("maxTimeline :" + maxTimeline );
			
			console.log("timeLabel : "+  timeLabel);
			console.log("lang : " + langs);
			
			console.log("hashtag : " + hashtagUniq);
			console.log("location : " + locationUniq);
			
			
			for(var i = 0; i<langUniq.length;  i++){
				if(langUniq[i]=='ko'){
					transferLang.push('한국어');
				}else if(langUniq[i]=='en'){
					transferLang.push('영어');
				}else if(langUniq[i]=='fr'){
					transferLang.push('불어');
				}else if(langUniq[i]=='ja'){
					transferLang.push('일본어');
				}else if(langUniq[i]=='zh'){
					transferLang.push('중국어');
				}else if(langUniq[i]=='es'){
					transferLang.push('스페인어');
				}else if(langUniq[i]=='ar'){
					transferLang.push('아랍어');
				}
				else{
					transferLang.push('ETC');
				}
				console.log("langs : " + transferLang);
			}
			
			 var transLoUniq = transferLang.filter((val,idx)=>{   // 같은 언어 중복
																	// 제거로
																	// 유니크한것만
				 return transferLang.indexOf(val) === idx;
			 })
			
			// 위에서 만든 데이터를 화면에 보여주기 위해 append 사옹
			$("#maxCount").append("<h3 style='background-color:#F6E3CE'>가장 많은 좋아요 수 : "+maxFavorite+"</h2>");
			$("#maxCount").append("<h3 style='background-color:orange'>가장 많은 좋아요 수의 타임라인 : "+maxTimeline+"</h2>");
			$("#maxCount").append("<h3 style='background-color:yellow'>해시태그 : "+hashtagUniq+"</h2>");
			$("#maxCount").append("<h3 style='background-color:cyan'>지역  : "+locationUniq+"</h2>");
			$("#maxCount").append("<h3 style='background-color:#A9F5D0'>사용된 언어  : "+transLoUniq+"</h2>");
			
			
			
	// 데이터 테이블에 서버에서 만든 데이터를 그리는 부분		
			$("#foo-table").dataTable({
				bDestroy: true,  //테이블 초기화
				data:data,
				columns : [
						{data: 'createAt'},
						{data: 'text'},
						{data: 'lang'},
						{data: 'location'},
						{data: 'retwitterCount'},
						{data: 'favorite'},

				]
			});
			
		},error:function(request,status,error){
			alert("트윗 API 횟수 제한. 잠시후 다시 시도 바랍니다.");
			return false;
		}
});
	
	$("#twitTable").on('click', 'tbody tr', function () {  //테이블 행 클릭시 해당 트윗으로 이동
	    var row = $("#foo-table").DataTable().row($(this)).data();
	    console.log(row.url);
	    window.open(row.url);

	});


})
});