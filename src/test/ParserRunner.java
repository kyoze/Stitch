package test;

import stitch.*;
import stitch.ast.ASTree;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/9/15
 * Time: 7:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class ParserRunner {
    public static void main(String[] args) throws ParseException{
        Lexer l=new Lexer(new CodeDialog());
        BasicParser bp=new BasicParser();
        while (l.peek(0)!= Token.EOF){
            ASTree ast=bp.parse(l);
            System.out.println("=>"+ast.toString());
        }
    }
}
