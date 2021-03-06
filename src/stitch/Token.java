package stitch;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 6/17/15
 * Time: 6:33 AM
 * 把分割后的数字,字符串,标识符统称为token
 */
public abstract class Token {
    public static final Token EOF=new Token(-1){};
    public static final String EOL="\\n";
    private int lineNumber;
    protected Token(int line){
        lineNumber=line;
    }
    public int getLineNumber(){
        return lineNumber;
    }
    public boolean isIdentifier(){
        return false;
    }
    public boolean isNumber(){
        return false;
    }
    public boolean isString(){
        return false;
    }
    public int getNumber(){
        throw new StitchException("not number token");
    }
    public String getText(){
        return "";
    }

}
