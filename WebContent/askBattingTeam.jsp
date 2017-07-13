<%@page import="java.util.HashMap"%>
<%@page import="com.mitaly.JDBCHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CricLive</title>
</head>
<body>
	<h2>Add team who will bat first</h2>
	<%
		int id = Integer.parseInt(request.getParameter("matches"));
		String team1 = (String)session.getAttribute("team1");
		String team2 = (String)session.getAttribute("team2");
	%>
	<form action ="UpdateMatchServlet" method="get">
		<input type="hidden" value="<%=id %>" name="id">
		<select name="batTeam">
			<option value="<%= team1 %>"><%= team1 %></option>
			<option value="<%= team2 %>"><%= team2 %></option>
		</select>
		<input type="submit" name="updateBattingTeam" value="Submit"/>
	</form>
</body>
</html>