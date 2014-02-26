package view.assignments_categories;

import java.util.HashMap;
import java.util.Map;

import model.assignments_categories.Category;
import model.assignments_categories.CategoryContainer;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

/**
 * Category tree builds a hierarchical representation of categories and
 * assignments. Each category is displayed with there children
 * categories. Each category displays the
 * percentage weight of their parent categories. This class also
 * contains a maps that map a category name to the actual
 * category.
 * @author jamesfazio
 *
 */
public class CategoryTree
{
    /** Root of the tree */
    private TreeItem<String> root;
    
    /** Maps category names to categories */
    private Map<String, Category> categoryMap;
    
    /** Instantiate a CategoryTree with the
     * given CategoryContainer
     * @param container The {@link CategoryContainer} to build the tree with
     */
    public CategoryTree(CategoryContainer container)
    {
        root = new TreeItem<String>();
        categoryMap = new HashMap<String, Category>();
        loadTreeView(container);
    }
    
    /**
     * Returns the root of the tree
     * @return 
     */
    public TreeItem<String> getRoot()
    {
        return root;
    }
    
    /**
     * Returns the category for the given categoryname
     * @param categoryName The name of the category
     * @return
     */
    public Category getCategory(String categoryName)
    {
        return categoryMap.get(categoryName);
    }
    
    /**
     * Sets up the TreeView recursively.
     */
    private void loadTreeView(CategoryContainer container)
    {
        Category parent = container.getRoot();
        root = new TreeItem<String>(parent.getName()
                + " ( " + parent.getPercentOfParent() + " )");
        
        addToTree(parent, root);
        
    }
    
    private void addToTree(Category parent, TreeItem<String> rootItem)
    {
        TreeItem<String> curItem;
        
        categoryMap.put(parent.getName(), parent);
        
        if (parent.getSubCategories() != null && 
                !parent.getSubCategories().isEmpty()) {
            for (Category subCategory : parent.getSubCategories()) {
                curItem = new TreeItem<String>(subCategory.getName()
                        + " ( " + subCategory.getPercentOfParent() + " %)");
                rootItem.getChildren().add(curItem);
                addToTree(subCategory, curItem);
            }
        }
    }
    

}
