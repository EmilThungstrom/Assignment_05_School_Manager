package se.lexicon.emil.SchoolManager;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String tempString = RandomStringUtils.random(5, "1234567890abcdfe");
    	int tempInt = Integer.parseInt(tempString, 16);
        
    	System.out.println(tempString);
    	System.out.println(Integer.toHexString(tempInt));
    }
}
