package stitch.ast;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/24/15
 * Time: 5:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class Dot extends Postfix {
    public Dot(List<ASTree> list) {
        super(list);
    }

    public String name() {
        return ((ASTLeaf)child(0)).token().getText();
    }

    public String toString() {
        return "."+name();
    }
}
