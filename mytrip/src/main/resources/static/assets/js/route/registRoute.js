let latitude = 33.450701;
let longitude = 126.570667;
let title = '';
let map;
let key ='opoKoaa3kaeINxgVEi1q+SrTFEFt/U8TOSyDXPcdAt6Ca5hjzRNGZZjSKUndxKSDlk/A164nPmQkpVk8c5f0NQ==';

let container = document.getElementById('map');
//let roadviewContainer = document.getElementById('roadview');
let options = {
	center: new kakao.maps.LatLng(latitude, longitude),
	level: 3
};

document.addEventListener('DOMContentLoaded',function(){
	getRoute();
	makeTourList();
})
function makeTourList(){
	let tourList = document.querySelector("#tour-list");
	let paramsDiv = document.querySelectorAll('.routeParams');
	console.log(paramsDiv);
	for(let idx=0; idx < paramsDiv.length;idx++){
		console.log(paramsDiv[idx]);
		let params = {
			title : paramsDiv[idx].getAttribute('data-title'),
			latitude : paramsDiv[idx].getAttribute('data-latitude'),
			longitude : paramsDiv[idx].getAttribute('data-longitude'),
		}
		let iconContext = '';
		if(idx == 0){
			iconContext = '<i class="fa-sharp fa-solid fa-location-dot"></i>';
		}else if(idx == paramsDiv.length-1){
			iconContext = '<i class="fa-solid fa-location-pin"></i>';
		}else{
			iconContext = '<span class="circle"></span>';
		}
		
		let context = `
			<span class="select-tour" id="${params.title}">	
				<span class="tour-left">${iconContext}</span>
				<span class="tour-right row">
					<div class="col-md-7 col-sm-7 col-xs-7">
						<span class="tour-title">${params.title}</span>
					</div>
					<div class="col-md-5 col-sm-5 col-xs-5">
						<span class="data-container"><p class="distance"></p><p class="duration"></p></span>
					</div>
				</span>
			</span>
			`;
		console.log(context);
		tourList.insertAdjacentHTML('beforeend',context);
	}
}

