<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  import="jp.nw.model.MyCalendar, java.util.Date, java.util.Calendar, java.util.List, java.util.Map, java.util.HashMap,java.util.ArrayList"%>
<%
	int month =(int)request.getAttribute("month");
    int day = (int)request.getAttribute("day");
    String userId = (String)request.getAttribute("user");
	Date date = new Date();
	Calendar calendar = Calendar.getInstance();
	calendar.setTime(date);
	String[][] rest = (String[][])request.getAttribute("SCH");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta http-equiv="Content-Type" Content="text/html;charset=Shift_JIS">
<meta http-equiv="Content-Style-Type" content="text/css">
<meta http-equiv="Content-Script-Type" content="text/javascript">

<title><%=month %>月カレンダー</title>
<style>
table.sche{border:1px solid #a9a9a9;padding:0px;margin:0px;border-collapse:collapse;}
td{vertical-align:top;margin:0px;padding:2px;font-size:0.75em;height:20px;}
td.top{border-bottom:1px solid #a9a9a9;text-align:center;}
td.time{background-color:#f0f8ff;text-align:right;border-right:1px double #a9a9a9;padding-right:5px;}
td.timeb{background-color:#f0f8ff;border-bottom:1px solid #a9a9a9;border-right:1px double #a9a9a9;}
td.contents{background-color:#ffffff;border-bottom:1px dotted #a9a9a9;}
td.contentsb{background-color:#ffffff;border-bottom:1px solid #a9a9a9;}
td.ex{background-color:#ffebcd;border:1px solid #8b0000;}
img{border:0px;}
p{font-size:0.75em;}

#contents{margin:0;padding:0;width:710px;}
#left{margin:0;padding:0;float:left;width:400px;}
#right{margin:0;padding:0;float:right;width:300px;background-color:#ffffff;}
#contents:after{content:".";display:block;height:0;clear:both;visibility:hidden;}
</style>
</head>
<body>
<form action="/nwproject/WriteShedule?<%=userId %>" method="post">

<p>スケジュール登録</p>
<div id="contents">
<div id="left">
<table class="sche">
<tr><td class="top" style="width:80px">時刻</td><td class="top" style="width:300px">予定</td></tr>

<%for(int i=0; i<rest.length;i++){ %>
<%if(rest.length > i){%>
<%if(rest[i][0].equals("0000")){ %>
<tr><td class="time">00:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">00:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>
<% }else {%>
<tr><td class="time">00:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("0100")){ %>
<tr><td class="time">01:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">01:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>
<% }else {%>
<tr><td class="time">01:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("0200")){ %>
<tr><td class="time">02:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">02:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>
<% }else {%>
<tr><td class="time">02:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("0300")){ %>
<tr><td class="time">03:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">03:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%} %>
<% }else {%>
<tr><td class="time">03:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("0400")){ %>
<tr><td class="time">04:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">04:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%} %>
<% }else {%>
<tr><td class="time">04:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("0500")){ %>
<tr><td class="time">05:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">05:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%} %>
<% }else {%>
<tr><td class="time">05:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("0600")){ %>
<tr><td class="time">06:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">06:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%} %>
<% }else {%>
<tr><td class="time">06:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("0700")){ %>
<tr><td class="time">07:00</td><td class="contents"><%=rest[i][1]%></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">07:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%} %>
<% }else {%>
<tr><td class="time">07:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("0800")){ %>
<tr><td class="time">08:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">08:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%} %>
<% }else {%>
<tr><td class="time">08:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("0900")){ %>
<tr><td class="time">09:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">09:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%} %>
<% }else {%>
<tr><td class="time">09:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("1000")){ %>
<tr><td class="time">10:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">10:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%} %>
<% }else {%>
<tr><td class="time">10:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("1100")){ %>
<tr><td class="time">11:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb">key</td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">11:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%} %>
<% }else {%>
<tr><td class="time">11:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("1200")){ %>
<tr><td class="time">12:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">12:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%} %>
<% }else {%>
<tr><td class="time">12:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("1300")){ %>
<tr><td class="time">13:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">13:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%} %>
<% }else {%>
<tr><td class="time">13:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("1400")){ %>
<tr><td class="time">14:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">14:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%} %>
<% }else {%>
<tr><td class="time">14:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("1500")){ %>
<tr><td class="time">15:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">15:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%} %>
<% }else {%>
<tr><td class="time">15:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("1600")){ %>
<tr><td class="time">16:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">16:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%} %>
<% }else {%>
<tr><td class="time">16:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("1700")){ %>
<tr><td class="time">17:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">17:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%} %>
<% }else {%>
<tr><td class="time">17:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("1800")){ %>
<tr><td class="time">18:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">18:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%} %>
<% }else {%>
<tr><td class="time">18:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("1900")){ %>
<tr><td class="time">19:00</td><td class="contents"><%=rest[i][1]%></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">19:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%} %>
<% }else {%>
<tr><td class="time">19:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("2000")){ %>
<tr><td class="time">20:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">20:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%} %>
<% }else {%>
<tr><td class="time">20:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("2100")){ %>
<tr><td class="time">21:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">21:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%} %>
<% }else {%>
<tr><td class="time">21:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("2200")){ %>
<tr><td class="time">22:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">22:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%} %>
<% }else {%>
<tr><td class="time">22:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>

<%if(rest.length > i){%>
<%if(rest[i][0].equals("2300")){ %>
<tr><td class="time">23:00</td><td class="contents"><%=rest[i][1] %></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%=i++ %>
<%}else{ %>
<tr><td class="time">23:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<%} %>
<% }else {%>
<tr><td class="time">23:00</td><td class="contents"></td></tr>
<tr><td class="timeb"></td><td class="contentsb"></td></tr>
<% } %>
<%} %>
</table>

</div>

<div id="right">

<table>
<tr>
<td nowrap>日付</td><td>
<select name="progyear">
<%for(int i=0; i<10; i++){ %>
<option value=<%=calendar.get(Calendar.YEAR)  + i%>>
<%=calendar.get(Calendar.YEAR) + i%>
<%} %>
</select>
<select name="progmonth">
<%for(int i=1; i<13; i++){ %>
<% if(month == i){ %>
<option value=<%=i %> selected><%=i %>
<%}else{ %>
<option value=<%=i %>><%=i %>
<%} %>
<%} %>
</select>
<select name="progday">
<%for(int i=1; i<32; i++){ %>
<% if(day == i) { %>
<option value=<%=i  %> selected><%=i  %>
<%}else{ %>
<option value=<%=i  %>><%=i  %>
<%} %>
<%} %>
</select>
</td></tr>
<tr><td nowrap>時刻</td><td>
<select name="fromhour">
<option value="" selected>--
<%for(int i=0; i<24; i++){ %>
<option value=<%=i  %>>
<%=i  %>
<%} %>
</select>
<select name="fromminut">
<option value="" selected>--
<option value="00">00
<option value="15">15
<option value="30">30
<option value="45">45
</select>
～
<select name="tohour">
<option value="" selected>--
<%for(int i=0; i<24; i++){ %>
<option value=<%=i  %>><%=i  %>
<%} %>
</select>
<select name="tominit">
<option value="" selected>--
<option value="00">00
<option value="15">15
<option value="30">30
<option value="45">45
</select>
</td></tr>

<tr>
<td nowrap>予定</td>
<td><input type="text" name="plan" value="" size="30" maxlength="100">
</td>
</tr>

<tr>
<td valign="top" nowrap>メモ</td>
<td><textarea name="memo" cols="30" rows="10" wrap="virtual"></textarea></td>
</tr>
</table>
<p>
	<button type="submit" name="Register">登録する</button>
<p>
</div>
</div>
</form>
</body>
</html>
