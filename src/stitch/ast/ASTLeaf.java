package stitch.ast;

import stitch.Token;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 6/23/15
 * Time: 7:09 PM
 * 语法树的叶子节点的父类
 */
public class ASTLeaf extends ASTree {
    private static ArrayList<ASTree> empty=new ArrayList<ASTree>();
    protected Token token;
    public ASTLeaf(Token t){
        token=t;
    }
    public ASTree child(int i){
        throw new IndexOutOfBoundsException();
    }
    public int numChildren(){
        return 0;
    }
    public Iterator<ASTree> children(){
        return empty.iterator();
    }
    public String toString(){
        return token.getText();
    }
    public String location(){
        return "at line "+token.getLineNumber();
    }
    public Token token(){
        return token;
    }
}
