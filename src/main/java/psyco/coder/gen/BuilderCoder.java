package psyco.coder.gen;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.Test;
import org.rythmengine.Rythm;
import psyco.coder.ast.parser.ClassParser;
import psyco.coder.ast.util.CaseUtil;
import psyco.coder.gen.param.ParamClass;
import psyco.coder.gen.param.ParamField;

import java.io.FileInputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by peng on 15/10/8.
 */
public class BuilderCoder {

    public static BuilderParam builder(String s) throws Exception {
        ClassParser ast = ClassParser.parse(s);
        TypeDeclaration clz = ast.findClass();
        return new BuilderParam(clz.getName().toString(), clz.getName().toString() + "BuilderCoder",
                ast.fields(clz).stream().map(f -> {
                            String fn = ClassParser.getFieldName(f);
                            return new ParamField(fn, CaseUtil.setter(fn), CaseUtil.getter(fn), f.getType().toString());
                        }
                ).collect(Collectors.toList()));
    }

    public static String gen(String s) throws Exception {
        return Rythm.render(IOUtils.toString(BuilderCoder.class.getClassLoader().getResourceAsStream("template/Builder.javaTM")), builder(s), Lists.newArrayList(1, 4, 3, 2));
    }


    @Test
    public void sdfsd() throws Exception {
        String s = IOUtils.toString(new FileInputStream("/Users/peng/workspace/github/coder/src/main/java/psyco/coder/ast/parser/Shit.java"));
//        System.out.println(gen(s));
        System.out.println(BeetlEngine.render("/template/Builder.btl", ImmutableMap.of("bp", builder(s))));
    }


    public static class BuilderParam extends ParamClass {
        public String builderClassName;

        public BuilderParam(String className, String builderClassName, List<ParamField> fields) {
            super(className, fields);
            this.builderClassName = builderClassName;
        }

        public String getBuilderClassName() {
            return builderClassName;
        }

        public void setBuilderClassName(String builderClassName) {
            this.builderClassName = builderClassName;
        }
    }


}
