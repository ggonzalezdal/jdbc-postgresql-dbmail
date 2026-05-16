package dbmail;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class UpdateUserTest {

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
			
			String sql = 
						"UPDATE users " +
						"SET password = ? " +
						"WHERE user_name = ?";
			
			try (
				
				Connection connection =
						DriverManager.getConnection(url, user, password);
					
				PreparedStatement preparedStatement = 
						connection.prepareStatement(sql);
				
			) { 
				
				preparedStatement.setString(1, "newpassword");
				preparedStatement.setString(2, "maria");
					
				int rows =
				        preparedStatement.executeUpdate();
				
				if (rows > 0) {

				    System.out.println(
				    		"Rows updated: " + rows);
				           
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}


