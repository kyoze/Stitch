package test;

import stitch.CodeDialog;
import stitch.Lexer;
import stitch.ParseException;
import stitch.Token;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 6/22/15
 * Time: 1:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class LexerRunner {
    public static void main(String[] args)throws ParseException{
        Lexer l=new Lexer(new CodeDialog());
        for (Token t; (t = l.read()) != Token.EOF;) {
            System.out.println("=>"+t.getText());
        }
    }
}
