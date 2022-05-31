package ajbc.webservice.rest.api_demo.DB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import ajbc.webservice.rest.api_demo.models.Course;
import ajbc.webservice.rest.api_demo.models.Student;

public class MyDB 
{
	private static MyDB instance = null;
	private Map<Long, Student> students;
	private Map<Long, Course> courses;

	public static synchronized MyDB getInstance() 
	{
		if(instance==null)
			instance = new MyDB();
		return instance;
	}
	
	private MyDB() 
	{
		students = new HashMap<Long, Student>();
		// seeding the db
		seedStudents();
		seedCourses();
		updateStudentsWithCourse();
	}

	private void seedStudents() 
	{
//		List<Course> courses_1 = new ArrayList<Course>();
//		courses_1.add(new Course("Algoritms"));
//		List<Course> courses_2 = new ArrayList<Course>();
//		courses_2.add(new Course("Data Structures"));
//		courses_2.add(new Course("Complexity"));
//		List<Course> courses_3 = new ArrayList<Course>();
//		courses_3.add(new Course("Algoritms"));
//		courses_3.add(new Course("Complexity"));
//		List<Course> courses_4 = new ArrayList<Course>();
//		courses_4.add(new Course("Algoritms"));
//		courses_4.add(new Course("Complexity"));
//		courses_4.add(new Course("Data Structures"));
//		List<Course> courses_5 = new ArrayList<Course>();
//		courses_5.add(new Course("Data Structures"));
		
		List<Student> studentList = Arrays.asList(
				new Student("Moses", "OOfnik", 88.9),// courses_1),
				new Student("Happy", "Roller", 75.6),// courses_2), 
				new Student("Gabby", "Dice", 98.1),// courses_3),
				new Student("Charles", "Samson", 78.9),// courses_4), 
				new Student("Rachel", "Palace", 89.2)
		);
		
		students = studentList.stream()
	      .collect(Collectors.toMap(Student::getID, Function.identity()));
	}
	
	
	private void seedCourses() 
	{
		List<Course> courseList = Arrays.asList(
				new Course("Algoritms"),
				new Course("Data Structures"), 
				new Course("Complexity")
		);
		
		courses = courseList.stream()
	      .collect(Collectors.toMap(Course::getNUMBER, Function.identity()));
	}
	
	private void updateStudentsWithCourse() {
		long defaultCourseIndex = 100;
		
		for(Student student : students.values()) {
			student.addCourse(courses.get(defaultCourseIndex));
		}
	}
   
	public Map<Long, Student> getStudents()
	{
		return students;
	}
	
	public Map<Long, Course> getCourses()
	{
		return courses;
	}
}
