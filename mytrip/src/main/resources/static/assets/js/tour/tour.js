var areaCode=1;
var sigunguCode=1;
var contentTypeId="";
let userinfo;
let favoriteList=[];
let key = 'opoKoaa3kaeINxgVEi1q%2BSrTFEFt%2FU8TOSyDXPcdAt6Ca5hjzRNGZZjSKUndxKSDlk%2FA164nPmQkpVk8c5f0NQ%3D%3D';

document.querySelector('#btn-search').addEventListener('click',function(){
	var keyWord = document.querySelector('#search').value;
	console.log(keyWord);
	if(keyWord == '' || keyWord == undefined){
		fetch(`https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=opoKoaa3kaeINxgVEi1q%2BSrTFEFt%2FU8TOSyDXPcdAt6Ca5hjzRNGZZjSKUndxKSDlk%2FA164nPmQkpVk8c5f0NQ%3D%3D&numOfRows=12&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&areaCode=1&sigunguCode=1`)
		.then((response)=>response.json())
		.then((data)=>listdata(data));
	}else{
		fetch(`https://apis.data.go.kr/B551011/KorService1/searchKeyword1?MobileOS=ETC&MobileApp=movbileapp&_type=json&keyword=${keyWord}&serviceKey=${key}`)
		.then((response)=>response.json())
		.then((data)=>listdata(data));
	}
})

fetch(`https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=opoKoaa3kaeINxgVEi1q%2BSrTFEFt%2FU8TOSyDXPcdAt6Ca5hjzRNGZZjSKUndxKSDlk%2FA164nPmQkpVk8c5f0NQ%3D%3D&numOfRows=12&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&areaCode=1&sigunguCode=1`)
.then((response)=>response.json())
.then((data)=>listdata(data));

fetch('https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=opoKoaa3kaeINxgVEi1q%2BSrTFEFt%2FU8TOSyDXPcdAt6Ca5hjzRNGZZjSKUndxKSDlk%2FA164nPmQkpVk8c5f0NQ%3D%3D&numOfRows=20&pageNo=1&MobileOS=win&MobileApp=AppTest&_type=json')
.then((response)=>response.json())
.then((data)=>makeOption(data));

fetch('https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=opoKoaa3kaeINxgVEi1q%2BSrTFEFt%2FU8TOSyDXPcdAt6Ca5hjzRNGZZjSKUndxKSDlk%2FA164nPmQkpVk8c5f0NQ%3D%3D&numOfRows=100&pageNo=1&MobileOS=win&MobileApp=AppTest&areaCode=1&_type=json')
		.then((response)=>response.json())
.then((data)=>makeGunGuOption(data));

fetch('/locations/list')
.then((response)=>response.json())
.then((data)=>{
	console.log(data)
	
	for(var idx in data){
		favoriteList.push(data[idx].title);
	}
	console.log(favoriteList)
})

fetch('/users/user')
.then((response)=>response.json())
.then((data)=>{
	userinfo = data;
	console.log(userinfo);
})

var mapx = 37;
var mapy = 127;
	
/* 선택한 관광지의 위치를 맵으로 보여주는 함수 */
function showMap(divId){
	console.log(divId);
	var title = divId.getAttribute('data-title');
	console.log(title, "title");
	mapx = divId.getAttribute('data-mapx');
	mapy = divId.getAttribute('data-mapy');
    // 이동할 위도 경도 위치를 생성합니다 
    var moveLatLon = new kakao.maps.LatLng(mapy, mapx);
    map.setLevel(3);
    // 지도 중심을 부드럽게 이동시킵니다
    // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
    map.panTo(moveLatLon);    
  //마커가 표시될 위치입니다 
    var markerPosition  = new kakao.maps.LatLng(mapy, mapx); 

    //마커를 생성합니다
    var marker = new kakao.maps.Marker({
    	position: markerPosition
    });
    
    var iwContent = `<p style=";">${title}</p>`, // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    iwPosition = new kakao.maps.LatLng(mapy, mapx); //인포윈도우 표시 위치입니다

	// 인포윈도우를 생성합니다
	var infowindow = new kakao.maps.InfoWindow({
	    position : iwPosition, 
	    content : iwContent 
	});
	  
	// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
	infowindow.open(map, marker); 
    
    //마커가 지도 위에 표시되도록 설정합니다
    marker.setMap(map);
    
}


var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = { 
    center: new kakao.maps.LatLng(mapx, mapy), // 지도의 중심좌표
    level: 13 // 지도의 확대 레벨
};

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

//일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
var mapTypeControl = new kakao.maps.MapTypeControl();

// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
var zoomControl = new kakao.maps.ZoomControl();
map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);


//아래 코드는 지도 위의 마커를 제거하는 코드입니다
//marker.setMap(null);





