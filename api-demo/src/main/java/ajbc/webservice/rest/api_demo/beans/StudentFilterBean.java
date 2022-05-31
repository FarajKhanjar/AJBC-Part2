package ajbc.webservice.rest.api_demo.beans;

import jakarta.ws.rs.QueryParam;

public class StudentFilterBean 
{
	@QueryParam("average") double average;
	@QueryParam("minAverage") double minAverage;
	@QueryParam("maxAverage") double maxAverage;
	

	public double getMinAverage() {
		return minAverage;
	}

	public void setMinAverage(double minAverage) {
		this.minAverage = minAverage;
	}

	public double getMaxAverage() {
		return maxAverage;
	}

	public void setMaxAverage(double maxAverage) {
		this.maxAverage = maxAverage;
	}

	public void setAverage(double average) {
		this.average = average;
	}
	
	public double getAverage() {
		return average;
	}
	
	

}
