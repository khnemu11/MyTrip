// form에서 빈 칸 해결 하기
document.querySelector("#register-form").addEventListener("click", () => {
	if (!document.querySelector("#id").value) {
		alert("아이디를 입력하세요");
		// 커서 아이디 칸으로 옮기기
	}
	else if (!document.querySelector("#name").value) {
		alert("이름을 입력하세요");
	}
	else if (!document.querySelector("#password").value) {
		alert("비밀번호를 입력하세요");
	}
	else if (!document.querySelector("#pwcheck").value) {
		alert("비밀번호를 확인해주세요");
	}
	else if (!document.querySelector("#email").value) {
		alert("이메일을 입력해주세요");
	}
})

// 아이디는 몇자 이상 몇자 이하인지
// 이름 몇자 이상 몇자 이하
// 비밀번호 일치