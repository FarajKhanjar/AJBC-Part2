package ajbc.webservice.rest.api_demo.resources;

import java.util.List;

import ajbc.webservice.rest.api_demo.DBservice.CourseDBservice;
import ajbc.webservice.rest.api_demo.models.Course;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("courses")
public class CourseResource 
{
	CourseDBservice courseDB = new CourseDBservice();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> getAllCourses()
	{
		return courseDB.getAllCourses();
	}
	
	
	@GET
	@Path("/{number}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course getCourseByNumber(@PathParam("number") long number)
	{
		return courseDB.getCourseByNumber(number);
	}
	
	//add new course 
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Course addCourse(Course course)
	{
		return courseDB.addCourse(course);
	}
	
	//update an existing course
	@PUT
	@Path("/{number}") //witch course to update?
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Course updateCourse(@PathParam("number") long number, Course course)
	{
		return courseDB.updateCourse(number,course);
	}
	
	
	//delete a course from map	
	@DELETE
	@Path("/{number}")
	@Produces(MediaType.APPLICATION_JSON)
	public Course deleteCourse(@PathParam("number") long number)
	{
		return courseDB.deleteCourse(number);
	}
	
//	@GET
//	public List<Course> getCourseByStudentId(@PathParam("id")long id){
//		return coursesDB.getCoursesByStudentId(id);
//		
//	}

}