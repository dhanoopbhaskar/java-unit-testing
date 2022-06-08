package mockito;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.VerificationCollector;

class LazyVerificationExtension implements BeforeEachCallback, AfterEachCallback {

    private static final ThreadLocal<VerificationCollector> threadLocal = new ThreadLocal<>();

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        threadLocal.set(MockitoJUnit.collector().assertLazily());
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        VerificationCollector collector = threadLocal.get();
        threadLocal.set(null);
        collector.collectAndReport();
    }
    
}
