package cucumber.runtime.java;

import cucumber.fallback.runtime.java.DefaultJavaObjectFactory;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertTrue;

/**
 * @author rmannibucau
 */
public class ObjectFactoryHolderTest {
    @Test
    public void testFactory() throws Exception {
        ObjectFactoryHolder.setFactory(new MockObjectFactory());
        JavaBackend backend = new JavaBackend();

        // do it by reflection to not change the API
        Field field = JavaBackend.class.getDeclaredField("objectFactory");
        field.setAccessible(true);
        assertTrue(field.get(backend) instanceof MockObjectFactory);

        ObjectFactoryHolder.setFactory(null);
        backend = new JavaBackend();
        assertTrue(field.get(backend) instanceof DefaultJavaObjectFactory);
    }

    public static class MockObjectFactory implements ObjectFactory {
        @Override
        public void createInstances() { }
        @Override
        public void disposeInstances() { }
        @Override
        public void addClass(Class<?> clazz) { }
        @Override
        public <T> T getInstance(Class<T> type) { return null; }
    }
}