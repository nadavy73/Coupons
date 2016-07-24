package Functions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc_connection 
{
	public Connection JdbcConnection() throws SQLException
	{	
		
			//1. Load the jdbc driver
					try {
					Class.forName("org.sqlite.JDBC");
						
					} catch (ClassNotFoundException e) {
						
						e.printStackTrace();
					}
					
					System.out.println("*********Driver Loaded*****");
				
				//2. get connections
					
					String url= "jdbc:sqlite:C:/Users/Ofer/Desktop/John Bryce/Coupon_System.db";
						Connection con = 
								DriverManager.getConnection(url, "root", "password");
						System.out.println("********Connection succeeded*****");
						return con;
					
		}	
	
}
