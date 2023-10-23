function checkForm(){
	console.log("document.form1.userId.value:" + document.form1.radiobutton.value);
	var checkNo =document.form1.radiobutton.value;
    if(checkNo == null || checkNo == "" ){
        alert("更新対象を選択してください");
        return false;
    } else {
    	return true;
    }
}
function checkUserInfo() {
	// FQDNの生成
	var fqdn = location;
	var nowId = document.form1.nowID.value;
    var userId =document.form1.editID.value;
    var userPass =document.form1.editPass.value;
    var userPerm =document.form1.editPermission.value;
    $.ajax({
        // HTTP通信の種類にGETを設定
        type: "GET",
        // リクエスト先URL
        // Javaのサーブレットを指定する
        url: fqdn.origin + "/nwproject/EditUserView?nowId=" + nowId + "&" +  "userId=" + userId + "&" + "userPass=" + userPass + "&" + "userPerm=" + userPerm,
        // サーバから返されるデータの型を指定
        // 今回はただの文字列
        dataType: "text"
    })
    // 通信成功の場合の処理
    .done(
        // dataでout.print(ret);の値を受け取る
        function (data) {
        	if(data === "0"){
        		alert("値を更新してください")
        		return false;
        	}
        	else {
        		alert("登録が完了しました")
        		return true;
        	}

        }
    );
}