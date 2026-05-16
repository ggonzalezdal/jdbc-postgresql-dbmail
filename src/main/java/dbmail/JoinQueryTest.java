package dbmail;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.Properties;

public class JoinQueryTest {

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
						"SELECT " +
						"u.user_name AS username, " +
						"u.name AS first_name, " +
						"u.surname AS surname, " +
						"a.balance AS account_balance " +
						"FROM accounts a " +
						"JOIN users u " +
						"ON a.user_name = u.user_name";
			
			try (
				
				Connection connection =
						DriverManager.getConnection(url, user, password);
					
				PreparedStatement preparedStatement = 
						connection.prepareStatement(sql);
				
			) { 
				
				ResultSet resultSet =
				        preparedStatement.executeQuery();
				
				while (resultSet.next()) {

				    String userName =
				            resultSet.getString("username");

				    String name =
				            resultSet.getString("first_name");

				    String surname =
				            resultSet.getString("surname");
				    
				    BigDecimal balance = resultSet.getBigDecimal("account_balance");
				    

				    System.out.println(
				    		 "Username: " + userName +
				    	     " | Name: " + name +
				    	     " | Surname: " + surname +
				    	     " | Balance: " + balance
				    );
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}

