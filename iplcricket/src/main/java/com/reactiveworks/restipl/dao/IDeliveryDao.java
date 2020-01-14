package com.reactiveworks.restipl.dao;

import java.util.List;

import com.reactiveworks.restipl.db.exceptions.DataAccessException;
import com.reactiveworks.restipl.model.Delivery;

public interface IDeliveryDao {
	List<Delivery> getAllDeliveries() throws DataAccessException;
}
