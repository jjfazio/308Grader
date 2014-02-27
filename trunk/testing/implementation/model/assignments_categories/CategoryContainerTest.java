package implementation.model.assignments_categories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import model.assignments_categories.Category;
import model.assignments_categories.CategoryContainer;
import model.exception.BadDataException;
import model.gradebook.Gradebook;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/****
 *
 * Class CategoryContainerTest is the companion testing class for class
 * {@link model.assignments_categories.CategoryContainer}
 * It implements the following module test plan:
 *                                                                         <ul>
 *                                                                      <p><li>
 *     Phase 1: Unit test the constructors.
 *                                                                      <p><li>
 *     Phase 2: Unit test the access method getAssignmentById, getRoot
 *                                                                      <p><li>
 *     Phase 3: Unit test the constructive methods addAssignment, addSubCategory
 *                                                                      <p><li>
 *     Phase 4: Unit test removeAssignment, removeSubCategory methods
 *                                                                      <p><li>
 *     Phase 5: Stress test by creating and deleting 1000 of assignment
 *              and categories
 *
 *                                                                        </ul>
 *     @author Jirbert Dilanchian
 */

public class CategoryContainerTest
{

    private Gradebook gradebook;

    @Before
    public void setUp() throws Exception
    {
        gradebook = Gradebook.getInstance();
    }

    /**
     * Empty constructor,
     */

    @Test
    public void testCategoryContainer()
    {
        CategoryContainer testContainer = new CategoryContainer();
        assert(testContainer != null);
    }

    /*-*
     * Individual unit testing methods for member methods
     */

    /**
     * Unit test by adding a Category by using CategoryContainer. The program needs to have instance
     * CategoryContainer which its constructor has already been tested.
     *  <pre>
     *  Test
     *  Case    Input                               Output             Remarks
     * ====================================================================
     *   1      Category, alpha String, String      Throws exception   alpha instead of double
     *   2      Category, "" , String               Throws exception   empty String instead of double
     *   3      Category, num String, ""            Throws exception   empty String for name
     */

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

    /**
     * Unit test removing a Category by using CategoryContainer. The program needs to have instance
     * CategoryContainer which its constructor has already been tested. Additionally it needs to have
     * a subCategory.
     *  <pre>
     *  Test
     *  Case    Input                    Output                 Remarks
     * ====================================================================
     *   1      Category, Category      Properly remove done    Only Case
     */


    @Test
    public void testRemoveCategory()
    {
        CategoryContainer testContainer = new CategoryContainer();
        try {
            testContainer.addCategory(testContainer.getRoot(), "23", "first");
        } catch (Exception e) {
            System.out.println("failed to make category");
        }
        testContainer.removeCategory(testContainer.getRoot(),
                (Category)((ArrayList)testContainer.getRoot().getSubCategories()).get(0));
        assert(testContainer.getRoot().getSubCategories().size() == 0);
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


    /**
     * Unit test getting the root Category by using CategoryContainer. The program needs to have instance
     * CategoryContainer which its constructor has already been tested.
     *  <pre>
     *  Test
     *  Case    Input                    Output                             Remarks
     * ====================================================================
     *   1      N/A                Properly returns the root Category       Only Case
     */

    @Test
    public void testGetRoot()
    {
        CategoryContainer testCont = new CategoryContainer();
        assertEquals(testCont.getRoot().getName(), "Overall");
    }

}
