package stitch;

import stitch.ast.Fun;

import static stitch.Parser.rule;
/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/14/15
 * Time: 1:27 AM
 * 支持闭包的语法分析器
 */
public class ClosureParser extends FuncParser {
    public ClosureParser() {
        primary.insertChoice(rule(Fun.class).sep("fun").ast(paramList).ast(block));
    }
}
