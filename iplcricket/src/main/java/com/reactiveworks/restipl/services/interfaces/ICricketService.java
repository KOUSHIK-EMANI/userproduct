package com.reactiveworks.restipl.services.interfaces;

import java.util.List;

import com.reactiveworks.restipl.db.exceptions.DataAccessException;
import com.reactiveworks.restipl.model.ScoreDetails;

public interface ICricketService {

	int getAllDeliveriesByMatchId(int id) throws DataAccessException;
	List<ScoreDetails> getScoreDetails() throws DataAccessException;

}