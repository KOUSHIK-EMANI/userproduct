package com.reactiveworks.userproduct.factory;

import com.reactiveworks.userproduct.csv.impl.UserCSVDAOImpl;
import com.reactiveworks.userproduct.dao.interfaces.IUserDAO;
import com.reactiveworks.userproduct.db.impl.UserDBDAOImpl;

/**
 * DAOFactory class is a Factory class. It will abstract or hide the instance
 * 
 * @author Sai Koushik Emani
 *
 */
public class UserDAOFactory {
	/**
	 * It will get the instance of Usercsvdaoimpl or Userdbdaoimpl based on requirement
	 * 
	 * @param str
	 * @return
	 */
	private UserDBDAOImpl userdbdaoimpl;
	private UserCSVDAOImpl usercsvdaoimpl;

	public IUserDAO getInstance(String str) {
		if (str.equalsIgnoreCase("DB")) {
			return userdbdaoimpl; //for database
		} else {
			return usercsvdaoimpl;// for CSV file
		}
	}
	public UserDBDAOImpl getUserdbdaoimpl() {
		return userdbdaoimpl;
	}
	public void setUserdbdaoimpl(UserDBDAOImpl userdbdaoimpl) {
		this.userdbdaoimpl = userdbdaoimpl;
	}
	public UserCSVDAOImpl getUsercsvdaoimpl() {
		return usercsvdaoimpl;
	}
	public void setUsercsvdaoimpl(UserCSVDAOImpl usercsvdaoimpl) {
		this.usercsvdaoimpl = usercsvdaoimpl;
	}
}