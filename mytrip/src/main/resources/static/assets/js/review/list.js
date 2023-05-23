let pageNo = 1;

document.addEventListener('DOMContentLoaded',function(){
	
})

const getList = () => {
	isNew = true;
	let body = {
			keyword : document.querySelector("#search").value
	}
	fetch('/reviews/search', {
		method: "POST",
		headers: {
			"Content-Type": "application/json",
		},
		body: JSON.stringify(body)
	})
	.then(response => response.json())
	.then((reviewList) => {
		let list = document.querySelector(".review-container");
		
		if(isNew){
			list.innerHTML = "";
		}
		
		if(reviewList.length != 0) {
			document.querySelector(".no-match").style.display = "none";
		} else {
			document.querySelector(".no-match").style.display = "block";
			return 0;
		}

		for (let idx in reviewList){
			let seq = reviewList[idx].seq;
			let title = reviewList[idx].title;
			let imageCode = reviewList[idx].imageCode;
			
			console.log(imageCode);
			
			
			if (imageCode == null) {
				imageCode = "no-image";
			}
			
			let context = `	
				<form class="review-card" action="/review/detail/${seq}" method="get" onClick="submit()">
					<img class="review-img" src="/img/review/${imageCode}.png">
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
	})
	.catch(error => {
		console.log(error);
	})
}

document.querySelector("#btn-search").addEventListener("click", () => getList());