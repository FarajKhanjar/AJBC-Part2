package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import models.Item;
import models.ItemLocation;

public class ItemLocationDBService 
{
	public ItemLocation addItemLocation(Connection con, ItemLocation item_location) 
	{
		try (Statement statement = con.createStatement()) {
			/*String query = "Insert Into ItemLocation (itemId,locationId)"
					+ "values(%d,%d)".formatted(itemLocation.getItemId(), itemLocation.getLocationId());
					*/
			
			String query = "Insert Into ItemLocation (itemId,locationId)"
			        + "values(?,?)";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setInt(1, item_location.getItemId());
			preparedStatement.setInt(2, item_location.getLocationId());

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected == 1) {
				System.out.println("Success to add to DB! " + rowsAffected + " rows affected: ItemLocation #"+item_location.getItemId()+" - "+item_location.getLocationId());
				return item_location;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	

	public List<ItemLocation> getItemLocationByIdItem(Connection con, int itemId) 
	{
		ResultSet resultSet = null;
		ItemLocation item_location = null;
		List<ItemLocation> listItemLocation = new ArrayList<ItemLocation>();

		try (Statement statement = con.createStatement()) {
			String query = "select * from ItemLocation where itemId = %d".formatted(itemId);
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				item_location = new ItemLocation();
				item_location.setItemId(resultSet.getInt(1));
				item_location.setLocationId(resultSet.getInt(2));
				listItemLocation.add(item_location);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listItemLocation;
	}
	
	
	public List<ItemLocation> getItemLocationByIdLocation(Connection con, int locationId) 
	{
		ResultSet resultSet = null;
		ItemLocation item_location = null;
		List<ItemLocation> listItemLocation = new ArrayList<ItemLocation>();

		try (Statement statement = con.createStatement()) {
			String query = "select * from ItemLocation where locationId = %d".formatted(locationId);
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				item_location = new ItemLocation();
				item_location.setItemId(resultSet.getInt(1));
				item_location.setLocationId(resultSet.getInt(2));
				listItemLocation.add(item_location);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listItemLocation;
	}
	
	public ItemLocation getItemLocation(Connection con, int itemId, int locationId) 
	{
		ResultSet resultSet = null;
		ItemLocation item_location = null;

		try (Statement statement = con.createStatement()) {
			String query = "select * from ItemLocation where itemId = %d and locationId = %d".formatted(itemId,locationId);
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				item_location = new ItemLocation();
				item_location.setItemId(resultSet.getInt(1));
				item_location.setLocationId(resultSet.getInt(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return item_location;
	}
	
	
	public ItemLocation deleteItemLocation(Connection con, ItemLocation item_location) 
	{
		try (Statement statement = con.createStatement()) 
		{
			String query = "delete from itemLocation where itemId = %d and locationId = %d".formatted(
					item_location.getItemId(),item_location.getLocationId());
			int rowsAffected = statement.executeUpdate(query);
			System.out.println(rowsAffected + " rows affected");

			if (statement.executeUpdate(query) == 1) {
				return item_location;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public ItemLocation updateItemLocation(Connection con, int indexOldItem, int indexOldLocation, ItemLocation item_location) 
	{
		ItemLocation currentItem = getItemLocation(con, indexOldItem, indexOldLocation);
		if (!item_location.equals(currentItem))  //if they are the same, it doesn't matter to update
		{
			try (Statement statement = con.createStatement()) 
			{
				/*String query = "update ItemLocation set locationId=%d where itemId=%d"
						       .formatted(item_location.getLocationId(),item_location.getItemId());
				int rowsAffected = statement.executeUpdate(query);
				*/
				
				String query = "update ItemLocation set itemId=?, locationId=? where itemId = ? and locationId=?";
				PreparedStatement preparedStatement = con.prepareStatement(query);
				preparedStatement.setInt(1, item_location.getItemId());
				preparedStatement.setInt(2, item_location.getLocationId());
				preparedStatement.setInt(3, indexOldItem);
				preparedStatement.setInt(4, indexOldLocation);
				
				int rowsAffected = preparedStatement.executeUpdate();
				
				if (rowsAffected > 0) 
				{
					System.out.println("Success ! " + rowsAffected + " rows affected: ItemLocation #"+item_location.getItemId()+" - "+item_location.getLocationId());
					return item_location;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public void multiUpdateItemLocation(Connection con) 
	{
		
		try (Statement statement = con.createStatement()) 
		{
			//set connection auto commit to false
			con.setAutoCommit(false);
			
			//update-ItemLocation #1
			int indexOldItem = 1006;
			int indexNewItem = 1006;
			int indexOldLocation = 110;
			int indexNewLocation = 102;
			ItemLocation newItemLocation = new ItemLocation(indexNewItem, indexNewLocation);	
			
				String query = "update ItemLocation set itemId=?, locationId=? where itemId = ? and locationId=?";
				PreparedStatement preparedStatement = con.prepareStatement(query);
				
				preparedStatement.setInt(1, newItemLocation.getItemId());
				preparedStatement.setInt(2, newItemLocation.getLocationId());
				preparedStatement.setInt(3, indexOldItem);
				preparedStatement.setInt(4, indexOldLocation);
				preparedStatement.addBatch();
				
				//update-ItemLocation #2
				indexOldItem = 1008;
				indexNewItem = 1008;
				indexOldLocation = 108;
				indexNewLocation = 101;
				newItemLocation = new ItemLocation(indexNewItem, indexNewLocation);
				preparedStatement.setInt(1, newItemLocation.getItemId());
				preparedStatement.setInt(2, newItemLocation.getLocationId());
				preparedStatement.setInt(3, indexOldItem);
				preparedStatement.setInt(4, indexOldLocation);
				preparedStatement.addBatch();
		
				//update-ItemLocation #3
				indexOldItem = 1000;
				indexNewItem = 1008;
				indexOldLocation = 104;
				indexNewLocation = 110;
				newItemLocation = new ItemLocation(indexNewItem, indexNewLocation);
				preparedStatement.setInt(1, newItemLocation.getItemId());
				preparedStatement.setInt(2, newItemLocation.getLocationId());
				preparedStatement.setInt(3, indexOldItem);
				preparedStatement.setInt(4, indexOldLocation);
				preparedStatement.addBatch();
				
				int[] rowsAffected = preparedStatement.executeBatch();
				int index = 1;
				for(int i : rowsAffected)
				{
					if(i==0)
					{
						String errorMsg = "Check data, something wrong!";
						throw new SQLException(errorMsg);
					} 
					else
					{
						System.out.println(i+" Success to update-ItemLocation #"+index);
						index++;
					}
				}
				
				//if everything is ok - commit changes
				con.commit();

			} catch (SQLException e) {
				try {
					con.rollback();
					System.out.println("Rolling DB back "+e.getMessage());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	}
}