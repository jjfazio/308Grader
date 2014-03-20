package model.assignments_categories;

import model.exception.BadDataException;

import java.util.GregorianCalendar;

/**
 * Created by Jib on 3/19/14.
 */
public class DueDate {
    String dateStr;
    public DueDate(String dateStr) {
        this.dateStr = dateStr;
    }

    private GregorianCalendar dueDate;
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

    public GregorianCalendar getDueDate(){
        return dueDate;
    }
}