package controller.spreadsheet;

import model.spreadsheet.SpreadsheetCourse;

<<<<<<< HEAD
public class SpreadsheetController {
   private SpreadsheetCourse course;

=======
/**
 * This class controls the Spreadsheet actions. Any action
 * done to a spreadsheet will happen here
 */
public class SpreadsheetController {
   private SpreadsheetCourse course;

   /**
    * Must be called before displaying the view. Sets the course
    * to be used for this spreadsheet.
    * @param course - The SpreadsheetCourse for this Spreadsheet
    */
>>>>>>> 9fe84d803cfd6e1e86687215486d9201a83a1161
   public void setSpreadsheet(SpreadsheetCourse course) {
      this.course = course;
      System.out.println("Set up spreadsheet for " + course.getCourseInfo().getCourseName());
   }
}
