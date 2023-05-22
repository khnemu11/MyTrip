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

// 아이디 글자수, 중복 처리
let isUseId = false;
document.querySelector("#id").addEventListener("keyup", () => {
    let userid = this.value;
    console.log(userid);
    let resultDiv = document.querySelector("#idcheck-result");
    if(userid.length < 6 || userid.length > 20) {
        resultDiv.setAttribute("class", "mb-3 text-dark");
        resultDiv.textContent = "아이디는 6자 이상 20자 이하 입니다.";
        isUseId = false;
    } else {
        fetch(`/user/login/idCheck/${userid}`)
            .then(response => response.text())
            .then(data => {
                console.log(data);
                if(data == 0) {
                    resultDiv.setAttribute("class", "mb-3 text-primary");
                    resultDiv.textContent = userid + "는 사용할 수 있습니다.";
                    isUseId = true;
                } else {
                    resultDiv.setAttribute("class", "mb-3 text-danger");
                    resultDiv.textContent = userid + "는 사용할 수 없습니다.";
                    isUseId = false;
                }
            });
        }
    });
// 이름 몇자 이상 몇자 이하
// 비밀번호 일치