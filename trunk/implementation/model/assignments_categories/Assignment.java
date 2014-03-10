package model.assignments_categories;

/**
 * @author Jirbert Dilanchian
 */

import java.io.Serializable;
import java.util.Date;

import model.exception.BadDataException;
import model.spreadsheet.GradingScheme;
import model.spreadsheet.LatePolicy;
import model.spreadsheet.SpreadsheetCourse;

/**
 * Assignment contains a name, a weight, a gradeType, and a set of statistics
 * along with methods to change these fields
 */
public class Assignment implements Serializable
{
    /*
     * The name of the assignment. It cannot be empty string or null.
     */
    private String name;

    /*
     * The percentage value that the assignment covers from parent category.
     */
    private Double percentOfCategory;

    /*
     * Maximum number of points available for the assignment. It cannot be less
     * than zero.
     */
    private Integer maxPoints;

    /*
     * The due date for the assignment. It should not be before the current date
     * of the computer.
     */
    private Date dueDate;

    /*
     * Grading scheme used for the assignment.
     */
    private GradingScheme gScheme;

    /*
     * Late policy used for the assignment.
     */
    private LatePolicy policy;

    /*
     * Controls if the assignment is being turned in electronically or not.
     */
    private Boolean hasElectronicTurnin;

    /*
     * Sets the curve value of the assignment. It cannot be less than zero.
     */
    private Double percentCurve;

    private double pecentOfClass;

    private int id;

    /*
     * Serializing number needed to serialize the assignment.
     */
    private static final long serialVersionUID = -2343709981975028696L;

    /**
     * Default constructor of Assignment which creates an assignment with
     * predefined values.
     */
    public Assignment()
    {
        this(null, "changeName", 100.0, 100, new Date(),
                new GradingScheme(), new LatePolicy(), false);
    }

    /**
     * Constructor for an assignment.
     * 
     * @param name
     *            name of the assignment to be created.
     * @param percentOfCategory
     *            weight of the assignment based on percentage from its parent
     *            category.
     * @param maxPoints
     *            maximum number of points each student can get on that
     *            assignment.
     * @param dueDate
     *            the date that the assignment is due.
     * @param gScheme
     *            the grading scheme that would be used for this assignment.
     * @param latePolicy
     *            the late policy defined for this assignment.
     * @param hasElectronicTurnin
     *            if the assignment should be turned in electronically or not.
     */

    public Assignment(Category parentCategory,
            String name, Double percentOfCategory, Integer maxPoints,
            Date dueDate, GradingScheme gScheme, LatePolicy latePolicy,
            Boolean hasElectronicTurnin)
    {
        this.name = name;
        this.percentOfCategory = percentOfCategory;
        this.maxPoints = maxPoints;
        this.dueDate = dueDate;
        this.gScheme = gScheme;
        this.policy = latePolicy;
        this.hasElectronicTurnin = hasElectronicTurnin;
        if(parentCategory == null) {
        	this.pecentOfClass = 100;
        }
        else {
        	this.pecentOfClass = (percentOfCategory / 100.0)
                * parentCategory.getPercentOfClass();
        }

        this.id = AssignmentDB.getInstance().getID();
        // this.gScheme = defaultGScheme;
    }

    /**
     * Sets the percentage curve for a particular assignment
     */
    /*
     * @ requires // //The amount curved parameter must be a decimal between -1
     * and 1 // (amountCurved >= -1 && amountCurved <= 1);
     * 
     * ensures // //the percentCurve field in Category is changed to match the
     * amountCurved parameter // (assignment.percentCurve.equals(amountCurved));
     * 
     * @
     */
    public void adjustAssignmentCurve(Double amountCurved)
    {

    }

    /**
     * Returns the name of the assignment.
     * 
     * @return the name of the assignment.
     */

    public String getName()
    {
        return name;
    }

    /**
     * Defined the name of the assignment.
     * 
     * @param name
     *            the string which becomes the name of the assignment.
     */
    /*
     * @ requires // //The name variable is not empty or null. //
     * (!name.equals(null) && !name.equals(""));
     * 
     * ensures // //the name of the Category is changes to match the name
     * variable. // (name.equals(this.name));
     * 
     * @
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Returns the percentage of the assignment from the parent category.
     * 
     * @return percentage of the assignment from the parent category
     */
    public Double getPercentOfCategory()
    {
        return percentOfCategory;
    }

