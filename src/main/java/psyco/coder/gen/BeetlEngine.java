package psyco.coder.gen;

import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;

import java.io.IOException;
import java.util.Map;

/**
 * Created by peng on 15/10/11.
 */
public class BeetlEngine {
    public static String render(String template, Map<String, Object> map) throws IOException {
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
        Configuration cfg = Configuration.defaultConfiguration();
//        cfg.setPlaceholderStart("@");
//        cfg.setPlaceholderEnd("@");
//        String del="@";
//        cfg.setStatementStart(del);
//        cfg.setPlaceholderEnd(del+"}");
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        Template t = gt.getTemplate(template);
        for (String s : map.keySet())
            t.binding(s, map.get(s));
        return t.render();
    }
}
