package mockito;

import org.junit.jupiter.api.Test;
import org.mockito.MockSettings;
import org.mockito.exceptions.verification.TooFewActualInvocations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MockMethodsTest {

    @Test
    public void whenUsingSimpleMock_thenCorrect() {
        MyList listMock = mock(MyList.class);
        when(listMock.add(anyString())).thenReturn(false);

        boolean added = listMock.add(randomAlphabetic(6));

        verify(listMock).add(anyString());
        assertEquals(false, added);
    }

    @Test
    public void whenUsingMockWithName_thenCorrect() {
        MyList listMock = mock(MyList.class, "myMock");
        when(listMock.add(anyString())).thenReturn(false);

        listMock.add(randomAlphabetic(6));

        TooFewActualInvocations exception = assertThrows(TooFewActualInvocations.class, () -> {
            verify(listMock, times(2)).add(anyString());
        });

        assertEquals(TooFewActualInvocations.class, exception.getClass());
        assertTrue(exception.getMessage().contains("myMock.add"));
    }

    @Test
    public void whenUsingMockWithAnswer_thenCorrect() {
        MyList listMock = mock(MyList.class, new CustomAnswer());

        boolean added = listMock.add(randomAlphabetic(6));

        verify(listMock).add(anyString());

        assertEquals(false, added);
    }

    private static class CustomAnswer implements Answer<Boolean> {

        @Override
        public Boolean answer(InvocationOnMock invocationOnMock) throws Throwable {
            return false;
        }
    }

    @Test
    public void whenUsingMockWithSettings_thenCorrect() {
        MockSettings customSettings = withSettings().defaultAnswer(new CustomAnswer());

        MyList listMock = mock(MyList.class, customSettings);
        boolean added = listMock.add(randomAlphabetic(6));

        verify(listMock).add(anyString());
        assertEquals(false, added);
    }
}
