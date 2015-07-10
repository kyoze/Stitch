package stitch;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/10/15
 * Time: 12:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class BasicEnv implements Environment {
    protected HashMap<String,Object> values;
    public BasicEnv(){
        values=new HashMap<String, Object>();
    }

    @Override
    public void put(String name, Object value) {
        values.put(name,value);
    }

    @Override
    public Object get(String name) {
        return values.get(name);
    }
}
