<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CricLive</title>
<link rel="stylesheet" href="style.css" type="text/css"/>
	<script type="text/javascript">
		function showBowlersList(){
			var div = document.getElementById("divBowlers");
			 div.style.display = '';
		}
	</script>
</head>
<body>
	<h2 class="teamName">Update details of match</h2>
	<hr>
	
	
	<%! int batsmanChosen = 0;
		int bowlerChosen = 0;
		int singleBatsman = 0;
		int innings = 0;
		String player1, player2, playerBowl;%>
	<% 	
		ArrayList<String> batTeamList = (ArrayList)session.getAttribute("batTeamList");
		ArrayList<String> bowlTeamList = (ArrayList)session.getAttribute("bowlTeamList");
		if(request.getAttribute("batsmanChosen") != null){
			batsmanChosen = (Integer)request.getAttribute("batsmanChosen");
		}
		if(request.getAttribute("bowlerChosen") != null){
			bowlerChosen = (Integer)request.getAttribute("bowlerChosen");
		}
		if(session.getAttribute("player1Bat") != null){
			player1 = (String)session.getAttribute("player1Bat");
		}
		if(session.getAttribute("player2Bat") != null){
			player2 = (String)session.getAttribute("player2Bat");
		}
		if(session.getAttribute("playerBowl") != null){
			playerBowl =  (String)session.getAttribute("playerBowl");
		}
		if(request.getAttribute("singleBatsman") != null){
			singleBatsman =  (Integer)request.getAttribute("singleBatsman");
		}
		if(request.getAttribute("innings") != null){
			innings =  (Integer)request.getAttribute("innings");
		}
		System.out.println("session batTeamList: "+batTeamList.toString());
		System.out.println("session bowlTeamList: "+bowlTeamList.toString());
		System.out.println("batsman choosen: "+batsmanChosen);	
		System.out.println("player1 bat: "+player1);	
		System.out.println("player2 bat: "+player2);
		System.out.println("bowler: "+playerBowl);
		System.out.println("bowlerChosen flag: "+bowlerChosen);
		System.out.println("batsmanChosen: "+batsmanChosen);
		System.out.println("single batsman: "+singleBatsman);
	%>
	<div id="outerDiv">
	<div id="battingDiv">
	<h3 class="teamName">Batting Team: <%=session.getAttribute("batTeam") %></h3>
	
	<div style="display:<% if((batsmanChosen==1) || (singleBatsman == 1)){%><%= "none"%> <%}%>"> 
	<h4 class="txtChoose">Choose Batsman</h4>
		<form action="UpdateMatchServlet" method="post" >
			<select  name="batPlayer1">
				<% for(int i=0;i<batTeamList.size();i++){%>
					<option value="<%= batTeamList.get(i) %>"><%= batTeamList.get(i) %></option>
				<%}%> 
			</select><br><br>
			
			<select name="batPlayer2">
				<% for(int i=0;i<batTeamList.size();i++){%>
					<option value="<%=batTeamList.get(i)%>"><%=batTeamList.get(i)%></option>
				<%} %>
			</select><br><br>
			<input  class="submitBtns" type="submit" name="battingPlayers" value="Submit"> 	
		</form>
 	</div>	
 	
 	<div style="display:<% if(singleBatsman == 1){%><%= "none"%> <%}%>">
	 	<form id="runsOutForm" action="UpdateMatchServlet" method="get" style="display:<% if(batsmanChosen==0){%><%= "none"%> <%}%>">
	 		<br><br>
	 		<input type="radio" name="batsman" value="1" checked><%=player1%><br>
	  		<input type="radio" name="batsman" value="2"><%=player2%> <br><br><br>
	  		<input id="txtRuns" type="text" name="runs" value="0">
	  		<input  class="submitBtns" type="submit" value="Submit" name="runsChosen"><br>
	  		<input  class="submitBtns" type="submit" name="out" value="OUT">
	 	</form>
 	</div>
 	
 	<div  style="display:<% if(singleBatsman == 1){%><%= ""%> <%}else{%><%="none" %><%}%>">
 		<h3 id="chooseScndBats">Choose second batsman</h3>
 		<form id="anotherBatsman" action="UpdateMatchServlet" method="get">
 		<br><br>
 		<input type="radio" name="batsman" value="<%=player1%>" checked><%=player1%><br><br>
 		<select name="batPlayer2">
				<% for(int i=0;i<batTeamList.size();i++){%>
					<option value="<%=batTeamList.get(i)%>"><%=batTeamList.get(i)%></option>
				<%} %>
			</select><br><br>
  		<input  class="submitBtns" type="submit" name="secondBatsman" value="Choose this player">
  		
 	</form>
 	</div>
 	</div>
 	
 	
 	<div id="bowlingDiv">
 	<h3 class="teamName">Bowling Team: <%=session.getAttribute("bowlTeam") %></h3>
	<div id="divBowlers" style="display:<% if(bowlerChosen==1){%><%= "none"%> <%}%>">
	<h4 class="txtChoose">Choose Bowler</h4>
		<form action="UpdateMatchServlet" method="post">
			<select  name="bowlingPlayer">
				<% for(int i=0;i<bowlTeamList.size();i++){%>
					<option value="<%= bowlTeamList.get(i) %>"><%= bowlTeamList.get(i) %></option>
				<%}%> 
			</select><br><br>
			<input  class="submitBtns" type="submit" name="bowlingPlayer" value="Submit"> 	
		</form>
	</div>
	
	
	<button  class="submitBtns" id="btnOtherBowler" onClick="showBowlersList()">Choose some other Bowler</button>
	
	<div id="bowlerInfo" style="display:<% if(bowlerChosen==0){%><%= "none"%> <%}%>" >
		Bowler: <% if(playerBowl !=null)%><%= playerBowl %><br>
	</div>
	
 	</div>
 	</div>
 	
	<form action="UpdateMatchServlet" style="display:<% if(innings != 1){%><%= "none"%> <%}%>" >
 	<Button id="btnNewInn" class="submitBtns" value="Start new innings" name="btnNewInn">Start new innings</Button>
	</form>
</body>
</html>