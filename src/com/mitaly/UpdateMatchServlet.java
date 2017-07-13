package com.mitaly;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/UpdateMatchServlet")
public class UpdateMatchServlet extends HttpServlet {
	HttpServletRequest request;
	HttpServletResponse response;
	String bowler;
	JDBCHelper helper;
	int matchId = 0;
	String batTeam, team1, team2;
	HttpSession session;
	int batTeamId, bowlTeamId;
	String bowlTeam;
	ArrayList<String> batTeamList, bowlTeamList;
	HashMap map;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.request = request;
		this.response = response;
		
		helper = new JDBCHelper();
		helper.createConnection();
		session = request.getSession();
		
		request.setAttribute("singleBatsman",0);
		request.setAttribute("bowlerChosen", 0);
	
		if(request.getParameter("matches") != null) {
			matchId = Integer.parseInt(request.getParameter("matches"));
			
			session.setAttribute("matchId",matchId);
		}
		System.out.println("id: "+matchId);
		
		if(request.getParameter("updateBattingTeam") != null) {
			matchId = Integer.parseInt(request.getParameter("id"));
		}
		
		
		map = helper.viewMatchInfo(matchId);
		team1 = (String)map.get("team1");
		
		team2 = (String)map.get("team2");
		session.setAttribute("team1", team1);
		session.setAttribute("team2", team2);
		request.setAttribute("innings",map.get("innings"));
	
