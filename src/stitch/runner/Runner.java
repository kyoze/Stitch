package stitch.runner;

import javassist.gluonj.util.Loader;
import stitch.BasicEvaluator;
import stitch.BasicInterpreter;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/11/15
 * Time: 6:55 AM
 * 解释器启动程序
 */
public class Runner {
    public static void main(String[] args)throws Throwable{
        Loader.run(BasicInterpreter.class,args, BasicEvaluator.class);
    }
}
