package psyco.coder.util;

import java.util.function.Supplier;

@FunctionalInterface
public interface ThrowingSupplier<T> extends Supplier<T> {

    @Override
    default T get() {
        try {
            return getT();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    T getT() throws Exception;
}