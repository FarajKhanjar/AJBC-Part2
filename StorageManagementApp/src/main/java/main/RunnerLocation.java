package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import connection.ConnectionManager;
import connection.ReadProperties;
import models.Item;
import models.Location;
import services.ItemDBService;
import services.LocationDBService;

public class RunnerLocation 
{
	protected static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException, SQLException 
	{
		ReadProperties read = new ReadProperties();
		read.connectToFile();

		try (Connection connection = new ConnectionManager(read.getServerAddress(), read.getPort(),
				read.getDatabaseName(), read.getServerName(), read.getUser(), read.getPassword()).createConnection();) {
			System.out.println("Connected to Location Table");
			
			//Get location by index from DB
			//getAndPrintLocation(connection);


			// Adding location to DB
			// addLocation(connection);

			
			// Delete location from DB
			// deleteLocation(connection);

			// update location
			// updateLocation(connection);
			
			
            //-------------------------{PreparedStatement}------------------------
			
			// Get location by index from DB
			// getAndPrintLocation(connection);
			
			// update location
			// updateLocation(connection);
			
			// Adding location to DB
			// addLocation(connection);
			
			// Multi-updating Location
			// multiUpdateLocation(connection);

		} catch (SQLException e) 
		{

			e.printStackTrace();
		}

	}
	
	private static void getAndPrintLocation(Connection connection) 
	{
		//for(int index = 100; index<=108; index++)
		//{
		//	 Location location = dbService.getLocation(connection, index);
		//   System.out.println(location);
		//}
		
		System.out.println("Enter an Location id to get all details: ");
		int index = Integer.parseInt(scanner.next());
		LocationDBService dbService = new LocationDBService();
		Location location = dbService.getLocation(connection, index);
		System.out.println(location);

	}
	
	private static void addLocation(Connection connection) 
	{
		LocationDBService dbService = new LocationDBService();
		//Location location_1 = new Location("Natania", "151515155555");
		//Location location_2 = new Location("Zagreb",  "272727166666");
		//Location location_3 = new Location("Baar Sheva",  "1515151777777");
		Location location_4 = new Location("Jerusalem", "72895425");

		 // dbService.addLocation(connection,location_1);
		 // dbService.addLocation(connection,location_2);
		 // dbService.addLocation(connection,location_3);
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
		Location location_1 = new Location(100, "Afula", "262177890");
		//Location location_2 = new Location(101, "Rishon LeZion", "4444444444");
		//Location location_3 = new Location(107, "Ashdod", "272727166666");

		LocationDBService dbService = new LocationDBService();
		dbService.updateLocation(connection, location_1);
		//dbService.updateLocation(connection, location_2);
		//dbService.updateLocation(connection, location_3);
	}
	
	private static void multiUpdateLocation(Connection connection) 
	{
		LocationDBService dbService = new LocationDBService();
		dbService.multiUpdateLocation(connection);
	}

}
