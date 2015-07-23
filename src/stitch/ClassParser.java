package stitch;

import stitch.ast.ClassBody;
import stitch.ast.ClassStmnt;
import stitch.ast.Dot;

import static stitch.Parser.rule;
/**
 * Created with IntelliJ IDEA.
 * User: kaya
 * Date: 7/24/15
 * Time: 1:15 AM
 * 支持类的语法分析期
 */
public class ClassParser extends ClosureParser {
    Parser member=rule().or(def,simple);
    Parser class_body=rule(ClassBody.class).sep("{").option(member)
            .repeat(rule().sep(";",Token.EOL).option(member)).sep("}");
    Parser defclass=rule(ClassStmnt.class).sep("class").identifier(reserved)
            .option(rule().sep("extends").identifier(reserved)).ast(class_body);

    public ClassParser() {
        postfix.insertChoice(rule(Dot.class).sep(".").identifier(reserved));
        program.insertChoice(defclass);
    }
}
