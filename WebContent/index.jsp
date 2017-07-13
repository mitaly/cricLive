<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
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
	<h1 id="IndexHeading">Welcome to CricLive</h1>
	<%
			JDBCHelper helper = new JDBCHelper();
			helper.createConnection();
			HashMap<Integer, String> map= helper.getListOfMatches();
			ArrayList<Integer> listKeys = new ArrayList(map.keySet());
			System.out.println("list: "+map.toString());
	%>
	<div id="divIndex">
	<form class="formIndex" action="addMatch.jsp">
		<input class="submitBtns" type="submit" value="Add New Match"/>
	</form><br><br>
	
	<form class="formIndex" action="RegisterPlayerServlet">
			<input type="hidden" name="registerFlag">
			<select name="matches">
				<% for(int i=0;i<map.size();i++){
				%>
					<option value="<%= listKeys.get(i) %>"><%= map.get(listKeys.get(i)) %></option>
				<%
				}
				%>
			</select>
			<input class="submitBtns" type="submit" value="Register Players"/>
	</form><br><br>

	<form class="formIndex" action="UpdateMatchServlet">	
		<select name="matches">
			<% for(int i=0;i<map.size();i++){
			%>
				<option value="<%= listKeys.get(i) %>"><%= map.get(listKeys.get(i)) %></option>
			<%
			}
			%>
		</select>
		<input class="submitBtns" type="submit" value="Update Match"/>
	</form><br><br>
	
		
	<form class="formIndex" action="">
		<select name="matches">
			<% for(int i=0;i<map.size();i++){
			%>
				<option value="<%= listKeys.get(i) %>"><%= map.get(listKeys.get(i)) %></option>
			<%
			}
			%>
		</select>
		<input class="submitBtns" type="submit" value="View Match Information"/>
	</form><br><br>
	
	<form class="formIndex" action="updateResult.jsp">
		<select name="matches">
			<% for(int i=0;i<map.size();i++){
			%>
				<option value="<%= listKeys.get(i) %>"><%= map.get(listKeys.get(i)) %></option>
			<%
			}
			helper.closeConnection();
			%>
		</select>
		<input class="submitBtns" type="submit" value="Update Result"/>
	</form><br><br>
	</div>
</body>
</html>