package com.reactiveworks.restipl.services.impl;

import java.util.Comparator;

import com.reactiveworks.restipl.model.HighestRunRate;

public class MyComporator implements Comparator<HighestRunRate> {

	@Override
	public int compare(HighestRunRate o1, HighestRunRate o2) {
		return -((Double) o1.getNetRunRate()).compareTo((Double) o2.getNetRunRate());
	}

}
