<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="jp.nw.model.MyCalendar"%>
<%
	MyCalendar mc=(MyCalendar)request.getAttribute("mc");
	String userId = (String)request.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=mc.getGengou() %>年<%=mc.getMonth() %>月カレンダー(<%=userId %>)</title>
  <link rel="stylesheet"  href="http://yui.yahooapis.com/3.18.1/build/cssreset/cssreset-min.css">
  <link href="https://fonts.googleapis.com/css?family=M+PLUS+Rounded+1c" rel="stylesheet">
  <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/style44.css">
</head>
<body>

    <h1><%=mc.getGengou() %>年<%=mc.getMonth() %>月のカレンダー</h1>
    <p>
		<a href="?year=<%=mc.getYear()%>&month=<%=mc.getMonth()-1%>">前月</a>
    	<a href="?year=<%=mc.getYear()%>&month=<%=mc.getMonth()+1%>">翌月</a>
    </p>
<table>
      <tr>
        <th>日</th>
        <th>月</th>
        <th>火</th>
        <th>水</th>
        <th>木</th>
        <th>金</th>
        <th>土</th>
      </tr>
      <%for(String[] row: mc.getData()){ %>
      <tr>
      	<%for(String col:row) {%>
      		<%if (col.startsWith("*")){ %>
      			<td class="day"><%=col.substring(1)%>
      			<form action="/nwproject/OpenCalender?<%=mc.getMonth() %>&<%=col %>&<%=userId %>" method="post">
      			<button type="submit">📝</button>
      			</form>
      			<!-- 試験追加 -->
      			<!--<%=mc.getContentsI()%></td>-->
      			</td>
      		<%}else{ %>
      			<td><%=col %>
      			<form action="/nwproject/OpenCalender?<%=mc.getMonth() %>&<%=col %>&<%=userId %>" method="post">
      			<button type="submit">📝</button>
				</form>
      			</td>
      		<%} %>
      	<%} %>
      </tr>
      <%} %>
</table>

</body>
</html>
