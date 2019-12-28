package com.reactiveworks.userproduct.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.reactiveworks.userproduct.model.User;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		User user = new User();
		user.setUserId(resultSet.getString(1));
		user.setUserName(resultSet.getString(2));
		user.setEmail(resultSet.getString(3));
		user.setPhoneNumber(resultSet.getString(4));
		user.setCity(resultSet.getString(5));
		return user;
	}

}
