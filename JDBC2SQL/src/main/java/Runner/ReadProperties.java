package Runner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import connection.ConnectionManager;

public class ReadProperties 
{

	private String user;
	private String password;
	private String serverAddress;
	private String port;
	private String databaseName;
	private String serverName;
	
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;

	private static final String PROPERTIES_FILE = "demo.properties";
	FileInputStream fileInputStream = null;
	Properties props = null;
	public static ConnectionManager connectStr;

	public ReadProperties() throws IOException
	{
		props = new Properties();
		fileInputStream = new FileInputStream(PROPERTIES_FILE);
		props.load(fileInputStream);
		
		

	}
	
	public void connectToFile() throws SQLException, IOException
	{
		this.user = props.getProperty("user");
		this.password = props.getProperty("password");
		this.serverAddress = props.getProperty("server_address");
		this.port = props.getProperty("port");
		this.databaseName = props.getProperty("db_name");
		this.serverName = props.getProperty("server_name");
		
		try {
		connectStr = new ConnectionManager(serverAddress, port, databaseName, serverName,user,password);
		
			connection = DriverManager.getConnection(connectStr.getUrl(), user, password);
			System.out.println("Connected to SQL Server " + connection.isValid(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			connection.close();
			fileInputStream.close();
		}
		
		

		
	}
	
	

	public String getServerAddress() {
		return serverAddress;
	}

	public String getPort() {
		return port;
	}

	public String getDatabaseName() {
		return databaseName;
	}

	public String getServerName() {
		return serverName;
	}
	
	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	
	

}