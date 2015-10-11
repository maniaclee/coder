package psyco.coder.engine;

import java.util.List;

import static java.util.stream.Collectors.*;

/**
 * Created by peng on 15/10/11.
 */
public class BeetlFunctions {

    public String join(List<?> list, String seperator) {
        return list.stream().map(e -> e.toString()).collect(joining(seperator));
    }
}
