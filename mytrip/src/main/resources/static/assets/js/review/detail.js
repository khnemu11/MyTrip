let reviewNo = document.querySelector("#seq").dataset.seq;
let list = document.querySelector("#reply-list-container");

document.addEventListener("DOMContentLoaded",function(){
	document.querySelector(".regist-reply").addEventListener("click",function(event){
		let baseURL = "/reviews/registReply?";
	    let context =  document.querySelector(".reply-context").textContent;
	    let params = {
	        parentSeq : reviewNo,
	        context : context
	    }
	    let paramURL = new URLSearchParams(params);
	    let url = baseURL + paramURL.toString();
	
	    fetch(url)
	    .then((response)=>response.text())
	    .then(function(data){
			if(data!="true"){
				alert(data);
			}
		})
		listReply();
	});
	
	listReply();
	
	let deleteBtn = document.querySelectorAll(".delete-reply-button");

	for(let i=0;i<deleteBtn.length;i++){
	    deleteBtn[i].addEventListener("click",deleteReply);
	}
})

function listReply(){
	list.innerText = "";
	let url = "/reviews/listReply?reviewNo="+reviewNo;

	fetch(url)
	.then((response)=>response.json())
	.then(function(data){
		console.log(data);
		//덧글 생성
		for(let i=0;i<data.length;i++){
			let reply = `<div class="reply">
							<div class="reply-left">
								<div class="reply-profile"><i class="fa-solid fa-user" style="color: #000000;"></i></div>
							</div>
							<div class="reply-right">
								<div class="reply-right-top">
									<div class="reply-right-top-left">
										<span>${data[i].writer}</span>
										<span>${data[i].ctime}</span>
									</div>
									<div class="reply-right-top-right">
										<i class="fa-solid fa-pen" style="color: #000000;"></i>
										<div class="delete-reply-button" data-seq="${data[i].seq}" data-writer="${data[i].writer}">
											<i class="fa-solid fa-trash" style="color: #000000;"></i>
										</div>
									</div>
								</div>
								<div class="reply-right-bottom">
									${data[i].context}
								</div>
							</div>
						</div>`
			list.insertAdjacentHTML('beforeend',reply);
		}
		//삭제버튼 이벤트
		
		let deleteBtn = document.querySelectorAll(".delete-reply-button");

		for(let i=0;i<deleteBtn.length;i++){
		    deleteBtn[i].addEventListener("click",deleteReply);
		}
	})	
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
        listReply();
	})
}