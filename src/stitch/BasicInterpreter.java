package stitch;

import stitch.ast.ASTree;
import stitch.ast.NullStmnt;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/11/15
 * Time: 6:43 AM
 * 解释器的主体程序
 */
public class BasicInterpreter{
    public static void main(String arg[])throws ParseException{
        run(new BasicParser(),new BasicEnv());
    }
    public static void run(BasicParser bp,Environment env)throws ParseException{
        Lexer lexer=new Lexer(new CodeDialog());
        while (lexer.peek(0)!=Token.EOF){
            ASTree t=bp.parse(lexer);
            if (!(t instanceof NullStmnt)) {
                Object r=((BasicEvaluator.ASTreeEx)t).eval(env);
                System.out.println("=>"+r);
            }
        }
    }
}
