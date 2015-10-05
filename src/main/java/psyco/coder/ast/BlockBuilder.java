package psyco.coder.ast;

/**
 * Created by lipeng on 15/9/16.
 */
public class BlockBuilder extends AbstractBuilder {
    MethodBuilder methodBuilder;

    public BlockBuilder(MethodBuilder methodBuilder) {
        this.methodBuilder = methodBuilder;
    }
}
