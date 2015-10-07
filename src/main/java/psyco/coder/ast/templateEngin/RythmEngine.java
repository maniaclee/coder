package psyco.coder.ast.templateEngin;

import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.Test;
import org.rythmengine.Rythm;
import psyco.coder.ast.parser.ASTParser;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Created by peng on 15/10/7.
 */
public class RythmEngine {


    public static Pair<List<String>, Object[]> map2pair(Map<String, Object> map) {
        List<String> ks = Lists.newArrayList(map.keySet());
        return Pair.of(ks, ks.stream().map(k -> map.get(k)).collect(Collectors.toList()).toArray());
    }

    public Map<String, Object> builderx(InputStream java) throws Exception {
        ASTParser ast = ASTParser.parse(IOUtils.toString(java));
        TypeDeclaration clz = ast.findClass();
        List<FieldDeclaration> fs = ast.fields(clz);
        fs.forEach(e -> {
            System.out.println(e);
        });
        return new TreeMap() {{
            put("fields", fs);
            put("builderName", clz.getName().toString() + "Builder");
            put("className", clz.getName().toString());
        }};
    }

    public Object[] builder(InputStream java) throws Exception {
        ASTParser ast = ASTParser.parse(IOUtils.toString(java));
        TypeDeclaration clz = ast.findClass();
        System.out.println("xxxx==="+ast.setters(clz));
        return new Object[]{ast.setters(clz), clz.getName().toString() + "Builder", clz.getName().toString()};
    }

    @Test
    public void est() throws Exception {
        Object[] re = builder(new FileInputStream("/Users/peng/workspace/github/coder/src/main/java/psyco/coder/Shit.java"));
//        System.out.println(Rythm.render("hello @whos! @sdf", "rythm", "shit"));
//        System.out.println(re);
        System.out.println(Rythm.render(IOUtils.toString(RythmEngine.class.getClassLoader().getResourceAsStream("Builder.java")), re));
    }

}
