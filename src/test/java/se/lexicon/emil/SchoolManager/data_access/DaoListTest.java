package se.lexicon.emil.SchoolManager.data_access;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import se.lexicon.emil.SchoolManager.data.Course;
import se.lexicon.emil.SchoolManager.data.Student;

public class DaoListTest {

	static final int TEST_ARRAY_LENGTH = 5;
	static String[] firstNames = {"Anna", "Emil", "Oscar", "Anneli", "Maria"};
	static String[] surnames = {"Thungstrom", "Edstrand", "Sundberg", "Anrep", "Carlson"};
	static String[] adresses = {"Studentvagen 1", "Karlskronavagen 17", "Falkenbergsvagen 22", "Johannesberg 77", "Oscarskulle 56"};
	static String[] emails = {"@gmail.com", "@outlook.com", "@lexicon.com", "@bth.se", "@yahoo.com"};

	static String[] courseNames = {"Matematik Introduktion", "Diskret Matematik", "Trigometri", "Kryption 1", "kryption 2"};
	
	StudentDao studentDao;
	CourseDao courseDao;
	
	static Student[] students = new Student[5];
	static Course[] courses = new Course[5];
	
	static Random random = new Random(System.currentTimeMillis());
	
	@BeforeClass
	public static void setUp() {
		for(int i = 0; i < TEST_ARRAY_LENGTH; i++) {
			students[i] = new Student();
			courses[i] = new Course();
			
			students[i].setFirstName(firstNames[i]);
			students[i].setSurname(surnames[i]);
			students[i].setAdress(adresses[i]);
			students[i].setEmail(firstNames[i] + '.' + surnames[i] + emails[i]);
			
			courses[i].setCourseName(courseNames[i]);
			courses[i].setStartDate(Date.valueOf(LocalDate.of((2000 + random.nextInt(20)), (random.nextInt(11) + 1), (random.nextInt(27) + 1))));
			courses[i].setWeekDuration(random.nextInt(51) + 1);
		}
	}
	
	@Before
	public void init() {
		studentDao = new StudentDaoList();
		courseDao = new CourseDaoList();
		
		for(int i = 0; i < TEST_ARRAY_LENGTH; i++) {
			studentDao.addStudent(students[i]);
			courseDao.addCourse(courses[i]);
		}
	}

	@Test
	public void testAdd() {
		for(Student student : students)
			Assert.assertTrue(studentDao.getAllStudents().contains(student));
		
		for(Course course : courses)
			Assert.assertTrue(courseDao.getAllCourses().contains(course));
	}

	@Test
	public void testGetByID() {
		int randInt = random.nextInt(TEST_ARRAY_LENGTH);
		Assert.assertTrue(studentDao.getByID(students[randInt].getId()) == students[randInt]);
		Assert.assertTrue(courseDao.getByID(courses[randInt].getId()) ==  courses[randInt]);
	}

	@Test
	public void testRemove() {
		int randInt = random.nextInt(TEST_ARRAY_LENGTH);
		Assert.assertTrue(studentDao.removeStudent(students[randInt]));
		Assert.assertFalse(studentDao.removeStudent(students[randInt]));
		
		Assert.assertTrue(courseDao.removeCourse(courses[randInt]));
		Assert.assertFalse(courseDao.removeCourse(courses[randInt]));
	}

}
