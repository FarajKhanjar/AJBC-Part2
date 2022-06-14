package main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionManager;
import connection.ReadProperties;
import models.Item;
import services.ItemDBService;

public class RunnerItem 
{
	public static void main(String[] args) throws IOException, SQLException 
	{
		ReadProperties read = new ReadProperties();
		read.connectToFile();

		try (Connection connection = new ConnectionManager(read.getServerAddress(), read.getPort(),
				read.getDatabaseName(), read.getServerName(), read.getUser(), read.getPassword()).createConnection();) {
			System.out.println("Connected");

			// Get Item by index from DB
			// for(int index = 1000; index<=1009; index++)
			// {
			// getAndPrintItem(connection,index);
			// }

			// Adding Item to DB
			// addItem(connection);

			// Delete employee from DB
			// deleteItem(connection);

			// update item
			// updateItem(connection);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private static void deleteItem(Connection connection) 
	{
		ItemDBService dbService = new ItemDBService();
		int INDEX_TO_DELETE = 1010;
		Item item = dbService.getItem(connection, INDEX_TO_DELETE);
		dbService.deleteItem(connection, item);

	}

	private static void addItem(Connection connection) 
	{
		ItemDBService dbService = new ItemDBService();
		// Item item_1 = new Item("Medical Latex Gloves", 25.5f, "2016-05-04", 150);
		// Item item_2 = new Item("Disposable Gown", 33.3f, "2021-06-01", 200);
		// Item item_3 = new Item("Shear-Cut", 15f, "2010-08-08", 80);
		// Item item_4 = new Item("First Aid Kit", 450, "2022-01-01", 115);
		Item item_5 = new Item("Eyewash Station", 112, "2021-01-01", 70);

		// dbService.addItem(connection,item_1);
		// dbService.addItem(connection,item_2);
		// dbService.addItem(connection,item_3);
		// dbService.addItem(connection,item_4);
		dbService.addItem(connection, item_5);

	}

	private static void getAndPrintItem(Connection connection, int index) 
	{
		ItemDBService dbService = new ItemDBService();
		Item item = dbService.getItem(connection, index);
		System.out.println(item);

	}

	private static void updateItem(Connection connection) 
	{
		Item item_4 = new Item(1008, "First Aid Kit", 550, "2022-01-02", 125);

		ItemDBService dbService = new ItemDBService();
		dbService.updateItem(connection, item_4);
	}

}
