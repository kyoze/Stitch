package stitch;

import stitch.ast.Arguments;
import stitch.ast.DefStmnt;
import stitch.ast.ParameterList;

import static stitch.Parser.rule;
/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/12/15
 * Time: 6:46 AM
 * 函数解释器
 */
public class FuncParser extends BasicParser {
    Parser param=rule().identifier(reserved);
    Parser params=rule(ParameterList.class)
            .ast(param).repeat(rule().sep(",").ast(param));
    Parser paramList=rule().sep("(").maybe(params).sep(")");
    Parser def=rule(DefStmnt.class).sep("def").identifier(reserved).ast(paramList).ast(block);
    Parser args=rule(Arguments.class).ast(expr).repeat(rule().sep(",").ast(expr));
    Parser postfix=rule().sep("(").maybe(args).sep(")");

    public FuncParser() {
        reserved.add(")");
        primary.repeat(postfix);
        simple.option(args);
        program.insertChoice(def);
    }
}
