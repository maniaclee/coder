@args List<org.eclipse.jdt.core.dom.FieldDeclaration>  fs , String builderName , String clzName
/**
 * Created by peng on 15/10/7.
 */
public class @builderName {

    private @clzName obj;

    private @builderName () {
    }

    public static @builderName create() {
        return new @builderName ();
    }

    public static @builderName create(@clzName obj) {
        @builderName re = new @builderName ();
        re.obj = obj;
        return re;
    }

    public @clzName set(String s) {
        obj.set(s);
        return this;
    }

    public @clzName build() {
        return this.obj;
    }
}
