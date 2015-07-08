package stitch.ast;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/9/15
 * Time: 6:50 AM
 * if语句
 */
public class IfStmnt extends ASTList {
    public IfStmnt(List<ASTree> c){
        super(c);
    }

    public ASTree condition(){
        return child(0);
    }

    public ASTree thenBlock(){
        return child(1);
    }

    public ASTree elseBlock() {
        return numChildren()>2?child(2):null;
    }

    public String toString() {
        return "(if"+condition()+" "+thenBlock()+"else"+elseBlock()+")";
    }
}
