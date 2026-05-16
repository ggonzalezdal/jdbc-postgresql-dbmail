package dbmail;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Properties;

public class PreparedStatementTest {

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
						"SELECT user_name, name, surname " +
						"FROM users " +
						"WHERE user_name = ?";
			
			try (
				
				Connection connection =
						DriverManager.getConnection(url, user, password);
					
				
					
				PreparedStatement preparedStatement = 
						connection.prepareStatement(sql);
				
				
			) { 
				
				preparedStatement.setString(1, "alice");
					
				ResultSet resultSet =
				        preparedStatement.executeQuery();
				
				
				while (resultSet.next()) {

				    String userName =
				            resultSet.getString("user_name");

				    String name =
				            resultSet.getString("name");

				    String surname =
				            resultSet.getString("surname");

				    System.out.println(
				            userName + " | " +
				            name + " | " +
				            surname
				    );
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}

