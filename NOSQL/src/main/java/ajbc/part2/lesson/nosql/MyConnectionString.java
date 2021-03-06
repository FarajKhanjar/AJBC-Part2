package ajbc.part2.lesson.nosql;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

import com.mongodb.ConnectionString;

public class MyConnectionString 
{
	private static String PROPERTIES_FILE = "nosql.properties";
	
	public static ConnectionString uri() 
	{
		Logger root = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.ERROR);
		
		Properties props = new Properties();
		FileInputStream fileStream = null;
		try {
			fileStream = new FileInputStream(PROPERTIES_FILE);
			props.load(fileStream);
		
		
		String user=props.getProperty("user");
				String	password=props.getProperty("password");
						String	cluster=props.getProperty("cluster");
								String	serverAddress=props.getProperty("serverAddress");
										String	param1=props.getProperty("param1");
												String	param2=props.getProperty("param2");
												
		String uri = "mongodb+srv://%s:%s@%s.%s/?%s&%s".formatted(user,password,cluster,serverAddress,param1,param2);
		
		return new ConnectionString(uri);
		
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
		finally 
		{
			try {
				fileStream.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		return null;
	}

}
