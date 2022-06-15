package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionManager;
import connection.ReadProperties;
import models.Item;
import models.ItemLocation;
import services.ItemDBService;
import services.ItemLocationDBService;

public class RunnerItemLocation 
{
	public static void main(String[] args) throws IOException, SQLException 
	{
		ReadProperties read = new ReadProperties();
		read.connectToFile();

		try (Connection connection = new ConnectionManager(read.getServerAddress(), read.getPort(),
				read.getDatabaseName(), read.getServerName(), read.getUser(), read.getPassword()).createConnection();) {
			System.out.println("Connected to ItemLocation Table");

			// Get ItemLocation by index of item from DB
			// int index = 1005;
			// getAndPrintItemLocationByIdItem(connection,index);
			
			// Get ItemLocation by index of location from DB
			//int index = 102;
			// getAndPrintItemLocationByIdLocation(connection,index);

			// Adding ItemLocation to DB
			// addItemLocation(connection);

			// Delete ItemLocation from DB
			// deleteItemLocation(connection);

			// update ItemLocation
			// updateItemLocation(connection);
			

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private static void getAndPrintItemLocationByIdItem(Connection connection, int index) 
	{
		List<ItemLocation> listItemLocation = new ArrayList<ItemLocation>();
		ItemLocationDBService dbService = new ItemLocationDBService();
		listItemLocation = dbService.getItemLocationByIdItem(connection, index);
		printListValues(listItemLocation);

	}
	
	private static void getAndPrintItemLocationByIdLocation(Connection connection, int index) 
	{
		List<ItemLocation> listItemLocation = new ArrayList<ItemLocation>();
		ItemLocationDBService dbService = new ItemLocationDBService();
		listItemLocation = dbService.getItemLocationByIdLocation(connection, index);
		printListValues(listItemLocation);

	}
	
	private static void printListValues(List<ItemLocation> listItemLocation) 
	{
		int index = 1;		
		for (ItemLocation oneItemLocation : listItemLocation) 
		{
			System.out.println(index + ") " + oneItemLocation);
			index++;
		}	
	}
	
	private static void deleteItemLocation(Connection connection) 
	{
		ItemLocationDBService dbService = new ItemLocationDBService();
		int INDEX_ITEM_TO_DELETE = 1008;
		int INDEX_LOCATION_TO_DELETE = 108;
		ItemLocation item_location = dbService.getItemLocation(connection, INDEX_ITEM_TO_DELETE, INDEX_LOCATION_TO_DELETE);
		dbService.deleteItemLocation(connection, item_location);

	}

	private static void addItemLocation(Connection connection) 
	{
		ItemLocationDBService dbService = new ItemLocationDBService();

		ItemLocation item_location_1 = new ItemLocation(1011,108);
		ItemLocation item_location_2 = new ItemLocation(1011,110);

		dbService.addItemLocation(connection,item_location_1);
		dbService.addItemLocation(connection,item_location_2);
	}

	private static void updateItemLocation(Connection connection) 
	{
		int indexOldItem = 1008;
		int indexNewItem = 1005;
		int indexOldLocation = 102;
		int indexNewLocation = 102;
		ItemLocation item_location_1 = new ItemLocation(indexNewItem, indexNewLocation);

		ItemLocationDBService dbService = new ItemLocationDBService();
		dbService.updateItemLocation(connection, indexOldItem, indexOldLocation, item_location_1);
	}
}