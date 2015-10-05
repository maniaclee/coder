package psyco.coder.ast;

import org.eclipse.jdt.core.dom.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class ClassBuilder extends AbstractBuilder {

    TypeDeclaration type;

    public static ClassBuilder newInstance(AST ast, String className) {
        ClassBuilder re = new ClassBuilder();
        re.ast = ast;
        re.type = ast.newTypeDeclaration();
        re.type.setName(ast.newSimpleName(className));
        return re;
    }

    public ClassBuilder classModifier(Modifier.ModifierKeyword... modifierKeywords) {
        Arrays.asList(modifierKeywords).forEach(e -> type.modifiers().add(ast.newModifier(e)));
        return this;
    }

    public ClassBuilder field(Type clz, String fieldName) {
        FieldDeclaration re = newFieldDeclaration(fieldName);
        re.setType(clz);
        type.bodyDeclarations().add(re);
        return this;
    }

    public ClassBuilder method() {

        return this;
    }


    public ClassBuilder addMethod(MethodDeclaration m) {
        type.bodyDeclarations().add(m);
        return this;
    }

    private FieldDeclaration newFieldDeclaration(String fieldName) {
        VariableDeclarationFragment field = ast.newVariableDeclarationFragment();
        field.setName(ast.newSimpleName(fieldName));
        return ast.newFieldDeclaration(field);
    }


    @Test
    public void test() {
        AST ast = AST.newAST(AST.JLS3);
        ClassBuilder re = newInstance(ast, "Fucker")
                .classModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD)
                .field(ast.newParameterizedType(ast.newPrimitiveType(PrimitiveType.BOOLEAN)), "param")
                .field(ast.newSimpleType(ast.newName("String")), "param")
                .field(ast.newPrimitiveType(PrimitiveType.BOOLEAN), "ri")
                .addMethod(MethodBuilder.newInstance(ast, "get", ast.newPrimitiveType(PrimitiveType.VOID))
                        .modifiers(ast.newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD))
                        .build());
        System.out.println(re.build());

    }

    public ClassBuilder sdfsd() {
        type.setInterface(false);
        MethodDeclaration methodDeclaration = ast.newMethodDeclaration();
        methodDeclaration.setConstructor(false);
        List modifiers = methodDeclaration.modifiers();
        modifiers.add(ast.newModifier(Modifier.ModifierKeyword.PUBLIC_KEYWORD));
        modifiers.add(ast.newModifier(Modifier.ModifierKeyword.STATIC_KEYWORD));
        methodDeclaration.setName(ast.newSimpleName("main"));
        methodDeclaration.setReturnType2(ast.newPrimitiveType(PrimitiveType.VOID));

        SingleVariableDeclaration variableDeclaration = ast.newSingleVariableDeclaration();
        variableDeclaration.setType(ast.newArrayType(ast.newSimpleType(ast.newSimpleName("String"))));
        variableDeclaration.setName(ast.newSimpleName("args"));
        methodDeclaration.parameters().add(variableDeclaration);
        org.eclipse.jdt.core.dom.Block block = ast.newBlock();
        MethodInvocation methodInvocation = ast.newMethodInvocation();
        QualifiedName name = ast.newQualifiedName(
                ast.newSimpleName("System"),
                ast.newSimpleName("out"));
        methodInvocation.setExpression(name);
        methodInvocation.setName(ast.newSimpleName("println"));
        InfixExpression infixExpression = ast.newInfixExpression();
        infixExpression.setOperator(InfixExpression.Operator.PLUS);
        StringLiteral literal = ast.newStringLiteral();
        literal.setLiteralValue("Hello");
        infixExpression.setLeftOperand(literal);
        literal = ast.newStringLiteral();
        literal.setLiteralValue(" world");
        infixExpression.setRightOperand(literal);
        methodInvocation.arguments().add(infixExpression);
        ExpressionStatement expressionStatement = ast.newExpressionStatement(methodInvocation);
        block.statements().add(expressionStatement);
        methodDeclaration.setBody(block);
        type.bodyDeclarations().add(methodDeclaration);
        return this;
    }

    public AST getAst() {
        return ast;
    }

    public TypeDeclaration build() {
        return type;
    }
}