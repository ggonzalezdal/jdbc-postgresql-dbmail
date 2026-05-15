package dbmail;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionTest {

	public static void main(String[] args) {
		
		Properties properties= new Properties();
		
		try (
			InputStream input = ConnectionTest.class
					.getClassLoader()
					.getResourceAsStream("db.properties")
		) {
			
			if (input == null) {
				
				System.out.println("db.properties file not found.");
				return;
			}
			
			properties.load(input);
			
			String url = properties.getProperty("db.url");
			String user = properties.getProperty("db.user");
			String password = properties.getProperty("db.password");
			
			try (
				
				Connection connection =
						DriverManager.getConnection(url, user, password);
					
				Statement statement = 
						connection.createStatement();
					
				ResultSet resultSet =
						statement.executeQuery("SELECT version()");
					
			) { 
				
				if (resultSet.next()) {
					
					System.out.println("Connection successful!");
                    System.out.println(resultSet.getString(1));
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}
