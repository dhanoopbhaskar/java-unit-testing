package mockito;

import java.util.AbstractList;

public class MyList extends AbstractList<String> {
    @Override
    public String get(int index) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void add(int index, String element) {
        // no-op
    }

    final public int finalMethod() {
        return 0;
    }
}
