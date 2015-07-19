package stitch;

import javassist.gluonj.Require;
import javassist.gluonj.Reviser;
import stitch.ast.ASTree;
import stitch.BasicEvaluator.ASTreeEx;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/15/15
 * Time: 4:46 AM
 * 支持原生方法的修改器
 */
@Require(FuncEvaluator.class)
@Reviser public class NativeEvaluator {
    @Reviser public static class NativeArgEx extends FuncEvaluator.ArgumentsEx{
        public NativeArgEx(List<ASTree> list) {
            super(list);
        }

        @Override
        public Object eval(Environment callerEnv, Object value) {
            if(!(value instanceof NativeFunction))
                return super.eval(callerEnv,value);
            NativeFunction func=(NativeFunction)value;
            int nparams=func.numOfParameters();
            if(size()!=nparams)
                throw new StitchException("bad number of arguments",this);
            Object[] args=new Object[nparams];
            int num=0;
            for (ASTree a : this) {
                ASTreeEx as=(ASTreeEx)a;
                args[num++]=as.eval(callerEnv);
            }
            return func.invoke(args,this);
        }
    }
}
