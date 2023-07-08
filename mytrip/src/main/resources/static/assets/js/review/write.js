const exampleModal = document.getElementById('exampleModal');
var areaCode=1;
var sigunguCode=1;
var contentTypeId="";
let userinfo;
let key ='opoKoaa3kaeINxgVEi1q+SrTFEFt/U8TOSyDXPcdAt6Ca5hjzRNGZZjSKUndxKSDlk/A164nPmQkpVk8c5f0NQ==';
let keyword = '';
let isLoad=false;
let pageNo= 1;

document.addEventListener("DOMContentLoaded", () => {
	let preview = document.querySelector("#preview")
	let url = preview.dataset.url
	
	document.querySelector("#preview").style.backgroundImage = "url('" + url+"')"
	console.log(preview.style);
	console.log(url)
});

if (exampleModal) {
  exampleModal.addEventListener('show.bs.modal', event => {
    const button = event.relatedTarget
    const recipient = button.getAttribute('data-bs-whatever')

    // Update the modal's content.
    const modalTitle = exampleModal.querySelector('.modal-title')
    const modalBodyInput = exampleModal.querySelector('.modal-body input')

    modalTitle.textContent = `여행지 검색`
  })
}
document.querySelector('#btn-search').addEventListener('click',function(){
	let tourList = document.querySelector('#location-list');
	console.log(tourList);

	
// document.querySelector('.loading-spinner').style.display='block';
	keyword = document.querySelector('#search').value;
	// 공백 제거 정규식
	keyword = keyword.replace(/^\s+|\s+$/gm,'');
	console.log("키워드 :"+keyword);
	console.log("a"+keyword+"a");
	if(keyword!=''){
		let context = `
			<div class="text-center loading-spinner">
				<div class="spinner-border" role="status"></div>
			</div>`;
		tourList.insertAdjacentHTML('beforebegin',context);
		makeTourList(true);
	}
// document.querySelector('.loading-spinner').style.display='none';
})
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
		}
		baseUrl = 'https://apis.data.go.kr/B551011/KorService1/searchKeyword1?';
		params.keyword = keyword;
		
		let paramUrl = new URLSearchParams(params);
		
		console.log(paramUrl.toString());
		await fetch(baseUrl + paramUrl.toString())
		.then((response)=>response.json()).
		then(function(data){
			var locations = data.response.body.items.item;
			console.log(data);
			
			let list = document.getElementById("location-list");
			let resultDiv = document.querySelector('#search-result');
			let selected = '';
			if(document.getElementById("selected")!=null){
				selected = document.getElementById("selected").innerText;
				
			}
			let resultContext = `검색 결과 : ${data.response.body.totalCount} 건<div id="selected"> ${selected}</div>`;
			
			resultDiv.innerHTML = resultContext;

			if(data.response.body.totalCount == 0){
				list.innerHTML = `<div>해당 조건에 맞는 관광지가 없습니다.</div>`
				if(document.querySelector('.loading-spinner')!=null){
					document.querySelector('.loading-spinner').remove();
				}
				return 0;
			}
			
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
				var img = locations[idx].firstimage = "" ? "/img/tour/no-image.png" : locations[idx].firstimage;
				var tel = locations[idx].tel == ""  ? "-" : locations[idx].tel;
				
				let baseUrl = '/tour/detail?';
				
				let url = baseUrl + paramUrl.toString();
				console.log(url);
				var context = `	
								<div type="button" class="card row" onclick="pickTour('${title}','${addr}','${mapx}','${mapy}','${tel}')" data-dismiss="modal">
									<div class="col-md-12 col-sm-12 col-xs-12 card-right">
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
								</div>
								`	
				list.insertAdjacentHTML('beforeend',context);
			}
		});
}
document.querySelector('#location-list').addEventListener('scroll',async function(e){
	let list = document.querySelector('#location-list'); 
	console.log(list);
	console.log(e.target.scrollHeight+" vs "+e.target.scrollTop+" + "+e.target.clientHeight);
	console.log(list.scrollHeight+" vs "+list.scrollTop+" + "+list.clientHeight);
	
	if(e.target.scrollHeight<=e.target.scrollTop+e.target.clientHeight+100 && !isLoad){
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

function pickTour(title,address,longitude,latitude,telephone){
	document.querySelector('#tour-title').value = title;
	document.querySelector('#tour-address').value = address;
	document.querySelector('#tour-longitude').value = longitude;
	document.querySelector('#tour-latitude').value = latitude;
	document.querySelector('#tour-telephone').value = telephone;
	
	document.querySelector('#selected').innerText = "선택한 관광지 : "+title;
	console.log(document.querySelector('#selected'));
//	let modal =new bootstrap.Modal('#exampleModal')
//	console.log(modal);
//	modal.hide();
	
//	document.getElementById('exampleModal').hide();
}


// 파일 미리보기
document.querySelector("#select-file").addEventListener('change', (e)=>{ 
    let files = e.target.files;
    if(files && files[0]){
        var reader = new FileReader();
        reader.onload = function(e) {
          console.log(e.target);
          document.querySelector('#preview').src = e.target.result;
        };
        reader.readAsDataURL(files[0]);
        e.target.src = files[0].name;
    }else{
        document.querySelector('#preview').src = ''
    }
});
