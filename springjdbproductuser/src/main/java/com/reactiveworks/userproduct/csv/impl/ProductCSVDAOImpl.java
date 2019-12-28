package com.reactiveworks.userproduct.csv.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.reactiveworks.userproduct.csv.exceptions.DataSourceOperationNotSupportException;
import com.reactiveworks.userproduct.csv.exceptions.DataSourceReadException;
import com.reactiveworks.userproduct.dao.interfaces.IProductDAO;
import com.reactiveworks.userproduct.model.Product;

public class ProductCSVDAOImpl implements IProductDAO{
	public static final Logger logger = Logger.getLogger(ProductCSVDAOImpl.class);
	public static final String FILE_NAME = "Product.csv";

	@Override
	public void addProduct(Product product)throws DataSourceOperationNotSupportException {
		throw new DataSourceOperationNotSupportException("Cannot perfom the operation");
	}

	@Override
	public List<Product> getProductData() throws DataSourceReadException {
		logger.debug("in the getAllProducts(), calling the file form the class path");
		InputStream inputsteram = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME);
		if (inputsteram == null) {
			throw new DataSourceReadException(FILE_NAME + " not found in the classpath");
		} else {
			logger.debug("reading the file from the class path");
			BufferedReader buffreader = new BufferedReader(new InputStreamReader(inputsteram));
			ArrayList<Product> arraylist = null;
			try {
				buffreader.readLine();
				String readline;
				arraylist = new ArrayList<Product>();

				while ((readline = buffreader.readLine()) != null) {
					
					arraylist.add(productCSVLine(readline));
				}
			} catch (IOException e) {
				logger.debug("Error occured while reading file" + e);
				throw new DataSourceReadException("Error occured while reading file", e);
			}
			return arraylist;
		}//...end of else
	}//...end of getProductData()
	
	/**
	 * This method read the line and convert into product object
	 * @param readline contains a single line of the csv file
	 * @return user object
	 */
		private Product productCSVLine(String readline) throws NumberFormatException {
			String strline[];
			strline = readline.split(",");
			Product product = new Product();
			product.setProductId(strline[0]);
			product.setProductName(strline[1]);
			product.setProductCategory(strline[2]);
			product.setPrice(Double.valueOf(strline[3]));
			String[] tempstr = strline[4].split("/");
			product.setAvailableCity(Arrays.asList(tempstr));
			

			return product;
		}//...end of productCSVLine
	}//...end of the class
