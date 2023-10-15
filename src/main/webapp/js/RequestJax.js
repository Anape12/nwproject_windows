function requestServe() {
	var projectName = "nwproject_B";
	// FQDNの生成
	var fqdn = location.origin;
	var url = fqdn + "/" + projectName;
    var userId ="a0005";
    var userPass ="k1226";
    var userPerm ="1";
    $.ajax({
        // HTTP通信の種類にGETを設定
        type: "GET",
        // リクエスト先URL
        // Javaのサーブレットを指定する
        url: url + "/SelectApp?userId=" + userId + "&" + "userPass=" + userPass + "&" + "userPerm=" + userPerm,
        // サーバから返されるデータの型を指定
        // 今回はただの文字列
        dataType: "text"
    })
    // 通信成功の場合の処理
    .done(
        // dataでout.print(ret);の値を受け取る
        function (data) {
        	if(data === "0"){
//        		event.preventDefault();
//        		event.stopPropagation();
//        		event.stopImmediatePropagation();
//        		alert("値を更新してください")
        		return false;
        	}
        	else if(data === "1") {
        		return true;
        	}
        }
    );
}