package psyco.coder.engine;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.io.IOUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.junit.Test;
import org.rythmengine.Rythm;
import psyco.coder.gen.CoderBuilder;
import psyco.coder.gen.CoderJavabean;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Created by peng on 15/10/11.
 */
public class BeetlEngine {
    public static String render(String template, Map<String, Object> map) throws IOException {
        Template t = loadTemplate(template);
        for (String s : map.keySet())
            t.binding(s, map.get(s));
        return t.render();
    }

    public static String render(String template, String key, Object obj) throws IOException {
        Template t = loadTemplate(template);
        t.binding(key, obj);
        return t.render();
    }

    public static String render(String template, ImmutableMap<String, Object> map) throws IOException {
        Template t = loadTemplate(template);
        for (String s : map.keySet())
            t.binding(s, map.get(s));
        return t.render();
    }

    public static Template loadTemplate(String template) throws IOException {
        ClasspathResourceLoader resourceLoader = new ClasspathResourceLoader();
        Configuration cfg = Configuration.defaultConfiguration();
        GroupTemplate gt = new GroupTemplate(resourceLoader, cfg);
        gt.registerFunctionPackage("psyco", BeetlFunctions.class);
        return gt.getTemplate(template);
    }


    @Test
    public void sdfsd() throws Exception {
        String s = IOUtils.toString(new FileInputStream("/Users/peng/workspace/github/coder/src/main/java/psyco/coder/ast/parser/Shit.java"));
//        System.out.println(CoderJavabean.exec(s, ""));
        System.out.println(CoderBuilder.exec(s, ""));
    }

    @Deprecated
    static String gen(String s) throws Exception {
        return Rythm.render(IOUtils.toString(CoderJavabean.class.getClassLoader().getResourceAsStream("template/Builder.javaTM")), "sdf");
    }
}

