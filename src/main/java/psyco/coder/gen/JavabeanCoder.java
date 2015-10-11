package psyco.coder.gen;

import org.eclipse.jdt.core.dom.TypeDeclaration;
import psyco.coder.ast.parser.ClassParser;
import psyco.coder.ast.util.CaseUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by peng on 15/10/11.
 */
public class JavabeanCoder {
    public static class Field {
        public String type;
        public String name;
        public String getter;
        public String setter;

        public Field(String type, String name, String getter, String setter) {
            this.type = type;
            this.name = name;
            this.getter = getter;
            this.setter = setter;
        }
    }

    public static class BeanClass extends ParamBase {
        public String name;
        public String pack;
        public List<Field> fields;

        public BeanClass(String name, String pack, List<Field> fields) {
            this.name = name;
            this.pack = pack;
            this.fields = fields;
        }
    }

    public static BeanClass builder(String s) throws Exception {
        ClassParser ast = ClassParser.parse(s);
        TypeDeclaration clz = ast.findClass();
        return new BeanClass(clz.getName().toString(), "",
                ast.fields(clz).stream().map(f -> {
                            String fn = ClassParser.getFieldName(f);
                            return new Field(fn, CaseUtil.setter(fn), CaseUtil.getter(fn), f.getType().toString());
                        }
                ).collect(Collectors.toList()));
    }


}
