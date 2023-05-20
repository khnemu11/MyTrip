let latitude = 33.450701;
let longitude = 126.570667;
let map;

let container = document.getElementById('map');
let options = {
	center: new kakao.maps.LatLng(latitude, longitude),
	level: 3
};

document.addEventListener('DOMContentLoaded',function(){
	latitude = container.getAttribute('data-lat');
	longitude = container.getAttribute('data-lng');
	
	makeMap(latitude,longitude);

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
