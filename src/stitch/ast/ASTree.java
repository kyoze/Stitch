package stitch.ast;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 6/20/15
 * Time: 4:58 AM
 * 语法树的抽象类,是叶子节点和含有树枝节点的父类
 */
public abstract class ASTree implements Iterable<ASTree> {
    public abstract ASTree child(int i);
    public abstract int numChildren();
    public abstract Iterator<ASTree> children();
    public abstract String location();
    public Iterator<ASTree> iterator(){
        return children();
    }

}
