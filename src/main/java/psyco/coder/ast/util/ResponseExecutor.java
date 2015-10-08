package psyco.coder.ast.util;

import psyco.coder.ast.core.WebResponse;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by peng on 15/10/8.
 */
public class ResponseExecutor {

    @FunctionalInterface
    public static interface ThrowingConsumer<T> extends Consumer<T> {

        @Override
        default void accept(final T elem) {
            try {
                acceptThrows(elem);
            } catch (final Exception e) {
            /* Do whatever here ... */
                System.out.println("handling an exception...");
                throw new RuntimeException(e);
            }
        }

        void acceptThrows(T elem) throws Exception;

    }

    @FunctionalInterface
    public static interface ThrowingSupplier<T> extends Supplier<T> {

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

    public static void throwss(String s) {
        throw new RuntimeException(s);
    }


    public static void check(boolean notOk, String errorMsg) {
        if (notOk)
            throwss(errorMsg);
    }

    public static WebResponse exec(ThrowingSupplier<Object> fn) {
        try {
            return WebResponse.response(fn.get());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return WebResponse.error(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return WebResponse.error(e.getMessage());
        }
    }
}