    /**
     * Sets the percentage of the assignment from the parent category.
     * 
     * @param percent
     *            sets the percentage of the assignment from the parent
     *            category.
     */
    /*
     * @ requires // // percent is greater or equal to zero. // (percent >= 0);
     * ensures // // percentOfCategory is equal to percent. // (percent ==
     * this.percentOfCategory);
     * 
     * @
     */

    public void setPercentOfCategory(double percent)
    {
        this.percentOfCategory = percent;
    }

    /**
     * Returns the maximum points available for the assignment.
     * 
     * @return the maximum points available for the assignment.
     */
    public Integer getMaxPoints()
    {
        return maxPoints;
    }

    /**
     * Sets the maximum points available for the assignment.
     * 
     * @param maxPoints
     *            sets the maximum points available for the assignment.
     */
    /*
     * @ requires // // maxPoints is greater or equal to zero. // (maxPoint >=
     * 0);
     * 
     * ensures // // maxPoints of the assignment is equal to the maxPoints of
     * argument list of the method. // (this.maxPoints == maxPoints);
     * 
     * @
     */

    public void setMaxPoints(Integer maxPoints)
    {
        this.maxPoints = maxPoints;
    }

    /**
     * Returns the due date of the assignment.
     * 
     * @return the due date of the assignment.
     */
    public Date getDueDate()
    {
        return dueDate;
    }

    /**
     * Sets the due date of the assignment.
     * 
     * @param dueDate
     *            sets the due date of the assignment.
     */
    /*
     * @ requires // // the due date is a date the same as current date or a
     * date in future. // (this.dueDate.compareTo(Date curDate = new Date));
     * ensures // // this.dueDate.equal to the dueDate //
     * (this.dueDate.compareTo(dueDate) == 0)
     * 
     * @
     */
    public void setDueDate(Date dueDate)
    { // work on date
        this.dueDate = dueDate;
    }

    /**
     * Returns the grading scheme used for the assignment.
     * 
     * @return the grading scheme used for the assignment.
     */
    public GradingScheme getgScheme()
    {
        return gScheme;
    }

    /**
     * Sets the grading scheme for the assignment.
     * 
     * @param gScheme
     *            grading scheme that is being used for the assignment.
     */
    /*
     * @ ensure // // the gScheme of the assignment is changed to gScheme of the
     * argument. // (this.gScheme.equals(gScheme));
     * 
     * @
     */
    public void setgScheme(GradingScheme gScheme)
    {
        this.gScheme = gScheme;
    }

    /**
     * Returns the late policy of the assignment.
     * 
     * @return the late policy of the assignment.
     */
    public LatePolicy getPolicy()
    {
        return policy;
    }

    /**
     * Sets the late policy of the assignment.
     * 
     * @param policy
     *            the late policy of the assignment,
     */
    /*
     * @ requires // // policy is not null. // (policy != null); ensures // //
     * the late policy is equal to policy. // (this.policy.equals(policy));
     * 
     * @
     */

    public void setPolicy(LatePolicy policy)
    {
        this.policy = policy;
    }

    /**
     * Returns true if the assignment should be turned in electronically.
     * 
     * @return true if the assignment should be turned in electronically.
     */
    public Boolean getHasElectronicTurnin()
    {
        return hasElectronicTurnin;
    }

    /**
     * Defines if the assignment should be turned in electronically.
     * 
     * @param hasElectronicTurnin
     *            defines if the assignment should be turned in electronically.
     */
    /*
     * @ requires // // hasElectronicTurnin is not null. // (hasElectronicTurnin
     * != null); ensures // // hasElectronicTurn of the assignment is set with
     * the value of hasElectronicTurnin in the arguments. // requires // //
     * hasElectronicTurnin is not null. // (hasElectronicTurnin != null);
     * 
     * @
     */

    public void setHasElectrionicTurnin(Boolean hasElectronicTurnin)
    {
        this.hasElectronicTurnin = hasElectronicTurnin;
    }

    /**
     * Returns the percentCurve of the assignment.
     * 
     * @return the percentCurve of the assignment.
     */
    public Double getPercentCurve()
    {
        return percentCurve;
    }

    public int getID()
    {
        return id;
    }

    public double getPercentOfClass()
    {
        return pecentOfClass;
    }

    /**
     * Sets the percentCurve value of the assignment.
     * 
     * @param percentCurve
     *            the percentCurve that is used for the assignment.
     */
    /*
     * @ requires // // percentCurve is not null. // (percentCurve != null);
     * 
     * @
     */
    public void setPercentCurve(Double percentCurve)
    {
        this.percentCurve = percentCurve;
    }
}
