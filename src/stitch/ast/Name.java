package stitch.ast;

import stitch.Token;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 6/23/15
 * Time: 11:12 PM
 * 存放语法树变量的节点
 */
public class Name extends ASTLeaf {
    public Name(Token t){
        super(t);
    }
    public String name(){
        return token().getText();
    }
}
