package psyco.coder.gen;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.beetl.core.Configuration;
import org.beetl.core.GroupTemplate;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.junit.Test;
import org.rythmengine.Rythm;
import psyco.coder.ast.parser.ClassParser;
import psyco.coder.ast.util.CaseUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by peng on 15/10/8.
 */
public class Builder {

    public static BuilderParam builder(String s) throws Exception {
        ClassParser ast = ClassParser.parse(s);
        TypeDeclaration clz = ast.findClass();
        return new BuilderParam(clz.getName().toString(), clz.getName().toString() + "Builder",
                ast.fields(clz).stream().map(f -> {
                            String fn = ClassParser.getFieldName(f);
                            return new Field(fn, CaseUtil.setter(fn), CaseUtil.getter(fn), f.getType().toString());
                        }
                ).collect(Collectors.toList()));
    }

    public static String gen(String s) throws Exception {
        return Rythm.render(IOUtils.toString(Builder.class.getClassLoader().getResourceAsStream("template/Builder.javaTM")), builder(s), Lists.newArrayList(1, 4, 3, 2));
    }

    public static String beetl(String template, Map<String, Object> map) throws IOException {
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
//
//    public static String genVelocity(String s) throws Exception {
//       return velocity(ImmutableMap.of("bp", builder(s)),"");
//    }
//
//    public static <T> String velocity(Map<String , T>  map , String template){
//        Template t = ve.getTemplate( template );
//        VelocityContext context = new VelocityContext();
//        for(String s : map)
//        context.put(s,map.get(s));
//        VelocityEngine ve = new VelocityEngine();
//        ve.init();
//        StringWriter writer = new StringWriter();
//        return t.merge( context, writer );
//    }


    @Test
    public void sdfsd() throws Exception {
        String s = IOUtils.toString(new FileInputStream("/Users/peng/workspace/github/coder/src/main/java/psyco/coder/ast/parser/Shit.java"));
//        System.out.println(gen(s));
        System.out.println(beetl("/template/Builder.btl", ImmutableMap.of("bp", builder(s))));
    }


    public static  class BuilderParam extends ParamBase{
        public  String className;
        public  String builderClassName;
        public List<Field> fields;

        public BuilderParam() {
        }

        public BuilderParam(String className, String builderClassName, List<Field> fields) {
            this.className = className;
            this.builderClassName = builderClassName;
            this.fields = fields;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public String getBuilderClassName() {
            return builderClassName;
        }

        public void setBuilderClassName(String builderClassName) {
            this.builderClassName = builderClassName;
        }

        public List<Field> getFields() {
            return fields;
        }

        public void setFields(List<Field> fields) {
            this.fields = fields;
        }

        @Override
        public String toString() {
            return "BuilderParam{" +
                    "className='" + className + '\'' +
                    ", builderClassName='" + builderClassName + '\'' +
                    ", fields=" + fields +
                    '}';
        }
    }

    public static class Field {
        public String name;
        public  String setter;
        public  String getter;
        public String type;

        public Field() {
        }

        public Field(String name, String setter, String getter, String type) {
            this.name = name;
            this.setter = setter;
            this.getter = getter;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSetter() {
            return setter;
        }

        public void setSetter(String setter) {
            this.setter = setter;
        }

        public String getGetter() {
            return getter;
        }

        public void setGetter(String getter) {
            this.getter = getter;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "Field{" +
                    "name='" + name + '\'' +
                    ", setter='" + setter + '\'' +
                    ", getter='" + getter + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

}
