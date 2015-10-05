package psyco.coder.ast;

import org.eclipse.jdt.core.dom.*;
import org.junit.Test;

/**
 * Created by lipeng on 15/9/15.
 */
public class AstBuilder {
    AST ast = AST.newAST(AST.JLS3);
    CompilationUnit unit = ast.newCompilationUnit();
    ImportDeclaration importDeclaration = ast.newImportDeclaration();

    public CompilationUnit build() {
        return unit;
    }

    public AstBuilder pack(String pack) {
        PackageDeclaration packageDeclaration = ast.newPackageDeclaration();
        packageDeclaration.setName(ast.newSimpleName(pack));
        unit.setPackage(packageDeclaration);
        return this;
    }

    public AstBuilder addImportPackage(String packagePath) {
        QualifiedName name =
                ast.newQualifiedName(
                        ast.newSimpleName("java"),
                        ast.newSimpleName("util"));
        importDeclaration.setName(name);
        importDeclaration.setOnDemand(true);
        unit.imports().add(importDeclaration);
        return this;
    }


    @Test
    public void sdfd() {
        pack("fuck");

        QualifiedName name =
                ast.newQualifiedName(
                        ast.newSimpleName("java"),
                        ast.newSimpleName("util"));
        importDeclaration.setName(name);
        importDeclaration.setOnDemand(true);
        unit.imports().add(importDeclaration);

        TypeDeclaration clz = ClassBuilder.newInstance(ast, "Fucker")
                .classModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD, Modifier.ModifierKeyword.FINAL_KEYWORD)
                .sdfsd()
                .build();
        unit.types().add(clz);
        System.out.println(unit);

    }
}
