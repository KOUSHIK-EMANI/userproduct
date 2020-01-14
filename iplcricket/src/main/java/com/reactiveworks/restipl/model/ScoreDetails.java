package com.reactiveworks.restipl.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ScoreDetails {
	private int year;
	private String teamname;
	private int four;
	private int six;
	private int totalscore;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public int getFour() {
		return four;
	}

	public void setFour(int four) {
		this.four = four;
	}

	public int getSix() {
		return six;
	}

	public void setSix(int six) {
		this.six = six;
	}

	public int getTotalscore() {
		return totalscore;
	}

	public void setTotalscore(int totalscore) {
		this.totalscore = totalscore;
	}

	@Override
	public String toString() {
		return "ScoreDetails [year=" + year + ", teamname=" + teamname + ", four=" + four + ", six=" + six
				+ ", totalscore=" + totalscore + "]";
	}

}
