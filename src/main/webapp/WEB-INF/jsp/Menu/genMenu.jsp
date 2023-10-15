<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<title>メインメニュー（管理者モード）</title>
</head>
<body>
<body style="background:#63515f;">
	<div style="width:200px margin-top:30px;"></div>
	<div style="text-align:center; background:#d3adf0; width:400px; margin-left:450px">
		<div style="margin-top:50px; padding-top:10px;"></div>
		<form action="/example/DairyWrite" method="get">
			<p><button class="search-btn3" type="submit">日記投稿</button></p>
		</form>
	<p><button class="search-btn2" onclick="history.back()">戻る</button></p>
	</div>
	<div style="width:200px"></div>
</body>
</html>