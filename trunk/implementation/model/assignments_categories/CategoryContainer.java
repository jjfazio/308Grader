package model.assignments_categories;

import java.io.Serializable;
import java.util.Observable;

public class CategoryContainer extends Observable implements Serializable
{
    private static final long serialVersionUID = -7477588878399698440L;
    
    private Category root;
    
    public CategoryContainer()
    {
        root = new Category();
    }
    
    public void addCategory(Category parent, Double percentOfParent, String name)
    {
        parent.addSubCategory(new Category(parent, percentOfParent, name));
        setChanged();
        notifyObservers();
    }
    
    public void removeCategory(Category parent, Category toRemove)
    {
        parent.removeCategory(toRemove);
        setChanged();
        notifyObservers();
    }
    
    public Category getRoot()
    {
        return root;
    }
}
