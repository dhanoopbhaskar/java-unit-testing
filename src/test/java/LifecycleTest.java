import org.junit.jupiter.api.*;

@DisplayName("LifecycleTest class for testing JUnit Lifecycles")
public class LifecycleTest {

    @BeforeAll
    public static void beforeAll(TestInfo testInfo) {
        System.out.println("LifecycleTest - beforeAll() got executed !!!");

        System.out.println("beforeAll() got executed with test info as - ");
        System.out.println("Display name - " + testInfo.getDisplayName());
        System.out.println("Test Class - " + testInfo.getTestClass());
        System.out.println("Test Method - " + testInfo.getTestMethod());
        System.out.println("*******************************************");
    }

    public LifecycleTest() {
        System.out.println("LifecycleTest - Constructor got executed !!!");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("LifecycleTest - beforeEach() got executed !!!");
    }

    @Test
    public void testOne() {
        System.out.println("LifecycleTest - testOne() got executed !!!");
    }

    @Test
    public void testTwo() {
        System.out.println("LifecycleTest - testTwo() got executed !!!");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("LifecycleTest - afterEach() got executed !!!");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("LifecycleTest - afterAll() got executed !!!");
    }

}