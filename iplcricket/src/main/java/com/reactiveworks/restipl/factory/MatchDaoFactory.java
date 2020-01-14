package com.reactiveworks.restipl.factory;

import com.reactiveworks.restipl.dao.IMatchDao;
import com.reactiveworks.restipl.db.impl.MatchDBDaoImpl;

public class MatchDaoFactory {
	public IMatchDao getInstance(String instance) {
		if (instance.equalsIgnoreCase("db")) {
			return new MatchDBDaoImpl();
		} else {
			return new MatchDBDaoImpl();
		}
	}
}
