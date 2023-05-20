var areaCode=1;
var sigunguCode=1;
var contentTypeId="";
let userinfo;
let favoriteList=[];
//let key = 'opoKoaa3kaeINxgVEi1q%2BSrTFEFt%2FU8TOSyDXPcdAt6Ca5hjzRNGZZjSKUndxKSDlk%2FA164nPmQkpVk8c5f0NQ%3D%3D';
let key ='opoKoaa3kaeINxgVEi1q+SrTFEFt/U8TOSyDXPcdAt6Ca5hjzRNGZZjSKUndxKSDlk/A164nPmQkpVk8c5f0NQ==';
let keyword = '';
	
document.addEventListener('DOMContentLoaded',function(){
//	fetch(`https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=opoKoaa3kaeINxgVEi1q%2BSrTFEFt%2FU8TOSyDXPcdAt6Ca5hjzRNGZZjSKUndxKSDlk%2FA164nPmQkpVk8c5f0NQ%3D%3D&numOfRows=12&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&areaCode=1&sigunguCode=1`)
//	.then((response)=>response.json())
//	.then((data)=>listdata(data));
	
	init();

	fetch('/locations/list')
	.then((response)=>response.json())
	.then((data)=>{
		console.log(data)
		
		for(var idx in data){
			favoriteList.push(data[idx].title);
		}
		console.log(favoriteList)
	})

//	fetch('/users/user')
//	.then((response)=>response.json())
//	.then((data)=>{
//		userinfo = data;
//		console.log(userinfo);
//	})
})
async function init(){
	await makeCityOption();
	await makeGunGuOption();
	await getTourData(true);
}
async function makeGunGuOption(data){
	console.log('makeGunGuOption 실행');
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
	keyword = document.querySelector('#search').value;
	getTourData(true);
})



function getPage(pageNo){
	console.log(pageNo);
	var link = `https://apis.data.go.kr/B551011/KorService1/areaBasedList1?serviceKey=opoKoaa3kaeINxgVEi1q%2BSrTFEFt%2FU8TOSyDXPcdAt6Ca5hjzRNGZZjSKUndxKSDlk%2FA164nPmQkpVk8c5f0NQ%3D%3D&numOfRows=12&pageNo=`+pageNo+`&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&areaCode=`+areaCode+`&sigunguCode=`+sigunguCode
	console.log(link);
	fetch(link)
	.then((response)=>response.json())
	.then((data)=>listdata(data));
}

function onChangeContentTypeId(){
	contentTypeId = document.getElementById("select-contentTypeId").value;
	getTourData(true);
}

function onChangeGunGu(){
	sigunguCode =  document.getElementById("select-gun").value;
	getTourData(true);
}

async function onChangeCity(){
	areaCode =  document.getElementById("select-city").value;
	await makeGunGuOption();
	await getTourData(true);		
}


function makeTourList(data, isNew){
	console.log(data);
	console.log(isNew);
	var locations = data.response.body.items.item;
	
	if(data.response.body.totalCount == 0){
		document.getElementById("location-list").innerHTML = `<div>해당 조건에 맞는 관광지가 없습니다.</div>`
		return 0;
	}
	
	let list = document.getElementById("location-list");
	
	if(isNew){
		list.innerHTML = "";
	}
	
	for(var idx in locations){
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
		list.insertAdjacentHTML('beforeend',context);
	}
}


async function getTourData(isNew){
		let params = {
			serviceKey : key,
			numOfRows : 12,
			pageNo : 1,
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
			var locations = data.response.body.items.item;
			
			if(data.response.body.totalCount == 0){
				document.getElementById("location-list").innerHTML = `<div>해당 조건에 맞는 관광지가 없습니다.</div>`
				return 0;
			}
			
			let list = document.getElementById("location-list");
			
			if(isNew){
				list.innerHTML = "";
			}
			
			for(var idx in locations){
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
				list.insertAdjacentHTML('beforeend',context);
			}
		});
}
	
