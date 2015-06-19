package stitch;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 6/20/15
 * Time: 1:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class Lexer {
    public static String regexPat="\\s*((//.*)|([0-9]+)|(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")" +
            "|[A-Z_a-z][A-Z_a-z0-9]*|==|<=|>=|&&|\\|\\||\\p{Punct})?";
    private Pattern pattern=Pattern.compile(regexPat);
    private ArrayList<Token> queue=new ArrayList<Token>();
    private boolean hasMore;
    private LineNumberReader reader;

    public Lexer(Reader r){
        hasMore=true;
        reader=new LineNumberReader(r);
    }
    public Token read() throws ParseException{
        if(fillQueue(0))
            return queue.remove(0);
        else
            return Token.EOF;
    }
    public Token peek(int i) throws ParseException{
        if(fillQueue(i))
            return queue.get(i);
        else
            return Token.EOF;
    }
    private boolean fillQueue(int i) throws ParseException{
        while(i>=queue.size())
            if(hasMore)
                readLine();
            else
                return false;
        return true;
    }
    protected void readLine() throws ParseException{
        String line;
        try{
            line=reader.readLine();
        }catch (IOException e){
            throw new ParseException(e);
        }
        if(line==null){
            hasMore=false;
            return;
        }
        int lineNo=reader.getLineNumber();
        Matcher matcher=pattern.matcher(line);

        //useTransparentBounds(true)设置在检索边界外也可以匹配
        //useAnchoringBounds(false)设置^和$匹配整个字符串的开头和结尾,默认是匹配检索范围的开头结尾
        matcher.useTransparentBounds(true).useAnchoringBounds(false);
        int pos=0;
        int endPos=line.length();
        while (pos < endPos) {
            //设置边界
            matcher.region(pos,endPos);

            if(matcher.lookingAt()){
                addToken(lineNo,matcher);
                pos=matcher.end();
            }else{
                throw new ParseException("bad token at line"+lineNo);
            }
            queue.add(new IdToken(lineNo,Token.EOL));
        }
    }
    protected void addToken(int lineNo,Matcher matcher){
        //Matcher的group(0)指的是匹配整个模式,group(n)指的是匹配左起第n个括号里的模式
        String m=matcher.group(1);
        //如果不是空格
        if(m!=null){
            //如果不是以//开头的注释
            if(matcher.group(2)==null){
                Token token;
                //如果是数字
                if (matcher.group(3) != null) {
                    token=new NumToken(lineNo,Integer.parseInt(m));
                }
                //如果是字符串
                else if (matcher.group(4)!=null) {
                    token=new StrToken(lineNo,toStringLiteral(m));
                }
                //如果是保留字
                else{
                    token=new IdToken(lineNo,m);
                }
                queue.add(token);
            }
        }
    }

    protected String toStringLiteral(String s) {
        StringBuilder sb=new StringBuilder();
        int len=s.length()-1;
        for(int i=1;i<len;i++){
            char c=s.charAt(i);
            if(c=='\\'&&i+1<len){
                int c2=s.charAt(i+1);
                if(c2=='"'||c2=='\\')
                    c=s.charAt(++i);
                else if(c2=='n'){
                    ++i;
                    c='\n';
                }
            }
            return sb.toString();
        }
    }

}
