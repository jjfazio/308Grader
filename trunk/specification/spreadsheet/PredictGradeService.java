package spreadsheet;

import users.Student;
import assignments_categories.Grade;

/**
 * The Predict Grade page contains methods which give students the ability to predict their score or
 * analyze their grades in their class's respective spreadsheet.
 */
abstract class PredictGradePage {

    /**
     * predictGrade uses various computations to assist the user with grade prediction.
     * Further information regarding functionality will be provided in a later milestone.
     * @param student
     * @param course
     * @return
     */
    abstract void predictGrade(Student student, SpreadsheetCourse course);

    /**
     * analyzeSpreadsheet allows the user to manipulate dialogs containing grade spreadsheets.
     * Further information regarding functionality will be provided in a later milestone.
     * @param student
     * @param course
     * @return
     */
    abstract void analyzeSpreadsheet(Student student, SpreadsheetCourse course);
	
}