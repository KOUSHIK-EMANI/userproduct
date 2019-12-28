/**
 * 
 */
package com.reactiveworks.userproduct.dao.interfaces;

import java.util.List;

import com.reactiveworks.userproduct.csv.exceptions.DataSourceOperationNotSupportException;
import com.reactiveworks.userproduct.csv.exceptions.DataSourceReadException;
import com.reactiveworks.userproduct.db.exceptions.DataBaseAccessException;
import com.reactiveworks.userproduct.model.User;


/**
 *  IUserDAO interface has two implementations csv and db
 * @author Sai Koushik Emani
 *
 */
public interface IUserDAO {
	/**
	 * add user details
	 * @param user expects user object to insert into table
	 * @return integer value no. of rows affected
	 * @throws DataSourceOperationNotSupportException 
	 * @throws DataBaseAccessException 
	 */
	public int addUser(User user) throws DataSourceOperationNotSupportException, DataBaseAccessException;
	/**
	 * This method returns List of user details 
	 * @return list of user objects
	 * @throws DataSourceReadException 
	 * @throws DataBaseAccessException 
	 */
	List<User> getUserData() throws DataSourceReadException, DataBaseAccessException;
	/**
	 * This method returns user with given userid 
	 * @param userId user id
	 * @return User object
	 * @throws DataSourceOperationNotSupportException 
	 * @throws DataBaseAccessException 
	 */
	public User getUser(String userId) throws DataSourceOperationNotSupportException, DataBaseAccessException;
	/**
	 * This method used to update the user details in the table
	 * @param user User object
	 * @throws DataSourceOperationNotSupportException 
	 * @throws DataBaseAccessException 
	 */
	void updateUser(User user) throws DataSourceOperationNotSupportException, DataBaseAccessException;
	/**
	 * This method used to delete the user details from the table 
	 * @param user user id
	 * @throws DataSourceOperationNotSupportException 
	 * @throws DataBaseAccessException 
	 */
	void removeUser(User user) throws DataSourceOperationNotSupportException, DataBaseAccessException;
}
