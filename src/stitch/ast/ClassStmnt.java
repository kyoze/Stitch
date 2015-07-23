package stitch.ast;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/24/15
 * Time: 5:15 AM
 * To change this template use File | Settings | File Templates.
 */
public class ClassStmnt extends ASTList{
    public ClassStmnt(List<ASTree> list) {
        super(list);
    }

    public String name() {
        return ((ASTLeaf)child(0)).token().getText();
    }

    public String superClass() {
        if(numChildren()<3)
            return null;
        else
            return ((ASTLeaf)child(1)).token().getText();
    }
    public ClassBody body(){
        return (ClassBody)child(numChildren()-1);
    }

    public String toString() {
        String parent=superClass();
        if(parent==null)
            parent="*";
        return "(class "+name()+" "+body()+")";
    }
}
