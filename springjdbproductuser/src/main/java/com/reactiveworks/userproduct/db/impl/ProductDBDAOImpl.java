package com.reactiveworks.userproduct.db.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.reactiveworks.userproduct.csv.exceptions.DataSourceReadException;
import com.reactiveworks.userproduct.dao.interfaces.IProductDAO;
import com.reactiveworks.userproduct.db.exceptions.DataBaseAccessException;
import com.reactiveworks.userproduct.model.Product;

/**
 * ProductDBDAOImpl class does the database Query operations
 * 
 * @author Sai Koushik Emani
 *
 */
public class ProductDBDAOImpl implements IProductDAO {
	private static final Logger LOGGER = Logger.getLogger(ProductDBDAOImpl.class);
	// Query part
	private static final String INSERT_PRODUCT = "INSERT INTO PRODUCTCITY VALUES(?,?)";
	private static final String SELECT_PRODUCT_QUERY_LIST = "SELECT PRO.PRODUCTID,PRO.PRODUCTNAME,PRO.PRODUCTCATEGORY,PRO.PRODUCTPRICE,C.CITIES FROM CITY C INNER JOIN PRODUCTCITY P INNER JOIN PRODUCT PRO ON C.CITYID= P.CITYID AND P.PRODUCTID=PRO.PRODUCTID";
	private JdbcTemplate jdbcTemplate;

	public ProductDBDAOImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * This method will add products to database
	 */
	@Override
	public void addProduct(Product product) throws DataBaseAccessException {
		LOGGER.debug("Inserting a single row into a database table");
		LOGGER.info("values of product " + product.getProductId());
		Object[] insert = new Object[] { product.getProductId(), product.getProductName(),
				product.getProductCategory() };
		try {
			jdbcTemplate.update(INSERT_PRODUCT, insert);
		} catch (DataAccessException e) {
			throw new DataBaseAccessException("unable to do the transaction " + e);
		}
	}// ...end of addProduct
	@Override
	public List<Product> getProductData() throws DataSourceReadException, DataBaseAccessException {
		List<Product> lstproduct = new ArrayList<Product>();
		List<Product> query = jdbcTemplate.query(SELECT_PRODUCT_QUERY_LIST, new ProductRowMapper());
		for (Product product : query) {
			if (lstproduct.isEmpty()) {
				lstproduct.add(product);
			} else {
				boolean add = true;
				for (Product prod : lstproduct) {
					if (prod.getProductId().equalsIgnoreCase(product.getProductId())) {
						add = false;
						List<String> templist = new ArrayList<>();
						List<String> availableCity = prod.getAvailableCity();
						templist.addAll(product.getAvailableCity());
						templist.addAll(availableCity);
						prod.setAvailableCity(templist);
					}
				}
				if (add) {
					lstproduct.add(product);
				}
			}
		} // ... end of while loop
		return lstproduct;
	}// ...end of getProductData
}
