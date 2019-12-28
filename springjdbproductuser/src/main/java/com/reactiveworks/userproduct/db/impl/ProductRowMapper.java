package com.reactiveworks.userproduct.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

import org.springframework.jdbc.core.RowMapper;

import com.reactiveworks.userproduct.model.Product;

public class ProductRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Product product = new Product();
		product.setProductId(resultSet.getString(1));
		product.setProductName(resultSet.getString(2));
		product.setProductCategory(resultSet.getString(3));
		product.setPrice(Double.valueOf(resultSet.getString(4)));
		product.setAvailableCity(Arrays.asList(resultSet.getString(5).split(",")));
		return product;
	}

}
