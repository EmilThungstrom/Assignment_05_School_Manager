package se.lexicon.emil.SchoolManager.data;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

public class Course {
	
	private int id;
	private String courseName;
	private Date startDate;
	private int weekDuration;
	private List<Student> students;
	
	public Course()
	{
		students = new LinkedList<>();
		id = Integer.parseInt(RandomStringUtils.random(5, "1234567890ABCDEF"), 16);
	}
	public Course(String courseName, Date startDate, int weekDuration) {
		this.courseName = courseName;
		this.startDate = startDate;
		this.weekDuration = weekDuration;
		
		id = Integer.parseInt(RandomStringUtils.random(5, "1234567890ABCDEF"), 16);
	}
	
	public void regStudent(Student student) {
		if(students.contains(student))
			throw new IllegalArgumentException("A student can only be registred on a course once");
		
		students.add(student);
	}
	
	public boolean unRegStudent(Student student) {
		for(int i = 0; i < students.size(); i++) {
			if(students.get(i) == student) {
				students.remove(i);
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
		result = prime * result + id;
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((students == null) ? 0 : students.hashCode());
		result = prime * result + weekDuration;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (courseName == null) {
			if (other.courseName != null)
				return false;
		} else if (!courseName.equals(other.courseName))
			return false;
		if (id != other.id)
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (students == null) {
			if (other.students != null)
				return false;
		} else if (!students.equals(other.students))
			return false;
		if (weekDuration != other.weekDuration)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", courseName=" + courseName + ", startDate=" + startDate + ", weekDuration="
				+ weekDuration + ", students=" + students + "]";
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public int getWeekDuration() {
		return weekDuration;
	}
	
	public void setWeekDuration(int weekDuration) {
		this.weekDuration = weekDuration;
	}
	
	public List<Student> getStudents() {
		return students;
	}
	
	public void setStudents(List<Student> students) {
		this.students = students;
	}
}
