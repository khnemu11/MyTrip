let latitude = 33.450701;
let longitude = 126.570667;
let title = '';
let map;
let key ='opoKoaa3kaeINxgVEi1q+SrTFEFt/U8TOSyDXPcdAt6Ca5hjzRNGZZjSKUndxKSDlk/A164nPmQkpVk8c5f0NQ==';
var areaCode=1;
var sigunguCode=1;
var contentTypeId="";
let userinfo;
let keyword = '';
let isLoad=false;
let pageNo= 1;
let container = document.getElementById('map');
let options = {
	center: new kakao.maps.LatLng(latitude, longitude),
	level: 3
};

document.addEventListener('DOMContentLoaded',function(){
	makeMap(latitude,longitude);
	init();
})
async function init(){
	await makeCityOption();
	await makeGunGuOption();
	await makeTourList(true);
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
	var mapTypeControl = new kakao.maps.MapTypeControl();

	// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
	// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
	map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

	// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
	var zoomControl = new kakao.maps.ZoomControl();
	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

	marker.setMap(map);
}
async function makeGunGuOption(data){
	console.log('makeGunGuOption 실행');
	document.querySelector('.loading-spinner').style.display='block';
	let params = {
			serviceKey : key,
			numOfRows : 20,
			pageNo : 1,
			MobileOS : 'win',
			MobileApp : 'mytrip',
			_type : 'json',
			areaCode: areaCode,
		}
		let baseUrl = 'https://apis.data.go.kr/B551011/KorService1/areaCode1?';
		let paramUrl = new URLSearchParams(params);
		console.log(paramUrl.toString());
		await fetch(baseUrl + paramUrl.toString())
		.then((response)=>response.json()).
		then(function(data){
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
			console.log("시/군/구 코드 옵션 생성 끝");
		})
}


async function makeCityOption(){
	console.log('makeCityOption 실행');
	document.querySelector('.loading-spinner').style.display='block';
	let params = {
		serviceKey : key,
		numOfRows : 20,
		pageNo : 1,
		MobileOS : 'win',
		MobileApp : 'mytrip',
		_type : 'json',
	}
	let baseUrl = 'https://apis.data.go.kr/B551011/KorService1/areaCode1?';
	let paramUrl = new URLSearchParams(params);
	console.log(paramUrl.toString());
	
	await fetch(baseUrl + paramUrl.toString())
	.then((response)=>response.json())
	.then(function(data){
		console.log(data);
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
		console.log("도시 코드 옵션 생성 끝");
	});
}
document.querySelector('#btn-search').addEventListener('click',function(){

	document.querySelector('.loading-spinner').style.display='block';
	keyword = document.querySelector('#search').value;
	makeTourList(true);
})

function onChangeContentTypeId(){
	contentTypeId = document.getElementById("select-contentTypeId").value;
	makeTourList(true);
}

function onChangeGunGu(){
	sigunguCode =  document.getElementById("select-gun").value;
	makeTourList(true);
}

async function onChangeCity(){
	areaCode =  document.getElementById("select-city").value;
	await makeGunGuOption();
	await makeTourList(true);		
}

async function makeTourList(isNew){
		if(isNew){
			pageNo = 1;
		}else{
			pageNo= pageNo+1;
		}
		
		let params = {
			serviceKey : key,
			numOfRows : 12,
			pageNo : pageNo,
			MobileOS : 'win',
			MobileApp : 'mytrip',
			_type : 'json',
			areaCode: areaCode,
			sigunguCode : sigunguCode,
			contentTypeId : contentTypeId,
		}
		let baseUrl = 'https://apis.data.go.kr/B551011/KorService1/areaBasedList1?';
		
		if(keyword !=''){
			baseUrl = 'https://apis.data.go.kr/B551011/KorService1/searchKeyword1?';
			params.keyword = keyword;
		}
		
		let paramUrl = new URLSearchParams(params);
		
		console.log(paramUrl.toString());
		await fetch(baseUrl + paramUrl.toString())
		.then((response)=>response.json()).
		then(function(data){
			console.log(data);
			var locations = data.response.body.items.item;
			let totalCnt = data.response.body.totalCount.toLocaleString('ko-KR');
			document.getElementById('result-count').innerHTML = `( ${totalCnt} )`
			if(data.response.body.totalCount == 0){
				document.getElementById("tour-list").innerHTML = `<div>해당 조건에 맞는 관광지가 없습니다.</div>`
				return 0;
			}
			
			let list = document.getElementById("tour-list");
			
			if(isNew){
				list.innerHTML = "";
			}
			
			for(var idx in locations){
				var title = locations[idx].title;
				var addr = locations[idx].addr1;
				var longitude = locations[idx].mapx
				var latitude = locations[idx].mapy;
				var telephone = locations[idx].tel;
				var img = locations[idx].firstimage;
				var tel =  locations[idx].tel;
				
				params ={
					title : locations[idx].title,
					address : locations[idx].addr1,
					longitude : locations[idx].mapx,
					latitude : locations[idx].mapy,
					telephone : locations[idx].tel,
				}
				
				paramUrl = new URLSearchParams(params);
				let baseUrl = '/tour/detail?';
				let url = baseUrl + paramUrl.toString();
				
				if(img == ""){
					img = "/img/tour/no-image.png";
				}
				
	
				var context = `	
					<div class="card row">
					<div class="col-md-6 col-sm-6 col-xs-6">
						<img src="${img}"/>
					</div>
					<div class="col-md-6 col-sm-6 col-xs-6 card-right">
						<div class="card-title">${title}</div>	
						<div class="card-description">
							<div class="card-address-container">
								<i class="fa-solid fa-location-dot"></i>
								<div class="card-address">${addr}</div>
							</div>
							<div class="card-tel-container">
								<i class="fa-solid fa-phone"></i><span class="card-tel">${tel}</span>
							</div>	
						</div>
					</div>
					<div class="card-button">
						<button type="button" class="btn btn-left" onclick="window.location.href='${url}'">상세정보</button>
						<button type="button" class="btn btn-right">경로추가</button>
					</div>
					</div>`	
				list.insertAdjacentHTML('beforeend',context);
			}
			
			let titleHeight = document.querySelector('.title-bar').offsetHeight;
			let searchHeight = document.querySelector('#search').offsetHeight;
			let mapHeight = document.querySelector('#map').offsetHeight;
			let height = mapHeight - (searchHeight + titleHeight);

			document.querySelector('#tour-list-wrapper').style.height = height;
			document.getElementById('tour-list-wrapper').setAttribute("style",`height:${height}px;`);
			document.querySelector('.loading-spinner').style.display='none';
		});
}
document.addEventListener('scroll',async function(e){
	if(document.body.scrollHeight>=document.body.scrollTop+document.body.clientHeight && !isLoad){
		isLoad=true;
		await makeTourList(false);
		isLoad=false;
	}
})
