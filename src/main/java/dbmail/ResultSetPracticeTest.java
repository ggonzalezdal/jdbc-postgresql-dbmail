package dbmail;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class ResultSetPracticeTest {

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
						"SELECT id, owner_name, balance " +
						"FROM accounts";
						
			
			try (
				
				Connection connection =
						DriverManager.getConnection(url, user, password);
					
				PreparedStatement preparedStatement = 
						connection.prepareStatement(sql);
				
			) { 
				
				ResultSet resultSet =
				        preparedStatement.executeQuery();
				
				while (resultSet.next()) {

				    int id =
				            resultSet.getInt(1);

				    String owner =
				            resultSet.getString("owner_name");

				    BigDecimal balance =
				            resultSet.getBigDecimal("balance");

					/*
					 * System.out.println( id + " | " + owner + " | " + balance );
					 */
				    System.out.printf(
				    		"%d | %s | %s%n",
				    		id,
				    		owner,
				    		balance
				    );
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}


