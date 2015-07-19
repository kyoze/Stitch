package stitch;

import stitch.ast.ASTree;

import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/15/15
 * Time: 7:21 AM
 * 原生函数类
 */
public class NativeFunction {
    protected Method method;
    protected String name;
    protected int numParams;
    public NativeFunction(String n,Method m) {
        name=n;
        method=m;
        numParams=m.getParameterTypes().length;
    }

    @Override
    public String toString() {
        return "<native:"+hashCode()+">";
    }

    public int numOfParameters() {
        return numParams;
    }

    public Object invoke(Object[] args, ASTree tree) {
        try {
            return method.invoke(null, args);
        } catch (Exception e) {
            throw new StitchException("bad native function call: "+name,tree);
        }
    }
}
