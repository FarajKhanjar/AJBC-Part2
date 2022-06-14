package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Location;

public class LocationDBService 
{
	public Location addLocation(Connection con, Location location) 
	{
		try (Statement statement = con.createStatement()) {
			String query = "Insert Into Location (locationName,accessCode)"
					+ "values('%s','%s')".formatted(location.getLocationName(), location.getAccessCode());

			if (statement.executeUpdate(query) == 1) {
				return location;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Location getLocation(Connection con, int locationId) 
	{
		ResultSet resultSet = null;
		Location location = null;

		try (Statement statement = con.createStatement()) {
			String query = "select * from Location where locationId = %d".formatted(locationId);
			resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				location = new Location();
				location.setLocationId(resultSet.getInt(1));
				location.setLocationName(resultSet.getString(2));
				location.setAccessCode(resultSet.getString(3));
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
		return location;
	}
	
	public Location deleteLocation(Connection con, Location location) 
	{
		try (Statement statement = con.createStatement()) 
		{
			String query = "delete from Location where locationId = %d ".formatted(location.getLocationId());
			int rowsAffected = statement.executeUpdate(query);
			System.out.println(rowsAffected + " rows affected");

			if (statement.executeUpdate(query) == 1) {
				return location;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Location updateLocation(Connection con, Location location) 
	{
		Location currentLocation = getLocation(con, location.getLocationId());
		if (!location.equals(currentLocation))  //if they are the same, it doesn't matter to update
		{
			try (Statement statement = con.createStatement()) 
			{
				String query = "update Location set locationName='%s', accessCode='%s' where locationId = %d"
						       .formatted(location.getLocationName(), location.getAccessCode(), location.getLocationId());

				int rowsAffected = statement.executeUpdate(query);
				if (rowsAffected > 0) 
				{
					System.out.println("Success ! " + rowsAffected + " rows affected: Location #"+location.getLocationId());
					return location;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}