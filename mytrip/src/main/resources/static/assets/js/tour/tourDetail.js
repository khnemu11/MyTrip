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
	init();
})
async function init(){
	latitude = container.getAttribute('data-lat');
	longitude = container.getAttribute('data-lng');
	title = container.getAttribute('data-title');
	
	await makeMap(latitude,longitude);
	await getTour();
	favorite = document.querySelector('.favorite');
	
	await setFavorite(title,false);
	await setFavorite(title,true);
	await setNearLocation();
	
}
async function setNearLocation(){
	let params = {
			serviceKey : key,
			numOfRows : 100,
			pageNo : 1,
			MobileOS : 'win',
			MobileApp : 'mytrip',
			_type : 'json',
			listYN:'Y',
			mapY:latitude,
			mapX:longitude,
			radius:1000,
			arrange:'A',
		}
	
		let baseUrl = 'https://apis.data.go.kr/B551011/KorService1/locationBasedList1?';
		let paramUrl = new URLSearchParams(params);
		
		console.log(paramUrl.toString());
		
		await fetch(baseUrl + paramUrl.toString())
		.then((response)=>response.json()).
		then(function(data){
			console.log("주변 관광지 데이터");
			let items = data.response.body.items.item;
			let list = document.querySelector('.tour-list');
			console.log(items);
			console.log(list);
			console.log(items.length);
			
			for(let idx =0; idx<items.length;idx++){
				let title = items[idx].title;
				let addr = items[idx].addr1;
				let mapx = items[idx].mapx;
				let mapy = items[idx].mapy;
				let tel = items[idx].tel == "" ? "-" : items[idx].tel;
				let img = items[idx].firstimage == "" ? "/img/tour/no-image.png" : items[idx].firstimage;
				
				params ={
						title : items[idx].title,
						address : items[idx].addr1,
						longitude : items[idx].mapx,
						latitude : items[idx].mapy,
						telephone : items[idx].tel,
				};
				
				paramUrl = new URLSearchParams(params);
				let baseUrl = '/tour/detail?';
				let url = baseUrl + paramUrl.toString();
				console.log(url)
				
				var context = `	
					<div class="tour-card row" onclick="window.location.href='${url}'">
								<div class="col-md-5 col-sm-5 col-xs-5">
									<img src="${img}">
								</div>
								<div class="col-md-7 col-sm-7 col-xs-7">
									<div class="tour-description-wrapper">
										<div class="tour-description-top">
											<div class="tour-title">${title}</div>
										</div>
										<div class="tour-description-bottom">
											<span>
												<span><i class="fa-solid fa-location-dot"></i>
												<span class="tour-address">${addr}</span>
												</span>
											</span>
											<span><i class="fa-solid fa-phone"></i><span class="tour-tel">${tel}</span></span>
											</div>
									</div>
								</div>
							</div>`
					console.log(context);
				list.insertAdjacentHTML('beforeend',context);
			}
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

async function setFavorite(title,isShow){
	console.log(title);
	let baseUrl = '/favorites/';
		fetch(baseUrl + title)
		.then((response)=>response.text()).
		then(function(data){
			console.log("요청 결과"+data);
			let favorite = document.querySelector('.favorite');
			console.log(favorite);
			if(data == 'regist' && isShow){
				console.log("등록 아이콘 설정");
				favorite.innerHTML = `<i class="fa-solid fa-heart"></i>`;
			}else if(data == 'delete' && isShow){
				console.log("삭제 아이콘 설정");
				favorite.innerHTML = `<i class="fa-regular fa-heart"></i>`;
			}
		})
}
function shareFaceBook(){
	let url = 'www.mytrip.or.kr';
	console.log('url');
	console.log(url);
	
	window.open('https://www.facebook.com/sharer/sharer.php?u=' + url,
		        'facebook-share-dialog',
		        'width=800,height=600');
}

