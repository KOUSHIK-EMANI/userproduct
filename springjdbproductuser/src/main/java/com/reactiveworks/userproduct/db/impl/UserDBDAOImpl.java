package com.reactiveworks.userproduct.db.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.reactiveworks.userproduct.dao.interfaces.IUserDAO;
import com.reactiveworks.userproduct.db.exceptions.DataBaseAccessException;
import com.reactiveworks.userproduct.model.User;
/**
 * UserDBDAOImpl class will perform the database Query operations
 * 
 * @author Sai Koushik Emani
 *
 */
public class UserDBDAOImpl implements IUserDAO {

	private static final Logger LOGGER = Logger.getLogger(UserDBDAOImpl.class);
	// Query part
	private final static String SELECT_QUERY = "SELECT * FROM USER";
	private final static String INSERT_QUERY = "INSERT INTO USER VALUES(?,?,?,?,?)";
	private final static String UPDATE_QUERY = "UPDATE USER SET USERNAME = ? WHERE USERID = ?";
	private final static String SELECT_QUERY_WHERE = "SELECT * FROM USER WHERE USERID = ?";
	private final static String DELETE_QUERY = "DELETE FROM USER WHERE USERID = ?";
	private JdbcTemplate jdbcTemplate;
	/**
	 * Get the instance of DBCPDataSource from spring container through constructor
	 * setter method
	 */
	public UserDBDAOImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * This method will add the user to the database
	 */
	@Override
	public int addUser(User user) throws DataBaseAccessException {
		LOGGER.debug("Adding into th user table");
		int add;
		Object[] inputs = new Object[] { user.getUserId(), user.getUserName(), user.getEmail(), user.getPhoneNumber(),
				user.getCity() };
		try {
			add = jdbcTemplate.update(INSERT_QUERY, inputs);
		} catch (DataAccessException e) {
			throw new DataBaseAccessException("Unable to add user", e);
		}
		return add;
	}// ...end of addUser

	/**
	 * This method will get the list of user data
	 */
	@Override
	public List<User> getUserData() throws DataBaseAccessException {
		LOGGER.debug("In getUserData()");
		List<User> queryList;
		try {
			queryList = jdbcTemplate.query(SELECT_QUERY, new UserRowMapper());

		} catch (DataAccessException e) {
			throw new DataBaseAccessException("unable to get the user details", e);
		}
		return queryList;
	}// ...end of getUserData

	/**
	 * This method is used to get the specific user details
	 */
	@Override
	public User getUser(String userId1) throws DataBaseAccessException {
		User user=jdbcTemplate.queryForObject(SELECT_QUERY_WHERE,new Object[] {userId1},new UserRowMapper());
		return user;
	}// ... end of getUser

	/**
	 * This method is used to update the name of the user
	 */
	@Override
	public void updateUser(User user) throws DataBaseAccessException {
		try {
			jdbcTemplate.update(UPDATE_QUERY, new Object[] { user.getUserName(), user.getUserId() });
		} catch (DataAccessException e) {
			throw new DataBaseAccessException("unable to update the user details", e);
		}
	}// ...end of updateUser

	/**
	 * This method is used to remove the user from database
	 */
	@Override
	public void removeUser(User user) throws DataBaseAccessException {
		jdbcTemplate.update(DELETE_QUERY, new Object[] { user.getUserId() });
	}// ...end of removeUser
}
