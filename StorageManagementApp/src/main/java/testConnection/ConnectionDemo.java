package testConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {

	public static void main(String[] args) {
		
		String conStr = "jdbc:sqlserver://localhost:1433;databaseName=Storage-Management-App;servername=LAPTOP-5CDCFF81;encrypt=false;";
		Connection connection = null;
		try {
			
			connection = DriverManager.getConnection(conStr,"faraj276","f12345");
			if(connection.isValid(5));
			System.out.println("Connected to database!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally 
		{
			try {
				connection.close();
			}  catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

	}

}
