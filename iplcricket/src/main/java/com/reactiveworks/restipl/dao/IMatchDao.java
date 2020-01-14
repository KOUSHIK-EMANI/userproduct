package com.reactiveworks.restipl.dao;

import java.util.List;

import com.reactiveworks.restipl.db.exceptions.DataAccessException;
import com.reactiveworks.restipl.model.Match;

public interface IMatchDao {
	List<Match> getAllMatches() throws DataAccessException;
}