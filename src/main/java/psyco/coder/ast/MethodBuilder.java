package psyco.coder.ast;

import com.google.common.collect.Lists;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Type;


/**
 *
 */
public class MethodBuilder extends AbstractBuilder {

    MethodDeclaration m;

    public static MethodBuilder newInstance(AST ast, String methodName, Type returnType) {
        MethodBuilder b = new MethodBuilder();
        b.setAst(ast);
        b.ast = ast;
        b.m = ast.newMethodDeclaration();
        b.m.setName(ast.newSimpleName(methodName));
        b.m.setReturnType2(returnType);
        return b;
    }

    public MethodBuilder modifiers(Modifier... modifiers) {
        m.modifiers().addAll(Lists.newArrayList(modifiers));
        return this;
    }


    public MethodDeclaration build() {
        return m;
    }

}