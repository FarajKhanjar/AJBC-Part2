package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionManager;
import connection.ReadProperties;
import models.Item;
import models.Location;
import services.ItemDBService;
import services.LocationDBService;

public class RunnerLocation 
{
	public static void main(String[] args) throws IOException, SQLException 
	{
		ReadProperties read = new ReadProperties();
		read.connectToFile();

		try (Connection connection = new ConnectionManager(read.getServerAddress(), read.getPort(),
				read.getDatabaseName(), read.getServerName(), read.getUser(), read.getPassword()).createConnection();) {
			System.out.println("Connected");
			
			//Get Location by index from DB
			//for(int index = 100; index<=105; index++)
			//{
			//	 getAndPrintLocation(connection,index);
			//}


			// Adding Item to DB
			// addLocation(connection);

			
			// Delete employee from DB
			// deleteLocation(connection);

			// update item
			// updateLocation(connection);

		} catch (SQLException e) 
		{

			e.printStackTrace();
		}

	}
	
	private static void getAndPrintLocation(Connection connection, int index) 
	{
		LocationDBService dbService = new LocationDBService();
		Location location = dbService.getLocation(connection, index);
		System.out.println(location);

	}
	
	private static void addLocation(Connection connection) 
	{
		LocationDBService dbService = new LocationDBService();
		Location location_1 = new Location("Natania", "151515155555");
		Location location_2 = new Location("Zagreb",  "272727166666");
		Location location_3 = new Location("Baar Sheva",  "1515151777777");
		Location location_4 = new Location("Batumi", "42224545");

		 dbService.addLocation(connection,location_1);
		 dbService.addLocation(connection,location_2);
		 dbService.addLocation(connection,location_3);
		 dbService.addLocation(connection,location_4);

	}

	private static void deleteLocation(Connection connection) 
	{
		 LocationDBService dbService = new LocationDBService();
		 int INDEX_TO_DELETE = 109;
		 Location location = dbService.getLocation(connection, INDEX_TO_DELETE);
		 dbService.deleteLocation(connection, location);
		
	}

	
	private static void updateLocation(Connection connection) 
	{
		Location location_1 = new Location(100, "Jerusalem", "250250250");
		Location location_2 = new Location(101, "Rishon LeZion", "4444444444");
		Location location_3 = new Location(107, "Ashdod", "272727166666");

		LocationDBService dbService = new LocationDBService();
		dbService.updateLocation(connection, location_1);
		dbService.updateLocation(connection, location_2);
		dbService.updateLocation(connection, location_3);
	}

}
