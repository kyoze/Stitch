package stitch.ast;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 6/23/15
 * Time: 11:20 PM
 * 存放语法树运算符的节点
 */
public class BinaryExpr extends ASList {
    public BinaryExpr(List<ASTree> c){
        super(c);
    }
    public ASTree left(){
        return child(0);
    }
    public String operator(){
        return ((ASLeaf)child(1)).token().getText();
    }
    public ASTree right(){
        return child(2);
    }
}
