package implementation.model.users;

import model.users.Teacher;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/****
 *
 * Class TeacherTest is the companion testing class for class
 * {@link model.users.Teacher}
 * It implements the following module test plan:
 *                                                                         <ul>
 *                                                                      <p><li>
 *     Phase 1: Unit test the constructor.
 *                                                                      <p><li>
 *     Phase 2: Unit test the access methods getFirstName, getLastName,
 *              getUserName.
 *                                                                        </ul>
 *     @author Kevin Feutz
 */
public class TeacherTest
{
    /**
     * Test method for {@link model.users.Teacher#Teacher(String, String, String)}
     *
     * Construct a Teacher. Ensure that once the Teacher
     * is created that it is not null.
     *                                                                    <pre>
     *  Test
     *  Case    Input                                    Output             Remarks
     * =================================================================================
     *   1      Teacher("Kevin", "Feutz", "1234")        Success            Tests creating
     *                                                                      a new teacher
     *
     */
    @Test
    public void testConstructor()
    {
        Teacher teacher = new Teacher("Kevin", "Feutz", "1234");
        assertNotNull(teacher);
    }

    /**
     * Test method for {@link model.users.Teacher#getFirstName()}
     *
     * Construct a Teacher. Ensure that once the Teacher
     * is created that its first name is assigned.
     *                                                                    <pre>
     *  Test
     *  Case    Input                                    Output             Remarks
     * =================================================================================
     *   1      Teacher("Kevin", "Feutz", "1234")        firstName          Tests retrieving a
     *                                                      = "Kevin"       teacher's first name.
     *
     *
     */
    @Test
    public void testGetFirstName()
    {
        Teacher teacher = new Teacher("Kevin", "Feutz", "1234");
        assertEquals("Kevin", teacher.getFirstName());
    }

    /**
     * Test method for {@link model.users.Teacher#getLastName()}
     *
     * Construct a Teacher. Ensure that once the Teacher
     * is created that its last name is assigned.
     *                                                                    <pre>
     *  Test
     *  Case    Input                                    Output             Remarks
     * =================================================================================
     *   1      Teacher("Kevin", "Feutz", "1234")        lastName          Tests retrieving a
     *                                                      = "Feutz"      teacher's last name.
     *
     *
     */
    @Test
    public void testGetLastName()
    {
        Teacher teacher = new Teacher("Kevin", "Feutz", "1234");
        assertEquals("Feutz", teacher.getLastName());
    }

    /**
     * Test method for {@link model.users.Teacher#getUserName()}
     *
     * Construct a Teacher. Ensure that once the Teacher
     * is created that its username is assigned.
     *                                                                    <pre>
     *  Test
     *  Case    Input                                    Output             Remarks
     * =================================================================================
     *   1      Teacher("Kevin", "Feutz", "1234")        username          Tests retrieving a
     *                                                      = "1234"       teacher's username.
     *
     *
     */
    @Test
    public void testGetUserName()
    {
        Teacher teacher = new Teacher("Kevin", "Feutz", "1234");
        assertEquals("1234", teacher.getUserName());
    }
}
