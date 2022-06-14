package models;

import java.util.Objects;

public class Location 
{
	private int locationId;
	private String locationName;
	private String AccessCode;
	
	public Location()
	{
		
	}
	
	public Location(String locationName, String AccessCode) 
	{
		this.locationName = locationName;
		this.AccessCode = AccessCode;
	}
	
	public Location(int locationId, String locationName, String AccessCode) 
	{
		this.locationId = locationId;
		this.locationName = locationName;
		this.AccessCode = AccessCode;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getAccessCode() {
		return AccessCode;
	}

	public void setAccessCode(String accessCode) {
		AccessCode = accessCode;
	}

	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", locationName=" + locationName + ", AccessCode=" + AccessCode
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(AccessCode, locationId, locationName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return Objects.equals(AccessCode, other.AccessCode) && locationId == other.locationId
				&& Objects.equals(locationName, other.locationName);
	}
	
	
	

}
