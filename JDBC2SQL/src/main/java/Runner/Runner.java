package Runner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import connection.ConnectionManager;
import models.Employee;
import services.EmployeeDBService;

public class Runner 
{
	public static void main(String[] args) throws IOException, SQLException 
	{
		ReadProperties read = new ReadProperties();
		read.connectToFile();

		try (Connection connection = new ConnectionManager(read.getServerAddress(), read.getPort(),
				read.getDatabaseName(), read.getServerName(), read.getUser(), read.getPassword()).createConnection();) 
		{
			System.out.println("Connected");

			Employee emp_1 = new Employee("Doron", "Rainer", "DR@gmail.com", "Computer", 15000f);
			Employee emp_2 = new Employee("Ariel", "Taub", "AR@hotmail.com", "Electronic", 18000f);
			Employee emp_3 = new Employee("Zach", "Kaplan", "zzkk@gmail.com", "Music", 10000f);


			// Adding Employee to DB
			/*EmployeeDBService dbService = new EmployeeDBService();
			dbService.addEmployee(connection,emp_1);
			dbService.addEmployee(connection,emp_2);
			dbService.addEmployee(connection,emp_3);
			*/

			// Get Employee by index from DB
			for(int index = 1000; index<=1011; index++) 
			{
				getAndPrintEmp(connection,index);
			}
			
			
			//Delete employee from DB
			/*EmployeeDBService dbService = new EmployeeDBService();
			int INDEX_TO_DELETE = 1026;
			Employee emp = dbService.getEmployee(connection, INDEX_TO_DELETE);
			dbService.deleteEmployee(connection,emp);
			*/
			
			//update emp
			//updateEmp(connection);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void getAndPrintEmp(Connection connection, int index) 
	{
		EmployeeDBService dbService = new EmployeeDBService();
		Employee emp = dbService.getEmployee(connection, index);
		System.out.println(emp);
		
	}
	
	private static void updateEmp(Connection connection) 
	{
		Employee emp_1 = new Employee(1024,"Doron", "Mdali", "DR@walla.com", "Computer", 15000f);
		Employee emp_2 = new Employee(1025,"Adar", "Taub", "AR@gmail.com", "Electronic", 18000f);
		Employee emp_3 = new Employee(1026,"Jack", "Kaplan", "zzkk@gmail.com", "Music", 10000f);
		
		EmployeeDBService dbService = new EmployeeDBService();
		dbService.updateEmployee(connection,emp_1);
		dbService.updateEmployee(connection,emp_2);
		dbService.updateEmployee(connection,emp_3);
		
		
	}
}
