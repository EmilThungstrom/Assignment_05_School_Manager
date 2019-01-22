package se.lexicon.emil.SchoolManager;

import java.util.Scanner;

import se.lexicon.emil.SchoolManager.data_access.CourseDaoList;
import se.lexicon.emil.SchoolManager.data_access.StudentDaoList;

public class UI {
	
	public static StudentDaoList studentDao = new StudentDaoList();
	public static CourseDaoList courseDao = new CourseDaoList();
	public static Scanner scanner = new Scanner(System. in);
	
	public void UILoop() {
		System.out.println("Welcome to the School Mananger application");
		
	}
}
