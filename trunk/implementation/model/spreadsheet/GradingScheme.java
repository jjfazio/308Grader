package model.spreadsheet;

import java.io.Serializable;
import java.util.List;

public class GradingScheme implements Serializable {

   Boolean plusMinusEnabled;
   List<GradeRange> gradeRanges;
   
   public GradingScheme()
   {
	   System.out.println("Creted a new grading scheme");
   }
}
