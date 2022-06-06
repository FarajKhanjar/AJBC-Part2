package ajbc.webservice.rest.api_demo.DBservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ajbc.webservice.rest.api_demo.DB.MyDB;
import ajbc.webservice.rest.api_demo.models.Course;

public class CourseDBservice 
{
	private MyDB dataBase;
	private Map<Long, Course> courses;
	
	public CourseDBservice()
	{
		dataBase=MyDB.getInstance();
		courses = dataBase.getCourses();
	}
	
	//returns all the courses from the DB as a list
	public List<Course> getAllCourses()
	{
		return new ArrayList<Course>(courses.values());
	}
	
	//returns course from the DB by his id
	public Course getCourseByNumber(long number)
	{
		return courses.get(number);
	}
	
	//add course to DB
	public Course addCourse(Course course)
	{
		courses.put(course.getNUMBER(), course);
		return course;
	}
	
	//update existing course
	public Course updateCourse(long number, Course courseDetails)
	{
		if (courses.containsKey(number)) {
			Course currCourse = courses.get(number);
			currCourse.setName(courseDetails.getName());
			currCourse.setStudents(courseDetails.getStudents());

			courses.put(number, currCourse);
			return currCourse;
		}

		return null;
	
	}
	
	
	//delete a course from map	
	public Course deleteCourse(long number) {
		return courses.remove(number);
	}
	
//	
//	public Student getStudentById(long id) {
//		return students.get(id);
//	}
//	
//	//add student to DB
//	public Student addStudent(Student student) {
//		students.put(student.getID(), student);
//		return student;
//	}
//	
//
//	public List<Course> getCoursesByStudentId(long id) {
//	}
//	

}
