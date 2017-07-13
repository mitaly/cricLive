<%@page import="java.util.HashMap"%>
<%@page import="com.mitaly.JDBCHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CricLive</title>
<link rel="stylesheet" href="style.css" type="text/css"/>
</head>
<body>
	<h2 class="txtMatch">Update Result</h2>
	<hr>
	<%
		int id = Integer.parseInt(request.getParameter("matches"));
		JDBCHelper helper = new JDBCHelper();
		helper.createConnection();
		HashMap map = helper.viewMatchInfo(id);
	%>
	<h3 class="txtMatchName"><%=map.get("team1") %> Vs <%=map.get("team2") %></h3>
	<div id="divUpdate">
	
	<form action="UpdateResultServlet" method="post">
		<input type="hidden" value="<%=id %>" name="txtMatchId">
		Team 1:<input class="txtUpdate" type="text" value="<%=map.get("team1") %>" name="txtTeam1"><br>
		Team 2:<input class="txtUpdate" type="text" value="<%=map.get("team2") %>" name="txtTeam2"><br>
		Date  :<input class="txtUpdate" type="date" value="<%=map.get("dateOfMatch") %>" name="txtDate"><br>
		Score (Team1):<input class="txtUpdate" type="text" value="<%=map.get("team1Score") %>" name="txtTeam1Score"><br>
		Score (Team2):<input class="txtUpdate" type="text" value="<%=map.get("team2Score") %>" name="txtTeam2Score"><br>
		Wickets (Team1):<input class="txtUpdate" type="text" value="<%=map.get("team1Wicket") %>" name="txtTeam1Wicket"><br>
		Wickets (Team2):<input  class="txtUpdate" type="text" value="<%=map.get("team2Wicket") %>" name="txtTeam2Wicket"><br>
		<input class="submitBtns" id="update" type="submit" value="Update"/>
	</form>
	</div>
	<% helper.closeConnection(); %>
</body>
</html>