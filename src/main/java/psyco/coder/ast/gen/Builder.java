package psyco.coder.ast.gen;

import org.apache.commons.io.IOUtils;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.Test;
import org.rythmengine.Rythm;
import psyco.coder.ast.parser.ClassParser;

import java.io.FileInputStream;

/**
 * Created by peng on 15/10/8.
 */
public class Builder {

    private static Object[] builder(String s) throws Exception {
        ClassParser ast = ClassParser.parse(s);
        TypeDeclaration clz = ast.findClass();
        return new Object[]{ast.fields(clz), clz.getName().toString() + "Builder", clz.getName().toString()};
    }

    public static String gen(String s) throws Exception {
        return Rythm.render(IOUtils.toString(Builder.class.getClassLoader().getResourceAsStream("template/Builder.javaTM")), builder(s));
    }

    @Test
    public void sdfsd() throws Exception {
        String s = IOUtils.toString(new FileInputStream("/Users/peng/workspace/github/coder/src/main/java/psyco/coder/ast/parser/Shit.java"));
        System.out.println(gen(s));
    }

}
