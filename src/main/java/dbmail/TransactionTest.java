package dbmail;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.math.BigDecimal;

public class TransactionTest {

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
			
			String withdrawSql = 
						"UPDATE accounts " +
						"SET balance = balance - ? " +
						"WHERE id = ?";
			
			String depositSql = 
					"UPDATE accounts " +
					"SET balance = balance + ? " +
					"WHERE id = ?";
			
			try (Connection connection =
						DriverManager.getConnection(url, user, password)) {
			
				connection.setAutoCommit(false);
				
				try (
						
					PreparedStatement withdrawStatement = 
						connection.prepareStatement(withdrawSql);	
						
					PreparedStatement depositStatement = 
						connection.prepareStatement(depositSql);	
						
				) {
					
					//boolean simulateFailure = true;
					
					withdrawStatement.setBigDecimal(1, new BigDecimal("100.00"));
					withdrawStatement.setInt(2,  1);
					withdrawStatement.executeUpdate();
					
					/*
					 * if (simulateFailure) { throw new Exception("Simulated transaction failure");
					 * }
					 */
					
					depositStatement.setBigDecimal(1, new BigDecimal("100.00"));
					depositStatement.setInt(2,  2);
					depositStatement.executeUpdate();
					
					connection.commit();
					
					System.out.println("Transaction completed successfully.");
					
				} catch (Exception e) {
					
					connection.rollback();
					
					System.out.println("Transaction failed. Rollback executed.");
					e.printStackTrace();
				}
			}	
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}


