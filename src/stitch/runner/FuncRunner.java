package stitch.runner;

import javassist.gluonj.util.Loader;
import stitch.FuncEvaluator;
import stitch.FuncInterpreter;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/13/15
 * Time: 7:48 AM
 * 测试函数的调用功能.
 */
public class FuncRunner {
    public static void main(String[] args)throws Throwable{
        Loader.run(FuncInterpreter.class,args, FuncEvaluator.class);
    }

}
