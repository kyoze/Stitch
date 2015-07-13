package stitch.ast;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/12/15
 * Time: 6:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class ParameterList extends ASTList {
    public ParameterList(List<ASTree> c){
        super(c);
    }
    public String name(int i){
        return ((ASTLeaf)child(i)).token().getText();
    }
    public int size(){
        return numChildren();
    }
}
