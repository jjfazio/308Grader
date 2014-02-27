/**
 *
 */
package implementation.model.assignments_categories;

import static org.junit.Assert.*;
import model.assignments_categories.Assignment;
import model.assignments_categories.Category;

import org.junit.Before;
import org.junit.Test;

/****
 *
 * Class CategoryTest is the companion testing class for class
 * {@link model.assignments_categories.Category}
 * It implements the following module test plan:
 *                                                                         <ul>
 *                                                                      <p><li>
 *     Phase 1: Unit test the constructors.
 *                                                                      <p><li>
 *     Phase 2: Unit test the access methods getSubCategories, getPercentCurve
 *              getPercentOfParent, getName
 *                                                                      <p><li>
 *     Phase 3: Unit test the constructive methods addAssignment, addSubCategory
 *
 *                                                                      <p><li>
 *     Phase 4: Unit test editAssignment, editCategory, setName, setPercentCurve
 *              methods
 *                                                                      <p><li>
 *     Phase 5: Unit test removeAssignment, removeSubCategory methods
 *                                                                      <p><li>
 *     Phase 6: Stress test by creating and deleting 1000 of assignment
 *              and categories
 *
 *                                                                        </ul>
 *     @author Jirbert Dilanchian
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
    /**
     * Unit test creating an instance root Category.
     *  <pre>
     *  Test
     *  Case    Input                    Output                                   Remarks
     * ====================================================================
     *   1      none                Properly instantiate intance of Category        Only Case
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
    /**
     * Unit test creating an instance of category which is subcategory of its parent category.
     * unacceptable inputs have already been handled by the categoryContainer
     *  <pre>
     *  Test
     *  Case    Input                            Output                             Remarks
     * ====================================================================
     *   1      paretCategory, 50, "name"       Properly creates an instance of Category        Only Case
     *                                         and adds it to subcategories of parent Category
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
//        assertEquals(testCat.getSubCategories().size(), 3);
    }

    /**
     * Test method for {@link model.assignments_categories.Category#addAssignment(model.assignments_categories.Assignment)}.
     */
    @Test
    public void testAddAssignment()
    {
    }

    /**
     * Test method for {@link model.assignments_categories.Category#editAssignment(model.assignments_categories.Assignment, model.assignments_categories.Assignment)}.
     */
    @Test
    public void testEditAssignment()
    {
    }

    /**
     * Test method for {@link model.assignments_categories.Category#removeAssignment(model.assignments_categories.Assignment)}.
     */
    @Test
    public void testRemoveAssignment()
    {
    }

    /**
     * Test method for {@link model.assignments_categories.Category#addSubCategory(model.assignments_categories.Category)}.
     */
    @Test
    public void testAddSubCategory()
    {
    }

    /**
     * Test method for {@link model.assignments_categories.Category#editCategory(model.assignments_categories.Category)}.
     */
    @Test
    public void testEditCategory()
    {
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
//        assert(testCat.getSubCategories().size() == 1);
    }

    /**
     * Test method for {@link model.assignments_categories.Category#getParentCategory()}.
     */
    @Test
    public void testGetParentCategory()
    {
    }

    /**
     * Test method for {@link model.assignments_categories.Category#getName()}.
     */
    /**
     * Unit test returning the name of category
     *  <pre>
     *  Test
     *  Case    Input                            Output                             Remarks
     * ====================================================================
     *   1      none                    Properly returns the name of category        Only Case
     *
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
    /**
     * Unit test returning the name of category. Empty string case has already been handled by
     * CategoryContainer
     *  <pre>
     *  Test
     *  Case    Input                            Output                             Remarks
     * ====================================================================
     *   1      "newName"                    Properly sets the name of category        Only Case
     *
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
    /**
     * Unit test returning the percent of parent of category.
     *  <pre>
     *  Test
     *  Case    Input                            Output                             Remarks
     * ====================================================================
     *   1      none                   Properly returns the percent of parent         Only Case
     *
     */

    @Test
    public void testGetPercentofparent()
    {
        Category testCat = new Category();
        assert(testCat.getPercentOfParent() == 100.0);
    }

    /**
     * Test method for {@link model.assignments_categories.Category#getPercentCurve()}.
     */
    @Test
    public void testGetPercentCurve()
    {
    }

    /**
     * Test method for {@link model.assignments_categories.Category#setPercentCurve(java.lang.Double)}.
     */
    @Test
    public void testSetPercentCurve()
    {
    }

}
