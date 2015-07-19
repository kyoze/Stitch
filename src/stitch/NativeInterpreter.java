package stitch;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/16/15
 * Time: 6:52 AM
 * 支持原生方法的解释器
 */
public class NativeInterpreter extends BasicInterpreter{
    public static void main(String[] args)throws ParseException{
        run(new ClosureParser(),new Natives().environment(new NestedEnv()));
    }
}
