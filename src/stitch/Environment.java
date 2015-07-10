package stitch;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/10/15
 * Time: 12:55 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Environment {
    void put(String name,Object value);
    Object get(String name);
}
