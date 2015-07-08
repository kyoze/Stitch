package stitch.ast;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/8/15
 * Time: 6:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class PrimaryExpr extends ASTList {
    public PrimaryExpr(List<ASTree> c){
        super(c);
    }
    public static ASTree create(List<ASTree> c){
        return c.size()==1?c.get(0):new PrimaryExpr(c);
    }
}
