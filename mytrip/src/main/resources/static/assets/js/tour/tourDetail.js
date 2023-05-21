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
})

https://www.googleapis.com/youtube/v3/search?part=snippet&q=%EA%B0%80%EB%8B%88%EC%95%88%20%EC%95%BD%EA%B5%AD&regionCode=kr&order=viewCount&maxResults=5&key=AIzaSyBvj1i1nHbR-GkwdSeu-OoTxhMh1V1FaFA

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
