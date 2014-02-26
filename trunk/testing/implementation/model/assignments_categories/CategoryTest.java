/**
 * 
 */
package implementation.model.assignments_categories;

import static org.junit.Assert.*;
import model.assignments_categories.Assignment;
import model.assignments_categories.Category;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Jirbert Dilanchian
 *C:\CSC309\308Grader\trunk\testing\implementation\model\assignments_categories\CategoryTest.java
 */
public class CategoryTest
{

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
    }

    /**
     * Test method for {@link model.assignments_categories.Category#Category()}.
     */
    @Test
    public void testCategory()
    {
        Category testCat = new Category();
        assert(testCat != null);
    }

    /**
     * Test method for {@link model.assignments_categories.Category#Category(model.assignments_categories.Category, java.lang.Double, java.lang.String)}.
     */
    @Test
    public void testCategoryCategoryDoubleString()
    {
        Category testCat = new Category();
        String name = "name";
        double percentOfParent = 50;
        Category testCat2 = new Category(testCat, percentOfParent, name);
        assert(testCat2 != null);
        assertEquals(testCat2.getName(), name);
        assert(testCat2.getPercentOfParent() == percentOfParent);
    }

    /**
     * Test method for {@link model.assignments_categories.Category#getSubCategories()}.
     */
    @Test
    public void testGetSubCategories()
    {
        Category testCat = new Category();
        Category testCat1 = new Category(testCat, 5.0, "first");
        Category testCat2 = new Category(testCat, 6.0, "second");
        Category testCat3 = new Category(testCat, 7.0, "third");
        assert(testCat.getSubCategories().size() == 3);
    }

    /**
     * Test method for {@link model.assignments_categories.Category#addAssignment(model.assignments_categories.Assignment)}.
     */
    @Test
    public void testAddAssignment()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.assignments_categories.Category#editAssignment(model.assignments_categories.Assignment, model.assignments_categories.Assignment)}.
     */
    @Test
    public void testEditAssignment()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.assignments_categories.Category#removeAssignment(model.assignments_categories.Assignment)}.
     */
    @Test
    public void testRemoveAssignment()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.assignments_categories.Category#addSubCategory(model.assignments_categories.Category)}.
     */
    @Test
    public void testAddSubCategory()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.assignments_categories.Category#editCategory(model.assignments_categories.Category)}.
     */
    @Test
    public void testEditCategory()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.assignments_categories.Category#removeCategory(model.assignments_categories.Category)}.
     */
    @Test
    public void testRemoveCategory()
    {
        Category testCat = new Category();
        Category testCat1 = new Category(testCat, 4.7, "first");
        Category testCat2 = new Category(testCat, 6.4, "second");
        testCat.removeCategory(testCat1);
        assert(testCat.getSubCategories().size() == 1);
    }

    /**
     * Test method for {@link model.assignments_categories.Category#getParentCategory()}.
     */
    @Test
    public void testGetParentCategory()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.assignments_categories.Category#getName()}.
     */
    @Test
    public void testGetName()
    {
        Category testCat = new Category();
        assertEquals(testCat.getName(), "Overall");
    }

    /**
     * Test method for {@link model.assignments_categories.Category#setName(java.lang.String)}.
     */
    @Test
    public void testSetName()
    {
        Category testCat = new Category();
        testCat.setName("newName");
        assertEquals(testCat.getName(), "newName");
    }

    /**
     * Test method for {@link model.assignments_categories.Category#getPercentOfParent()}.
     */
    @Test
    public void testGetPercentofparent()
    {
        Category testCat = new Category();
        assertEquals(testCat.getName(), "Overall");
    }

    /**
     * Test method for {@link model.assignments_categories.Category#getPercentCurve()}.
     */
    @Test
    public void testGetPercentCurve()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.assignments_categories.Category#setPercentCurve(java.lang.Double)}.
     */
    @Test
    public void testSetPercentCurve()
    {
        fail("Not yet implemented");
    }

}
