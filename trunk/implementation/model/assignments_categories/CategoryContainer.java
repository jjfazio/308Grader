package model.assignments_categories;

import java.io.Serializable;
import java.util.Observable;
import model.exception.BadDataException;
import java.lang.String;

/**
 * Container that holds a reference to the top category which is essentially 
 * a Tree data structure of sub categories and assignments. This
 * is where adding/removing assignments/categories should happen. When
 * an add or remove happens the Observers of this class are notified.
 * @author jamesfazio
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
        else if(!percentOfParent.matches("[0-9]+")) {
            errors += "The Percent of Parent field should contain numbers.\n";
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
    
    public void addAssignment(Category parent, Assignment assignment)
    {
        parent.addAssignment(assignment);
        setChanged();
        notifyObservers();
    }
    
    public void deleteAssignment(Category parent, Assignment assignment)
    {
        parent.removeAssignment(assignment);
        setChanged();
        notifyObservers();
    }
    
    /**
     * Returns a reference to the top category
     * @return A reference to the top category
     */
    public Category getRoot()
    {
        return root;
    }
}
