package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

import models.Item;

public class ItemDBService 
{
	protected static Scanner scanner = new Scanner(System.in);

	public Item addItem(Connection con, Item item) 
	{
		try (Statement statement = con.createStatement()) 
		{
			/*String query = "Insert Into Item (itemName,unitPrice,purchaseDate,quantity)"
					+ "values('%s',%f,'%s',%d)".formatted(item.getItemName(), item.getUnitPrice(),
							LocalDate.parse(item.getPurchaseDate()), item.getQuantity());
							*/
			
			String query = "Insert Into Item (itemName,unitPrice,purchaseDate,quantity)"
			        + "values(?,?,?,?)";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			preparedStatement.setString(1, item.getItemName());
			preparedStatement.setFloat(2, item.getUnitPrice());
			preparedStatement.setDate(3, Date.valueOf(LocalDate.parse(item.getPurchaseDate())));
			preparedStatement.setInt(4, item.getQuantity());

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected == 1) {
				System.out.println("Success to add to DB! " + rowsAffected + " rows affected: Item #"+item.getId());
				return item;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Item getItem(Connection con, int id) 
	{
		ResultSet resultSet = null;
		Item item = null;

		try (Statement statement = con.createStatement()) {
			String query = "select * from item where id = %d".formatted(id);
			resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				item = new Item();
				item.setId(resultSet.getInt(1));
				item.setItemName(resultSet.getString(2));
				item.setUnitPrice(resultSet.getFloat(3));
				item.setPurchaseDate(resultSet.getString(4).toString());
				item.setQuantity(resultSet.getInt(5));
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
		return item;
	}
	
	public Item deleteItem(Connection con, Item item) 
	{
		try (Statement statement = con.createStatement()) 
		{
			String query = "delete from Item where id = %d ".formatted(item.getId());
			int rowsAffected = statement.executeUpdate(query);
			System.out.println(rowsAffected + " rows affected");

			if (statement.executeUpdate(query) == 1) {
				return item;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Item updateItem(Connection con, Item item) 
	{
		Item currentItem = getItem(con, item.getId());
		if (!item.equals(currentItem))  //if they are the same, it doesn't matter to update
		{
			try (Statement statement = con.createStatement()) 
			{
				/*String query = "update Item set itemName='%s', unitPrice=%f, purchaseDate='%s', quantity=%d where id = %d"
						       .formatted(item.getItemName(), item.getUnitPrice(),
						    	LocalDate.parse(item.getPurchaseDate()), item.getQuantity(), item.getId());
						    	*/
				
				String query = "update Item set itemName=?, unitPrice=?, purchaseDate=?, quantity=? where id = ?";
				PreparedStatement preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, item.getItemName());
				preparedStatement.setFloat(2, item.getUnitPrice());
				preparedStatement.setDate(3, Date.valueOf(LocalDate.parse(item.getPurchaseDate())));
				preparedStatement.setInt(4, item.getQuantity());
				preparedStatement.setInt(5, item.getId());
				
				int rowsAffected = preparedStatement.executeUpdate();
				if (rowsAffected > 0) 
				{
					System.out.println("Success ! " + rowsAffected + " rows affected: Item #"+item.getId());
					return item;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	////////////
	
	public Item getItemById(Connection con) 
	{
		ResultSet resultSet = null;
		Item item = null;

		try (Statement statement = con.createStatement()) 
		{
			String query = "select * from item where id = ?";
			PreparedStatement preparedStatement = con.prepareStatement(query);
			System.out.println("Enter an item id to get all details: ");
			int id = Integer.parseInt(scanner.next());
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				item = new Item();
				item.setId(resultSet.getInt(1));
				item.setItemName(resultSet.getString(2));
				item.setUnitPrice(resultSet.getFloat(3));
				item.setPurchaseDate(resultSet.getString(4).toString());
				item.setQuantity(resultSet.getInt(5));
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
		return item;
	}
	
	
	public void multiUpdateItem(Connection con) 
	{
		
		try (Statement statement = con.createStatement()) 
		{
			//set connection auto commit to false
			con.setAutoCommit(false);
			
			//update-Item #1
			int currentItemIndex = 1000;
			String newItemName = "Oxygen Tanks";
			float newUnitPrice = 300f;
			String newPurchaseDate = "2015-02-02";
			int newQuantity = 15;
			
				String query = "update Item set itemName=?, unitPrice=?, purchaseDate=?, quantity=? where id = ?";
				PreparedStatement preparedStatement = con.prepareStatement(query);
				preparedStatement.setString(1, newItemName);
				preparedStatement.setFloat(2, newUnitPrice);
				preparedStatement.setDate(3, Date.valueOf(LocalDate.parse(newPurchaseDate)));
				preparedStatement.setInt(4, newQuantity);
				preparedStatement.setInt(5, currentItemIndex);
				preparedStatement.addBatch();
				
				//update-Item #2
				currentItemIndex = 1011;
				newItemName = "Eyewash Station";
				newUnitPrice = 112;
				newPurchaseDate = "2021-01-01";
				newQuantity = 77;
				preparedStatement.setString(1, newItemName);
				preparedStatement.setFloat(2, newUnitPrice);
				preparedStatement.setDate(3, Date.valueOf(LocalDate.parse(newPurchaseDate)));
				preparedStatement.setInt(4, newQuantity);
				preparedStatement.setInt(5, currentItemIndex);
				preparedStatement.addBatch();
		
				//update-Item #3
				currentItemIndex = 1008;
				newItemName = "First Aid Kit";
				newUnitPrice = 550;
				newPurchaseDate = "2022-02-02";
				newQuantity = 140;
				preparedStatement.setString(1, newItemName);
				preparedStatement.setFloat(2, newUnitPrice);
				preparedStatement.setDate(3, Date.valueOf(LocalDate.parse(newPurchaseDate)));
				preparedStatement.setInt(4, newQuantity);
				preparedStatement.setInt(5, currentItemIndex);
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
						System.out.println(i+" Success to update-Item #"+index);
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