package stitch.ast;

import stitch.Token;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/7/15
 * Time: 6:08 AM
 * 存放语法树字符串字面值的节点
 */
public class StringLiteral extends ASTLeaf {
    public StringLiteral(Token t)
    {
        super(t);
    }
    public String value()
    {
        return token().getText();
    }
}
