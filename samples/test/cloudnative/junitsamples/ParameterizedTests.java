package cloudnative.junitsamples;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Month;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

public class ParameterizedTests {
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

    /*
     * For simple tests with only one variable element we can use
     * a ValueSource to establish out parameters. The test method takes a
     * single argument which matches the type of the arguments in the
     * ValueSource list.
     */

    @ParameterizedTest
    @ValueSource(strings = {"March", "January", "May"})
    void isMonth(String input) {
        for(Month m : Month.values()) {
            if (m.name().equalsIgnoreCase(input)) return;
        }
        fail(input + " is not a month name.");
    }

    /*
     * We can use an enumeration as our source. Here, too,
     * we're limited to a single value per test.
     */

    @ParameterizedTest
    @EnumSource(Month.class)
    void noMonthsOverThirtyOneDays(Month month) {
        assertTrue(month.maxLength() <= 31);
    }

    /*
     * We can limit what items from our enum we're passing
     * into our test.
     * In this case we're limiting by the names property and
     * specifying that we're excluding the listed name.
     * Default is to include the listed items.
     */

    @ParameterizedTest
    @EnumSource(
            value = Month.class,
            names = {"FEBRUARY"},
            mode = EnumSource.Mode.EXCLUDE)
    void monthsThirtyPlusDaysExceptFebruary(Month month) {
        assertTrue(month.length(false) >= 30);
    }

    /*
     * We often need to run tests with more than one variable value,
     * such as an expected result and one or more parameters to
     * the method being tested. We can do this using comma-separated value
     * (CSV) sources.  Comma is the default delimiter, but it can be
     * customized to whatever.
     * Our CSVs can be literals, as below, or files.
     */

    @ParameterizedTest
    @CsvSource({"3, 3", "-3, 3", "-12.7, 12.7", "-3.2, 3.2"})
    void mathAbsTest(double input, double result) {
        assertEquals(result, Math.abs(input));
    }

    /*
     * For a CSV file, the method itself remains the same.
     * The annotation is
     * @CsvFileSource(resources = "path/file", numLinesToSkip = #)
     * numLinesToSkip allows you to skip header information easily.
     * Leave it out if it doesn't apply to your file.
     */
}
