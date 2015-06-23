package stitch.ast;

import java.util.Iterator;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 6/23/15
 * Time: 7:33 PM
 * 含有树枝的节点的父类
 */
public class ASList extends ASTree{
    protected List<ASTree> children;
    public ASTree child(int i){
        return children.get(i);
    }
    public ASList(List<ASTree> list){
        children=list;
    }
    public int numChildren(){
        return children.size();
    }
    public Iterator<ASTree> children(){
        return children.iterator();
    }
    public String toString(){
        StringBuilder builder=new StringBuilder();
        builder.append('(');
        String sep="";
        for(ASTree t:children){
            builder.append(sep);
            sep=" ";
            builder.append(t.toString());
        }
        return builder.append(')').toString();
    }
    public String location(){
        for(ASTree t:children){
            String s=t.location();
            if(s!=null)
                return s;
        }
        return null;
    }
}
