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
 * Created by peng on 15/10/11.
 */
public class JavabeanCoder {

    public static ParamClass bean(String s, String pack) throws Exception {
        return bean(s, pack, ParamClass.class);
    }

    public static ParamClass bean(String s, String pack, Class<? extends ParamClass> paramClass) throws Exception {
        ClassParser ast = ClassParser.parse(s);
        TypeDeclaration clz = ast.findClass();
        return paramClass.getConstructor(String.class, String.class, List.class)
                .newInstance(clz.getName().toString(), pack,
                        ast.fields(clz).stream().map(f -> {
                                    String fn = ClassParser.getFieldName(f);
                                    return new ParamField(fn, CaseUtil.setter(fn), CaseUtil.getter(fn), f.getType().toString());
                                }
                        ).collect(Collectors.toList()));
    }


    public static ParamClass builder(String s, String pack) throws Exception {
        BuilderParam b = (BuilderParam) bean(s, pack, BuilderParam.class);
        b.setBuilderClassName(b.getClassName() + "Builderxxx");
        return b;
    }

    @Test
    public void sdfsd() throws Exception {
        String s = IOUtils.toString(new FileInputStream("/Users/peng/workspace/github/coder/src/main/java/psyco/coder/ast/parser/Shit.java"));
//        System.out.println(BeetlEngine.render("/template/Builder.btl", ImmutableMap.of("bp", builder(s, ""))));
        System.out.println(BeetlEngine.render("/template/bean.btl", ImmutableMap.of("bean", bean(s, ""))));
    }

    public static class BuilderParam extends ParamClass {
        public String builderClassName;

        public String getBuilderClassName() {
            return builderClassName;
        }

        public void setBuilderClassName(String builderClassName) {
            this.builderClassName = builderClassName;
        }
    }

    @Deprecated
    static String gen(String s) throws Exception {
        return Rythm.render(IOUtils.toString(JavabeanCoder.class.getClassLoader().getResourceAsStream("template/Builder.javaTM")), bean(s, ""), Lists.newArrayList(1, 4, 3, 2));
    }

}
