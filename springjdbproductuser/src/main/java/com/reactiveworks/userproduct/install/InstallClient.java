package com.reactiveworks.userproduct.install;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.reactiveworks.userproduct.csv.exceptions.DataSourceOperationNotSupportException;
import com.reactiveworks.userproduct.csv.exceptions.DataSourceReadException;
import com.reactiveworks.userproduct.dao.interfaces.IProductDAO;
import com.reactiveworks.userproduct.db.exceptions.DataBaseAccessException;
import com.reactiveworks.userproduct.exceptions.UserIdInvalidException;
import com.reactiveworks.userproduct.factory.ProductDAOFactory;
import com.reactiveworks.userproduct.model.Product;
import com.reactiveworks.userproduct.model.User;

/**
 * Install Client class has spring IOC cotainer to instantiate the classes
 * 
 * @author Sai Koushik Emani
 *
 */
public class InstallClient {
	// contains the spring config xml path
	String filepath = getClass().getClassLoader().getResource("springconfig.xml").toString();
	public static final String FILE_TYPE = "file";
	public static final String FILE_NAME = "fileType.properties";

	public void client() throws DataSourceReadException, DataBaseAccessException,
			DataSourceOperationNotSupportException, UserIdInvalidException {
		Properties propertie = null;
		InputStream filestream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
		try {
			if (filestream == null) {
				throw new DataSourceReadException("connection failed");
			} else {
				propertie = new Properties();
				propertie.load(filestream);
			}
		} catch (IOException e) {
			throw new DataSourceReadException("Unable to load the properties file ", e);
		}
		ApplicationContext application = new ClassPathXmlApplicationContext(filepath);
		/*UserDAOFactory daofactory = application.getBean("userdaofactory", UserDAOFactory.class);
		IUserDAO instanceOfUser = daofactory.getInstance(propertie.getProperty(FILE_TYPE));
		List<User> userData = instanceOfUser.getUserData();
		for (User user : userData) {
			System.out.println(user);
		}
*/
		ProductDAOFactory daofactory = application.getBean("productdaofactory", ProductDAOFactory.class);
		System.out.println(daofactory);
		IProductDAO instanceOfUser = daofactory.getInstance(propertie.getProperty(FILE_TYPE));
		System.out.println(instanceOfUser);
		List<Product> userData = instanceOfUser.getProductData();
		for (Product user : userData) {
			System.out.println(user);
		}
	}

}
