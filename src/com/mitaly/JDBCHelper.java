package com.mitaly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class JDBCHelper {
	Connection conn;
	public JDBCHelper() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded");
		} catch (ClassNotFoundException e) {
			System.out.println("Error: "+e);
		}
	}
	
	public void createConnection() {
		String url = "jdbc:mysql://localhost/CricketDB";
		try {
			conn = DriverManager.getConnection(url,"root","");
			System.out.println("Connection established");
		}catch(Exception e) {
			System.out.println("Error: "+e);
		}
	}
	
	public int addMatch(MatchInfoBean ref) {
		int status=0;
		try {
			String sql = "Insert into Match_Info values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, ref.getTeam1());
			pStmt.setString(2, ref.getTeam2());
			if(ref.getDateOfMatch() != null)
				pStmt.setDate(3, new java.sql.Date(ref.getDateOfMatch().getTime()));
			else
				pStmt.setDate(3, null);
			pStmt.setInt(4, ref.getTeam1Score());
			pStmt.setInt(5, ref.getTeam2Score());
			pStmt.setInt(6, ref.getTeam1Wicket());
			pStmt.setInt(7, ref.getTeam2Wicket());
			pStmt.setString(8, ref.getBatFirst());
			pStmt.setInt(9, ref.getBallsDone());
			pStmt.setInt(10, ref.getInnings());
			status = pStmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("Error: "+e);
		}
		 
		return status;
	}
	
	public HashMap<Integer,String> getListOfMatches(){
		HashMap<Integer, String> map = new HashMap();
		try {
			String sql = "Select matchId, team1, team2, dateOfMatch from Match_Info";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				map.put(rs.getInt(1), rs.getString(2)+" vs "+rs.getString(3)+"("+rs.getDate(4)+")");
			}
		}catch(Exception e) {
			System.out.println("Error: "+e);
		}
		return map;
		
	}
	
	public HashMap<String, Object> viewMatchInfo(int id) {
		HashMap<String, Object> map = new HashMap<>();
		try{
			String sql = "Select * from Match_Info where matchId = "+id;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				map.put("team1", rs.getString(2));
				map.put("team2", rs.getString(3));
				map.put("dateOfMatch", rs.getDate(4));
				map.put("team1Score", rs.getInt(5));
				map.put("team2Score", rs.getInt(6));
				map.put("team1Wicket", rs.getInt(7));
				map.put("team2Wicket", rs.getInt(8));
				map.put("batFirst", rs.getString(9));
				map.put("ballsDone", rs.getInt(10));
				map.put("innings", rs.getInt(11));
			}
		
		}catch(Exception e) {
			System.out.println("Error: "+e);
		}
		return map;
	}
	
	public int updateMatchInfo(MatchInfoBean ref) {
		int i=0;
		try {
			String sql = "Update Match_Info set team1=?, team2=?, dateOfMatch=?, team1Score=?, team2Score=?, team1Wicket=?, team2Wicket=?  where matchId="+ref.getId();
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, ref.getTeam1());
			pStmt.setString(2, ref.getTeam2());
			if(ref.getDateOfMatch() != null)
				pStmt.setDate(3, new java.sql.Date(ref.getDateOfMatch().getTime()));
			else
				pStmt.setDate(3, null);
			pStmt.setInt(4, ref.getTeam1Score());
			pStmt.setInt(5, ref.getTeam2Score());
			pStmt.setInt(6, ref.getTeam1Wicket());
			pStmt.setInt(7, ref.getTeam2Wicket());
			
			i = pStmt.executeUpdate();
		}catch (Exception e) {
			System.out.println("Error: "+e);
		}
		return i;
	}
	
	public ArrayList<String> getTeams(int id) {
		ArrayList<String> str = new ArrayList<>();
		try {
			String sql = "Select team1, team2 from Match_Info where matchId="+id;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				str.add(rs.getString(1));
				str.add(rs.getString(2));
			}
		}catch(Exception e) {
			System.out.println("Error: "+e);
		}
		return str;
	}
	
	public int getTeamId(String team){
		int id = 0;
		try {
			String sql = "Select id from Teams where teamName = '"+team+"'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
				id = rs.getInt(1);
		}catch(Exception e) {
			System.out.println("Error: "+e);
		}
		return id;
	}
	
	public void registerPlayer(int id, String[] namesArr) {
		try {
			String sql = "Insert into TeamMembers values(?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			for(String name: namesArr) {
				if(!name.trim().equals("")) {
					pStmt.setString(2, name);
					pStmt.executeUpdate();
				}
			}
			
		}catch(Exception e) {
			System.out.println("Error: "+e);
		}
	}
	
	public void updateBatInnings(MatchInfoBean ref) {
		try {
			String sql = "Update Match_Info set batFirst=?, innings=? where matchId= ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1,ref.getBatFirst());
			pStmt.setInt(2, ref.getInnings());
			pStmt.setInt(3, ref.getId());
			pStmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("Error: "+e);
		}
	}
	
	public ArrayList<String> getPlayersList(int id){
		ArrayList<String> playersList = new ArrayList<>();
		try {
			String sql = "Select playerName from TeamMembers where id = "+id;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				playersList.add(rs.getString(1));
			}
			
		}catch(Exception e) {
			System.out.println("Error: "+e);
		}
		return playersList;
	}
	
	public void insertBattingInfo(BatsmanBean ref) {
		try {
			String sql = "Insert into BattingInfo values(?,?,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,ref.getMatchId());
			pStmt.setInt(2,ref.getTeamId());
			pStmt.setString(3,ref.getName());
			pStmt.setInt(4,ref.getRuns());
			pStmt.setInt(5,ref.getBalls());
			pStmt.setInt(6,ref.getIsOut());
			pStmt.setString(7,ref.getOutPlayer());
			pStmt.setInt(8,1);
			pStmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("There's an error "+e.toString());
		}
	}
	
	int checkBowler(BowlerBean ref) {
		int i=0;
		try {
			String sql = "Select * from BowlingInfo where matchId=? and teamId=? and name=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, ref.getMatchId());
			pStmt.setInt(2, ref.getTeamId());
			pStmt.setString(3, ref.getName());
			ResultSet rs = pStmt.executeQuery();
			if(rs.next())
				i = 1;
		}catch(Exception e) {
			System.out.println("There's an error "+e.toString());
		}
		return i;
	}
	public void updateBowlingInfo(BowlerBean ref) {
		try {
			String sql = "Update BowlingInfo set playFlag = 1 where matchId=? and teamId=? and name=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, ref.getMatchId());
			pStmt.setInt(2, ref.getTeamId());
			pStmt.setString(3, ref.getName());
		
			pStmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("There's an error "+e.toString());
		}
	}

	public void insertBowlingInfo(BowlerBean ref) {
		try {
			String sql = "Insert into BowlingInfo values(?,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,ref.getMatchId());
			pStmt.setInt(2,ref.getTeamId());
			pStmt.setString(3,ref.getName());
			pStmt.setInt(4,ref.getBalls());
			pStmt.setInt(5,ref.getRuns());
			pStmt.setInt(6,ref.getWickets());
			pStmt.setInt(7,1);
		
			pStmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("There's an error "+e.toString());
		}
	}

	public void updateBattingRuns(BatsmanBean ref) {
		try {
			String sql = "Update BattingInfo set runs = runs+?, balls=balls+1 where matchId=? AND "
					+ "teamId=? AND name=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, ref.getRuns());
			pStmt.setInt(2, ref.getMatchId());
			pStmt.setInt(3, ref.getTeamId());
			pStmt.setString(4, ref.getName());
			pStmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("There's an error "+e.toString());
		}
	}
	void updateBowlerChange(BowlerBean ref) {
		try {
			String sql = "Update BowlingInfo set playFlag = 0 where matchId=? AND "
					+ "teamId=? AND name=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, ref.getMatchId());
			pStmt.setInt(2, ref.getTeamId());
			pStmt.setString(3, ref.getName());
			pStmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("There's an error "+e.toString());
		}
	}
	public void updateBowlingRuns(BowlerBean ref) {
		try {
			String sql = "Update BowlingInfo set balls=balls+1, runs = runs+? where matchId=? AND "
					+ "teamId=? AND name=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, ref.getRuns());
			pStmt.setInt(2, ref.getMatchId());
			pStmt.setInt(3, ref.getTeamId());
			pStmt.setString(4, ref.getName());
			pStmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("There's an error "+e.toString());
		}
	}
	public void teamScoreUpdate(int matchId, boolean team1Bat, int runs) {
		try {
			String sql;
			if(team1Bat)
				sql = "Update Match_Info set team1Score=team1Score+?, ballsDone=ballsDone+1 where matchId=? ";
			else
				sql = "Update Match_Info set team2Score=team1Score+?, ballsDone=ballsDone+1 where matchId=? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, runs);
			pStmt.setInt(2, matchId);
			pStmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("There's an error "+e.toString());
		}
	}
	public void updateBattingOut(BatsmanBean ref) {
		try {
			String sql = "Update BattingInfo set balls=balls+1, isOut=1, playFlag=0, outPlayer=? where matchId=? AND"
					+ " teamId=? AND name=? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, ref.getOutPlayer());
			pStmt.setInt(2, ref.getMatchId());
			pStmt.setInt(3, ref.getTeamId());
			pStmt.setString(4, ref.getName());
			pStmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("There's an error "+e.toString());
		}
	}
	public void updateBowlingOut(BowlerBean ref) {
		try {
			String sql = "Update BowlingInfo set balls=balls+1, wickets=wickets+1 where matchId=? AND"
					+ " teamId=? AND name=? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, ref.getMatchId());
			pStmt.setInt(2, ref.getTeamId());
			pStmt.setString(3, ref.getName());
			pStmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("There's an error "+e.toString());
		}
	}
	public void teamOutUpdate(int matchId, boolean team1Bat) {
		try {
			String sql;
			if(team1Bat)
				sql = "Update Match_Info set team1Wicket=team1Wicket+1, ballsDone=ballsDone+1 where matchId=? ";
			else
				sql = "Update Match_Info set team2Wicket=team2Wicket+1, ballsDone=ballsDone+1 where matchId=? ";
			
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, matchId);
			pStmt.executeUpdate();
		}catch(Exception e) {
			System.out.println("There's an error "+e.toString());
		}
	}
	public ArrayList<String> batsmanPlaying(int matchId){
		ArrayList<String> playerNames = new ArrayList<>();
		try {
			String sql = "Select * from BattingInfo where matchId=? and playFlag=1";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,matchId);
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				playerNames.add(rs.getString(3));
			}
		}catch(Exception e) {
			System.out.println("There's an error "+e.toString());
		}
		return playerNames;
	}
	public String bowlerPlaying(int matchId){
		String bowler = null;
		try {
			String sql = "Select * from BowlingInfo where matchId=? and playFlag=1";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1,matchId);
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) 
				bowler = rs.getString(3);
		}catch(Exception e) {
			System.out.println("There's an error "+e.toString());
		}
		return bowler;
	}
	
	public void startNewInn(int matchId, String bowlTeam) {
		try {
			String sql = "Update Match_Info set batFirst=? , innings = 2 where matchId=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, bowlTeam);
			pStmt.setInt(2, matchId);
			pStmt.executeUpdate();
			
			String sql1 = "Update BattingInfo set playFlag=0 where playFlag=1";
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql1);
			
			String sql2 = "Update BowlingInfo set playFlag=0 where playFlag=1";
			Statement stmt1 = conn.createStatement();
			stmt1.executeUpdate(sql2);
		}catch(Exception e) {
			System.out.println("There's an error "+e.toString());
		}

	}
	public void closeConnection() {
		try {
			//close the connection
			conn.close();
			System.out.println("Connection closed");
		}catch (Exception e) {
			System.out.println("There's an error "+e.toString());
		}
	}

	
}
