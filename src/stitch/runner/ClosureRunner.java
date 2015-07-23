package stitch.runner;

import javassist.gluonj.util.Loader;
import stitch.ClosureEvaluator;
import stitch.ClosureInterpreter;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/14/15
 * Time: 7:41 AM
 * 启动支持闭包功能的解释器
 */
public class ClosureRunner {
    public static void main(String[] args)throws Throwable{
        Loader.run(ClosureInterpreter.class,args, ClosureEvaluator.class);
    }
}
