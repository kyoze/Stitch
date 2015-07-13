package stitch;

import java.util.HashMap;
import stitch.FuncEvaluator.EnvEx;
/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/12/15
 * Time: 6:37 PM
 * 嵌套环境,即局部变量
 */
public class NestedEnv implements Environment {
    protected HashMap<String,Object> values;
    protected Environment outer;

    public NestedEnv() {
        this(null);
    }
    public NestedEnv(Environment outer) {
        this.outer = outer;
        values=new HashMap<String, Object>();
    }

    public void setOuter(Environment env) {
        outer=env;
    }

    public void putNew(String name, Object value) {
        values.put(name,value);
    }

    public Environment where (String name) {
        if (values.get(name) != null) {
            return this;
        }else if (outer == null) {
            return null;
        }else {
            return ((EnvEx)outer).where(name);
        }
    }
    @Override
    public void put(String name, Object value) {
        Environment e=where(name);
        if (e == null) {
            e=this;
        }
        ((EnvEx)e).putNew(name,value);
    }

    @Override
    public Object get(String name) {
        Object v=values.get(name);
        if (v == null&&outer!=null) {
            return outer.get(name);
        }else {
            return v;
        }
    }
}
