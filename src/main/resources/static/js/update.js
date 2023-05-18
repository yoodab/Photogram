// (1) 회원정보 수정
function update(userId, event) {

	event.preventDefault(); // 폼태그 액션 막기
	let data = $("#profileUpdate").serialize(); // key=value 


	$.ajax({
		type: "put",
		url: `/api/user/${userId}`,
		data: data,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType: "json",
	}).done((res) => { // HttpStatus 상태코드 200번대 일때
		console.log("update 성공", res);
		location.href = `/user/${userId}`;
	}).fail(error => { // HttpStatus 상태코드 200번대가 아닐 때
		console.log("update 실패", error);
		if (error.data == null) {
			alert(error.responseJSON.message);
		} else {
			alert(JSON.stringify(error.responseJSON.data));
		}


	})
}
