package stitch;

import stitch.ast.ASTree;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 6/19/15
 * Time: 11:07 PM
 * 自定义的异常类
 */
public class StitchException extends RuntimeException {
    public StitchException(String m){
        super(m);
    }
    public StitchException(String m,ASTree t){
        super(m+" "+t.location());
    }
}
