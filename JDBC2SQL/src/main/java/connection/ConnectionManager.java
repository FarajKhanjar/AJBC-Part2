package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager 
{
	private String url;
	private String serverAddress;
	private String port;
	private String databaseName;
	private String serverName;
	
	private String userName;
	private String password;
	
	public ConnectionManager(String serverAddress, String port, String databaseName
			                 , String serverName, String userName, String password) 
	{
		setServerAddress(serverAddress);
		setPort(port);
		setDatabaseName(databaseName);
		setServerName(serverName);
		
		this.userName =userName;
		this.password = password;
		buildUrl();
	}
	
	private void buildUrl() 
	{
		this.url = "jdbc:sqlserver://" + this.serverAddress + ":" + this.port +
				";databaseName=" + this.databaseName + ";servername=" + this.serverName +
				";" + "encrypt=false";

	}

	public Connection createConnection() throws SQLException
	{
		return DriverManager.getConnection(url, userName, password);

	}
	
	public void setServerAddress(String serverAddress) 
	{
		this.serverAddress = serverAddress;
	}

	public void setPort(String port) 
	{
		this.port = port;
	}

	public void setDatabaseName(String databaseName) 
	{
		this.databaseName = databaseName;
	}

	public void setServerName(String serverName) 
	{
		this.serverName = serverName;
	}
	
	public String getUrl() {
		return url;
	}

}
