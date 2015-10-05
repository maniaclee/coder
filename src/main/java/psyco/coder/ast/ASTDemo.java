package psyco.coder.ast;

import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.eclipse.jdt.core.dom.*;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.util.List;

/**
 * Created by lipeng on 15/9/14.
 */
public class ASTDemo {
    static final File f = new File("/Users/psyco/workspace/github/projects/src/main/java/psyco/springRMI/client/Data.java");
    CompilationUnit unit;
    List<ImportDeclaration> imports;

    public ASTDemo parse(File file) throws Exception {
        String content = IOUtils.toString(new FileReader(file));
        ASTParser parser = ASTParser.newParser(AST.JLS3); //initialize
        parser.setKind(ASTParser.K_COMPILATION_UNIT);     //to parse compilation unit
        parser.setSource(content.toCharArray());          //content is a string which stores the java source
        parser.setResolveBindings(true);
        this.unit = (CompilationUnit) parser.createAST(null);
        this.imports = unit.imports();
        return this;
    }

    public List<TypeDeclaration> classes() {
        return unit.types();
    }

    public TypeDeclaration findClass() {
        return classes().isEmpty() ? null : classes().get(0);
    }

    public FieldDeclaration[] fields(TypeDeclaration clz) {
        return clz.getFields();
    }

    public static String getFieldName(FieldDeclaration fieldDeclaration) {
        return ((VariableDeclarationFragment) fieldDeclaration.fragments().get(0)).getName().getFullyQualifiedName();
    }


    @Test
    public void test() throws Exception {
        ASTDemo re = parse(f);
//        System.out.println(re.unit);
//        System.out.println(re.findClass().getFields()[0].getClass());
        Lists.newArrayList(re.findClass().getFields()).forEach(e -> {
            System.out.println(e.getType().getClass());
            System.out.println(getFieldName(e));
            System.out.println("----------------");
        });
    }

}
