package stitch;

import javassist.gluonj.Reviser;
import stitch.ast.*;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/10/15
 * Time: 5:47 AM
 * To change this template use File | Settings | File Templates.
 */
@Reviser public class BasicEvaluator {
    public static final int TRUE=1;
    public static final int FALSE=0;
    @Reviser public static abstract class ASTreeEx extends ASTree{
        public abstract Object eval(Environment env);
    }
    @Reviser public static class ASTListEx extends ASTList{
        public ASTListEx(List<ASTree> c){
            super(c);
        }
        public Object eval(Environment env){
            throw new StitchException("cannot eval: "+toString(),this);
        }
    }
    @Reviser public static class ASTLeafEx extends ASTLeaf{
        public ASTLeafEx(Token t){
            super(t);
        }
        public Object eval(Environment env){
            throw new StitchException("cannot eval: "+toString(),this);
        }
    }
    @Reviser public static class NumberEx extends NumberLiteral{
        public NumberEx(Token t){
            super(t);
        }
        public Object eval(Environment env){
            return value();
        }
    }
    @Reviser public static class StringEx extends StringLiteral{
        public StringEx(Token t){
            super(t);
        }
        public Object eval(Environment env){
            return value();
        }
    }
    @Reviser public static class NameEx extends Name{
        public NameEx(Token t){
            super(t);
        }
        public Object eval(Environment env){
            Object value=env.get(name());
            if(value==null){
                throw new StitchException("undefined name: "+name(),this);
            }else{
                return value;
            }
        }
    }
    @Reviser public static class NegativeEx extends NegativeExpr{
        public NegativeEx(List<ASTree> c){
            super(c);
        }
        public Object eval(Environment env){
            Object v=((ASTreeEx)operand()).eval(env);
            if(v instanceof Integer)
                return -((Integer)v);
            else
                throw new StitchException("bad type for -",this);
        }
    }
    @Reviser public static class BinaryEx extends BinaryExpr{
        public BinaryEx(List<ASTree> c){
            super(c);
        }
        public Object eval(Environment env){
            String op=operator();
            if ("=".equals(op)) {
                Object right=((ASTreeEx)right()).eval(env);
                return computeAssign(env,right);
            }else{
                Object left=((ASTreeEx)left()).eval(env);
                Object right=((ASTreeEx)right()).eval(env);
                return computeOp(left,op,right);
            }
        }
        protected Object computeAssign(Environment env,Object rvalue){
            ASTree l=left();
            if(l instanceof Name){
                env.put(((Name) l).name(), rvalue);
                return rvalue;
            }
            else
                throw new StitchException("bad assignment",this);
        }
        protected Object computeOp(Object left,String op,Object right){
            if(left instanceof Integer && right instanceof Integer){
                return computeNumber((Integer)left,op,(Integer)right);
            }else{
                if(op.equals("+"))
                    return String.valueOf(left)+String.valueOf(right);
                else if(op.equals("==")){
                    if(left==null)
                        return right==null?TRUE:FALSE;
                    else
                        return left.equals(right)?TRUE:FALSE;
                }else {
                    throw new StitchException("bad type",this);
                }
            }
        }
        protected Object computeNumber(Integer left,String op,Integer right){
            int a=left;
            int b=right;
            if(op.equals("+"))
                return a+b;
            else if(op.equals("*"))
                return a*b;
            else if(op.equals("-"))
                return a-b;
            else if(op.equals("/"))
                return a/b;
            else if(op.equals("%"))
                return a%b;
            else if(op.equals("=="))
                return a==b?TRUE:FALSE;
            else if(op.equals("<"))
                return a<b?TRUE:FALSE;
            else if(op.equals(">"))
                return a>b?TRUE:FALSE;
            else
                throw new StitchException("bad operator",this);
        }
    }
    @Reviser public static class BlockEx extends BlockStmnt{
        public BlockEx(List<ASTree> c){
            super(c);
        }
        public Object eval(Environment env){
            Object result=0;
            for(ASTree t:this){
                if(!(t instanceof NullStmnt))
                    result=((ASTreeEx)t).eval(env);
            }
            return result;
        }
    }
    @Reviser public static class IfEx extends IfStmnt{
        public IfEx(List<ASTree> c){
            super(c);
        }
        public Object eval(Environment env){
            Object c=((ASTreeEx)condition()).eval(env);
            if (c instanceof Integer && (Integer) c != FALSE) {
                return ((ASTreeEx)thenBlock()).eval(env);
            }else {
                ASTree b=elseBlock();
                if(b==null)
                    return 0;
                else
                    return ((ASTreeEx)b).eval(env);
            }
        }
    }
    @Reviser public static class WhileEx extends WhileStmnt{
        public WhileEx(List<ASTree> c){
            super(c);
        }
        public Object eval(Environment env){
            Object result=0;
            for(;;){
                Object c=((ASTreeEx)condition()).eval(env);
                if(c instanceof Integer && (Integer)c==FALSE)
                    return result;
                else result=((ASTreeEx)body()).eval(env);
            }
        }
    }
}
