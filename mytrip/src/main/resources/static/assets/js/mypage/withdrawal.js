document.querySelector("#withdrawal").addEventListener("click", () => {
	if (confirm("정말 회원 탈퇴하시겠습니까?")) {
		fetch("/mypages/withdrawal")
		.then(response => {
			if (response.ok) {
				return response.text();
			}
			else {
				throw new Error('회원 탈퇴 실패');
			}
		})
		.then(message => {
			alert(message);
		})
		.catch(error => {
			console.error(error);
			alert("오류가 발생했습니다.", error.message);
		})
	}
})