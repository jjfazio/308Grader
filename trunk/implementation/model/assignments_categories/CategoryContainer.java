package model.assignments_categories;

import java.io.Serializable;
import java.util.Observable;

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
    public void addCategory(Category parent, Double percentOfParent, String name)
    {
        parent.addSubCategory(new Category(parent, percentOfParent, name));
        setChanged();
        notifyObservers();
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
     * Returns a reference to the top category
     * @return A reference to the top category
     */
    public Category getRoot()
    {
        return root;
    }
}