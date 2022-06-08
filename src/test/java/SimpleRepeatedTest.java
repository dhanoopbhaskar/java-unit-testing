import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleRepeatedTest {
    @RepeatedTest(name = "{displayName} - {currentRepetition}/{totalRepetitions}",
            value = 5)
    @DisplayName("Repeated test")
    public void simpleRepeatedTest() {
        assertTrue(0 < 5);
    }
}