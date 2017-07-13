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
<h2 id="headingAddMatch">Add details of new match</h2>
<div id="divAddMatch">

	<form id="formAddMatch" action="AddMatchServlet" method="get">
		<input type="text" class="inputAddMatch" name="txtTeam1" placeholder="Team 1"/><br>
		<input type="text" class="inputAddMatch" name="txtTeam2" placeholder="Team 2"/><br>
		<input type="date" class="dateAddMatch" name="txtDate"/><br>
		<button class="submitBtns" id="btnAddMatch" type="submit" value="Submit">Add Match</button> 
	</form>
</div>
</body>
</html>