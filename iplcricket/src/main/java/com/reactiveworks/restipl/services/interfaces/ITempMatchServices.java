package com.reactiveworks.restipl.services.interfaces;

import java.util.List;

import com.reactiveworks.restipl.db.exceptions.DataAccessException;
import com.reactiveworks.restipl.model.Match;

public interface ITempMatchServices {

	List<Match> getAllMatch() throws DataAccessException;

	Match getMatchById(int id) throws DataAccessException;

	int addMatch(Match match) throws DataAccessException;

	void updateMatchById(Match match) throws DataAccessException;

	void deleteMatchById(int id) throws DataAccessException;

}