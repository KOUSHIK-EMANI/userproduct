package com.reactiveworks.userproduct.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.reactiveworks.userproduct.csv.exceptions.DataSourceReadException;
import com.reactiveworks.userproduct.db.exceptions.DataBaseAccessException;
import com.reactiveworks.userproduct.exceptions.UserIdInvalidException;
import com.reactiveworks.userproduct.factory.ProductDAOFactory;
import com.reactiveworks.userproduct.factory.UserDAOFactory;
import com.reactiveworks.userproduct.model.Product;
import com.reactiveworks.userproduct.model.User;
import com.reactiveworks.userproduct.services.interfaces.IProductAvailabilityService;

/**
 * Class ProductAvailabilityService will Gets all the product from all the
 * category that are available in the city where user lives and get the city of
 * the Selected User. later this will call getAllProductForUser(userId) this
 * will give all the product in the user city iterate over the list and find
 * those product which matches the given category
 * 
 * @author Sai Koushik Emani
 *
 */
public class ProductAvailabilityService implements IProductAvailabilityService {

	public final static Logger logger = Logger.getLogger(ProductAvailabilityService.class);
	private List<Product> productList;
	private List<User> userList;
	UserDAOFactory userDAOFactory;
	ProductDAOFactory productDAOFactory;
	/**
	 * This constructor will get list of user and product from the database or from csv
	 * @param userDAOFactory it is a user factory class
	 * @param productDAOFactory it is a product factory class
	 * @throws DataSourceReadException occurs when file not found of miss spell of the file name
	 * @throws DataBaseAccessException occurs when unable to connect to database
	 */
	public ProductAvailabilityService(UserDAOFactory userDAOFactory, ProductDAOFactory productDAOFactory) throws DataSourceReadException, DataBaseAccessException {
		this.userDAOFactory = userDAOFactory;
		this.productDAOFactory = productDAOFactory;
		userList = userDAOFactory.getInstance("DB").getUserData();
		productList = productDAOFactory.getInstance("DB").getProductData();
	}
	public List<Product> getAllProductForUser(String userId) throws UserIdInvalidException {
		logger.info("Given UserID is " + userId);
		ArrayList<Product> userproductsList = new ArrayList<Product>();
		String strcity = null;
		User userToSearch = null;
		for (User user : userList) {
			if (user.getUserId().contentEquals(userId)) {
				userToSearch = user;
				strcity = user.getCity();
				break;
			}
		}
		if (userToSearch == null) {
			logger.error("City cannot be null");
			throw new UserIdInvalidException("UserId not found, Please Check the UserID");
		} else {
			logger.debug("User City is " + strcity);
			for (Product product : productList) {
				if (product.getAvailableCity().contains(strcity)) {
					userproductsList.add(product);
				}
			}
			return userproductsList;
		}

	}
}
