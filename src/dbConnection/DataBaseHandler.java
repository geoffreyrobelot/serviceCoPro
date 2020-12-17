package dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseHandler extends Configuration {

	Connection dbconnection;

	public Connection getConnection() {
		
		String connectionString = "jdbc:mysql://localhost:3306/servicecopro";
		System.out.println(connectionString);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.getMessage();
			e.printStackTrace();
		}

		try {
			dbconnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servicecopro", "admin", "admin");
		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
		}

		return dbconnection;

	}

}
