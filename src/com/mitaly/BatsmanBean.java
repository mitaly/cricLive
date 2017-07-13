package com.mitaly;

public class BatsmanBean {
	int matchId;
	int teamId;
	String name;
	int runs;
	int balls;
	int isOut;
	String outPlayer;
	int playFlag;
	
	public BatsmanBean() {
		matchId = 0;
		teamId = 0;
		name = null;
		runs = 0;
		balls = 0;
		isOut = 0;
		outPlayer = null;
		playFlag = 0;
	}

	public BatsmanBean(int matchId, int teamId, String name, int runs, int balls, int isOut, String outPlayer,
			int playFlag) {
		super();
		this.matchId = matchId;
		this.teamId = teamId;
		this.name = name;
		this.runs = runs;
		this.balls = balls;
		this.isOut = isOut;
		this.outPlayer = outPlayer;
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

	public int getRuns() {
		return runs;
	}

	public void setRuns(int runs) {
		this.runs = runs;
	}

	public int getBalls() {
		return balls;
	}

	public void setBalls(int balls) {
		this.balls = balls;
	}

	public int getIsOut() {
		return isOut;
	}

	public void setIsOut(int isOut) {
		this.isOut = isOut;
	}

	public String getOutPlayer() {
		return outPlayer;
	}

	public void setOutPlayer(String outPlayer) {
		this.outPlayer = outPlayer;
	}

	public int getPlayFlag() {
		return playFlag;
	}

	public void setPlayFlag(int playFlag) {
		this.playFlag = playFlag;
	}

	@Override
	public String toString() {
		return "BatsmanBean [matchId=" + matchId + ", teamId=" + teamId + ", name=" + name + ", runs=" + runs
				+ ", balls=" + balls + ", isOut=" + isOut + ", outPlayer=" + outPlayer + ", playFlag=" + playFlag + "]";
	}
	
	
}
