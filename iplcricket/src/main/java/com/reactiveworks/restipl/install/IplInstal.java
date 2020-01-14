package com.reactiveworks.restipl.install;

import java.util.List;

import com.reactiveworks.restipl.db.exceptions.DataAccessException;
import com.reactiveworks.restipl.model.HighestRunRate;
import com.reactiveworks.restipl.model.ScoreDetails;
import com.reactiveworks.restipl.services.impl.CricketServiceImpl;
import com.reactiveworks.restipl.services.interfaces.ICricketService;

public class IplInstal {

	public static void main(String[] args) throws DataAccessException {
		CricketServiceImpl cricker = new CricketServiceImpl();
		/*
		 * List<ScoreDetails> scoreDetails = cricker.getScoreDetails(); for(ScoreDetails
		 * score:scoreDetails) { System.out.println(score); }
		 */
		List<HighestRunRate> highestRunRate = cricker.getHighestRunRate();
		for (HighestRunRate h : highestRunRate) {
			System.out.println(h);
		}
	}
}