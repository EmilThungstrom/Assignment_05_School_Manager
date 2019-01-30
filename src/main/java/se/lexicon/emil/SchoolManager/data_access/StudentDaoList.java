package se.lexicon.emil.SchoolManager.data_access;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import se.lexicon.emil.SchoolManager.data.Student;

public class StudentDaoList implements StudentDao {

	private List<Student> students;

	public StudentDaoList() {
		students = new LinkedList<>();
	}

	@Override
	public Student addStudent(Student student) {
		students.add(student);
		return student;
	}

	@Override
	public Student getByEmail(String email) {

		return students.stream().filter(student -> student.getEmail().contains(email)).collect(Collectors.toList())
				.get(0);

//		for (Student student : students) {
//			if (student.getEmail().contains(email))
//				return student;
//		}
//		return null;
	}

	@Override
	public Student getByID(String id) {

		return students.stream().filter(student -> student.getId().equals(id)).collect(Collectors.toList()).get(0);

//		for (Student student : students) {
//			if (student.getId().contains(id))
//				return student;
//		}
//		return null;
	}

	@Override
	public List<Student> getByFirstName(String firstName) {

		return students.stream().filter(student -> student.getFirstName().contains(firstName))
				.collect(Collectors.toList());

//		List<Student> retStudents = new LinkedList<>();
//		for (Student student : students) {
//			if (student.getFirstName().contains(firstName))
//				retStudents.add(student);
//		}
//		return retStudents;
	}

	@Override
	public List<Student> getBySurname(String surname) {

		return students.stream().filter(student -> student.getSurname().contains(surname)).collect(Collectors.toList());

//		List<Student> retStudents = new LinkedList<>();
//		for(Student student : students) {
//			if(student.getSurname().contains(surname))
//				retStudents.add(student);
//		}
//		return retStudents;
	}

	@Override
	public List<Student> getByFullName(String firstName, String surname) {

		return students.stream()
				.filter(student -> student.getFirstName().contains(firstName) && student.getSurname().contains(surname))
				.collect(Collectors.toList());

//		  List<Student> retStudents = new LinkedList<>(); for(Student student :
//		  students) { if(student.getFirstName().contains(firstName) &&
//		  student.getSurname().contains(surname)) retStudents.add(student); } return
//		  retStudents;
	}

	@Override
	public List<Student> getAllStudents() {
		return students;
	}

	@Override
	public boolean removeStudent(Student student) {

		for (int i = 0; i < students.size(); i++) {
			if (students.get(i) == student) {
				students.remove(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		String retString = new String();

		for (Student student : students) {
			retString += student.toString() + '\n';
		}
		return retString;
	}
}
