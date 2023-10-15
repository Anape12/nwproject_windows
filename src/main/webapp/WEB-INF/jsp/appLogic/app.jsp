<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
    <html lang="ja">
<%
String result = (String)session.getAttribute("resultApp");
%>
<!DOCTYPE html>
 <script src='http://ajax.aspnetcdn.com/ajax/modernizr/modernizr-2.8.3.js'></script>
    <script src='http://code.jquery.com/jquery-1.11.3.min.js'></script>
    <script src='http://code.jquery.com/ui/1.11.1/jquery-ui.min.js'></script>
    <!-- Ignite UI Required Combined CSS Files -->
    <link href='http://cdn-na.infragistics.com/igniteui/2019.2/latest/css/themes/infragistics/infragistics.theme.css' rel='stylesheet' />
    <link href='http://cdn-na.infragistics.com/igniteui/2019.2/latest/css/structure/infragistics.css' rel='stylesheet' />
    <!-- Ignite UI Required Combined JavaScript Files -->
    <script src='http://cdn-na.infragistics.com/igniteui/2019.2/latest/js/infragistics.core.js'></script>
    <script src='http://cdn-na.infragistics.com/igniteui/2019.2/latest/js/infragistics.lob.js'></script>
<html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>メインメニュー（<%= result %>）</title>
</head>
<body>
<body style="background:#63515f;">
	<div style="width:200px margin-top:30px;"></div>
	<div style="text-align:center; width:400px;">
		<p>選択アプリ【<%= result %>】</p>
	<form method="POST" enctype="multipart/form-data" action="/nwproject/UploadServlet" onsubmit="return requestServe()">
	<input type="file" name="FILE_INFO"/><br>
	<input type="submit" value="Upload" name="Upload"/>
	</form>
	<p><button class="search-btn2" onclick="history.back()">ログアウト</button></p>
	<jsp:include page="../footer.jsp" flush="true"/>
	</div>
  <table id='grid'></table>
  <script>
      $(function () {
          var data = [
                    { 'Name': 'a0001', 'Age': 99 },
                    { 'Name': 'a0002', 'Age': 99 },
                    { 'Name': 'a0003', 'Age': 99 },
                    { 'Name': 'a0004', 'Age': 99 },
                    { 'Name': 'a0005', 'Age': 99 }
              ];
          $('#grid').igGrid({
              dataSource: data,
          });
      });
  </script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/js/RequestJax.js"></script>
</body>
</html>