function getRoute(){
	let paramsDiv = document.querySelectorAll('.routeParams');
	let baseUrl = '/route/findRoute?';
	let paramUrl = '';
	console.log(paramsDiv);
	for(let idx=0; idx < paramsDiv.length;idx++){
		console.log(paramsDiv[idx]);
		let params = {
			title : paramsDiv[idx].getAttribute('data-title'),
			latitude : paramsDiv[idx].getAttribute('data-latitude'),
			longitude : paramsDiv[idx].getAttribute('data-longitude'),
		}
		paramUrl = paramUrl + '&'+new URLSearchParams(params).toString();
	}
	fetch(baseUrl+paramUrl.substr(1))
	.then(response => response.json())
	.then(function(data){
		console.log(data);
		makeMap(data);
		setData(data);
	})
}
function setData(data){
	console.log("Set Data");
	console.log(data);
	let totalDistance = Math.floor(data.summary.distance/1000);
	let totalTime = Math.floor(data.summary.duration / 60);
	console.log(totalDistance +" , "+totalTime);
	//총 거리 저장
	document.querySelector('#expectedDistance').value= totalDistance;
	document.querySelector('#total-distance').innerText= totalDistance+"km";
	
	//총 시간 저장
	document.querySelector('#expectedTime').value = totalTime;
	
	let timeContext = totalTime+"분";
	if(totalTime >= 60){
		timeContext =  Math.floor(totalTime/60)+"시간" + (totalTime%60)+"분"
	}
	document.querySelector('#total-time').innerText = timeContext;
	
	//택시 비용 저장
	document.querySelector('#taxiCost').value = data.summary.fare.taxi;
	document.querySelector('#total-taxi').innerText = "택시비 약 "+data.summary.fare.taxi.toLocaleString()+"원";
	//통행 비용 저장
	document.querySelector('#fuelCost').value= data.summary.fare.toll;
	document.querySelector('#total-toll').innerText = "통행비 약 "+data.summary.fare.toll.toLocaleString()+"원";
	
	let tourList = document.querySelectorAll(".data-container");
	let paramList = document.querySelectorAll(".routeParams");
	console.log(paramList);
	for(let idx=1; idx < tourList.length;idx++){
		console.log(tourList[idx].childNodes);
		let distance = Math.floor(data.sections[idx-1].distance/1000).toLocaleString('ko-KR');
		let time = Math.floor(data.sections[idx-1].duration / 60);
		tourList[idx].childNodes[0].innerText = distance+"km";
		tourList[idx].childNodes[1].innerText = `(${time}분)`;
		console.log("파라미터 노드");
		paramList[idx].querySelector('[name = tourDistance]').value = distance;
		paramList[idx].querySelector('[name = tourTime]').value = time;
		
	}
}
function makeMap(data){
	let center_y = (data.summary.bound.min_y+data.summary.bound.max_y)/2; 
	let center_x = (data.summary.bound.min_x+data.summary.bound.max_x)/2;
	console.log(center_y+" , "+center_x);
	
	options = {
			center: new kakao.maps.LatLng(center_y,center_x),
//			center: new kakao.maps.LatLng(latitude,longitude),
			level: 12,
	};
	
	map = new kakao.maps.Map(container, options);
	
	let linePath = [];
	linePath.push(new kakao.maps.LatLng(data.summary.origin.y, data.summary.origin.x));
	console.log(data.sections);
	
	for(let section in data.sections){
		let guides = data.sections[section].guides
		for(let idx=1;idx<guides.length;idx++){
			console.log(guides[idx].y+" , "+guides[idx].x);
			linePath.push(new kakao.maps.LatLng(guides[idx].y, guides[idx].x));
		}
	}
	console.log(linePath);
	// 지도에 표시할 선을 생성합니다
	
	var polyline = new kakao.maps.Polyline({
	    path: linePath, // 선을 구성하는 좌표배열 입니다
	    strokeWeight: 5, // 선의 두께 입니다
	    strokeColor: '#3F80F8', // 선의 색깔입니다
	    strokeOpacity: 0.7, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
	    strokeStyle: 'solid' // 선의 스타일입니다
	});
	
	var bounds = new kakao.maps.LatLngBounds();    
	  
	for(let i=0;i<linePath.length;i++){
		var content = `<div class ="label"><span class="left"></span><span class="center">${i+1}</span><span class="right"></span></div>`;		
		// 커스텀 오버레이가 표시될 위치입니다 
		var customOverlay = new kakao.maps.CustomOverlay({
		    position: linePath[i],
		    content: content   
		});
		// 커스텀 오버레이를 지도에 표시합니다
//		customOverlay.setMap(map);
		bounds.extend(linePath[i]);
	}
////	
////
////	// 지도에 선을 표시합니다 
	polyline.setMap(map); 
	
	console.log(data);
	var positions =[];
	let position = {
		title : data.summary.origin.name,
		latlng:new kakao.maps.LatLng(data.summary.origin.y,data.summary.origin.x),
	}
	positions.push(position);
	
	position = {
		title : data.summary.destination.name,
		latlng:new kakao.maps.LatLng(data.summary.destination.y,data.summary.destination.x),
	}
	positions.push(position);
	let waypoints = data.summary.waypoints;
	
	for(let idx=0;idx<waypoints.length;idx++){
		position = {
			title : waypoints[idx].name,
			latlng:new kakao.maps.LatLng(waypoints[idx].y,waypoints[idx].x),
		}
		positions.push(position);
	}
	console.log(positions);
	
	for (var i = 0; i < positions.length; i ++) {
	    // 마커 이미지의 이미지 크기 입니다
	    var imageSize = new kakao.maps.Size(35, 35); 
	    // 마커 이미지를 생성합니다    
	    var imageSrc = "/img/tour/maps-and-flags.png"; 
	    
	    if(i==0 || i == 1){
	    	imageSrc = "/img/tour/marker-green.png";
	    }
	    console.log(imageSrc);
	    console.log(positions[i].title);
	    
	    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);
	    // 마커를 생성합니다
	    var marker = new kakao.maps.Marker({
	        map: map, // 마커를 표시할 지도
	        position: positions[i].latlng, // 마커를 표시할 위치
	        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
	        image : markerImage // 마커 이미지 
	    });
	}
	
	console.log(bounds);
	
	map.setBounds(bounds);
}	
