package dbmail;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Properties;

public class GenerateKeyTest {

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
						"INSERT INTO accounts " +
						"(balance, user_name) " +
						"VALUES (?, ?) " +
						"RETURNING id, balance, user_name, created_at";
			
			try (
				
				Connection connection =
						DriverManager.getConnection(url, user, password);
					
				PreparedStatement preparedStatement = 
						connection.prepareStatement(sql);
				
			) { 
				
				preparedStatement.setBigDecimal(1, new BigDecimal("3000"));
				
				preparedStatement.setString(2,  "trinity");
					
				ResultSet resultSet =
				        preparedStatement.executeQuery();
				
				if (resultSet.next()) {
					
					int generatedId = resultSet.getInt("id");
					
					String userName =
					        resultSet.getString("user_name");

					BigDecimal balance =
					        resultSet.getBigDecimal("balance");

					
					java.sql.Timestamp createdAt =
							resultSet.getTimestamp("created_at");

					System.out.println(
							"Generated account id: " +
							        generatedId +
							        " | User: " +
							        userName +
							        " | Balance: " +
							        balance +
							        " | Created at: " +
							        createdAt
				    );
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}


