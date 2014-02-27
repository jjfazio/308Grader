package implementation.model.assignments_categories;

import model.assignments_categories.Category;
import model.assignments_categories.CategoryContainer;
import model.exception.BadDataException;
import model.gradebook.Gradebook;

import org.junit.Before;
import org.junit.Test;

public class CategoryContainerTest
{
    
    private Gradebook gradebook;

    @Before
    public void setUp() throws Exception
    {
        gradebook = Gradebook.getInstance();
    }

    @Test
    public void testCategoryContainer()
    {
        CategoryContainer testContainer = new CategoryContainer();
        assert(testContainer != null);
    }

    @Test
    public void testAddCategory()
    {
        Category testCat = new Category();
        CategoryContainer testContainer = new CategoryContainer();
        try {
            testContainer.addCategory(testCat, "df" , "first");
        } catch (BadDataException e){
            assert(e.getMessage() != null);
        }

        try {
            testContainer.addCategory(testCat, "" , "first");
        } catch (BadDataException e){
            assert(e.getMessage() != null);
        }

        try {
            testContainer.addCategory(testCat, "df" , "");
        } catch (BadDataException e){
            assert(e.getMessage() != null);
        }
    }

    @Test
    public void testRemoveCategory()
    {

    }

    @Test
    public void testAddAssignment()
    {

    }

    @Test
    public void testDeleteAssignment()
    {
    }

    @Test
    public void testGetAssignmentById()
    {

    }

    @Test
    public void testGetRoot()
    {

    }

}
