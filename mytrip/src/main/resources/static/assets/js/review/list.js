let pageNo = 1;
let isLoad = false;

document.addEventListener("DOMContentLoaded", () => {
	getReviewList(true);
});

const getReviewList = (isNew) => {
	if (isNew) {
		pageNo = 1;
	} else {
		pageNo = pageNo + 1;
	}
	
	let params = {
			pageNo : pageNo,
			keyword : document.querySelector("#search").value,
	}
	
	let paramUrl = new URLSearchParams(params);
	
	console.log("/reviews/search?" + paramUrl.toString(params));
	
	fetch('/reviews/search?' + paramUrl.toString(params))
	.then(response => response.json())
	.then((reviewList) => {
		let list = document.querySelector(".review-container");
		
		if(isNew){
			list.innerHTML = "";
		}
		
		console.log("히히히히"+ reviewList.length);
		

		for (let idx in reviewList){
			let seq = reviewList[idx].seq;
			let title = reviewList[idx].title;
			let imageCode = reviewList[idx].imageCode;
			let imageSrc;
			
			if (imageCode != null) {
				if (imageCode.length < 3) {
					imageSrc = "review/"+imageCode+".png";
				} else {
					imageSrc = "upload/" + imageCode;
				}
				
			} else {
				imageSrc = "review/no-image.png";
			}
			
			let context = `	
				<form class="review-card" action="/review/detail/${seq}" method="get" onClick="submit()">
					<img class="review-img" src="/img/${imageSrc}">
					<div class="tour-info">
						<div class="tour-info-top">
							<span class="review-title">
							&nbsp;`+title+`
							</span>
						</div>
					</div>
				</form>
				`;
			list.insertAdjacentHTML('beforeend', context);
		}		
		if(list.childNodes.length != 0) {
			document.querySelector(".no-match").style.display = "none";
		} else {
			document.querySelector(".no-match").style.display = "block";
			return 0;
		}

	})
	.catch(error => {
		console.log(error);
	})	
};

document.querySelector("#btn-search").addEventListener("click", () => getReviewList(true));


document.addEventListener('scroll', function(e){
	
	console.log(document.documentElement.scrollHeight+" , "+ document.documentElement.scrollTop+" ,"+ document.documentElement.clientHeight);
	
	if(document.documentElement.scrollHeight <= document.documentElement.scrollTop + document.documentElement.clientHeight+100 && !isLoad){
		isLoad=true;
		getReviewList(false);
		isLoad=false;
	}
});