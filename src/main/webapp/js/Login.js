function checkForm(){
	console.log("document.form1.userId.value:" + document.form1.userId.value);
	console.log("document.form1.password.value:" + document.form1.password.value);
	var userId =document.form1.userId.value;
	var pass = document.form1.password.value;
    if(userId == "" || pass == ""){
        alert("必須項目を入力して下さい。");
        return false;
    } else if (userId.length !== 5 || pass.length !== 5){
    	alert("桁数が不正です");
    	return false
    } else {
    	return true;
    }
    $.ajax({
        // HTTP通信の種類にGETを設定
        type: "POST",
        // リクエスト先URL
        // Javaのサーブレットを指定する
        url: "http://localhost:8080/nwproject_B/Login?postalCode=" + postalCode,
        // サーバから返されるデータの型を指定
        // 今回はただの文字列
        dataType: "text"
    }
    )
    // 通信成功の場合の処理
    .done(
        // dataでout.print(ret);の値を受け取る
        function (data) {
            // テキストボックス書き換え処理
            var userId = document.form1.userId.value;
            alert(userId);
            addressInput.value = userId;
        }
    );
}