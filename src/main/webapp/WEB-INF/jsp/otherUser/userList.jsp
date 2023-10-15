<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jp.nw.model.User,java.util.List" %>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<%
User loginUser = (User) session.getAttribute("loginUser");
List<User> userList = (List<User>) request.getAttribute("userList");
String errorMsg = (String) request.getAttribute("errorMsg");
String userInfo = loginUser.getName();
userInfo = userInfo + ":" +loginUser.getPass();
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
<head>
<meta charset="UTF-8">
<link rel="stylesheet"  href="${pageContext.request.contextPath}/css/style22.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
jQuery(window).on('load', function() {
	jQuery('#loader-bg').hide();
});
</script>
<title>ユーザー一覧(管理者モード)</title>
<%= loginUser.getName() %>さん、ログイン中
</head>
<body>
	<div style="width:200px"></div>
	<h1 style="margin-left:450px">ユーザーID：パスワード一覧</h1>
	<div style="width:200px"></div>
	<% if(errorMsg != null) { %>
	<p><%= errorMsg %></p>
	<% } %>
	<form method="post" action="/nwproject/UserView" name="form1">
	<table border="7" style="margin-left:450px">
	  <tr class="tr-back">
	      <th>選択</th>
	      <th>No</th>
	      <th>ユーザーID</th>
	      <th>パスワード</th>
	      <th>権限レベル（1:管理者/2:通常）</th>
	  </tr>
		<% for(User userinfo:userList) { %>
		<tr>
			<th><input type="radio" name="radiobutton" value=<%=userinfo.getName() %>></th>
		  <th><%=userinfo.getNum() %></th>
		  <th><%=userinfo.getName() %></th>
		  <th><%=userinfo.getPass() %></th>
		  <th><%=userinfo.getPermission() %></th>
	  </tr>
	  <%} %>
	  </table>
	<input class="search-btn3" style="margin-left:570px; margin-top:30px" type="submit" name="change" value="変更画面へ"  onclick="return checkForm();">
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/UserListCheck.js"></script>
</body>
<button class="search-btn2" style="margin-left:570px; margin-top:30px" name="userInfo" onclick="window.close();">ウィンドウを閉じる</button>
</html>