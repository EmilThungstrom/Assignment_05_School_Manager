package se.lexicon.emil.SchoolManager.data_access;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import se.lexicon.emil.SchoolManager.data.Course;

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
		return courses.stream().filter(course -> course.getId().equals(id)).collect(Collectors.toList()).get(0);
	}

	@Override
	public List<Course> getByName(String courseName) {
		return courses.stream().filter(course -> course.getName().contains(courseName)).collect(Collectors.toList());
	}

	@Override
	public List<Course> getByDate(LocalDate startDate) {
		return courses.stream().filter(course -> course.getStartDate().toLocalDate().isEqual(startDate))
				.collect(Collectors.toList());
	}

	@Override
	public List<Course> getAllCourses() {
		return courses;
	}

	@Override
	public boolean removeCourse(Course course) {
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i) == course) {
				courses.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		String retString = new String();

		for (Course course : courses) {
			retString += course.toString() + '\n';
		}
		return retString;
	}
}
