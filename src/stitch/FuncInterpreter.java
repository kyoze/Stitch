package stitch;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/13/15
 * Time: 7:43 AM
 * 支持函数功能的解释器
 *  */
public class FuncInterpreter extends BasicInterpreter {
    public static void main(String[] args)throws ParseException{
        run(new FuncParser(),new NestedEnv());
    }
}
