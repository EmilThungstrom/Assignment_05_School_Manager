package se.lexicon.emil.SchoolManager.data_access;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import se.lexicon.emil.SchoolManager.data.Course;
import se.lexicon.emil.SchoolManager.data.Student;

public class CourseDaoList implements CourseDao {

	private List<Course> courses;
	
	public CourseDaoList() {
		courses = new LinkedList<>();
	}
	
	@Override
	public Course addCourse(Course course) {
		courses.add(course);
		return course;
	}

	@Override
	public Course getByID(String id) {
		for(Course course : courses) {
			if(course.getId().equals(id))
				return course;
		}
		return null;
	}

	@Override
	public List<Course> getByName(String courseName) {
		List<Course> retCourses = new LinkedList<>();
		
		for(Course course : courses) {
			if(course.getName().contains(courseName))
				retCourses.add(course);
		}
		return retCourses;
	}

	@Override
	public List<Course> getByDate(LocalDate startDate) {
		List<Course> retCourses = new LinkedList<>();
		
		for(Course course : courses) {
			if(course.getStartDate().toLocalDate().isEqual(startDate))
				retCourses.add(course);
		}
		return retCourses;
	}

	@Override
	public List<Course> getAllCourses() {
		return courses;
	}

	@Override
	public boolean removeCourse(Course course) {
		for(int i = 0; i < courses.size(); i++) {
			if(courses.get(i) == course) {
				courses.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		String retString = new String();
		
		for(Course course : courses) {
			retString += course.toString() + '\n';
		}
		return retString;
	}
}
