package model.assignments_categories;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.Observable;

import model.exception.BadDataException;
import model.spreadsheet.GradingScheme;
import model.spreadsheet.LatePolicy;
import model.spreadsheet.SpreadsheetCourse;
import model.users.Student;

import java.lang.String;

/**
 * Container that holds a reference to the top category which is essentially 
 * a Tree data structure of sub categories and assignments. This
 * is where adding/removing assignments/categories should happen. When
 * an add or remove happens the Observers of this class are notified.
 * @author jamesfazio / Jirbert Dilanchian
 *
 */
public class CategoryContainer extends Observable implements Serializable
{
    private static final long serialVersionUID = -7477588878399698440L;
    
    /** Reference to the top category */
    private Category root;
    
    /**
     * Constructor that initializes the top category 
     */
    public CategoryContainer()
    {
        root = new Category();
    }
    
    /**
     * Adds a subcategory to the provided parent category
     * @param parent The parent category to be added to
     * @param percentOfParent Percent of the parent of the new category
     * @param name Name of the new category
     */
    public void addCategory(Category parent, String percentOfParent, String name) throws BadDataException
    {
        double sum = 0;

        BadDataException addCategoryException;
        String errors = "";
        if(name.equals("")) {
            errors += "The Name field is empty.\n";
        }
        if(parent == null) {
            errors += "You need to choose parent category.\n";
        }
        if(percentOfParent.equals("")) {
            errors += "The Percent of Parent field is empty.\n";
        }
        else if(!percentOfParent.matches("\\d+") || (Double.parseDouble(percentOfParent) < 0)) {
            errors += "The Percent of Parent field should contain numbers.\n";
        }
        else {
            sum += Double.parseDouble(percentOfParent);
        }
        if(parent.getSubCategories() != null) {
            for(Category subCat : parent.getSubCategories()) {
                sum += subCat.getPercentOfParent();
            }
        }

        if(sum > 100) {
            errors += "Sum of percentage of sub-categories exceeds 100%";
        }

        if(!errors.equals("")) {
            throw addCategoryException = new BadDataException(errors);
        }
        else {
            parent.addSubCategory(new Category(parent, Double.parseDouble(percentOfParent), name));
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Edits the category
     * @param oldParent Parent of the category being edited
     * @param newParent The new parent of the editted category.
     * @param name New name of the category
     * @param percentOfParentNew New percentage of new category
     */

    public void editCategory(Category oldParent, Category newParent, Category theCategory,
                             String name, String percentOfParentNew) throws BadDataException{
        BadDataException editCategoryException;
        String errors = "";

        if(percentOfParentNew.equals("")) {
            errors += "The Percent of Parent field is empty.\n";
        }
        else if(!percentOfParentNew.matches("\\d+") || (Double.parseDouble(percentOfParentNew) < 0)) {
            errors += "The Percent of Parent field should contain numbers which are greater than 0.\n";
        }
        if(!errors.equals("")) {
            throw editCategoryException = new BadDataException(errors);
        }
        else {
            if(oldParent.getName().equals(name)) {
                throw new BadDataException("A category cannot be subCategory of itself.");
            }
            else {
                oldParent.removeCategory(theCategory);
                theCategory.setName(name);
                newParent.addSubCategory(theCategory);
                theCategory.setPercentOfParent(Double.parseDouble(percentOfParentNew));

                setChanged();
                notifyObservers();
            }
        }
    }

    /**
     * Edits the category
     * @param oldParent Parent of the category being edited
     * @param newParent The new parent of the editted category.
     * @param name New name of the category
     * @param percentOfParentNew New percentage of new category
     */

    public void editAssignment(Category oldParent, Category newParent, Assignment theAssignment,
                             String name, String percentOfParentNew, String dueDate, String points)
                             throws BadDataException{
        BadDataException editCategoryException;
        String errors = "";
        System.out.println("Model Edit Assignment");
        if(name.equals("")){
            errors += "You need to assign a name to the Assignment.\n";
        }
        if(percentOfParentNew.equals("")) {
            errors += "The Percent of Parent field is empty.\n";
        }
        else if(!percentOfParentNew.matches("\\d+") || (Double.parseDouble(percentOfParentNew) < 0)) {
            errors += "The Percent of Parent field should contain numbers which are greater than 0.\n";
        }

        DueDate newDueDate = new DueDate(dueDate);
        if(!newDueDate.isValidDate()) {
            errors += "Due date is not valid.\n";
        }

        if(!points.matches("\\d+") || Integer.parseInt(points) <= 0) {
            errors += "The points should be numbers greater than 0.\n";
        }

        if(!errors.equals("")) {
            throw editCategoryException = new BadDataException(errors);
        }
        else {
            oldParent.removeAssignment(theAssignment);
            theAssignment.setName(name);
            theAssignment.setPercentOfCategory(Double.parseDouble(percentOfParentNew));
            theAssignment.setDueDate(newDueDate.getDueDate());
            theAssignment.setMaxPoints(Integer.parseInt(points));
            newParent.addAssignment(theAssignment);

            setChanged();
            notifyObservers();
        }
    }


    /**
     * Remove the provided category 
     * @param parent Parent of the category being removed
     * @param toRemove The category to be removed
     */
    public void removeCategory(Category parent, Category toRemove)
    {
        parent.removeCategory(toRemove);
        setChanged();
        notifyObservers();

    }

    /**
     * Helper to add assignment to specified category. It also does the error checking for the user inputs.
     * @param parent the category which assignment to be added
     * @param name the name of the assignment
     * @param weight the weight of the assignment
     * @param points the points of the assignment
     * @param date the date of the assignment
     * @param gradingScheme the grading scheme of the assignment
     * @param latePolicy the late policy of the assingment
     * @param online if it can be submitted online. 
     * @throws BadDataException
     */
    public void addAssignment(Category parent, String name, String weight, String points, String date, GradingScheme gradingScheme,
                              LatePolicy latePolicy, boolean online ) throws BadDataException
    {

        Double catWeight = 0.0;
        String errors = "";

        if(weight.equals("") || !weight.matches("\\d+") || Double.parseDouble(weight) < 0) {
            errors += "Weight field must contain numbers which are grater than 0.\n";
        }
        else {
            catWeight += Double.parseDouble(weight);
        }
        if(points.equals("") || !points.matches("\\d+") || Double.parseDouble(points) < 0) {
            errors += "Points field must contain numbers which are grater than 0.\n";
        }
        if(name.equals("")) {
            errors += "Name field cannot be empty";
        }
        if(parent.getSubCategories() != null) {
            for(Assignment x : parent.getAssignments()){
                catWeight += x.getPercentOfCategory();
            }
        }

        if(catWeight > 100) {
            errors += "Total weight of this category cannot exceed 100%";
        }

        DueDate dueDate = new DueDate(date);
        boolean isValid = false;
        isValid = dueDate.isValidDate();
        if(!isValid) {
            errors += "The due Date is not valid";
        }

        if(!errors.equals("")) {
            throw new BadDataException(errors);
        }
        Assignment newAss = new Assignment(parent, name, Double.parseDouble(weight), Integer.parseInt(points),
                dueDate.getDueDate(), gradingScheme, latePolicy, online);


        parent.addAssignment(newAss);
        setChanged();
        notifyObservers();
    }

    /**
     * Removes an assignment from specified parent category
     * @param parent The parent category where it removes the assignment from.
     * @param assignment the assignment to be removed
     * @param course the course which parent and assignment belong to.
     */
    public void deleteAssignment(Category parent, Assignment assignment, SpreadsheetCourse course)
    {
        for (Student x : course.getStudentRoster()){
            x.removeGrade(course, assignment);
        }
        parent.removeAssignment(assignment);

        setChanged();
        notifyObservers();
    }

    /**
     * Returns the assignment corresponding to the id
     * @param id the identification number designated to the assignment
     * @return the assignment corresponding to the id
     */
    public Assignment getAssignmentById(int id)
    {
        return searchAssignments(root, id);
    }
    
    /**
     * Returns a reference to the top category
     * @return A reference to the top category
     */
    public Category getRoot()
    {
        return root;
    }
    
    private Assignment searchAssignments(Category category, int id)
    {
        LinkedList<Category> categories = new LinkedList<Category>();
        
        categories.push(category);
        
        while (!categories.isEmpty())
        {
            category = categories.poll();
            
            for (Assignment assign : category.getAssignments())
                if (assign.getID() == id)
                    return assign;
            
            for (Category subCategory : category.getSubCategories())
                categories.push(subCategory);
        }
        
        return null;
    }

    /**
     * Checks the equality between two containers.
     * @param obj the container to be checked with current container
     * @return true if both containers are equal
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CategoryContainer other = (CategoryContainer) obj;
        if (root == null)
        {
            if (other.root != null)
                return false;
        }
        else if (!root.equals(other.root))
            return false;
        return true;
    }
}
