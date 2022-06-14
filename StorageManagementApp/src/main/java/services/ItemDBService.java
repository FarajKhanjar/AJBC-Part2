package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import models.Item;

public class ItemDBService 
{
	public Item addItem(Connection con, Item item) 
	{
		try (Statement statement = con.createStatement()) {
			String query = "Insert Into Item (itemName,unitPrice,purchaseDate,quantity)"
					+ "values('%s',%f,'%s',%d)".formatted(item.getItemName(), item.getUnitPrice(),
							LocalDate.parse(item.getPurchaseDate()), item.getQuantity());

			if (statement.executeUpdate(query) == 1) {
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
				String query = "update Item set itemName='%s', unitPrice=%f, purchaseDate='%s', quantity=%d where id = %d"
						       .formatted(item.getItemName(), item.getUnitPrice(),
						    	LocalDate.parse(item.getPurchaseDate()), item.getQuantity(), item.getId());

				int rowsAffected = statement.executeUpdate(query);
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
}