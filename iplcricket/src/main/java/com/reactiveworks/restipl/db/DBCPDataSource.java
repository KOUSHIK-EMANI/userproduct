package com.reactiveworks.restipl.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

import com.reactiveworks.restipl.db.exceptions.DataAccessException;

/**
 * DBCPDataSource Class is a singleton class which is used to establish the
 * connection pool
 * 
 * @author Sai Koushik Emani
 *
 */
public class DBCPDataSource {
	private static final String FILE_NAME = "dbproperties.properties";
	private static final String URL = "url";
	private static final String USER = "user";
	private static final String PASSWORD = "password";
	private static final String DRIVER = "driver";
	private static Properties propertie = null;
	private static BasicDataSource basicdatasource = null;
	private static Connection connection = null;
	private static DBCPDataSource dbcpdata = null;

	/**
	 * create the instance of the class. and also create the instance of properties
	 * only once
	 * 
	 * @return
	 */
	public static DBCPDataSource getInstance() {
		if (propertie == null) {
			propertie = new Properties();
			try {
				dbProperties();// dbProperties will read and load the properties file only once
			} catch (DataAccessException e) {
				e.printStackTrace();
			}

		}
		if (dbcpdata == null) {
			dbcpdata = new DBCPDataSource(); // create the instance of the class for the first time
		} // ..end of if block
		return dbcpdata; // return the same instance
	}// ..end of method getInstance

	/**
	 * It is a private constructor which is used to set the mandatory fields such as
	 * url, username, password and classname to datasource only once
	 */
	private DBCPDataSource() {
		basicdatasource = new BasicDataSource();
		if (connection == null) {
			basicdatasource.setDriverClassName(propertie.getProperty(DRIVER));
			basicdatasource.setUrl(propertie.getProperty(URL));
			basicdatasource.setUsername(propertie.getProperty(USER));
			basicdatasource.setPassword(propertie.getProperty(PASSWORD));
			basicdatasource.setMaxActive(100);
		}
	}

	/**
	 * get connection will establish the connection from the BasicDataSource
	 * 
	 * @return connection object
	 * @throws SQLException
	 * @throws DataAccessException
	 * @throws DataBaseException
	 *             unable to connect to database
	 */
	public static Connection getConnection() throws DataAccessException {
		try {
			connection = basicdatasource.getConnection();
			return connection;
		} catch (SQLException e) {
			throw new DataAccessException("unable to connect to database", e);
		}

	}// ...end of getConnection() constructor

	/**
	 * This close() is used to close the connection of resultset, statement,
	 * connection
	 */
	public void close(ResultSet resultset, Statement statment, Connection connectionclose) {
		if (resultset != null) {
			try {
				resultset.close();
			} catch (SQLException e) {
			}
		}
		if (statment != null) {
			try {
				statment.close();
			} catch (SQLException e) {
			}
		}
		if (connectionclose != null) {
			try {
				connectionclose.close();
			} catch (SQLException e) {
			}
		}
	}

	/**
	 * This close() is used to close the connections of preparedstatment
	 * and connection
	 *
	 */
	public void close(PreparedStatement preparestatment, Connection connectionclose) {
		if (preparestatment != null) {
			try {
				preparestatment.close();
			} catch (SQLException e) {
			}
		}
		if (connectionclose != null) {
			try {
				connectionclose.close();
			} catch (SQLException e) {
			}
		}

	}

	/**
	 * dbProperties will read and load the properties file. and it will load only
	 * once
	 * 
	 * @throws DataAccessException
	 * 
	 * @throws DataSourceReadException
	 * 
	 * @throws DBConnectionException
	 *             occurs when properties file is not found
	 */
	private static void dbProperties() throws DataAccessException {
		InputStream filestream = DBCPDataSource.class.getClassLoader().getResourceAsStream(FILE_NAME);
		try {
			if (filestream == null) {
				throw new DataAccessException(
						"unable to load properties file, please check the name or path of the file " + FILE_NAME);
			} else {
				propertie.load(filestream); // load the properties file
			}
		} catch (IOException e) {
		}
	}// ...end of dbProperties()

}
