package stitch;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 6/22/15
 * Time: 12:31 AM
 * 解析错误的异常类
 */
public class ParseException extends Exception {
    public ParseException(Token t){
        this("",t);
    }
    public ParseException(String msg,Token t){
        super("syntax error around"+location(t)+"."+msg);
    }
    private static String location(Token t){
        if(t==Token.EOF)
            return "the last line";
        else
            return "\""+t.getText()+"\" at line "+t.getLineNumber();
    }
    public ParseException(IOException e){
        super(e);
    }
    public ParseException(String msg){
        super(msg);
    }
}
