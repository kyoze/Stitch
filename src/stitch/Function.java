package stitch;

import stitch.ast.BlockStmnt;
import stitch.ast.ParameterList;

/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/13/15
 * Time: 4:50 AM
 * 函数
 */
public class Function{
    protected ParameterList parameters;
    protected BlockStmnt body;
    protected Environment env;
    public Function(ParameterList parameters,BlockStmnt body,Environment env) {
        this.parameters=parameters;
        this.body=body;
        this.env=env;
    }

    public ParameterList parameters() {
        return parameters;
    }

    public BlockStmnt body() {
        return body;
    }

    public Environment makeEnv() {
        return new NestedEnv(env);
    }

    @Override
    public String toString() {
        return "<fun:"+hashCode()+">";
    }
}
