package model.spreadsheet;

import model.assignments_categories.Assignment;
import model.users.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents the statistics that are calculated
 * and displayed in the stats view table.  This class contains
 * a stat name, a value, an assignment that the statistic belongs to,
 * and a list of students that belong to the current course
 *
 * @author Kevin Feutz
 */
public class Statistics
{
    /** Contains the name of the statistic to calculate */
    private String statName;

    /** Contains the value calculated by the various stat methods */
    private double value;

    /** Contains the assignment used to obtain grades */
    private Assignment assignment;

    /** Contains the list of students belonging to the current course */
    private List<Student> students;

    /** Contains the mean, used to calculate the standard deviation */
    private double mean;

    /**
     * Constructs a new statistic
     *
     * @param name          The name of the statistic
     *                      ie Mean, Median, ect.
     * @param studentList   The roster of the students
     */
    public Statistics(String name, List<Student> studentList)
    {
        this.statName = name;
        this.students = studentList;
    }

    /**
     * This method calculates the mean of the given assignment
     *
     * @param assignment    The assignment used to obtain grades
     *                      for the mean.
     * @return  double      The calculated mean value
     */
    public double calcMean(Assignment assignment)
    {
        double totalScore = 0;
        int numGraded = 0;
        for(Student currentStudent : students)
        {
            if(currentStudent.getAssignmentGrade(assignment) != null)
            {
                totalScore += (currentStudent.getAssignmentGrade(assignment).getScore()
                    /assignment.getMaxPoints()) * 100.0;
                numGraded++;
            }
        }
        if(numGraded == 0)
        {
            totalScore = 0.0;
        }
        else
        {
            totalScore = totalScore/numGraded;
        }
        this.mean = totalScore;
        return totalScore;
    }

    /**
     * This method calculates the number of assignments that
     * have been graded.
     *
     * @param assignment    The assignment used to calculate the
     *                      number of grades.
     * @return  int         The number of graded assignments for
     *                      the passed assignment.
     */
    public int calcNumAssignmentsGraded(Assignment assignment)
    {
        int numGraded = 0;
        for(Student currentStudent : students)
        {
            if(currentStudent.getAssignmentGrade(assignment) != null)
            {
                numGraded++;
            }
        }
        return numGraded;
    }

    /**
     * Retrieves the string representation of this
     * Statistic's name
     *
     * @return  String  This Statistic's name.
     */
    public String getStatName()
    {
        return this.statName;
    }

    /**
     * Calculates the median based on the passed
     * assignment
     *
     * @param assignment    The Assignment used to calculate
     *                      the median.
     * @return  double      The calculated median
     */
    public double calcMedian(Assignment assignment)
    {
        ArrayList<Double> gradePercentages = new ArrayList<Double>();
        for(Student currentStudent : students)
        {
            if(currentStudent.getAssignmentGrade(assignment) != null)
            {
                gradePercentages.add((currentStudent.getAssignmentGrade(assignment).getScore()
                        /assignment.getMaxPoints()) * 100.0);
            }
        }
        Double[] unsortedGrades = gradePercentages.toArray(new Double[gradePercentages.size()]);
        Arrays.sort(unsortedGrades);
        /** Indicates an odd length of grades */
        if(unsortedGrades.length == 0)
        {
            return 0;
        }
        else if(unsortedGrades.length == 1)
        {
            return unsortedGrades[0];
        }
        else if(unsortedGrades.length % 2 == 1)
        {
            return unsortedGrades[unsortedGrades.length/2];
        }
        else
        {
            return (unsortedGrades[unsortedGrades.length/2]
                + unsortedGrades[unsortedGrades.length/2 - 1])/2;
        }
    }

    /**
     * Calculates the range based off the highest and lowest
     * recorded grades.
     *
     * @param assignment    The Assignment used to calculate
     *                      the range.
     * @return  double      The difference between the highest
     *                      and lowest grades belonging to
     *                      the passed assignment
     */
    public double calcRange(Assignment assignment)
    {
        ArrayList<Double> gradePercentages = new ArrayList<Double>();
        Double range;
        for(Student currentStudent : students)
        {
            if(currentStudent.getAssignmentGrade(assignment) != null)
            {
                gradePercentages.add((currentStudent.getAssignmentGrade(assignment).getScore()
                        /assignment.getMaxPoints()) * 100.0);
            }
        }
        Double[] unsortedGrades = gradePercentages.toArray(new Double[gradePercentages.size()]);
        Arrays.sort(unsortedGrades);
        if(unsortedGrades.length == 0)
        {
            range = 0.0;
        }
        else if(unsortedGrades.length == 1)
        {
            range = unsortedGrades[0];
        }
        else
        {
            range = unsortedGrades[unsortedGrades.length - 1] - unsortedGrades[0];
        }
        return range;
    }

    /**
     * Calculates the standard deviation of the grades
     * belonging to the passed assignment
     *
     * @param assignment    The Assignment used to obtain grades
     *                      for the standard deviation.
     * @return  double      The calculated standard deviation.
     */
    public double calcStandardDeviation(Assignment assignment)
    {
        ArrayList<Double> gradePercentages = new ArrayList<Double>();
        ArrayList<Double> meanMinusPercentages = new ArrayList<Double>();
        Double total = 0.0;
        for(Student currentStudent : students)
        {
            if(currentStudent.getAssignmentGrade(assignment) != null)
            {
                gradePercentages.add((currentStudent.getAssignmentGrade(assignment).getScore()
                        /assignment.getMaxPoints()) * 100.0);
            }
        }
        Double[] unsortedGrades = gradePercentages.toArray(new Double[gradePercentages.size()]);
        Arrays.sort(unsortedGrades);
        for(int index = 0; index < unsortedGrades.length; index++)
        {
            meanMinusPercentages.add(unsortedGrades[index] - mean);
        }
        for(int index = 0; index < unsortedGrades.length; index++)
        {
            meanMinusPercentages.set(index,
                meanMinusPercentages.get(index) * meanMinusPercentages.get(index));
        }
        for(int index = 0; index < unsortedGrades.length; index++)
        {
            total += meanMinusPercentages.get(index);
        }
        total = total/(unsortedGrades.length - 1);

        return Math.sqrt(total);
    }

    /**
     * Calculates mean for all assignments belonging to the passed
     * course
     *
     * @param   currentCourse   currentCourse
     */
    public double calcTotalMean(SpreadsheetCourse currentCourse)
    {
        double totalGrade = 0;
        int numTotals = 0;
        for(Student currentStudent : students)
        {
            totalGrade += currentStudent.getTotalGrade(currentCourse.getID());
            numTotals++;
        }
        return totalGrade/numTotals;
    }
}
