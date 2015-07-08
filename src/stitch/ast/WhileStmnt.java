package stitch.ast;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/9/15
 * Time: 6:57 AM
 * while语句
 */
public class WhileStmnt extends ASTList {
    public WhileStmnt(List<ASTree> c){
        super(c);
    }
    public ASTree condition(){
        return child(0);
    }
    public ASTree body(){
        return child(1);
    }
    public String toString(){
        return "(while"+condition()+" "+body()+")";
    }
}
