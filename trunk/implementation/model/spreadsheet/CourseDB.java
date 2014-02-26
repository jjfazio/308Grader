package model.spreadsheet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * CourseDB generates unique courseID's for SpreadhseetCourses.
 * The next ID to use are contained in a file called "courseDB.txt".
 * Every time the ID is used it increments in the file. This 
 * class is a singleton.
 * @author jamesfazio
 *
 */
public class CourseDB
{
    /** Name of the courseDB file */
    private static final String FILE_NAME = "courseDB.txt";
    
    /** CourseDB file */
    private File file;
    
    /** The instance of this class, only created once */
    private static CourseDB instance;
    
    /** Singleton private constructor that generates this class once */
    private CourseDB()
    {
        getFile();
    }
    
    /**
     * Returns the instance of this class, only generated once.
     * @return The instance of this class.
     */
    public static CourseDB getInstance()
    {
        if (instance == null)
            instance = new CourseDB();
        
        return instance;
    }
    
    /**
     * Get the next unique course ID, from the CourseDB database
     * file 
     * @return The next unique course ID
     */
    public int getID()
    {
        Scanner scanner = null;
        FileWriter writer = null;
        int id = -1;
        try {
            scanner = new Scanner(file);
            id = Integer.parseInt(scanner.nextLine());
            writer = new FileWriter(file, false);
            id ++;
            
            writer.write("" + id);
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            scanner.close();
            try
            {
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
         }
        
        return id;
    }
    
    /**
     * Checks to see if the CourseDB file exists, if it 
     * does not it creates the file and writes a 1 to it.
     */
    private void getFile()
    {
        FileWriter writer = null;
        file = new File(FILE_NAME);
        
        if (!file.exists())
        {
            try
            {
                file.createNewFile();
                writer = new FileWriter(file);
                
                writer.write("1");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                try
                {
                    writer.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

}