		if(request.getParameter("matches") != null) {
			meth();
			meth1();
		}
	
			
		//When batFirst is asked
		if(request.getParameter("updateBattingTeam") != null) {
			System.out.println("update is clicked");
			batTeam = request.getParameter("batTeam");
			
			MatchInfoBean ref = new MatchInfoBean();
			ref.setId(matchId);
			ref.setBatFirst(batTeam);
			ref.setInnings(1);
			helper.updateBatInnings(ref);
			
			meth();
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("updateMatch.jsp");
			dispatcher.forward(request, response);
			
		}//When bat team not asked yet 
		else if((Integer)map.get("innings")==0) {
			System.out.println("innings 0");
			RequestDispatcher dispatcher = request.getRequestDispatcher("askBattingTeam.jsp");
			dispatcher.forward(request, response);
			
		}//when batting players are chosen
		else if(request.getParameter("battingPlayers") != null) {
			System.out.println("players chosen");

			String player1 = request.getParameter("batPlayer1");
			String player2 = request.getParameter("batPlayer2");
			
			BatsmanBean ref = new BatsmanBean();
			//1st player
			ref.setMatchId(matchId);
			ref.setTeamId(batTeamId);
			ref.setName(player1);
			helper.insertBattingInfo(ref);
			//2nd player
			ref.setName(player2);
			helper.insertBattingInfo(ref);
			
			session.setAttribute("player1Bat", player1);
			session.setAttribute("player2Bat", player2);
			System.out.println("player1 and player2: "+player1+" "+player2);
			request.setAttribute("batsmanChosen", 1);
			
			meth1();
			RequestDispatcher dispatcher = request.getRequestDispatcher("updateMatch.jsp");
			dispatcher.forward(request, response);
			
		}else if(request.getParameter("bowlingPlayer") != null) {
			System.out.println("bowling player");
		
			int batTeamId = (Integer)session.getAttribute("bowlTeamId");
			String bowler = request.getParameter("bowlingPlayer");
			
			if(session.getAttribute("playerBowl") != null) {
				String prBowler = (String)session.getAttribute("playerBowl");
				BowlerBean ref1 = new BowlerBean();
				ref1.setMatchId(matchId);
				ref1.setTeamId(bowlTeamId);
				ref1.setName(prBowler);
			 	helper.updateBowlerChange(ref1);
			}
			BowlerBean ref = new BowlerBean();
			ref.setMatchId(matchId);
			ref.setTeamId(batTeamId);
			ref.setName(bowler);
			//check if the bowler is already there in the table
			//if there, then update else, insert
			int i = helper.checkBowler(ref);
			if(i == 0) {
				helper.insertBowlingInfo(ref);
			}else {
				helper.updateBowlingInfo(ref);
			}
			
			
			session.setAttribute("playerBowl", bowler);
			request.setAttribute("bowlerChosen", 1);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("updateMatch.jsp");
			dispatcher.forward(request, response);
		}
		else if(request.getParameter("secondBatsman") != null){
			String player2 = request.getParameter("batPlayer2");
			BatsmanBean ref = new BatsmanBean();
			ref.setMatchId(matchId);
			ref.setTeamId(batTeamId);
			ref.setName(player2);
			helper.insertBattingInfo(ref);
			
			session.setAttribute("player2Bat", player2);
			
			meth1();
			RequestDispatcher dispatcher = request.getRequestDispatcher("updateMatch.jsp");
			dispatcher.forward(request, response);
		}else if(request.getParameter("runsChosen") != null) {
			System.out.println("runs made");
			int runs;
			String runsStr = (String)request.getParameter("runs");
			if(runsStr.trim().equals("")) {
				runs = 0;
			}else {
				runs = Integer.parseInt(runsStr);
			}
			String playerName = null;
			int playerNum = Integer.parseInt(request.getParameter("batsman"));
			if(playerNum == 1) {
				playerName = (String)session.getAttribute("player1Bat");
			}else if(playerNum == 2) {
				playerName = (String)session.getAttribute("player2Bat");
			}
			//updating batsman's runs and balls
			BatsmanBean ref = new BatsmanBean();
			ref.setMatchId(matchId);
			ref.setTeamId(batTeamId);
			ref.setName(playerName);
			ref.setRuns(runs);
			helper.updateBattingRuns(ref);
			String bowlerName = (String)session.getAttribute("playerBowl");
			BowlerBean ref1 = new BowlerBean();
			ref1.setMatchId(matchId);
			ref1.setTeamId(bowlTeamId);
			ref1.setName(bowlerName);
			ref1.setRuns(runs);
			helper.updateBowlingRuns(ref1);
			
			boolean team1Bat = false;
			//updating batting team main score and ball
			if(batTeam.equals(map.get("team1"))) {
				team1Bat = true;
			}
			
			meth1();
			helper.teamScoreUpdate(matchId, team1Bat, runs);
			RequestDispatcher dispatcher = request.getRequestDispatcher("updateMatch.jsp");
			dispatcher.forward(request, response);
			
		}else if(request.getParameter("out") != null) {
			int playerNum = Integer.parseInt(request.getParameter("batsman"));
			System.out.println("who is out? "+playerNum);
			String playerName = null;
			if(playerNum == 1)
				playerName = (String)session.getAttribute("player1Bat");
			else if(playerNum == 2)
				playerName = (String)session.getAttribute("player2Bat");
			
			String bowlerName = (String)session.getAttribute("playerBowl");
			System.out.println("bowler name: "+bowlerName);
			//Batsman table
			BatsmanBean ref = new BatsmanBean();
			ref.setMatchId(matchId);
			ref.setTeamId(batTeamId);
			ref.setName(playerName);
			ref.setOutPlayer(bowlerName);
			helper.updateBattingOut(ref);
			
			//Bowler Info
			BowlerBean ref1 = new BowlerBean();
			ref1.setMatchId(matchId);
			ref1.setTeamId(bowlTeamId);
			ref1.setName(bowlerName);
			helper.updateBowlingOut(ref1);
			
			//Match_Info table
			boolean team1Bat = false;
			if(batTeam.equals(map.get("team1"))) {
				team1Bat = true;
			}
			helper.teamOutUpdate(matchId, team1Bat);
			
			if(playerNum == 1) {
				session.setAttribute("player1Bat", (String)session.getAttribute("player2Bat"));
				session.setAttribute("player2Bat", null);
			}else if(playerNum == 2) {
				session.setAttribute("player2Bat", null);
			}
			request.setAttribute("singleBatsman", 1);
			
			ArrayList<String> singleBatsman = helper.batsmanPlaying(matchId);
			String batsman = "";
			if(singleBatsman.size() == 1) {
				batsman = singleBatsman.get(0);
				System.out.println("Single batsman");
			}
			
			meth1();
			RequestDispatcher dispatcher = request.getRequestDispatcher("updateMatch.jsp");
			dispatcher.forward(request, response);
			
		}else if(request.getParameter("btnNewInn") != null){
			helper.startNewInn(matchId, bowlTeam);
			response.sendRedirect("index.jsp");
		}
		else if((Integer)map.get("innings") !=0) {
			System.out.println("innings != 0");
			batTeam = (String)map.get("batFirst"); 
			
			meth();
			meth1();
			RequestDispatcher dispatcher = request.getRequestDispatcher("updateMatch.jsp");
			dispatcher.forward(request, response);
		}
		helper.closeConnection();
	}
	void meth1() {
		System.out.println("meth1");
		ArrayList<String> playerNames = helper.batsmanPlaying(matchId);
	
		if(playerNames.size() == 2) {
			session.setAttribute("player1Bat", playerNames.get(0));
			session.setAttribute("player2Bat", playerNames.get(1));
			request.setAttribute("batsmanChosen",1);
			System.out.println("ArrayList: "+playerNames.toString());
		}else if(playerNames.size() == 1){
			request.setAttribute("singleBatsman", 1);
			System.out.println("Single batsman");
		}else if(playerNames.size() == 0) {
			session.setAttribute("player1Bat", null);
			session.setAttribute("player2Bat", null);
			request.setAttribute("batsmanChosen",0);
		}
		String bowler = helper.bowlerPlaying(matchId);
		System.out.println("Bowler is: "+bowler);
		if(bowler != null) {
			session.setAttribute("playerBowl", bowler);
			request.setAttribute("bowlerChosen", 1);
		}
		
		
	}
	void meth() {
		System.out.println("meth");
		batTeam = (String)map.get("batFirst");
		if(batTeam.equals(team1)) {
			batTeamId = helper.getTeamId(batTeam);
			bowlTeamId = helper.getTeamId(team2);
			bowlTeam = team2;
		}else {
			batTeamId = helper.getTeamId(team2);
			bowlTeamId = helper.getTeamId(bowlTeam);
			bowlTeam = team1;
		}
	
		batTeamList = helper.getPlayersList(batTeamId);
		bowlTeamList = helper.getPlayersList(bowlTeamId);

		session.setAttribute("batTeamId", batTeamId);
		session.setAttribute("bowlTeamId", bowlTeamId);
		session.setAttribute("batTeam", batTeam);
		session.setAttribute("bowlTeam", bowlTeam);
		session.setAttribute("batTeamList", batTeamList);
		session.setAttribute("bowlTeamList", bowlTeamList);
		
		System.out.println("bowl id: "+bowlTeamId+" list: "+bowlTeamList.toString());
	}


}
