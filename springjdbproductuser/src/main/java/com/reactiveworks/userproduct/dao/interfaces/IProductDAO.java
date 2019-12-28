/**
 * 
 */
package com.reactiveworks.userproduct.dao.interfaces;
import java.util.List;

import com.reactiveworks.userproduct.csv.exceptions.DataSourceOperationNotSupportException;
import com.reactiveworks.userproduct.csv.exceptions.DataSourceReadException;
import com.reactiveworks.userproduct.db.exceptions.DataBaseAccessException;
import com.reactiveworks.userproduct.model.Product;

/**
 * IproductDAO interface has two implementations csv and db
 * @author Sai Koushik Emani
 *
 */
public interface IProductDAO {
	/**
	 * This method will add a product into the database
	 * @param product
	 * @throws DataBaseAccessException
	 * @throws DataSourceOperationNotSupportException 
	 */
	public void addProduct(Product product) throws DataBaseAccessException, DataSourceOperationNotSupportException;

	/**
	 * This method will get the product data and return it as product listS
	 * @return list of user objects
	 * @throws DataSourceReadException 
	 * @throws DataBaseAccessException 
	 */
	List<Product> getProductData() throws DataSourceReadException, DataBaseAccessException;
}
