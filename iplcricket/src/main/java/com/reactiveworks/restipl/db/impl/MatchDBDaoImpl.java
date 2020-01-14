package com.reactiveworks.restipl.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reactiveworks.restipl.dao.IMatchDao;
import com.reactiveworks.restipl.db.DBCPDataSource;
import com.reactiveworks.restipl.db.exceptions.DataAccessException;
import com.reactiveworks.restipl.model.Match;

public class MatchDBDaoImpl implements IMatchDao{
	public static final Logger logger = Logger.getLogger("MatchService");
	private static final String SELECT_ALL_MATCHES = "SELECT * FROM MATCHES";
	private DBCPDataSource dbcpdatasource;
	private Connection connection;

	public MatchDBDaoImpl() {
		dbcpdatasource = DBCPDataSource.getInstance();
	}
	@Override
	public List<Match> getAllMatches() throws DataAccessException {
		logger.debug("in getAllMatches()");
		connection = DBCPDataSource.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SELECT_ALL_MATCHES);
			List<Match> matchList = new ArrayList<Match>();
			while (resultSet.next()) {
				Match match = new Match();
				match.setMatchId(resultSet.getInt(1));
				match.setSeason(resultSet.getInt(2));
				match.setCity(resultSet.getString(3));
				match.setDate(resultSet.getDate(4));
				match.setTeam1(resultSet.getString(5));
				match.setTeam2(resultSet.getString(6));
				match.setTossWinner(resultSet.getString(7));
				match.setTossDecision(resultSet.getString(8));
				match.setResult(resultSet.getString(9));
				match.setWinner(resultSet.getString(10));
				matchList.add(match);
			}
			logger.debug("exiting getAllMatches()");
			return matchList;
		} catch (SQLException e) {
			throw new DataAccessException("unable to get all Match details ", e);
		} finally {
			if (resultSet != null && statement != null && connection != null) {
				dbcpdatasource.close(resultSet, statement, connection);
			}
		}
	}
	

}
