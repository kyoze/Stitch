package stitch;

import javax.swing.*;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/16/15
 * Time: 1:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class Natives {
    public Environment environment(Environment env) {
        appendNatives(env);
        return env;
    }

    protected void appendNatives(Environment environment) {
        append(environment,"print", Natives.class,"print",Object.class);
        append(environment,"read", Natives.class,"read");
        append(environment,"length", Natives.class,"length",String.class);
        append(environment,"toInt", Natives.class,"toInt",Object.class);
        append(environment,"currentTime", Natives.class,"currentTime");
    }
    protected void append(Environment environment,String name,Class<?> clazz,String methodName,Class<?> ...params) {
        Method m;
        try {
            m = clazz.getMethod(methodName, params);
        } catch (Exception e) {
            throw new StitchException("cannot find a native function: "+methodName);
        }
        environment.put(name,new NativeFunction(methodName,m));
    }

    //原生方法
    public static int print(Object obj) {
        System.out.println(obj.toString());
        return 0;
    }

    public static String read() {
        return JOptionPane.showInputDialog(null);
    }

    public static int length(String s) {
        return s.length();
    }

    public static int toInt(Object value) {
        if(value instanceof String)
            return Integer.parseInt((String)value);
        else if(value instanceof Integer)
            return (Integer)value;
        else
            throw new NumberFormatException(value.toString());
    }
    private static long startTime=System.currentTimeMillis();

    public static int currentTime() {
        return (int)(System.currentTimeMillis()-startTime);
    }
}
