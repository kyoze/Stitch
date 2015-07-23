package stitch.runner;

import javassist.gluonj.util.Loader;
import stitch.ClosureEvaluator;
import stitch.NativeEvaluator;
import stitch.NativeInterpreter;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/16/15
 * Time: 6:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class NativeRunner {
    public static void main(String[] args) throws Throwable{
        Loader.run(NativeInterpreter.class,args, NativeEvaluator.class, ClosureEvaluator.class);
    }

}
