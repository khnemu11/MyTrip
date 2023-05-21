let latitude = 33.450701;
let longitude = 126.570667;
let title = '';
let map;
let key ='opoKoaa3kaeINxgVEi1q+SrTFEFt/U8TOSyDXPcdAt6Ca5hjzRNGZZjSKUndxKSDlk/A164nPmQkpVk8c5f0NQ==';

let container = document.getElementById('map');
let roadviewContainer = document.getElementById('roadview');
let options = {
	center: new kakao.maps.LatLng(latitude, longitude),
	level: 3
};

document.addEventListener('DOMContentLoaded',function(){
	makeMap(latitude,longitude);
})

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
	var mapTypeControl = new kakao.maps.MapTypeControl();

	// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
	// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
	map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

	// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
	var zoomControl = new kakao.maps.ZoomControl();
	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

	marker.setMap(map);
	
	var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
	var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

	var position = new kakao.maps.LatLng(33.450701, 126.570667);

	// 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
	roadviewClient.getNearestPanoId(position, 50, function(panoId) {
	    roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
	});
}
