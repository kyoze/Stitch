package stitch.ast;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/12/15
 * Time: 6:57 AM
 * 函数声明语句
 */
public class DefStmnt extends ASTList {
    public DefStmnt(List<ASTree> list) {
        super(list);
    }
    public String name(){
        return ((ASTLeaf)child(0)).token().getText();
    }

    public ParameterList parameters() {
        return (ParameterList)child(1);
    }

    public BlockStmnt body() {
        return (BlockStmnt)child(2);
    }

    public String toString() {
        return "(def "+name()+" "+parameters()+" "+body()+")";
    }
}
