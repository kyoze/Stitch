package stitch;

import javassist.gluonj.Require;
import javassist.gluonj.Reviser;
import stitch.ast.ASTree;
import stitch.ast.Fun;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/14/15
 * Time: 6:29 AM
 * 增加闭包功能的修改器
 */
@Require(FuncEvaluator.class)
@Reviser public class ClosureEvaluator {
    @Reviser public static class FunEx extends Fun {
        public FunEx(List<ASTree> list) {
            super(list);
        }

        public Object eval(Environment environment) {
            return new Function(parameters(),body(),environment);
        }
    }
}
