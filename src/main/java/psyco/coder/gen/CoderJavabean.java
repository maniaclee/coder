package psyco.coder.gen;

import org.eclipse.jdt.core.dom.TypeDeclaration;
import psyco.coder.ast.parser.ClassParser;
import psyco.coder.ast.util.CaseUtil;
import psyco.coder.engine.BeetlEngine;
import psyco.coder.bean.BeanClass;
import psyco.coder.bean.BeanField;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by peng on 15/10/11.
 */
public class CoderJavabean {

    public static BeanClass bean(String s, String pack) throws Exception {
        return bean(s, pack, BeanClass.class);
    }

    public static BeanClass bean(String s, String pack, Class<? extends BeanClass> paramClass) throws Exception {
        ClassParser ast = ClassParser.parse(s);
        TypeDeclaration clz = ast.findClass();
        return paramClass.getConstructor(String.class, String.class, List.class)
                .newInstance(clz.getName().toString(), pack,
                        ast.fields(clz).stream().map(f -> {
                                    String fn = ClassParser.getFieldName(f);
                                    return new BeanField(fn, CaseUtil.setter(fn), CaseUtil.getter(fn), f.getType().toString());
                                }
                        ).collect(Collectors.toList()));
    }

    public static String exec(String s, String pack) throws Exception {
        return exec(bean(s, pack), pack);
    }

    public static String exec(BeanClass paramClass, String pack) throws Exception {
        return BeetlEngine.render("/template/bean.btl", "bean", paramClass);
    }


}
