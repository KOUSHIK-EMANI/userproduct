package com.reactiveworks.restipl.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reactiveworks.restipl.dao.ITempMatchDao;
import com.reactiveworks.restipl.db.DBCPDataSource;
import com.reactiveworks.restipl.db.exceptions.DataAccessException;
import com.reactiveworks.restipl.model.Match;

public class TempMatchDBDaoImpl implements ITempMatchDao {
	public static final Logger logger = Logger.getLogger("MatchService");
	private static final String INSERT = "INSERT INTO TEMPMATCHES VALUES(?,?,?,?,?,?,?,?,?,?)";
	private static final String SELECT_BY_ID = "SELECT * FROM TEMPMATCHES WHERE MATCHID=?";
	private static final String UPDATE_BY_ID = "UPDATE TEMPMATCHES SET CITY=? WHERE MATCHID=?";
	private static final String DELETE_BY_ID = "DELETE FROM TEMPMATCHES WHERE MATCHID=?";
	private static final String SELECT_ALL = "SELECT * FROM TEMPMATCHES";
	private DBCPDataSource dbcpdatasource;
	private Connection connection;

	public TempMatchDBDaoImpl() {
		dbcpdatasource = DBCPDataSource.getInstance();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.reactiveworks.restipl.services.interfaces.IMatchService#addMatch(com.
	 * reactiveworks.restipl.model.Match)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.reactiveworks.restipl.db.impl.IMatchDAO#addMatch(com.reactiveworks.
	 * restipl.model.Match)
	 */
	/* (non-Javadoc)
	 * @see com.reactiveworks.restipl.db.impl.IMatchDao#addMatch(com.reactiveworks.restipl.model.Match)
	 */
	@Override
	public int addMatch(Match match) throws DataAccessException {
		logger.debug("in the addMatch()");
		connection = DBCPDataSource.getConnection();
		PreparedStatement prepareStatement = null;
		int noOfRowsAffected = 0;
		try {
			prepareStatement = connection.prepareStatement(INSERT);
			prepareStatement.setInt(1, match.getMatchId());
			prepareStatement.setInt(2, match.getSeason());
			prepareStatement.setString(3, match.getCity());
			prepareStatement.setDate(4, match.getDate());
			prepareStatement.setString(5, match.getTeam1());
			prepareStatement.setString(6, match.getTeam2());
			prepareStatement.setString(7, match.getTossWinner());
			prepareStatement.setString(8, match.getTossDecision());
			prepareStatement.setString(9, match.getResult());
			prepareStatement.setString(10, match.getWinner());
			noOfRowsAffected = prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataAccessException("unable to insert match id " + match.getMatchId(), e);
		} finally {
			if (prepareStatement != null && connection != null) {
				dbcpdatasource.close(prepareStatement, connection);
			}
		}
		logger.debug("exiting the addMatch()");
		return noOfRowsAffected;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.reactiveworks.restipl.db.impl.IMatchDAO#getMatchById(int)
	 */
	/* (non-Javadoc)
	 * @see com.reactiveworks.restipl.db.impl.IMatchDao#getMatchById(int)
	 */
	@Override
	public Match getMatchById(int id) throws DataAccessException {
		logger.debug("in getMatchById()");
		connection = DBCPDataSource.getConnection();
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		try {
			prepareStatement = connection.prepareStatement(SELECT_BY_ID);
			prepareStatement.setInt(1, id);
			resultSet = prepareStatement.executeQuery();
			Match match = new Match();
			while (resultSet.next()) {
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
			}
			logger.debug("exiting getMatchById()");
			return match;
		} catch (SQLException e) {
			throw new DataAccessException("unable to get match details of id " + id, e);
		} finally {
			if (resultSet != null && prepareStatement != null && connection != null) {
				dbcpdatasource.close(resultSet, prepareStatement, connection);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.reactiveworks.restipl.db.impl.IMatchDao#updateMatchById(com.reactiveworks.restipl.model.Match)
	 */
	@Override
	public void updateMatchById(Match match) throws DataAccessException {
		logger.debug("in updateMatchById()");
		connection = DBCPDataSource.getConnection();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connection.prepareStatement(UPDATE_BY_ID);
			prepareStatement.setString(1, match.getCity());
			prepareStatement.setInt(2, match.getMatchId());
			prepareStatement.executeUpdate();
			logger.debug("exiting updateMatchById()");
		} catch (SQLException e) {
			throw new DataAccessException("unable to update match id " + match.getMatchId(), e);
		} finally {
			if (prepareStatement != null && connection != null) {
				dbcpdatasource.close(prepareStatement, connection);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.reactiveworks.restipl.db.impl.IMatchDao#deleteMatchById(int)
	 */
	@Override
	public void deleteMatchById(int id) throws DataAccessException {
		logger.debug("in deleteMatchById()");
		connection = DBCPDataSource.getConnection();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connection.prepareStatement(DELETE_BY_ID);
			prepareStatement.setInt(1, id);
			prepareStatement.executeUpdate();
			logger.debug("exiting deleteMatchById()");
		} catch (SQLException e) {
			throw new DataAccessException("unable to delete match id " + id, e);
		} finally {
			if (prepareStatement != null && connection != null) {
				dbcpdatasource.close(prepareStatement, connection);
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.reactiveworks.restipl.db.impl.IMatchDao#getAllMatches()
	 */
	@Override
	public List<Match> getAllMatches() throws DataAccessException {
		logger.debug("in getAllMatches()");
		connection = DBCPDataSource.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SELECT_ALL);
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
