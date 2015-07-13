package stitch.ast;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/12/15
 * Time: 7:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class Arguments extends Postfix {
    public Arguments(List<ASTree> list) {
        super(list);
    }

    public int size() {
        return numChildren();
    }
}
