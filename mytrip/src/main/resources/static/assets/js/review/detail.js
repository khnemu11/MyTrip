document.querySelector(".delete-btn").addEventListener("click", () => {
	
	if (confirm("정말 삭제하시겠습니까?")) {
		console.log(document.querySelector("#seq").data-seq);
		
		const body = {
				seq : document.querySelector("#seq").data-seq
		}
		
//		fetch('/reviews/delete')
//		.then()
	}

})