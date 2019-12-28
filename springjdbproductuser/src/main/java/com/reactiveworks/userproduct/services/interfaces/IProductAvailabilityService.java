/**
 * 
 */
package com.reactiveworks.userproduct.services.interfaces;

import java.util.List;

import com.reactiveworks.userproduct.exceptions.UserIdInvalidException;
import com.reactiveworks.userproduct.model.Product;

/**
 * IProductAvailabilityService is a service interface
 * @author Sai Koushik Emani
 *
 */
public interface IProductAvailabilityService {
	/**
	 * Gets all the product from all the category that are available in the city
	 * where user lives
	 * this method will get the user from the instance variable userList for the given userId
	 * get the city of the Selected User
	 * @param userId contains user identity number
	 * @return list
	 * @throws UserIdInvalidException occurs when userid is invalid
	 * @throws ProductAvailableException occurs when there are no products available in city
	 */
	public List<Product> getAllProductForUser(String userId) throws UserIdInvalidException;
}
