package stitch;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/14/15
 * Time: 7:37 AM
 * 支持闭包的解释器
 */
public class ClosureInterpreter extends BasicInterpreter{
    public static void main(String[] args)throws ParseException{
        run(new ClosureParser(),new NestedEnv());
    }
}
