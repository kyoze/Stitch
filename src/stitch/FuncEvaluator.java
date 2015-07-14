package stitch;

import javassist.gluonj.Require;
import javassist.gluonj.Reviser;
import stitch.ast.*;
import stitch.BasicEvaluator.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/13/15
 * Time: 4:36 AM
 * To change this template use File | Settings | File Templates.
 */
@Require(BasicEvaluator.class)
@Reviser public class FuncEvaluator {
    @Reviser public static interface EnvEx extends Environment{
        void putNew(String name,Object value);
        Environment where (String name);
        void setOuter(Environment environment);
    }
    @Reviser public static class DefStmntEx extends DefStmnt{
        public DefStmntEx(List<ASTree> list) {
            super(list);
        }
        public Object eval(Environment env) {
            ((EnvEx)env).putNew(name(),new Function(parameters(),body(),env));
            return name();
        }
    }
    @Reviser public static class PrimaryEx extends PrimaryExpr{
        public PrimaryEx(List<ASTree> c) {
            super(c);
        }

        public ASTree operand() {
            return child(0);
        }
        public boolean hasPostfix(int nest) {
            return numChildren()-nest>1;
        }

        public Postfix postfix(int nest) {
            return (Postfix)child(numChildren()-nest-1);
        }
        public Object eval(Environment env) {
            return evalSubExpr(env,0);
        }
        public Object evalSubExpr(Environment env, int nest) {
            if (hasPostfix(nest)) {
                Object target=evalSubExpr(env, nest+1);
                return ((PostfixEx)postfix(nest)).eval(env,target);
            }else{
                return ((ASTreeEx)operand()).eval(env);
            }
        }
    }
    @Reviser public static abstract class PostfixEx extends Postfix{
        public PostfixEx(List<ASTree> list) {
            super(list);
        }
        public abstract Object eval(Environment env,Object value);
    }
    @Reviser public static class ArgumentsEx extends Arguments{
        public ArgumentsEx(List<ASTree> list) {
            super(list);
        }

        public Object eval(Environment callerEnv, Object value) {
            if(!(value instanceof Function))
                throw new StitchException("bad function",this);
            Function func=(Function)value;
            ParameterList params=func.parameters();
            if(size()!=params.size())
                throw new StitchException("bad number of arguments",this);
            Environment newEnv=func.makeEnv();
            int num=0;
            for (ASTree a : this) {
                ((ParamsEx)params).eval(newEnv,num++,((ASTreeEx)a).eval(callerEnv));
            }
            return ((BlockEx)func.body()).eval(newEnv);
        }

    }
    @Reviser public static class ParamsEx extends ParameterList{
        public ParamsEx(List<ASTree> c) {
            super(c);
        }

        public void eval(Environment env, int index, Object value) {
            ((EnvEx)env).putNew(name(index),value);
        }
    }
}
