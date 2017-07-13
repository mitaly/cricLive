package com.mitaly;

import java.util.Date;

public class MatchInfoBean {
	int id;
	String team1;
	String team2;
	Date dateOfMatch;
	int team1Score;
	int team2Score;
	int team1Wicket;
	int team2Wicket;
	String batFirst;
	int ballsDone;
	int innings;

	
	public MatchInfoBean() {
		id = 0;
		team1 = null;
		team2 = null;
		dateOfMatch = null;
		team1Score = 0;
		team2Score = 0;
		team1Wicket = 0;
		team2Wicket = 0;
		batFirst = null;
		innings = 0;
		ballsDone = 0;
	}


	public MatchInfoBean(int id, String team1, String team2, Date dateOfMatch, int team1Score, int team2Score,
			int team1Wicket, int team2Wicket, String batFirst, int ballsDone, int innings) {
		super();
		this.id = id;
		this.team1 = team1;
		this.team2 = team2;
		this.dateOfMatch = dateOfMatch;
		this.team1Score = team1Score;
		this.team2Score = team2Score;
		this.team1Wicket = team1Wicket;
		this.team2Wicket = team2Wicket;
		this.batFirst = batFirst;
		this.ballsDone = ballsDone;
		this.innings = innings;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTeam1() {
		return team1;
	}


	public void setTeam1(String team1) {
		this.team1 = team1;
	}


	public String getTeam2() {
		return team2;
	}


	public void setTeam2(String team2) {
		this.team2 = team2;
	}


	public Date getDateOfMatch() {
		return dateOfMatch;
	}


	public void setDateOfMatch(Date dateOfMatch) {
		this.dateOfMatch = dateOfMatch;
	}


	public int getTeam1Score() {
		return team1Score;
	}


	public void setTeam1Score(int team1Score) {
		this.team1Score = team1Score;
	}


	public int getTeam2Score() {
		return team2Score;
	}


	public void setTeam2Score(int team2Score) {
		this.team2Score = team2Score;
	}


	public int getTeam1Wicket() {
		return team1Wicket;
	}


	public void setTeam1Wicket(int team1Wicket) {
		this.team1Wicket = team1Wicket;
	}


	public int getTeam2Wicket() {
		return team2Wicket;
	}


	public void setTeam2Wicket(int team2Wicket) {
		this.team2Wicket = team2Wicket;
	}


	public String getBatFirst() {
		return batFirst;
	}


	public void setBatFirst(String batFirst) {
		this.batFirst = batFirst;
	}


	public int getBallsDone() {
		return ballsDone;
	}


	public void setBallsDone(int ballsDone) {
		this.ballsDone = ballsDone;
	}


	public int getInnings() {
		return innings;
	}


	public void setInnings(int innings) {
		this.innings = innings;
	}


	@Override
	public String toString() {
		return "MatchInfoBean [id=" + id + ", team1=" + team1 + ", team2=" + team2 + ", dateOfMatch=" + dateOfMatch
				+ ", team1Score=" + team1Score + ", team2Score=" + team2Score + ", team1Wicket=" + team1Wicket
				+ ", team2Wicket=" + team2Wicket + ", batFirst=" + batFirst + ", ballsDone=" + ballsDone + ", innings="
				+ innings + "]";
	}
	
	
	
}
