package psyco.coder.util;

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

    public CaseFormat detectFormat(String s) {
        if (s.contains("_")) {
            return isLower(s.charAt(0)) ? CaseFormat.LOWER_UNDERSCORE : CaseFormat.LOWER_UNDERSCORE;
        }
        if (s.contains("-"))
            return CaseFormat.LOWER_HYPHEN;
        return isLower(s.charAt(0)) ? CaseFormat.LOWER_CAMEL : CaseFormat.UPPER_CAMEL;
    }

    public static boolean isLower(char a) {
        return !(a >= 'A' && a <= 'Z');
    }

    public static boolean isUpper(char a) {
        return !(a >= 'a' && a <= 'z');
    }
}
