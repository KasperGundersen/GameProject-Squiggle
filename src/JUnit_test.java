import org.junit.jupiter.api.*;

public class JUnit_test {
    private static JUnit_test instance;

    @BeforeAll
    public static void setUpClass() throws Exception {
        // Create objects of the classes that are to be tested
    }


    @AfterAll
    public  static void tearDownClass() {
        // Runs once, after all the test methods have completed
    }

    @BeforeEach
    public void setUp() throws Exception {
        // Runs before each test
        instance = new JUnit_test();
    }

    @AfterEach
    public void tearDown() {
        // Runs after each test
    }

    // Test of given method ......
    @Test
    public void ExampleTest() throws Exception {
        // String actualResult = someObject.someMethod();
        // String expectedResult = "expected result";
        // assertEquals(actualResult, expectedResult);
    }
}
