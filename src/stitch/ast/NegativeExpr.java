package stitch.ast;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/8/15
 * Time: 6:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class NegativeExpr extends ASTList {
    public NegativeExpr(List<ASTree> c){
        super(c);
    }
    public ASTree operand(){
        return child(0);
    }
    public String toString(){
        return "-"+operand();
    }
}
