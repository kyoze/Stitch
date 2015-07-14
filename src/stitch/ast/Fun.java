package stitch.ast;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/14/15
 * Time: 1:30 AM
 * 表示闭包的抽象语法树的节点
 */
public class Fun extends ASTList{
    public Fun(List<ASTree> list) {
        super(list);
    }

    public ParameterList parameters() {
        return (ParameterList)child(0);
    }

    public BlockStmnt body() {
        return (BlockStmnt)child(1);
    }

    public String toString() {
        return "(fun "+parameters()+" "+body()+")";
    }
}
