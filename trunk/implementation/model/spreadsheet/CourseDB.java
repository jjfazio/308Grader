package model.spreadsheet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CourseDB
{
    private static final String FILE_NAME = "courseDB.txt";
    private File file;
    private static CourseDB instance;
    
    private CourseDB()
    {
        getFile();
    }
    
    public static CourseDB getInstance()
    {
        if (instance == null)
            instance = new CourseDB();
        
        return instance;
    }
    
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
