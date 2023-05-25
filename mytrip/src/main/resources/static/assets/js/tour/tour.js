var areaCode="";
var sigunguCode="";

var contentTypeId="";
let userinfo;
let key ='opoKoaa3kaeINxgVEi1q+SrTFEFt/U8TOSyDXPcdAt6Ca5hjzRNGZZjSKUndxKSDlk/A164nPmQkpVk8c5f0NQ==';
let keyword = '';
let isLoad=false;
let pageNo= 1;

document.addEventListener('DOMContentLoaded',function(){
	init();
})
async function init(){
	await makeCityOption();
	await makeGunGuOption();
	await makeTourList(true);
}
async function makeGunGuOption(data){
	console.log('makeGunGuOption 실행');
	if(areaCode == ''){
		var select = document.getElementById("select-gun");
		options ="<option value = ''>전체</option>";
		select.innerHTML = options;
		return true;
	}
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
			var options ="<option value = ''>전체</option>";
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
		numOfRows : 30,
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
		var options = "<option value = ''>전체</option>";
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
	let tourList = document.querySelector('#location-list');
	console.log(tourList);
	let context = `
				<div class="text-center loading-spinner">
					<div class="spinner-border" role="status"></div>
				</div>`;
	tourList.insertAdjacentHTML('beforebegin',context);
//	document.querySelector('.loading-spinner').style.display='block';
	keyword = document.querySelector('#search').value;
	console.log(keyword);
	makeTourList(true);
//	document.querySelector('.loading-spinner').style.display='none';
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
	
	console.log("도시 변경");
	sigunguCode='';
	areaCode =  document.getElementById("select-city").value;
	await makeGunGuOption();
	await makeTourList(true);		
}

async function makeTourList(isNew){
	console.log("리스트 출력");
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
			var locations = data.response.body.items.item;
			console.log(document.getElementById('no-result'));
			document.getElementById('no-result').setAttribute('style',"display:none;")
			if(data.response.body.totalCount == 0){
				document.getElementById("location-list").innerHTML = '';
				if(document.querySelector('.loading-spinner')!=null){
					document.querySelector('.loading-spinner').remove();
				}
				
				document.getElementById('no-result').setAttribute('style',"display:block;")
				
				
				return 0;
			}
			let list = document.getElementById("location-list");
			
			if(isNew){
				list.innerHTML = "";
			}
			if(document.querySelector('.loading-spinner')!=null){
				document.querySelector('.loading-spinner').remove();
			}
			
			for(var idx in locations){
				var title = locations[idx].title;
				var addr = locations[idx].addr1;
				var mapx = locations[idx].mapx;
				var mapy = locations[idx].mapy;
				var img = locations[idx].firstimage;
				var tel = locations[idx].tel;

				if(img == ""){
					img = "/img/tour/no-image.png";
				}
				
				let baseUrl = '/tour/detail?';
				
				let url = baseUrl + paramUrl.toString();
				console.log(url);
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
			}
		});
}
document.addEventListener('scroll',async function(e){
	console.log(document.documentElement.scrollHeight+" vs "+document.documentElement.scrollTop+" + "+document.documentElement.clientHeight);
	console.log(document.body.scrollHeight+" vs "+document.body.scrollTop+" + "+document.body.clientHeight);
	
	if(document.documentElement.scrollHeight<=document.documentElement.scrollTop+document.documentElement.clientHeight+100 && !isLoad){
		let tourList = document.querySelector('#list-container');
		let context = `
					<div class="text-center loading-spinner">
						<div class="spinner-border" role="status"></div>
					</div>`;
		tourList.insertAdjacentHTML('beforeend',context);
		console.log(tourList.childNodes);
		isLoad=true;
		await makeTourList(false);
		isLoad=false;
	}
})

function submitChildForm(e){
	console.log(e);
}
