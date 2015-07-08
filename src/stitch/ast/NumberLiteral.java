package stitch.ast;

import stitch.Token;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 6/23/15
 * Time: 9:00 PM
 * 存放语法树数字字面值的节点
 */
public class NumberLiteral extends ASTLeaf {
    public NumberLiteral(Token t){
        super(t);
    }
    public int value(){
        return token().getNumber();
    }
}
