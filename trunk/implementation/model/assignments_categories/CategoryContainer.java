package model.assignments_categories;

import java.io.Serializable;
import java.util.LinkedList;
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
        else if(!percentOfParent.matches("\\d+") || (Double.parseDouble(percentOfParent) < 0)) {
            errors += "The Percent of Parent field should contain numbers.\n";
        }
        double sum = 0;
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
