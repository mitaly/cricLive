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
<script src="dynamicAddPlayers.js" language="Javascript" type="text/javascript"></script>
</head>
<body>
	
	<h2 class="teamName"><%= request.getAttribute("team1") %></h2>
	<form action="RegisterPlayerServlet" method="POST" >
	     <div id="playerRegDiv1">
	         <input class="textPlayers" placeholder="Player 1" type="text" name="myInputs[]">
	     </div>
	     <input type="hidden" name="team1Name" value='<%= request.getAttribute("team1") %>'>
	     <input type="hidden" name="team2Name" value='<%= request.getAttribute("team2") %>'>
	     <input type="hidden" name="team1Id" value='<%= request.getAttribute("team1Id") %>'>
	     <input type="hidden" name="team2Id" value='<%= request.getAttribute("team2Id") %>'>
	     <input class="submitBtns" type="button" value="Add another Player" onClick="addInput1('playerRegDiv1');">
		<input class="submitBtns" type="submit" value="Register" name="team1Form">
	</form>
	
	<h2 class="teamName"><%= request.getAttribute("team2") %></h2>
	<form action="RegisterPlayerServlet" method="POST" >
	     <div id="playerRegDiv2">
	          <input class="textPlayers" placeholder="Player 1" type="text" name="myInputs[]">
	     </div>
	     <input type="hidden" name="team1Name" value='<%= request.getAttribute("team1") %>'>
	     <input type="hidden" name="team2Name" value='<%= request.getAttribute("team2") %>'>
	      <input type="hidden" name="team1Id" value='<%= request.getAttribute("team1Id") %>'>
	     <input type="hidden" name="team2Id" value='<%= request.getAttribute("team2Id") %>'>
	   	 <input class="submitBtns" type="button" value="Add another Player" onClick="addInput2('playerRegDiv2');">
		<input class="submitBtns" type="submit" value="Register" name="team2Form">
	</form>
	
</body>
</html>