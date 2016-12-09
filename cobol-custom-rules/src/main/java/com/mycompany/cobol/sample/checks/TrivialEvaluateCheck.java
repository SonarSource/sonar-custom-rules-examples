package com.mycompany.cobol.sample.checks;

import com.sonar.sslr.api.AstNode;
import com.sonarsource.cobol.api.ast.CobolAstCheck;
import java.util.List;
import org.sonar.check.Priority;
import org.sonar.check.Rule;

@Rule(
  name = "EVALUATE statements should have several WHEN clauses",
  description = "IF an <code>EVALUATE</code> statement has only one <code>WHEN</code> and possibly "
    + "one <code>WHEN OTHER</code> then it should probably be changed to an <code>IF</code> statement.",
  priority = Priority.MINOR)
public class TrivialEvaluateCheck extends CobolAstCheck {

  @Override
  public void init() {
    subscribeTo(getCobolGrammar().evaluateStatement);
  }

  @Override
  public void visitNode(AstNode evaluateNode) {
    List<AstNode> whenClauses = evaluateNode.getChildren(getCobolGrammar().evaluateWhen);
    if (whenClauses.size() == 1) {
      log("Change this EVALUATE into an IF statement.", evaluateNode);
    }
  }

}
