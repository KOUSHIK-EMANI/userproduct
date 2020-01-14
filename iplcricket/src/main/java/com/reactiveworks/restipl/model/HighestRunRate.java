package com.reactiveworks.restipl.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HighestRunRate {
	private int year;
	private String teamName;
	private double overs;
	private int totalScore;
	private double netRunRate;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public double getOvers() {
		return overs;
	}

	public void setOvers(double overs) {
		this.overs = overs;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public double getNetRunRate() {
		return netRunRate;
	}

	public void setNetRunRate(double netRunRate) {
		this.netRunRate = netRunRate;
	}

	@Override
	public String toString() {
		return "HighestRunRate [year=" + year + ", teamName=" + teamName + ", overs=" + overs + ", totalScore="
				+ totalScore + ", netRunRate=" + netRunRate + "]";
	}
}
