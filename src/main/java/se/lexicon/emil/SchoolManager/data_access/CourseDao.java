package se.lexicon.emil.SchoolManager.data_access;

import java.time.LocalDate;
import java.util.List;

import se.lexicon.emil.SchoolManager.data.Course;

public interface CourseDao {
	
	Course addCourse(Course course);
	Course getByID(String id);
	
	List<Course> getByName(String courseName);
	List<Course> getByDate(LocalDate startDate);
	List<Course> getAllCourses();
	
	boolean removeCourse(Course course);
}
