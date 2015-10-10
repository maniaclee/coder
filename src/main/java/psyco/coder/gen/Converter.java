package psyco.coder.gen;

import org.eclipse.jdt.core.dom.TypeDeclaration;
import psyco.coder.ast.parser.ClassParser;

/**
 * Created by peng on 15/10/8.
 */
public class Converter {

    public static Object[] build(String content, String targetClassName) throws Exception {
        ClassParser ast = ClassParser.parse(content);
        TypeDeclaration clz = ast.findClass();
        return new Object[]{ast.fields(clz), clz.getName().toString(), targetClassName};
    }
}
