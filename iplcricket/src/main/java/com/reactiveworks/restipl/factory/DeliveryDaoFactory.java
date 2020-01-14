package com.reactiveworks.restipl.factory;

import com.reactiveworks.restipl.dao.IDeliveryDao;
import com.reactiveworks.restipl.db.impl.DeliveryDBDaoImpl;

public class DeliveryDaoFactory {
	public IDeliveryDao getInstance(String instance) {
		if (instance.equalsIgnoreCase("db")) {
			return new DeliveryDBDaoImpl();
		} else {
			return new DeliveryDBDaoImpl();
		}
	}
}
