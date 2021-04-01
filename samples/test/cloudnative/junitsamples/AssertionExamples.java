package cloudnative.junitsamples;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AssertionExamples {

    @BeforeAll
    static void setUpBeforeClass() throws Exception {

    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {

    }

    @BeforeEach
    void setUp() throws Exception {

    }

    @AfterEach
    void tearDown() throws Exception {

    }

    @Test
    void emptyTest() {
        /*
         * JUnit tests fail by throwing an AssertionFailedError, which is caught by the framework.
         * Test methods that end normally, regardless of whether they have any actual assertions in them, are passing tests.
         */
    }

    @Test
    void testFail() {
        /*
         * The most general way to write tests is to use the fail method, which simply throws an AssertionFailedError with
         * whatever method you give it. This allows you to have complex validation logic without having to figure out how
         * to write it in a way that fits a JUnit assertion type.
         */
        if (false) {fail("Condition 1 not met");}
        else if(false) {fail("Condition 2 not met");}
        else if(true) {fail("Condition 3 not met");}
        else if(true) {fail("Condition 4 not met");}

        /*
         * Note that since a failure throws an exception, only one failure per test will be reported. This is true of any
         * test with multiple assertions.
         */

    }

    @Test
    void trueTest() {
        /*
         * The next most general options are assertTrue() and assertFalse(). These take boolean conditions that, as their
         * names imply, should be either true or false respectively. This allows you to assert based on complex conditions
         */
        assertTrue(Math.abs(-4) == 4 && Math.sqrt(Math.abs(-4)) == 2, "Absolute Value of 4 is not 2");

        /*
         * As with fail(), JUnit can't give any useful information if an assertTrue or assertFalse fails, so it's important to have a message
         */

    }

    @Test
    void testEquals() {
        /*
         * assertEquals() and assertNotEquals() compare parameters based on .equals()
         * for object types and == for primatives.
         * The first parameter is your expected value and the second is your actual value.
         * JUnit does give a reasonable message indicating the expected and actual values.
         */

        assertEquals(2, Math.sqrt(4));

        /*
         * Recall, of course, that comparing doubles for equality is dangerous due to the imprecision of doubles.
         * For instance: assertEquals(7.14, 3.14 + 4) will fail as the actual values for 7.14 are off in opposite directions
         * (closest double above and closest double below). For this reason if we have two doubles we can include a third
         * double parameter to define the delta, or acceptable error. This can be an actual experimental delta or just one
         * to avoid imprecision problems.  In the latter case, using something like expected/1000000000000.0 is generally good.
         */

        assertNotEquals(7.14, 3.14 + 4);
        assertEquals(7.14, 3.14 + 4, 7.14/100000000000.0);

    }

    @Test
    void sameTest() {
        /*
         * assertSame() and assertNotSame() compare parameters based on == for all
         * types, thus for objects checking whether they are the same object, not the
         * same contents.
         */
        String s1 = "Hello";
        String s2 = "Hello";
        assertSame(s1, s2);

        String s3 = "H";
        s3 = s3 + s1.substring(1);
        assertNotSame(s1, s3);

        /*
         * These take advantage of the way the JVM handles immutable objects. If the object of a new reference is
         * trivially equal to an existing object, the new reference will just be assigned that object to save memory.
         * If it's equal to an existing object but it's not trivial to verify that at runtime, a new object is created
         * to avoid wasting processor cycles.
         * Thus, never use == to check Strings unless you're just curious whether the JVM created a new object or not,
         * as the result isn't completely predictable.
         */


    }

    @Test
    void nullTest() {
        /*
         * assertNull and assertNotNull test for whether an object exists or not.
         */
        String s = null;

        assertNull(s);

        s = "";

        assertNotNull(s);

    }

    @Test
    void throwstest() {
        /*
         * assertThrows checks that the action in question throws the
         * expected exception and fails if it does not
         */

        String s = null;

        assertThrows(NullPointerException.class, () -> s.length());

        /*
         * The second parameter here is a function, which would typically be written using a Lambda.
         * If you're uncomfortable with Lambdas or have functionality that's too complex to use one,
         * You can also use the old-school technique using try/catch and fail
         */

        try {
            int a = s.length();
            fail("String should be null but is not");
        }
        catch(NullPointerException e) {
            //Nothing needed here.
        }

        /*
         * As always with exceptions, check for the most specific exception that applies. Any other exception
         * should be allowed to be thrown and will be reported as an error in the test run.
         */

    }
}

