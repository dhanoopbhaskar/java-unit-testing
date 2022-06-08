package mockito;

import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MockitoWhenThenCookbookTests {

    @Test
    public final void whenMockReturnBehaviorIsConfigured_thenBehaviorIsVerified() {
        final MyList listMock = mock(MyList.class);

        when(listMock.add(anyString())).thenReturn(false);

        final boolean added = listMock.add(randomAlphabetic(6));
        assertFalse(added);
    }

    @Test
    public final void whenMockReturnBehaviorIsConfigured2_thenBehaviorIsVerified() {
        final MyList listMock = mock(MyList.class);

        doReturn(false).when(listMock).add(anyString());

        final boolean added = listMock.add(randomAlphabetic(6));
        assertFalse(added);
    }

    @Test
    public final void givenMethodIsConfiguredToThrowException_whenCallingMethod_thenExceptionIsThrown() {
        final MyList listMock = mock(MyList.class);

        when(listMock.add(anyString())).thenThrow(IllegalStateException.class);

        assertThrows(IllegalStateException.class, () -> listMock.add(randomAlphabetic(6)));
    }

    @Test
    public final void whenMethodHasNoReturnType_whenConfiguringBehaviorOfMethod_thenPossible() {
        final MyList listMock = mock(MyList.class);

        doThrow(NullPointerException.class).when(listMock).clear();

        assertThrows(NullPointerException.class, listMock::clear);
    }

    @Test
    public final void givenBehaviorIsConfiguredToThrowExceptionOnSecondCall_whenCallingOnlyOnce_thenNoExceptionIsThrown() {
        final MyList listMock = mock(MyList.class);

        when(listMock.add(anyString())).thenReturn(false).thenThrow(IllegalStateException.class);

        listMock.add(randomAlphabetic(6));
    }

    @Test
    public final void givenBehaviorIsConfiguredToThrowExceptionOnSecondCall_whenCallingTwice_thenExceptionIsThrown() {
        final MyList listMock = mock(MyList.class);

        when(listMock.add(anyString())).thenReturn(false).thenThrow(IllegalStateException.class);

        assertThrows(IllegalStateException.class, () -> {
            listMock.add(randomAlphabetic(6));
            listMock.add(randomAlphabetic(6));
        });
    }

    @Test
    public final void whenMockMethodCallIsConfiguredToCallTheRealMethod_thenRealMethodIsCalled() {
        final MyList listMock = mock(MyList.class);

        when(listMock.size()).thenCallRealMethod();

        assertEquals(0, listMock.size());
    }

    @Test
    public final void whenMockMethodCallIsConfiguredWithCustomAnswer_thenRealMethodIsCalled() {
        final MyList listMock = mock(MyList.class);

        doAnswer(invocation -> "Always the same").when(listMock).get(anyInt());

        final String element = listMock.get(1);
        assertEquals("Always the same", element);
    }

    @Test
    public final void givenSpy_whenConfiguringBehaviorOfSpy_thenCorrectlyConfigured() {
        final MyList instance = new MyList();
        final MyList spy = spy(instance);

        doThrow(NullPointerException.class).when(spy).size();

        assertThrows(NullPointerException.class, spy::size);
    }
}
