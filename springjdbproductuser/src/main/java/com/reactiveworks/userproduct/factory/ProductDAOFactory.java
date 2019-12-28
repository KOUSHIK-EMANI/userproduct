package com.reactiveworks.userproduct.factory;

import com.reactiveworks.userproduct.csv.impl.ProductCSVDAOImpl;
import com.reactiveworks.userproduct.dao.interfaces.IProductDAO;
import com.reactiveworks.userproduct.db.impl.ProductDBDAOImpl;

/**
 * ProductDAOFactory class is a Factory class.It will abstract or hide the
 * instance
 * 
 * @author Sai Koushik Emani
 *
 */
public class ProductDAOFactory {
	private ProductDBDAOImpl productDBDAOImpl;
	private ProductCSVDAOImpl productCSVDAOImpl;

	/**
	 * It will get the instance of ProductCsv and ProductDB based on requirement
	 * @param str
	 * @return Object
	 */
	public IProductDAO getInstance(String str) {
		if (str.equalsIgnoreCase("DB")) {
			return productDBDAOImpl; // for database
		} else {
			return productCSVDAOImpl; // for CSV file
		}
	}

	public ProductDBDAOImpl getProductDBDAOImpl() {
		return productDBDAOImpl;
	}

	public void setProductDBDAOImpl(ProductDBDAOImpl productDBDAOImpl) {
		this.productDBDAOImpl = productDBDAOImpl;
	}

	public ProductCSVDAOImpl getProductCSVDAOImpl() {
		return productCSVDAOImpl;
	}

	public void setProductCSVDAOImpl(ProductCSVDAOImpl productCSVDAOImpl) {
		this.productCSVDAOImpl = productCSVDAOImpl;
	}
}
