package com.mitaly;

public class BowlerBean {
	int matchId;
	int teamId;
	String name;
	int balls;
	int runs;
	int wickets;
	int playFlag;
	
	public BowlerBean() {
		matchId = 0;
		teamId = 0;
		name = null;
		balls = 0;
		runs = 0;
		wickets = 0;
		playFlag = 0;
	}

	public BowlerBean(int matchId, int teamId, String name, int balls, int runs, int wickets, int playFlag) {
		this.matchId = matchId;
		this.teamId = teamId;
		this.name = name;
		this.balls = balls;
		this.runs = runs;
		this.wickets = wickets;
		this.playFlag = playFlag;
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalls() {
		return balls;
	}

	public void setBalls(int balls) {
		this.balls = balls;
	}

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public int getWickets() {
		return wickets;
	}

	public void setWickets(int wickets) {
		this.wickets = wickets;
	}

	public int getPlayFlag() {
		return playFlag;
	}

	public void setPlayFlag(int playFlag) {
		this.playFlag = playFlag;
	}

	@Override
	public String toString() {
		return "BowlerBean [matchId=" + matchId + ", teamId=" + teamId + ", name=" + name + ", balls=" + balls
				+ ", runs=" + runs + ", wickets=" + wickets + ", playFlag=" + playFlag + "]";
	}
	
	
}
