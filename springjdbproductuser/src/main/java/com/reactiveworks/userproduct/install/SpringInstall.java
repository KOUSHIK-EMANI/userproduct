package com.reactiveworks.userproduct.install;


import com.reactiveworks.userproduct.csv.exceptions.DataSourceOperationNotSupportException;
import com.reactiveworks.userproduct.csv.exceptions.DataSourceReadException;
import com.reactiveworks.userproduct.db.exceptions.DataBaseAccessException;
import com.reactiveworks.userproduct.exceptions.UserIdInvalidException;

/**
 * DEOFactoryInstall Class will call the Client classes and get the instance of
 * respective file type
 */
public class SpringInstall {
	public static void main(String[] args) throws DataSourceReadException, DataBaseAccessException, DataSourceOperationNotSupportException, UserIdInvalidException {
		InstallClient install=new InstallClient();
		install.client();
	}
}