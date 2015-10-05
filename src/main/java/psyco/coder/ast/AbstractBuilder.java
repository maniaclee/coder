package psyco.coder.ast;

import org.eclipse.jdt.core.dom.AST;

/**
 * Created by lipeng on 15/9/16.
 */
public abstract class AbstractBuilder {
    protected AST ast;


    public AST getAst() {
        return ast;
    }

    public void setAst(AST ast) {
        this.ast = ast;
    }
}
