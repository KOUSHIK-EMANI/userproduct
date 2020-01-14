package com.reactiveworks.restipl.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.reactiveworks.restipl.dao.IDeliveryDao;
import com.reactiveworks.restipl.db.DBCPDataSource;
import com.reactiveworks.restipl.db.exceptions.DataAccessException;
import com.reactiveworks.restipl.model.Delivery;

public class DeliveryDBDaoImpl implements IDeliveryDao{
	private static final String SELECT_ALL_DELIVERIES = "SELECT * FROM DELIVERIES";
	private DBCPDataSource dbcpdatasource;
	private Connection connection;

	public DeliveryDBDaoImpl() {
		dbcpdatasource = DBCPDataSource.getInstance();
	}
	@Override
	public List<Delivery> getAllDeliveries() throws DataAccessException {
		connection = DBCPDataSource.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SELECT_ALL_DELIVERIES);
			List<Delivery> deliveryList = new ArrayList<Delivery>();
			while (resultSet.next()) {
			Delivery delivery =new Delivery();
			delivery.setMatchId(resultSet.getInt(1));
			delivery.setInnings(resultSet.getInt(2));
			delivery.setBattingTeam(resultSet.getString(3));
			delivery.setBowlingTeam(resultSet.getString(4));
			delivery.setOvers(resultSet.getInt(5));
			delivery.setBall(resultSet.getInt(6));
			delivery.setBatsmen(resultSet.getString(7));
			delivery.setBowler(resultSet.getString(8));
			delivery.setWideRuns(resultSet.getInt(9));
			delivery.setByeRuns(resultSet.getInt(10));
			delivery.setLegByeRuns(resultSet.getInt(11));
			delivery.setNoBallRuns(resultSet.getInt(12));
			delivery.setPenalityRuns(resultSet.getInt(13));
			delivery.setBatsmenRuns(resultSet.getInt(14));
			delivery.setExtraRuns(resultSet.getInt(15));
			delivery.setTotalRuns(resultSet.getInt(16));
			
			deliveryList.add(delivery);
			}
			return deliveryList;
		} catch (SQLException e) {
			throw new DataAccessException("unable to get all deliveries details ", e);
		} finally {
			if (resultSet != null && statement != null && connection != null) {
				dbcpdatasource.close(resultSet, statement, connection);
			}
		}
	}
	
}
