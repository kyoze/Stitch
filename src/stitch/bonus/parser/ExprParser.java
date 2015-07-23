package stitch.bonus.parser;


import stitch.CodeDialog;
import stitch.Lexer;
import stitch.ParseException;
import stitch.Token;
import stitch.ast.ASTLeaf;
import stitch.ast.ASTree;
import stitch.ast.BinaryExpr;
import stitch.ast.NumberLiteral;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/21/15
 * Time: 7:35 PM
 * 四则运算表达式的语法分析器,四则运算的语法规则
 *  factor:Number|"("expression")"
 *  term:factor{("*"|"/")factor}
 *  expression:term{("+"|"-")term}
 */
public class ExprParser {
    private Lexer lexer;

    public ExprParser(Lexer lexer) {
        this.lexer = lexer;
    }
    public ASTree expression()throws ParseException{
        ASTree left=term();
        while (isToken("+")||isToken("-")){
            ASTLeaf op=new ASTLeaf(lexer.read());
            ASTree right=term();
            left=new BinaryExpr(Arrays.asList(left,op,right));
        }
        return left;
    }
    public ASTree term()throws ParseException {
        ASTree left=factor();
        while (isToken("*") || isToken("/")) {
            ASTLeaf op=new ASTLeaf(lexer.read());
            ASTree right=factor();
            left=new BinaryExpr(Arrays.asList(left,op,right));
        }
        return left;
    }
    public ASTree factor()throws ParseException {
        if (isToken("(")) {
            token("(");
            ASTree e=expression();
            token(")");
            return e;
        }else {
            Token t=lexer.read();
            if (t.isNumber()) {
                NumberLiteral n=new NumberLiteral(t);
                return n;
            }else
                throw new ParseException(t);
        }
    }
    void token(String name)throws ParseException {
        Token t=lexer.read();
        if(!(t.isIdentifier()&&name.equals(t.getText())))
            throw new ParseException(t);
    }
    boolean isToken(String name)throws ParseException{
        Token t=lexer.peek(0);
        return t.isIdentifier()&&name.equals(t.getText());
    }
    public static void main(String[] args)throws ParseException{
        Lexer lexer=new Lexer(new CodeDialog());
        ExprParser p=new ExprParser(lexer);
        ASTree t=p.expression();
        System.out.println("=> "+t);
    }
}
