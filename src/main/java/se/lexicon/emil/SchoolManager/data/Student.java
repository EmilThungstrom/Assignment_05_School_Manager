package se.lexicon.emil.SchoolManager.data;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

public class Student {

	private int id;
	private String firstName;
	private String surname;
	private String email;
	private String adress;
	private List<Course> courses;

	public Student() {
		courses = new LinkedList<>();
		id = Integer.parseInt(RandomStringUtils.random(5, "1234567890ABCDEF"), 16);
	}

	public Student(String firstName, String surname, String email, String adress) {
		this.firstName = firstName;
		this.surname = surname;
		this.email = email;
		this.adress = adress;

		courses = new LinkedList<>();
		id = Integer.parseInt(RandomStringUtils.random(5, "1234567890ABCDEF"), 16);
	}

	public void addCourse(Course course) {
		if (courses.contains(course))
			throw new IllegalArgumentException("Student can't registred to the same course more then once");

		courses.add(course);
	}

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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		result = prime * result + ((courses == null) ? 0 : courses.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((surname == null) ? 0 : surname.hashCode());
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
		Student other = (Student) obj;
		if (adress == null) {
			if (other.adress != null)
				return false;
		} else if (!adress.equals(other.adress))
			return false;
		if (courses == null) {
			if (other.courses != null)
				return false;
		} else if (!courses.equals(other.courses))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (surname == null) {
			if (other.surname != null)
				return false;
		} else if (!surname.equals(other.surname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", surname=" + surname + ", email=" + email
				+ ", adress=" + adress + ", courses=" + courses + "]";
	}

	// getters and setters
	// ---------------------------------------------------------------------------------------------------------------------------------------
	public String getId() {
		return Integer.toHexString(id);
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String name) {
		this.surname = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}
