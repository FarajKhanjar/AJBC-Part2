package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import models.Employee;

public class EmployeeDBService 
{
	public Employee addEmployee(Connection con, Employee employee) 
	{

		try (Statement statement = con.createStatement()) {
			String query = "Insert Into Employees (lastName, firstName, email, department, salary)"
					+ "values('%s','%s','%s','%s',%f)".formatted(employee.getLastName(), employee.getFirstName(),
							employee.getEmail(), employee.getDepartment(), employee.getSalary());

			if (statement.executeUpdate(query) == 1) {
				return employee;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Employee getEmployee(Connection con, int id) 
	{
		ResultSet resultSet = null;
		Employee emp = null;

		try (Statement statement = con.createStatement()) {
			String query = "select * from employees where id = %d".formatted(id);
			resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				emp = new Employee();
				emp.setId(resultSet.getInt(1));
				emp.setLastName(resultSet.getString(2));
				emp.setFirstName(resultSet.getString(3));
				emp.setEmail(resultSet.getString(4));
				emp.setDepartment(resultSet.getString(5));
				emp.setSalary(resultSet.getFloat(6));
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
		return emp;
	}

	public Employee updateEmployee(Connection con, Employee employee) 
	{
		Employee currentEmp = getEmployee(con, employee.getId());
		if (!employee.equals(currentEmp))  //if they are the same, it doesn't matter to update
		{
			try (Statement statement = con.createStatement()) 
			{
				String query = "update employees set lastName='%s', firstName='%s', email='%s', department='%s', salary=%f where id = %d"
						       .formatted(employee.getLastName(), employee.getFirstName(), employee.getEmail(),
								employee.getDepartment(), employee.getSalary(), employee.getId());

				int rowsAffected = statement.executeUpdate(query);
				if (rowsAffected > 0) 
				{
					System.out.println("Success ! " + rowsAffected + " rows affected: employee #"+employee.getId());
					return employee;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Employee deleteEmployee(Connection con, Employee employee) 
	{

		try (Statement statement = con.createStatement()) {
			String query = "delete from employees where id = %d ".formatted(employee.getId());
			int rowsAffected = statement.executeUpdate(query);
			System.out.println(rowsAffected + " rows affected");

			if (statement.executeUpdate(query) == 1) {
				return employee;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}