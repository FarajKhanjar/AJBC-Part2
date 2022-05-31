package ajbc.webservice.rest.api_demo.DBservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ajbc.webservice.rest.api_demo.DB.MyDB;
import ajbc.webservice.rest.api_demo.models.Student;

public class StudentDBservice 
{
	private MyDB dataBase;
	private Map<Long, Student> students;
	
	public StudentDBservice()
	{
		dataBase=MyDB.getInstance();
		students = dataBase.getStudents();
	}
	
	//returns all the students from the DB as a list
	public List<Student> getAllStudents()
	{
		return new ArrayList<Student>(students.values());
	}
	
	//returns student from the DB by his id
	public Student getStudentById(long id)
	{
		return students.get(id);
	}
	
	//add student to DB
	public Student addStudent(Student student)
	{
		students.put(student.getID(), student);
		return student;
	}
	
	//update existing student
	public Student updateStudent(long id, Student studentDetails)
	{
		if(students.containsKey(id))
		{
			Student currentStudent = students.get(id);	

			currentStudent.setFirstName(studentDetails.getFirstName());
			currentStudent.setLastName(studentDetails.getLastName());
			currentStudent.setAverage(studentDetails.getAverage());
			//currentStudent.setCourses(studentDetails.getCourses());
			students.put(id, currentStudent);
			
			return currentStudent;
		}
		return null;
	}
	
	
	//delete a student from map	
	public Student deleteStudent(long id)
	{
		return students.remove(id);
	}
	
	public List<Student> getStudentsByAverage(double average) 
	{
		return students.values().stream().filter(s -> s.getAverage() > average).collect(Collectors.toList());
	}

	public List<Student> getStudentsByAverage(double minAverage, double maxAverage) 
	{
		return students.values().stream().filter(s -> s.getAverage() >= minAverage && s.getAverage() <= maxAverage)
				.collect(Collectors.toList());
	}

}
