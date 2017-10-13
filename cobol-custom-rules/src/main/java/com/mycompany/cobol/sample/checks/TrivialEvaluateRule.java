package com.mycompany.cobol.sample.checks;

import com.sonar.sslr.api.AstNode;
import com.sonarsource.cobol.api.ast.CobolCheck;
import java.util.List;
import org.sonar.check.Priority;
import org.sonar.check.Rule;

@Rule(
  key = "TrivialEvaluate",
  name = "EVALUATE statements should have several WHEN clauses",
  priority = Priority.MINOR,
  tags = {"bad-practice"})
public class TrivialEvaluateRule extends CobolCheck {

  @Override
  public void init() {
    subscribeTo(getCobolGrammar().evaluateStatement);
  }

  @Override
  public void visitNode(AstNode evaluateNode) {
    List<AstNode> whenClauses = evaluateNode.getChildren(getCobolGrammar().evaluateWhen);
    if (whenClauses.size() == 1) {
      reportIssue("Change this EVALUATE into an IF statement.").on(evaluateNode.getToken());
    }
  }

}
