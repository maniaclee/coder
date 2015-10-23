package psyco.coder.ast.parser;

import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.eclipse.jdt.core.dom.*;
import org.junit.Test;
import psyco.coder.util.CaseUtil;
import psyco.coder.component.bean.JavaBean;
import psyco.coder.component.bean.BeanField;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by lipeng on 15/9/14.
 */
public class ClassParser {
    CompilationUnit unit;
    List<ImportDeclaration> imports;

    public static ClassParser parse(File file) throws Exception {
        return parse(IOUtils.toString(new FileReader(file)));
    }

    public static ClassParser parse(String content) throws Exception {
        ClassParser re = new ClassParser();
        org.eclipse.jdt.core.dom.ASTParser parser = org.eclipse.jdt.core.dom.ASTParser.newParser(AST.JLS3); //initialize
        parser.setKind(org.eclipse.jdt.core.dom.ASTParser.K_COMPILATION_UNIT);     //to parse compilation unit
        parser.setSource(content.toCharArray());          //content is a string which stores the java source
        parser.setResolveBindings(true);
        re.unit = (CompilationUnit) parser.createAST(null);
        re.imports = re.unit.imports();
        return re;
    }

    public List<TypeDeclaration> classes() {
        return unit.types();
    }

    public TypeDeclaration findClass() {
        return classes().isEmpty() ? null : classes().get(0);
    }

    public List<FieldDeclaration> fieldsAll(TypeDeclaration clz) {
        return Lists.newArrayList(clz.getFields());
    }

    public List<FieldDeclaration> fields(TypeDeclaration clz) {
        return Lists.newArrayList(clz.getFields()).stream().filter(e -> hasSetter(e)).collect(Collectors.toList());
    }

    public List<MethodDeclaration> setters(TypeDeclaration clz) {
        List<MethodDeclaration> ms = Lists.newArrayList();
        for (FieldDeclaration f : clz.getFields()) {
            MethodDeclaration setter = setter(f);
            if (setter != null)
                ms.add(setter);
        }
        return ms;
    }

    public boolean hasSetter(FieldDeclaration fieldDeclaration) {
        String setter = CaseUtil.setter(getFieldName(fieldDeclaration));
        return Lists.newArrayList(clz(fieldDeclaration).getMethods()).stream().anyMatch(m -> Objects.equals(m.getName().toString(), setter));
    }

    public MethodDeclaration setter(FieldDeclaration fieldDeclaration) {
        String setter = CaseUtil.setter(getFieldName(fieldDeclaration));
        for (MethodDeclaration m : clz(fieldDeclaration).getMethods())
            if (m.getName().equals(setter))
                return m;
        return null;
    }


    public TypeDeclaration clz(FieldDeclaration fieldDeclaration) {
        return (TypeDeclaration) fieldDeclaration.getParent();
    }

    public static String getFieldName(FieldDeclaration fieldDeclaration) {
        return ((VariableDeclarationFragment) fieldDeclaration.fragments().get(0)).getName().getFullyQualifiedName();
    }


    public static JavaBean extractClass(String src) throws Exception {
        List<JavaBean> javaBeans = extractClasses(src);
        return javaBeans.isEmpty() ? null : javaBeans.get(0);
    }

    public static List<JavaBean> extractClasses(String src) throws Exception {
        ClassParser parser;
        try {
            parser = parse(src);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JavaSrcParsingException("Failed to parse src :" + src);
        }
        return parser.classes().stream().map(clz -> {
                    JavaBean bean = new JavaBean();
                    bean.setClassName(clz.getName().toString());
                    bean.setFields(Lists.newArrayList(clz.getFields()).stream().map(f -> {
                                BeanField field = new BeanField();
                                field.setName(getFieldName(f));
                                field.setType(f.getType().toString());
                                field.setGetter(CaseUtil.getter(field.getName()));
                                field.setSetter(CaseUtil.setter(field.getName()));
                                return field;
                            }
                    ).collect(Collectors.toList()));
                    return bean;
                }
        ).collect(Collectors.toList());
    }

    public static class JavaSrcParsingException extends Exception {
        public JavaSrcParsingException(String message) {
            super(message);
        }
    }


    @Test
    public void test() throws Exception {
        File f = new File("/Users/peng/workspace/github/coder/src/main/java/psyco/coder/ast/parser/Shit.java");
        ClassParser re = parse(f);
//        System.out.println(re.unit);
//        System.out.println(re.findClass().getFields()[0].getClass());
        Lists.newArrayList(re.findClass().getFields()).forEach(e -> {
//            System.out.println(e.getType().getClass());
            System.out.println(getFieldName(e));
            System.out.println(e.getParent().getClass());
            System.out.println("----------");
        });
    }

}
