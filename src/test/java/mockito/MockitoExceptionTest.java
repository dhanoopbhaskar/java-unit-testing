package mockito;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class MockitoExceptionTest {

    @Test
    public void whenConfigNonVoidReturnMethodToThrowEx_thenExIsThrown() {
        MyDictionary dictMock = mock(MyDictionary.class);

        when(dictMock.getMeaning(anyString())).thenThrow(NullPointerException.class);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            dictMock.getMeaning("word");
        });
    }

    @Test
    public void whenConfigVoidReturnMethodToThrowEx_thenExIsThrown() {
        MyDictionary dictMock = mock(MyDictionary.class);

        doThrow(IllegalStateException.class).when(dictMock)
                .add(anyString(), anyString());

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            dictMock.add("word", "meaning");
        });
    }

    @Test
    public void whenConfigNonVoidReturnMethodToThrowExWithNewExObj_thenExIsThrown() {
        MyDictionary dictMock = mock(MyDictionary.class);

        when(dictMock.getMeaning(anyString())).thenThrow(new NullPointerException("Error occurred"));

        Exception exception = assertThrows(NullPointerException.class, () -> {
            dictMock.getMeaning("word");
        });
    }

    @Test
    public void whenConfigVoidReturnMethodToThrowExWithNewExObj_thenExIsThrown() {
        MyDictionary dictMock = mock(MyDictionary.class);

        doThrow(new IllegalStateException("Error occurred")).when(dictMock)
                .add(anyString(), anyString());

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            dictMock.add("word", "meaning");
        });
    }

    @Test
    public void givenSpy_whenConfigNonVoidReturnMethodToThrowEx_thenExIsThrown() {
        MyDictionary dict = new MyDictionary();
        MyDictionary spy = spy(dict);

        when(spy.getMeaning(anyString())).thenThrow(NullPointerException.class);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            spy.getMeaning("word");
        });
    }
}
