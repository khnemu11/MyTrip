let latitude = 33.450701;
let longitude = 126.570667;
let title = '';
let map;
let key ='opoKoaa3kaeINxgVEi1q+SrTFEFt/U8TOSyDXPcdAt6Ca5hjzRNGZZjSKUndxKSDlk/A164nPmQkpVk8c5f0NQ==';

let container = document.getElementById('map');
let options = {
	center: new kakao.maps.LatLng(latitude, longitude),
	level: 3
};

document.addEventListener('DOMContentLoaded',function(){
	latitude = container.getAttribute('data-lat');
	longitude = container.getAttribute('data-lng');
	title = container.getAttribute('data-title');
	
	makeMap(latitude,longitude);
	getTour();
	setFavorite(title);
	setNearLocation();
})
async function setNearLocation(){
	let params = {
			serviceKey : key,
			numOfRows : 4,
			pageNo : 1,
			MobileOS : 'win',
			MobileApp : 'mytrip',
			_type : 'json',
			listYN:'Y',
			mapY:latitude,
			mapX:longitude,
			radius:5000,
			arrange:'A',
		}
	
		let baseUrl = 'https://apis.data.go.kr/B551011/KorService1/locationBasedList1?';
		let paramUrl = new URLSearchParams(params);
		
		console.log(paramUrl.toString());
		
		await fetch(baseUrl + paramUrl.toString())
		.then((response)=>response.json()).
		then(function(data){
			console.log("주변 관광지 데이터");
			console.log(data);
			let items = data.response.body.items;
			for(let idx =1 ; idx<items.length;idx++){
				
			}
			
			var context = `	
				<form class="tour-card" action="${baseUrl}" method="get" onclick="submit()">
					<input type="hidden" name="title" value="${title}"/>
					<input type="hidden" name="address" value="${addr}"/>
					<input type="hidden" name="longitude" value="${mapx}"/>
					<input type="hidden" name="latitude" value="${mapy}"/>
					<input type="hidden" name="telephone" value="${tel}"/>
					
					<img src="`+img+`">
					<div class="tour-info">
						<div class="tour-info-top">
							<span class="tour-info-title">
							`+title+`
							</span>
						</div>
					</div>
				</form>
				`	
list.insertAdjacentHTML('beforeend',context);
		})
}

function makeMap(lat,lng){
	latitude = lat;
	longitude = lng;
	
	options = {
			center: new kakao.maps.LatLng(latitude, longitude),
			level: 3
	};
	map = new kakao.maps.Map(container, options);
	
	var markerPosition  = new kakao.maps.LatLng(lat,lng, lng); 

	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
	    position: markerPosition
	});

	marker.setMap(map);
}
async function getTour(){
	let params = {
			serviceKey : key,
			numOfRows : 10,
			pageNo : 1,
			MobileOS : 'win',
			MobileApp : 'mytrip',
			_type : 'json',
			listYN:'Y',
			keyword:title,
			arrange:'A',
		}
	
		let baseUrl = 'https://apis.data.go.kr/B551011/KorService1/searchKeyword1?';
		let paramUrl = new URLSearchParams(params);
		
		console.log(paramUrl.toString());
		
		await fetch(baseUrl + paramUrl.toString())
		.then((response)=>response.json()).
		then(function(data){
			console.log(data);
			let result = data.response.body.items.item;
			for(let idx in result){
				if(result[idx].title == title){
					let target = document.getElementById('tour-img');
					console.log(target);
					target.setAttribute('src',result[idx].firstimage);
					break;
				}
			}
		})
}

function setFavorite(title){
	console.log(title);
	let baseUrl = '/favorites/';
		fetch(baseUrl + title)
		.then((response)=>response.text()).
		then(function(data){
			console.log("요청 결과"+data);
			let favorite = document.querySelector('.favorite');
			console.log(favorite);
			if(data == 'regist'){
				console.log("등록 아이콘 설정");
				favorite.innerHTML = `<i class="fa-solid fa-heart"></i>`;
			}else if(data == 'delete'){
				console.log("삭제 아이콘 설정");
				favorite.innerHTML = `<i class="fa-regular fa-heart"></i>`;
			}
		})
}

