let key ='opoKoaa3kaeINxgVEi1q+SrTFEFt/U8TOSyDXPcdAt6Ca5hjzRNGZZjSKUndxKSDlk/A164nPmQkpVk8c5f0NQ==';
let tourData = document.querySelectorAll('.tour-data');
let cardImg = document.querySelectorAll('.tour-card img');

document.addEventListener('DOMContentLoaded',async function(){
	tourData = document.querySelectorAll('.tour-data');
	cardImg = document.querySelectorAll('.tour-card img');
	await getPicture();
	
	tourData = document.querySelectorAll('.favorite-data');
	cardImg = document.querySelectorAll('.favorite-card img');
	await 	getPicture();
});

async function getPicture(){
	let tourIdx=0;
	for(let i=0;i<tourData.length;i++){
		console.log(cardImg[tourIdx]);
		let baseUrl = 'https://apis.data.go.kr/B551011/KorService1/searchKeyword1?';
		let params = {
				serviceKey : key,
				numOfRows : 1,
				pageNo : 1,
				MobileOS : 'win',
				MobileApp : 'mytrip',
				_type : 'json',
				keyword: tourData[i].dataset.title,
			}
		let paramUrl = new URLSearchParams(params);
		tourIdx=i;
		await fetch(baseUrl + paramUrl.toString())
		.then((response)=>response.json()).
		then(function(data){
			let img = "/img/tour/no-image.png";
			if(data.response.body.items.item!=null && data.response.body.items.item[0].firstimage!=''){
				img = data.response.body.items.item[0].firstimage;
			}
			console.log(cardImg[tourIdx]);
			cardImg[tourIdx].src = img;
		})
	}

}