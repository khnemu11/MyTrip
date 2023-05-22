// 비밀번호 일치 로직

let isValid = true;

document.querySelector("#confirm-password").addEventListener("keyup", () => {
	const password = document.getElementById("password").value;
	const confirmPassword = document.querySelector("#confirm-password").value;
	console.log(this);
	let confirmResult = document.getElementById("confirm-password-result");
	console.log(password+" vs "+confirmPassword);
	if (password != confirmPassword) {
		confirmResult.setAttribute("class", "mb-3 text-danger");
		confirmResult.textContent = "비밀번호가 일치하지 않습니다.";
		isValid = false;
	} else {
		confirmResult.textContent = "";
		isValid = true;
	}
});

document.querySelector("#update-form").addEventListener("submit", (e) => {
	e.preventDefault();
	if (isValid) {
		document.querySelector("#update-form").submit();
	} else {
		alert("비밀번호를 다시 확인해주세요");
	}
})
