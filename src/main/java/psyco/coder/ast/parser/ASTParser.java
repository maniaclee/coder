package psyco.coder.ast.parser;

import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.eclipse.jdt.core.dom.*;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lipeng on 15/9/14.
 */
public class ASTParser {
    CompilationUnit unit;
    List<ImportDeclaration> imports;

    public static ASTParser parse(File file) throws Exception {
        return parse(IOUtils.toString(new FileReader(file)));
    }

    public static ASTParser parse(String content) throws Exception {
        ASTParser re = new ASTParser();
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

    public List<FieldDeclaration> fields(TypeDeclaration clz) {
        return Lists.newArrayList(clz.getFields());
    }

    public Object setters(TypeDeclaration clz) {
//        List<MethodDeclaration> ms = Lists.newArrayList(clz.getMethods());
//        return Lists.newArrayList(clz.getFields()).stream().filter(e ->ms.stream().anyMatch(m->m.getName().equals("set"))).collect(Collectors.toList());
        return Lists.newArrayList(clz.getMethods()).stream()
                .filter(e -> e.getName().toString().startsWith("set") && Modifier.isPublic(e.getModifiers()))
                .collect(Collectors.toList());
    }

    public static String getFieldName(FieldDeclaration fieldDeclaration) {
        return ((VariableDeclarationFragment) fieldDeclaration.fragments().get(0)).getName().getFullyQualifiedName();
    }


    @Test
    public void test() throws Exception {
        File f = new File("/Users/peng/workspace/github/coder/src/main/java/psyco/coder/Shit.java");
        ASTParser re = parse(f);
//        System.out.println(re.unit);
//        System.out.println(re.findClass().getFields()[0].getClass());
        Lists.newArrayList(re.findClass().getFields()).forEach(e -> {
            System.out.println(e.getType().getClass());
            System.out.println(getFieldName(e));
            System.out.println("----------------");
        });
    }

}
