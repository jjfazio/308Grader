package view.assignments_categories;

import java.util.HashMap;
import java.util.Map;

import model.assignments_categories.Assignment;
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
public class AssignmentTree
{
    /** Root of the tree */
    private TreeItem<String> root;
    
    /** Maps category names to categories */
    private Map<String, Assignment> assignmentMap;
    
    /** Instantiate a CategoryTree with the
     * given CategoryContainer
     * @param container The {@link CategoryContainer} to build the tree with
     */
    public AssignmentTree(CategoryContainer container)
    {
        root = new TreeItem<String>();
        assignmentMap = new HashMap<String, Assignment>();
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
     * Returns the assignment for the given assignment name
     * @param assignmentName The name of the assignment
     * @return
     */
    public Assignment getAssignment(String assignmentName)
    {
        return assignmentMap.get(assignmentName);
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
        
        for(Assignment ass : parent.getAssignments()) {
        	assignmentMap.put(ass.getName(), ass);
        	curItem = new TreeItem<String>(ass.getName());
        	rootItem.getChildren().add(curItem);
        }
        //categoryMap.put(parent.getName(), parent);
        
        if (parent.getSubCategories() != null && 
                !parent.getSubCategories().isEmpty()) {
            for (Category subCategory : parent.getSubCategories()) {
                curItem = new TreeItem<String>(subCategory.getName()
                        + " ( " + subCategory.getPercentOfParent() + " %)");
                curItem.setExpanded(true);
                rootItem.getChildren().add(curItem);
                addToTree(subCategory, curItem);
            }
        }
    }
    

}

