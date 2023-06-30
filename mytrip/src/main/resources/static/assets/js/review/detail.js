document.querySelector(".regist-reply").addEventListener("click",function(event){
	let baseURL = "/reviews/registReply?";
    let parentSeq = document.querySelector("#seq").dataset.seq;
    let context =  document.querySelector(".reply-context").textContent;
    let params = {
        parentSeq : parentSeq,
        context : context
    }
    let paramURL = new URLSearchParams(params);
    let url = baseURL + paramURL.toString();

    fetch(url)
    .then((response)=>response.text())
    .then(function(data){
        alert(data);
	})
});

let deleteBtn = document.querySelectorAll(".delete-reply-button");

for(let i=0;i<deleteBtn.length;i++){
    deleteBtn[i].addEventListener("click",deleteReply);
}

function deleteReply(event){
	if(confirm("정말로 삭제하시겠습니까?")==false){
		return false;
	}
    console.log(event.currentTarget);
    
    let dataset = event.currentTarget.dataset;
    let params = {
        seq : dataset.seq,
        writer : dataset.writer
    }
    let baseURL = "/reviews/deleteReply?";
    let paramURL = new URLSearchParams(params);
    let url = baseURL + paramURL.toString();

    fetch(url)
    .then((response)=>response.text())
    .then(function(data){
        alert(data);
	})
}