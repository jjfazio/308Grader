package model.spreadsheet;

import model.assignments_categories.Assignment;
import model.users.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// not yet implemented
public class Statistics
{
    private String statName;

    private double value;

    private Assignment assignment;

    private List<Student> students;


    public Statistics(String name, List<Student> studentList)
    {
        this.statName = name;
        this.students = studentList;

    }

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
        totalScore = totalScore/numGraded;
        return totalScore;
    }

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

    public String getStatName()
    {
        return this.statName;
    }

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
        if(unsortedGrades.length == 1)
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

        range = unsortedGrades[unsortedGrades.length - 1] - unsortedGrades[0];
        return range;
    }
}
