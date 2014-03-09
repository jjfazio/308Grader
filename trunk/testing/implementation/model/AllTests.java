package implementation.model;

import implementation.model.assignments_categories.CategoryContainerTest;
import implementation.model.assignments_categories.CategoryTest;
import implementation.model.assignments_categories.GradeTest;
import implementation.model.gradebook.GradebookTest;
import implementation.model.graph.GraphTest;
import implementation.model.spreadsheet.CourseInfoTest;
import implementation.model.spreadsheet.GradingSchemeTest;
import implementation.model.spreadsheet.SpreadsheetCourseTest;
import implementation.model.users.StudentDBTest;
import implementation.model.users.StudentTest;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({GraphTest.class, GradeTest.class, CourseInfoTest.class, GradingSchemeTest.class,
    StudentTest.class, StudentDBTest.class, CategoryContainerTest.class, CategoryTest.class, 
    GradebookTest.class, SpreadsheetCourseTest.class})

public class AllTests
{
    public static void main(String[] args) throws Exception {                    
        JUnitCore.main("implementation.model.AllTests");     
    }

}