function getPage(pageNo){
	console.log(pageNo);
	var link = `https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=opoKoaa3kaeINxgVEi1q%2BSrTFEFt%2FU8TOSyDXPcdAt6Ca5hjzRNGZZjSKUndxKSDlk%2FA164nPmQkpVk8c5f0NQ%3D%3D&numOfRows=12&pageNo=`+pageNo+`&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&areaCode=`+areaCode+`&sigunguCode=`+sigunguCode
	console.log(link);
	fetch(link)
	.then((response)=>response.json())
	.then((data)=>listdata(data));
}

function onChangeContentTypeId(){
	var select = document.getElementById("select-contentTypeId");
	contentTypeId = select.value;
	var link = `https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=opoKoaa3kaeINxgVEi1q%2BSrTFEFt%2FU8TOSyDXPcdAt6Ca5hjzRNGZZjSKUndxKSDlk%2FA164nPmQkpVk8c5f0NQ%3D%3D&numOfRows=12&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&areaCode=`+areaCode+`&sigunguCode=`+sigunguCode+`&contentTypeId=`+contentTypeId;
	console.log(link);
	/* console.log(sigunguCode); */
	
	fetch(link)
	.then((response)=>response.json())
	.then((data)=>listdata(data));	
}
function makeOption(data){
	var select = document.getElementById("select-city");
	var options = "";
	var cities = data.response.body.items.item;
	
	for(var idx in cities){
		var area = cities[idx].code;
		var name = cities[idx].name;
		var row = `<option value = "` + area+`">`+ name+`</option>`;
		options+=row;
	}
	select.innerHTML = options;
}

function onChangeGunGu(){
	sigunguCode =  document.getElementById("select-gun").value;
	var link = `https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=opoKoaa3kaeINxgVEi1q%2BSrTFEFt%2FU8TOSyDXPcdAt6Ca5hjzRNGZZjSKUndxKSDlk%2FA164nPmQkpVk8c5f0NQ%3D%3D&numOfRows=12&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&areaCode=`+areaCode+`&sigunguCode=`+sigunguCode

	/* console.log(sigunguCode); */
	
	fetch(link)
	.then((response)=>response.json())
	.then((data)=>listdata(data));	
}

function onChangeCity(){
	areaCode =  document.getElementById("select-city").value;
	var link = `https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=opoKoaa3kaeINxgVEi1q%2BSrTFEFt%2FU8TOSyDXPcdAt6Ca5hjzRNGZZjSKUndxKSDlk%2FA164nPmQkpVk8c5f0NQ%3D%3D&numOfRows=12&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&areaCode=`+areaCode;
		
	fetch(link)
	.then((response)=>response.json())
	.then((data)=>listdata(data));
	
	link = `https://apis.data.go.kr/B551011/KorService1/areaCode1?serviceKey=opoKoaa3kaeINxgVEi1q%2BSrTFEFt%2FU8TOSyDXPcdAt6Ca5hjzRNGZZjSKUndxKSDlk%2FA164nPmQkpVk8c5f0NQ%3D%3D&numOfRows=100&pageNo=1&MobileOS=win&MobileApp=AppTest&areaCode=`+ areaCode+`&_type=json`
	
	fetch(link)
	.then((response)=>response.json())
	.then((data)=>makeGunGuOption(data));		
}


function makeGunGuOption(data){
	var select = document.getElementById("select-gun");
	var options = "";
	var sigungus = data.response.body.items.item;
	sigunguCode = sigungus[0].code;
	for(var idx in sigungus){
		var sigungu = sigungus[idx].code;
		var name = sigungus[idx].name;
		var row = `<option value = "` + sigungu+`">`+ name+`</option>`;
		options+=row;
	}
	select.innerHTML = options;
}


function listdata(data){
	var cards="";
	var locations = data.response.body.items.item;
	
	if(data.response.body.totalCount == 0){
		document.getElementById("location-list").innerHTML = `<div>해당 조건에 맞는 관광지가 없습니다.</div>`
		return 0;
	}
	
	for(var idx in locations){
		var row = "<tr>";
		var title = locations[idx].title;
		var addr = locations[idx].addr1;
		var mapx = locations[idx].mapx;
		var mapy = locations[idx].mapy;
		var img = locations[idx].firstimage;
		var divId = "regist" + idx;
		var cardId = "card" + idx;
		
		var jsonData = {
			"title" : title,
			"addr" : addr,
			"mapx" : mapx,
			"mapy" : mapy,
			"userid" : "test"
		}
		/*
		 * console.log(jsonData);
		 */
		if(img == ""){
			img = "/img/tour/no-image.png";
		}
		
		var context = `<div class="tour-card">
					<img src="`+img+`">
					<div class="tour-info">
						<div class="tour-info-top">
							<span class="tour-info-title">
							`+title+`
							</span>
							<span class="tour-info-view">
							<i class="fa-regular fa-eye" style="color: #ffffff;"></i>111,111
							</span>
						</div>
					</div>
				</div>`	
		cards+=context;
	}



document.getElementById("location-list").innerHTML= cards;

}
	
