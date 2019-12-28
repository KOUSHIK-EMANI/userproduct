package com.reactiveworks.userproduct.csv.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reactiveworks.userproduct.csv.exceptions.DataSourceOperationNotSupportException;
import com.reactiveworks.userproduct.csv.exceptions.DataSourceReadException;
import com.reactiveworks.userproduct.dao.interfaces.IUserDAO;
import com.reactiveworks.userproduct.model.User;
/**
 * UserCSVDAOImpl class will read the file and 
 * convert it into a list object
 * @author Sai Koushik Emani
 *
 */
public class UserCSVDAOImpl implements IUserDAO {
	private static final Logger LOGGER = Logger.getLogger(UserCSVDAOImpl.class);
	public static final String FILE_NAME = "User.csv";
/**
 * This method throw exception when we perform add operation in csv file
 */
	@Override
	public int addUser(User user) throws DataSourceOperationNotSupportException {
		throw new DataSourceOperationNotSupportException("Cannot perform the operation");
	}
	/**
	 * This method will get the User data and return the User list 
	 */
	@Override
	public List<User> getUserData() throws DataSourceReadException {
		LOGGER.debug("in the getAllUsers() calling the file form the class path");
		InputStream inputsteram = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME);
		if (inputsteram == null) {
			LOGGER.error("File not found in the class path");
			throw new DataSourceReadException("User file not found in the classpath");
		} else {
			LOGGER.debug("reading the file from the class path");
			BufferedReader buffreader = new BufferedReader(new InputStreamReader(inputsteram));
			String readline;
			ArrayList<User> arraylist = null;
			try {
				buffreader.readLine(); // reading the first line
				arraylist = new ArrayList<User>();
				while ((readline = buffreader.readLine()) != null) {
					arraylist.add(userCSVLine(readline));
				}
			} catch (IOException e) {
				LOGGER.debug("error while reading file" + e);
			}
			LOGGER.debug("retuning the list object");
			return arraylist;
		}//...end of else
	}//...end of getUserData()
	
	/**
	 * This method throw exception when we perform add operation in csv file
	 */
	@Override
	public User getUser(String userId) throws DataSourceOperationNotSupportException {
		throw new DataSourceOperationNotSupportException("Cannot perform the operation");
	}
	/**
	 * This method throw exception when we perform update operation in csv file
	 */
	@Override
	public void updateUser(User user) throws DataSourceOperationNotSupportException {
		throw new DataSourceOperationNotSupportException("Cannot perform the operation");
	}
	/**
	 * This method throw exception when we perform delete operation in csv file
	 */
	@Override
	public void removeUser(User user) throws DataSourceOperationNotSupportException {
		throw new DataSourceOperationNotSupportException("Cannot perform the operation");
	}
	/**
	 * This method read the line and convert into user object
	 * 
	 * @param readline contains a single line of the csv file
	 * @return user object
	 */
	private User userCSVLine(String readline) {

		User user = new User();
		String strline[];
		strline = readline.split(",");
		user.setUserId(strline[0]);
		user.setUserName(strline[1]);
		user.setEmail(strline[2]);
		user.setPhoneNumber(strline[3]);
		user.setCity(strline[4]);
		return user;
	}

}
