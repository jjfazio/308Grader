package implementation.model.assignments_categories;

import static org.junit.Assert.fail;
import model.assignments_categories.CategoryContainer;
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
        fail("Not yet implemented");
    }

    @Test
    public void testAddCategory()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testRemoveCategory()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testAddAssignment()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testDeleteAssignment()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testGetAssignmentById()
    {
        CategoryContainer container = gradebook.getCourses().get(0).getCategoryContainer();
        
        container.getAssignmentById(1);
        
    }

    @Test
    public void testGetRoot()
    {
        fail("Not yet implemented");
    }

}
