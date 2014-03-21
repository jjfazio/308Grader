package model.assignments_categories;

import model.exception.BadDataException;

import java.util.GregorianCalendar;

/**
 * The DueDate class creates a GregorianCalendar type date. This date is used to define the due date of instances of
 * Assignments.
 *
 * @author Jirbert Dilanchian
 */
public class DueDate {
    // Stores the string the user entered the date
    private String dateStr;
    // Stores the Gregorian Calender representation of the date user entered.

    private GregorianCalendar dueDate;
    /**
     * The constructor of DueDate
     *
     * @param dateStr the date the user enters for the due date of assignment
     */
    public DueDate(String dateStr) {
        this.dateStr = dateStr;
    }

    /**
     * Determines if the date entered by the user is a valid date
     * @return true if the user entered a valid date.
     */
    public boolean isValidDate(){
        int month, day, year;
        String monthStr, dayStr, yearStr;
        boolean isValid = false;
        dueDate = null;

        if(dateStr.indexOf('/') >= 1 && dateStr.lastIndexOf('/') >= 3){
            monthStr = dateStr.substring(0, dateStr.indexOf('/'));
            dayStr = dateStr.substring(dateStr.indexOf('/') + 1, dateStr.lastIndexOf('/'));
            yearStr = dateStr.substring(dateStr.lastIndexOf('/') + 1);

            if (!monthStr.equals("") && monthStr.matches("\\d+") && !dayStr.equals("") && dayStr.matches("\\d+") &&
                    !yearStr.equals("") && yearStr.matches("\\d+"))
            {
                month = Integer.parseInt(monthStr);
                day = Integer.parseInt(dayStr);
                year = Integer.parseInt(yearStr);

                if(month <= 12 && month > 0 && year > 2000 && day > 0) {
                    if(((month == 1 || month == 3 || month == 5 || month == 6 || month == 8 || month == 10 || month == 12)
                            && day <= 31) ||
                            ((month == 4 || month == 7 || month == 9 || month == 11) && day <=30) ||
                            ((month == 2) && ((year % 4 == 0) && (year % 400 != 0)) ? day <= 29 : day <28 )){
                        dueDate = new GregorianCalendar(year, month, day);
                        isValid = true;
                    }
                }
            }
        }

        System.out.println("the validity " + isValid);
        return isValid;
    }

    /**
     * Returns the Gregorian Calender representation of the due date
     * @return the Gregorian Calender representation of the due date
     */
    public GregorianCalendar getDueDate(){
        return dueDate;
    }
}