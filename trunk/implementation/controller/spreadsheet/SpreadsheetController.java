package controller.spreadsheet;

import model.spreadsheet.SpreadsheetCourse;

/**
 * This class controls the Spreadsheet actions. Any action
 * done to a spreadsheet will happen here
 * @author jamesfazio
 */
public class SpreadsheetController {
   private SpreadsheetCourse course;

   /**
    * Must be called before displaying the view. Sets the course
    * to be used for this spreadsheet.
    * @param course - The SpreadsheetCourse for this Spreadsheet
    */
   public void setSpreadsheet(SpreadsheetCourse course) {
      this.course = course;
      System.out.println("Set up spreadsheet for " + course.getCourseInfo().getCourseName());
   }
}
