// form에서 빈 칸 해결 하기
let isUseId = false;
let isAuthenticated = false;

function formSubmit(){
	console.log("form submit 실행");
	if (!document.querySelector("#id").value) {
		alert("아이디를 입력하세요");
		return false;
		// 커서 아이디 칸으로 옮기기
	}
	else if (!document.querySelector("#name").value) {
		alert("이름을 입력하세요");
		return false;
	}
	else if (!document.querySelector("#password").value) {
		alert("비밀번호를 입력하세요");
		return false;
	}
	else if (!document.querySelector("#pwcheck").value) {
		alert("비밀번호를 확인해주세요");
		return false;
	}
	else if (!document.querySelector("#email").value) {
		alert("이메일을 입력해주세요");
		return false;
	}
	else if (!isAuthenticated) {
		alert("이메일을 인증해주세요");
		return false;
	}
	
	return true;
}
// 아이디 글자수, 중복 처리
document.querySelector("#id").addEventListener("keyup", () => {
    console.log(document.querySelector("#id"));
	let userid = document.querySelector("#id").value;
	
    console.log(userid);
    let resultDiv = document.querySelector("#idcheck-result");
    if(userid.length < 6 || userid.length > 20) {
        resultDiv.setAttribute("class", "mb-3 text-dark");
        resultDiv.textContent = "아이디는 6자 이상 20자 이하 입니다.";
        isUseId = false;
    } else {
    	resultDiv.setAttribute("class", "mb-3 text-warning");
        resultDiv.textContent = "아이디 조회중입니다.";
        
        fetch(`/user/login/idCheck/${userid}`)
            .then(response =>response.text())
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

document.querySelector("#btn-send").addEventListener("click",function(){
    console.log("클릭!");
    let email = document.querySelector("#email").value;
    
    let param = {
    		email:email
    }
    
    let paramUrl = new URLSearchParams(param);
    let baseUrl = "http://52.79.119.160:9999/email/send?";
   
    fetch(baseUrl+paramUrl)
	.then((response)=>response.json())
	.then(function(data){
		console.log("이메일 발행");
		alert("입력하신 이메일로 인증번호를 발송하였습니다.");
	});
});

function comfirmEmail(){
	console.log(document.getElementById('btn-auth'));
	console.log(document.querySelector("#btn-auth"));
	
	let value = document.getElementById('auth').value;
	let param={
		code : value
	}
	
	let paramUrl = new URLSearchParams(param);
	let baseUrl = "http://52.79.119.160:9999/email/authenticate?";

    fetch(baseUrl+paramUrl.toString())
	.then((response)=>response.json())
	.then(function(data){
		if(data==true){
			console.log("이메일 검증 성공");

			alert("인증번호 검증을 성공하였습니다.");
			document.getElementById('btn-auth').disabled= true;		
			isAuthenticated=true;
		}else{
			console.log("이메일 검증 실패");
			alert("인증번호가 다릅니다. 다시 입력해 주세요");
		}		
	});
}
// 이름 몇자 이상 몇자 이하
// 비밀번호 일치
