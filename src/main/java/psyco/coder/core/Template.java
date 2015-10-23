package psyco.coder.core;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by peng on 15/10/24.
 */
@Target({METHOD})
@Retention(RUNTIME)
public @interface Template {
    String value();
}
