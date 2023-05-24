document.querySelector(".delete-btn").addEventListener("click", () => {
	
	if (confirm("정말 삭제하시겠습니까?")) {
		
		let seq = document.querySelector("#seq").dataset.seq;
		
		window.location.href = "/review/delete/" + seq;
	}
})