package psyco.coder.gen;

import com.google.common.collect.ImmutableMap;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import psyco.coder.ast.parser.ClassParser;
import psyco.coder.component.bean.BeanClass;
import psyco.coder.component.bean.BeanField;
import psyco.coder.engine.BeetlEngine;
import psyco.coder.util.CaseUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by peng on 15/10/11.
 */
public class CoderJavabean {

    public static BeanClass bean(String s) throws Exception {
        return bean(s, BeanClass.class);
    }


    public static BeanClass bean(String s, Class<? extends BeanClass> paramClass) throws Exception {
        ClassParser ast = ClassParser.parse(s);
        TypeDeclaration clz = ast.findClass();
        return paramClass.getConstructor(String.class, List.class)
                .newInstance(clz.getName().toString(),
                        ast.fields(clz).stream().map(f -> {
                                    String fn = ClassParser.getFieldName(f);
                                    return new BeanField(fn, CaseUtil.setter(fn), CaseUtil.getter(fn), f.getType().toString());
                                }
                        ).collect(Collectors.toList()));
    }

    public static String exec(String s) throws Exception {
        return exec(bean(s));
    }

    public static String exec(BeanClass paramClass) throws Exception {
        return exec(paramClass, new BeanClassParameterExtend());
    }

    public static String exec(BeanClass paramClass, BeanClassParameterExtend ext) throws Exception {
        return BeetlEngine.render("/template/bean.btl", new ImmutableMap.Builder()
                .put("bean", paramClass)
                .put("ext", Optional.ofNullable(ext).orElse(new BeanClassParameterExtend()))
                .build());
    }

    public static class BeanClassParameterExtend {
        String dtoPackage;
        boolean generateBuilderMethod = false;

        public boolean isGenerateBuilderMethod() {
            return generateBuilderMethod;
        }

        public void setGenerateBuilderMethod(boolean generateBuilderMethod) {
            this.generateBuilderMethod = generateBuilderMethod;
        }

        public String getDtoPackage() {
            return dtoPackage;
        }

        public void setDtoPackage(String dtoPackage) {
            this.dtoPackage = dtoPackage;
        }
    }


}
