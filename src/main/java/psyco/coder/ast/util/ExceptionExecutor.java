package psyco.coder.ast.util;

import psyco.coder.ast.core.WebResponse;

/**
 * Created by peng on 15/10/8.
 */
public class ExceptionExecutor {


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

    public static <T> T toRuntimeException(ThrowingSupplier<T> fn, String errorMsg) {
        try {
            return fn.get();
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
