package com.reactiveworks.restipl.dao;

import java.util.List;

import com.reactiveworks.restipl.db.exceptions.DataAccessException;
import com.reactiveworks.restipl.model.Match;

public interface ITempMatchDao {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.reactiveworks.restipl.db.impl.IMatchDAO#addMatch(com.reactiveworks.
	 * restipl.model.Match)
	 */
	int addMatch(Match match) throws DataAccessException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.reactiveworks.restipl.db.impl.IMatchDAO#getMatchById(int)
	 */
	Match getMatchById(int id) throws DataAccessException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.reactiveworks.restipl.db.impl.IMatchDAO#updateMatchById(com.reactiveworks
	 * .restipl.model.Match)
	 */
	void updateMatchById(Match match) throws DataAccessException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.reactiveworks.restipl.db.impl.IMatchDAO#deleteMatchById(int)
	 */
	void deleteMatchById(int id) throws DataAccessException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.reactiveworks.restipl.db.impl.IMatchDAO#getAllMatches()
	 */
	List<Match> getAllMatches() throws DataAccessException;

}