package controller.spreadsheet;

import model.spreadsheet.SpreadsheetCourse;

public class SpreadsheetController {
   private SpreadsheetCourse course;

   public void setSpreadsheet(SpreadsheetCourse course) {
      this.course = course;
      System.out.println("Set up spreadsheet for " + course.getCourseInfo().getCourseName());
   }
}
