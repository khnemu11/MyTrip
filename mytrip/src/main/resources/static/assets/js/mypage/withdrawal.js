document.querySelector("#password-submit").addEventListener("click", () => {
	if (confirm("정말 회원 탈퇴하시겠습니까?")) {
		const body = {
				password : document.querySelector("#password").value
		}
		
		fetch("/mypages/withdrawal", {
			method : "POST",
			headers : {
				"Content-Type" : "application/json"
			},
			body : JSON.stringify(body)	
		})
		.then(response => {
			if (response.ok) {
				return response.text();
			}
			else {
				alter('회원 탈퇴 실패');
			}
		})
		.then(message => {
			alert(message);
			window.location.href = "/";
		})
		.catch(error => {
			console.error(error);
			alert("오류가 발생했습니다.", error.message);
		})
	}
})