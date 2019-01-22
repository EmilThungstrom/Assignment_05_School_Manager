package se.lexicon.emil.SchoolManager.data_access;

import java.util.List;

import se.lexicon.emil.SchoolManager.data.Student;

public interface StudentDao {
	
	Student addStudent(Student student);
	Student getByEmail(String email);
	Student getByID(int id);
	
	List<Student> getByFirstName(String firstName);
	List<Student> getBySurname(String surname);
	List<Student> getByFullName(String firstName, String surname);
	List<Student> getAllStudents();
	
	boolean removeStudent(Student student);
}
