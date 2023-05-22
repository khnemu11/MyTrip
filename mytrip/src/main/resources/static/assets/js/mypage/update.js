// 비밀번호 일치 로직
let isValid = true;
const password = document.getElementById("password");
const confirmPassword = document.querySelector("#confirm-password");
const validPassword = () => {
	console.log(this);
	const passwordValue = password.value;
	const confirmPasswordValue = confirmPassword.value;
	let confirmResult = document.getElementById("confirm-password-result");
	console.log(passwordValue, confirmPasswordValue);
	if (passwordValue != confirmPasswordValue) {
		confirmResult.setAttribute("class", "mb-3 text-danger");
		confirmResult.textContent = "비밀번호가 일치하지 않습니다.";
		isValid = false;
	} else {
		confirmResult.textContent = "";
		isValid = true;
	}
}
password.addEventListener("keyup", validPassword);
confirmPassword.addEventListener("keyup", validPassword);

document.querySelector("#update-form").addEventListener("submit", (e) => {
	e.preventDefault();
	if (isValid) {
		document.querySelector("#update-form").submit();
	} else {
		alert("비밀번호를 다시 확인해주세요");
	}
})
