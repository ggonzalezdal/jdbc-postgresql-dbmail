package dbmail;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

public class NullValueTest {

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
						"INSERT INTO users " +
						"(user_name, password, name, surname, age) " +
						"VALUES (?, ? ,? ,?, ?)";
			
			try (
				
				Connection connection =
						DriverManager.getConnection(url, user, password);
					
				PreparedStatement preparedStatement = 
						connection.prepareStatement(sql);
				
			) { 
				
				preparedStatement.setString(1, "trinity");
				preparedStatement.setString(2, "1234");
				preparedStatement.setString(3, "Trinity");
				preparedStatement.setString(4, "Unknown");
				preparedStatement.setInt(5, 39);
					
				int totalRows = 0;
				
				int rows1 =
				        preparedStatement.executeUpdate();
				totalRows += rows1;
				
				if (rows1 > 0) {

				    System.out.println(
				    		"Rows1 inserted: " + rows1);
				           
				}
				
				preparedStatement.setString(1, "agent");
				preparedStatement.setString(2, "1234");
				preparedStatement.setString(3, "Agent");
				preparedStatement.setString(4, "Smith");

				preparedStatement.setNull(
				    5,
				    java.sql.Types.INTEGER
				);
				

				int rows2 = preparedStatement.executeUpdate();
				totalRows += rows2;
				
				if (rows2 > 0) {

				    System.out.println(
				    		"Rows2 inserted: " + rows2);
				           
				}
				
				System.out.println(
					    "Rows inserted: " +
					    (rows1 + rows2)
					);
				
				System.out.println(
					    "Total rows inserted: " + totalRows
					);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}


