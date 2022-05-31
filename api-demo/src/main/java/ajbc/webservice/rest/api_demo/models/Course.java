package ajbc.webservice.rest.api_demo.models;

import java.util.ArrayList;
import java.util.List;

import ajbc.webservice.rest.api_demo_2.models.Student;

public class Course 
{
	private final long NUMBER;
	private static long counter = 100;
	private String name;
	private List<Student> students = new ArrayList();

	public Course() {
		this.NUMBER = generateNumber();
	}

	public Course(String name) {
		this.NUMBER = generateNumber();
		setName(name);
	}

	private synchronized long generateNumber() {
		return counter++;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getNUMBER() {
		return NUMBER;
	}

	public String getName() {
		return name;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
}
