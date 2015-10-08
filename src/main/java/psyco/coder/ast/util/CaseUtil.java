package psyco.coder.ast.util;

import com.google.common.base.CaseFormat;

/**
 * Created by peng on 15/10/8.
 */
public class CaseUtil {

    public static String setter(String s) {
        return "set" + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, s);
    }

    public static String getter(String s) {
        return "get" + CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, s);
    }
}
