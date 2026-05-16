package dbmail;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.util.List;

import java.util.Properties;

public class BatchInsertUsersTest {

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
						"(user_name, password, name, surname) " +
						"VALUES (?, ?, ?, ?)";
			
			/*
			 * String[][] users = { {"john", "1234", "John", "Smith"}, {"ema", "1234",
			 * "Ema", "Brown"}, {"lucas", "1234", "Lucas", "White"} };
			 */
			
			List<User> users = List.of(
				
				new User(
						"mike",
						"1234",
						"Michael",
						"Jordan"
				),
				
				new User(
						"2Pac",
						"1234",
						"Tupac",
						"Shakur"
				),
				
				new User(
						"chest",
						"1234",
						"Chester",
						"Cano"
				),
				
				new User(
						"airgon",
						"1234",
						"Gon",
						"Gon"
				),
				
				new User(
						"fb",
						"1234",
						"Fuse",
						"Boy"
				),
				
				new User(
						"lauryn",
						"1234",
						"Lauryn",
						"Hill"
				),
				
				new User(
						"jp",
						"1234",
						"Jackson",
						"Pollock"
				)
			);
			
			try (
				
				Connection connection =
						DriverManager.getConnection(url, user, password);
					
				PreparedStatement preparedStatement = 
						connection.prepareStatement(sql);
				
			) { 
				
				/*for (String[] userData : users) {

				    preparedStatement.setString(1, userData[0]);
				    preparedStatement.setString(2, userData[1]);
				    preparedStatement.setString(3, userData[2]);
				    preparedStatement.setString(4, userData[3]);*/
				
				for (User currentUser : users) {
					
					preparedStatement.setString(1, currentUser.getUserName());
				    preparedStatement.setString(2, currentUser.getPassword());
				    preparedStatement.setString(3, currentUser.getName());
				    preparedStatement.setString(4, currentUser.getSurname());

				    int rows =
				        preparedStatement.executeUpdate();

				    System.out.println(
				        "Rows inserted: " + rows
				    );
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}



