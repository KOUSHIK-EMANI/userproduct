package com.reactiveworks.restipl.model;

public class Delivery {
	private int matchId;
	private int innings;
	private String battingTeam;
	private String bowlingTeam;
	private int overs;
	private int ball;
	private String Batsmen;
	private String Bowler;
	private int wideRuns;
	private int byeRuns;
	private int legByeRuns;
	private int noBallRuns;
	private int penalityRuns;
	private int batsmenRuns;
	private int extraRuns;
	private int totalRuns;

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}

	public int getInnings() {
		return innings;
	}

	public void setInnings(int innings) {
		this.innings = innings;
	}

	public String getBattingTeam() {
		return battingTeam;
	}

	public void setBattingTeam(String battingTeam) {
		this.battingTeam = battingTeam;
	}

	public String getBowlingTeam() {
		return bowlingTeam;
	}

	public void setBowlingTeam(String bowlingTeam) {
		this.bowlingTeam = bowlingTeam;
	}

	public int getOvers() {
		return overs;
	}

	public void setOvers(int overs) {
		this.overs = overs;
	}

	public int getBall() {
		return ball;
	}

	public void setBall(int ball) {
		this.ball = ball;
	}

	public String getBatsmen() {
		return Batsmen;
	}

	public void setBatsmen(String batsmen) {
		Batsmen = batsmen;
	}

	public String getBowler() {
		return Bowler;
	}

	public void setBowler(String bowler) {
		Bowler = bowler;
	}

	public int getWideRuns() {
		return wideRuns;
	}

	public void setWideRuns(int wideRuns) {
		this.wideRuns = wideRuns;
	}

	public int getByeRuns() {
		return byeRuns;
	}

	public void setByeRuns(int byeRuns) {
		this.byeRuns = byeRuns;
	}

	public int getLegByeRuns() {
		return legByeRuns;
	}

	public void setLegByeRuns(int legByeRuns) {
		this.legByeRuns = legByeRuns;
	}

	@Override
	public String toString() {
		return "Delivery [matchId=" + matchId + ", innings=" + innings + ", battingTeam=" + battingTeam
				+ ", bowlingTeam=" + bowlingTeam + ", overs=" + overs + ", ball=" + ball + ", Batsmen=" + Batsmen
				+ ", Bowler=" + Bowler + ", wideRuns=" + wideRuns + ", byeRuns=" + byeRuns + ", legByeRuns="
				+ legByeRuns + ", noBallRuns=" + noBallRuns + ", penalityRuns=" + penalityRuns + ", batsmenRuns="
				+ batsmenRuns + ", extraRuns=" + extraRuns + ", totalRuns=" + totalRuns + "]";
	}

	public int getNoBallRuns() {
		return noBallRuns;
	}

	public void setNoBallRuns(int noBallRuns) {
		this.noBallRuns = noBallRuns;
	}

	public int getPenalityRuns() {
		return penalityRuns;
	}

	public void setPenalityRuns(int penalityRuns) {
		this.penalityRuns = penalityRuns;
	}

	public int getBatsmenRuns() {
		return batsmenRuns;
	}

	public void setBatsmenRuns(int batsmenRuns) {
		this.batsmenRuns = batsmenRuns;
	}

	public int getExtraRuns() {
		return extraRuns;
	}

	public void setExtraRuns(int extraRuns) {
		this.extraRuns = extraRuns;
	}

	public int getTotalRuns() {
		return totalRuns;
	}

	public void setTotalRuns(int totalRuns) {
		this.totalRuns = totalRuns;
	}
}
