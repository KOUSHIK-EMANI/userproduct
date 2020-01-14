package com.reactiveworks.restipl.services.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.reactiveworks.restipl.db.exceptions.DataAccessException;
import com.reactiveworks.restipl.factory.DeliveryDaoFactory;
import com.reactiveworks.restipl.factory.MatchDaoFactory;
import com.reactiveworks.restipl.model.Delivery;
import com.reactiveworks.restipl.model.HighestRunRate;
import com.reactiveworks.restipl.model.Match;
import com.reactiveworks.restipl.model.ScoreDetails;
import com.reactiveworks.restipl.services.interfaces.ICricketService;

@Path("cricketservice")
public class CricketServiceImpl implements ICricketService {
	public static final Logger logger = Logger.getLogger(CricketServiceImpl.class);
	private static final String DAO_TYPE = "DB";

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.reactiveworks.restipl.services.impl.ICricketServiceImpl#
	 * getAllDeliveriesByMatchId(int)
	 */
	@Override
	@GET
	@Path("cricket/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public int getAllDeliveriesByMatchId(@PathParam("id") int id) throws DataAccessException {
		List<Delivery> deliveryList = new DeliveryDaoFactory().getInstance(DAO_TYPE).getAllDeliveries();
		int countDeliveries = 0;
		for (Delivery singleDelivery : deliveryList) {
			if (singleDelivery.getMatchId() == id) {
				countDeliveries++;
			}
		}
		return countDeliveries;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.reactiveworks.restipl.services.impl.ICricketServiceImpl#getScoreDetails()
	 */
	@Override
	@GET
	@Path("cricket")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<ScoreDetails> getScoreDetails() throws DataAccessException {
		long start = System.currentTimeMillis();
		List<Delivery> deliveryList = new DeliveryDaoFactory().getInstance(DAO_TYPE).getAllDeliveries();
		List<Match> matchList = new MatchDaoFactory().getInstance(DAO_TYPE).getAllMatches();
		List<ScoreDetails> scorelist = new ArrayList<ScoreDetails>();
		ScoreDetails scoreDetails1 = null;
		ScoreDetails scoreDetails2 = null;
		for (Match singleMatch : matchList) {
			boolean fisInnings = false;
			boolean secInnings = false;
			boolean updateteam1 = true;
			boolean updateteam2 = true;
			int team1totalruns = 0;
			int team1fourcount = 0;
			int team1sixcount = 0;
			int team2totalruns = 0;
			int team2fourcount = 0;
			int team2sixcount = 0;
			List<Delivery> singleMatchDeliveriesList = new ArrayList<Delivery>();
			for (Delivery singleDelivery : deliveryList) {
				if (singleMatch.getMatchId() == singleDelivery.getMatchId()) {
					singleMatchDeliveriesList.add(singleDelivery);
				}
			} // ....end of delivery loop which gives single match deliveries list
				// this loop calculates total number of fours sixes and total runs of both
				// team1 and team2
			for (Delivery single : singleMatchDeliveriesList) {
				if (single.getBattingTeam().equalsIgnoreCase(singleMatch.getTeam1())) {
					fisInnings = true;
					if (single.getBatsmenRuns() == 4) {
						team1fourcount++;
					}
					if (single.getBatsmenRuns() == 6) {
						team1sixcount++;
					}
					int totalsocre = single.getTotalRuns();
					team1totalruns = team1totalruns + totalsocre;
				}
				if (single.getBattingTeam().equalsIgnoreCase(singleMatch.getTeam2())
						&& (single.getInnings() == 1 || single.getInnings() == 2)) {
					secInnings = true;
					if (single.getBatsmenRuns() == 4) {
						team2fourcount++;
					}
					if (single.getBatsmenRuns() == 6) {
						team2sixcount++;
					}
					int totalsocre = single.getTotalRuns();
					team2totalruns = team2totalruns + totalsocre;
				}
			} // ....end of loop which calculates no of fours, sixes and total score
			if (!scorelist.isEmpty()) {
				for (ScoreDetails singleDetail : scorelist) {
					if (fisInnings) {
						if (singleMatch.getSeason() == singleDetail.getYear()
								&& singleMatch.getTeam1().equalsIgnoreCase(singleDetail.getTeamname())) {
							updateteam1 = false;
							singleDetail.setFour((team1fourcount + singleDetail.getFour()));
							singleDetail.setSix((team1sixcount + singleDetail.getSix()));
							singleDetail.setTotalscore((team1totalruns + singleDetail.getTotalscore()));
						}
					}
					if (secInnings) {
						if (singleMatch.getSeason() == singleDetail.getYear()
								&& singleMatch.getTeam2().equalsIgnoreCase(singleDetail.getTeamname())) {
							updateteam2 = false;
							singleDetail.setFour((team2fourcount + singleDetail.getFour()));
							singleDetail.setSix((team2sixcount + singleDetail.getSix()));
							singleDetail.setTotalscore((team2totalruns + singleDetail.getTotalscore()));
						}
					}
				}
			}
			if (updateteam1) {
				scoreDetails1 = new ScoreDetails();
				scoreDetails1.setYear(singleMatch.getSeason());
				scoreDetails1.setTeamname(singleMatch.getTeam1());
				scoreDetails1.setFour(team1fourcount);
				scoreDetails1.setSix(team1sixcount);
				scoreDetails1.setTotalscore(team1totalruns);
				scorelist.add(scoreDetails1);
			}
			if (updateteam2) {
				scoreDetails2 = new ScoreDetails();
				scoreDetails2.setYear(singleMatch.getSeason());
				scoreDetails2.setTeamname(singleMatch.getTeam2());
				scoreDetails2.setFour(team2fourcount);
				scoreDetails2.setSix(team2sixcount);
				scoreDetails2.setTotalscore(team2totalruns);
				scorelist.add(scoreDetails2);
			}
		}
		long end = System.currentTimeMillis();
		long diff = end - start;
		logger.debug("time taken by the getScoreDetails() is :" + diff);
		return scorelist;

	}

	public List<HighestRunRate> getHighestRunRate() throws DataAccessException {
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		long start = System.currentTimeMillis();
		List<Delivery> deliveryList = new DeliveryDaoFactory().getInstance(DAO_TYPE).getAllDeliveries();
		List<Match> matchList = new MatchDaoFactory().getInstance(DAO_TYPE).getAllMatches();
		List<HighestRunRate> netRunRateList = new ArrayList<HighestRunRate>();
		HighestRunRate highestRunRate1 = null;
		HighestRunRate highestRunRate2 = null;
		for (Match singleMatch : matchList) {
			boolean fisInnings = false;
			boolean secInnings = false;
			boolean updateteam1 = true;
			boolean updateteam2 = true;
			boolean calcOversTeam1 = false;
			boolean calcOversTeam2 = false;
			int team1totalruns = 0;
			int team2totalruns = 0;
			double netRunRateTeam1 = 0.0;
			double netRunRateTeam2 = 0.0;
			double totalOversTeam1 = 0.0;
			double totalOversTeam2 = 0.0;
			List<Delivery> singleMatchDeliveriesList = new ArrayList<Delivery>();
			for (Delivery singleDelivery : deliveryList) {
				if (singleMatch.getMatchId() == singleDelivery.getMatchId()) {
					singleMatchDeliveriesList.add(singleDelivery);
				}
			} // ....end of delivery loop which gives single match deliveries list
				// this loop calculates total number of fours sixes and total runs of both
				// team1 and team2

			int ballCountTeam1 = 0;
			int ballCountTeam2 = 0;
			for (Delivery singleDelivery : singleMatchDeliveriesList) {
				if (singleDelivery.getBattingTeam().equalsIgnoreCase(singleMatch.getTeam1())) {
					fisInnings = true;
					calcOversTeam1 = true;
					int totalsocre = singleDelivery.getTotalRuns();
					team1totalruns = team1totalruns + totalsocre;
					if (singleDelivery.getBall() != 0) {
						ballCountTeam1++;
					}
				}
				if (singleDelivery.getBattingTeam().equalsIgnoreCase(singleMatch.getTeam2())
						&& (singleDelivery.getInnings() == 1 || singleDelivery.getInnings() == 2)) {
					secInnings = true;
					calcOversTeam2 = true;
					int totalsocre = singleDelivery.getTotalRuns();
					team2totalruns = team2totalruns + totalsocre;

					if (singleDelivery.getBall() != 0) {
						ballCountTeam2++;
					}
				}
			} // ....end of loop
			if (calcOversTeam1) {
				int overs = (int) ballCountTeam1 / 6;
				int balls = (int) ballCountTeam1 % 6;
				totalOversTeam1 = (double) overs + (double) balls / 10.0;
				netRunRateTeam1 = team1totalruns / totalOversTeam1;
			}
			if (calcOversTeam2) {
				int overs = (int) ballCountTeam2 / 6;
				int balls = (int) ballCountTeam2 % 6;
				totalOversTeam2 = (double) overs + (double) balls / 10.0;
				netRunRateTeam2 = team2totalruns / totalOversTeam2;
			}
			if (!netRunRateList.isEmpty()) {
				for (HighestRunRate singleRunRate : netRunRateList) {
					if (fisInnings) {
						if (singleMatch.getSeason() == singleRunRate.getYear()
								&& singleMatch.getTeam1().equalsIgnoreCase(singleRunRate.getTeamName())) {
							updateteam1 = false;

							int totalBallsOfPrevious = calculateBalls(singleRunRate.getOvers());
							int totalBallsOfPresent = calculateBalls(totalOversTeam1);
							int totalBalls = totalBallsOfPrevious + totalBallsOfPresent;
							int overs = (int) totalBalls / 6;
							int balls = (int) totalBalls % 6;
							double totalovers = (double) overs + (double) balls / 10.0;
							double totalrunrate = singleRunRate.getTotalScore() / totalovers;

							singleRunRate.setOvers(totalovers);
							singleRunRate.setTotalScore((team1totalruns + singleRunRate.getTotalScore()));
							singleRunRate.setNetRunRate(Double.valueOf(decimalFormat.format(totalrunrate)));
						}
					}
					if (secInnings) {
						if (singleMatch.getSeason() == singleRunRate.getYear()
								&& singleMatch.getTeam2().equalsIgnoreCase(singleRunRate.getTeamName())) {
							updateteam2 = false;
							int totalBallsOfPrevious = calculateBalls(singleRunRate.getOvers());
							int totalBallsOfPresent = calculateBalls(totalOversTeam2);
							int totalBalls = totalBallsOfPrevious + totalBallsOfPresent;
							int overs = (int) totalBalls / 6;
							int balls = (int) totalBalls % 6;
							double totalovers = (double) overs + (double) balls / 10.0;
							double totalrunrate = singleRunRate.getTotalScore() / totalovers;
							singleRunRate.setOvers(totalovers);
							singleRunRate.setTotalScore((team2totalruns + singleRunRate.getTotalScore()));
							singleRunRate.setNetRunRate(Double.valueOf(decimalFormat.format(totalrunrate)));
						}
					}
				}
			}
			if (updateteam1) {
				highestRunRate1 = new HighestRunRate();
				highestRunRate1.setYear(singleMatch.getSeason());
				highestRunRate1.setTeamName(singleMatch.getTeam1());
				highestRunRate1.setOvers(totalOversTeam1);
				highestRunRate1.setTotalScore(team1totalruns);
				highestRunRate1.setNetRunRate(Double.valueOf(decimalFormat.format(netRunRateTeam1)));
				netRunRateList.add(highestRunRate1);
			}
			if (updateteam2) {
				highestRunRate2 = new HighestRunRate();
				highestRunRate2.setYear(singleMatch.getSeason());
				highestRunRate2.setTeamName(singleMatch.getTeam2());
				highestRunRate2.setOvers(totalOversTeam2);
				highestRunRate2.setTotalScore(team2totalruns);
				highestRunRate2.setNetRunRate(Double.valueOf(decimalFormat.format(netRunRateTeam2)));
				netRunRateList.add(highestRunRate2);
			}
		}
		long end = System.currentTimeMillis();
		long diff = end - start;
		netRunRateList=sortRunRateTeams(netRunRateList);
		List<HighestRunRate> highestRunRatelist = new ArrayList<>();
		for (HighestRunRate highestRunRate : netRunRateList) {
			
			if() {
				
			}
		}
		
		
		
		logger.debug("time taken by the getScoreDetails() is :" + diff);
		return netRunRateList;
	}

	/**
	 * This method is used to convert overs into balls
	 * 
	 * @param overs
	 * @return
	 */
	private int calculateBalls(double overs) {
		int tempNum = (int) overs * 10;
		int balls = tempNum % 10;
		int over = tempNum / 10;
		int tempballs = over * 6;
		return (tempballs + balls);
	}

	private List<HighestRunRate> sortRunRateTeams(List<HighestRunRate> runRateList) {
		Collections.sort(runRateList, new MyComporator());
		return runRateList;
	}
}
