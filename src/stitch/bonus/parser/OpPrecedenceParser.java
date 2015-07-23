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
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/23/15
 * Time: 6:20 AM
 * 使用了算符优先分析法的语法分析器,表达式如下:
 *  factor:Number|"("expression")"
 *  term:factor{("*"|"/")factor}
 *  add_expr:term{("+"|"-")term}
 *  rel_expr:add_expr{("<"|">")add_expr}
 *  eq_expr:rel_expr{("=="|"!=")rel_expr}
 *  and_expr:eq_expr{"&&"eq_expr}
 *  or_expr:and_expr{"||"and_expr}
 */
public class OpPrecedenceParser {
    private Lexer lexer;
    protected HashMap<String, Precedence> operators;
    public static class Precedence{
        int value;
        boolean leftAssoc;//left associative

        public Precedence(int v, boolean a) {
            value=v;
            leftAssoc=a;
        }
    }

    public OpPrecedenceParser(Lexer lexer) {
        this.lexer = lexer;
        operators=new HashMap<String, Precedence>();
        operators.put("<",new Precedence(1,true));
        operators.put(">",new Precedence(1,true));
        operators.put("+",new Precedence(2,true));
        operators.put("-",new Precedence(2,true));
        operators.put("*",new Precedence(3,true));
        operators.put("/",new Precedence(3,true));
        operators.put("^",new Precedence(4,false));
    }
    public ASTree expression()throws ParseException {
        ASTree right=factor();
        Precedence next;
        while ((next = nextOperator()) != null) {
            right=doShift(right,next.value);
        }
        return right;
    }
    private ASTree doShift(ASTree left,int prec)throws ParseException{
        ASTLeaf op=new ASTLeaf(lexer.read());
        ASTree right=factor();
        Precedence next;
        while ((next = nextOperator()) != null && rightIsExpr(prec,next)) {
            right=doShift(right,next.value);
        }
        return new BinaryExpr(Arrays.asList(left,op,right));
    }
    private Precedence nextOperator()throws ParseException {
        Token t=lexer.peek(0);
        if(t.isIdentifier())
            return operators.get(t.getText());
        else
            return null;
    }

    private static boolean rightIsExpr(int prec, Precedence nextPrec) {
        if(nextPrec.leftAssoc)
            return prec<nextPrec.value;
        else
            return prec<=nextPrec.value;
    }
    public ASTree factor()throws ParseException {
        if(isToken("(")){
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
    void token(String name)throws ParseException{
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
        OpPrecedenceParser p=new OpPrecedenceParser(lexer);
        ASTree t=p.expression();
        System.out.println("=> "+t);
    }
}
