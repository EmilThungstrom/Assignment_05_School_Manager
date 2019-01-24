package se.lexicon.emil.SchoolManager;

import se.lexicon.emil.SchoolManager.data_access.CourseDaoList;
import se.lexicon.emil.SchoolManager.data_access.StudentDaoList;

public class DataManager {
	
	public static StudentDaoList studentDao = new StudentDaoList();
	public static CourseDaoList courseDao = new CourseDaoList();
	
	
}
