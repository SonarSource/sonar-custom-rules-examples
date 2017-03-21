package com.mycompany.cobol.sample.checks;

import com.sonar.sslr.api.AstNode;
import com.sonarsource.cobol.api.ast.CobolAstCheck;
import java.util.List;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.squidbridge.annotations.SqaleConstantRemediation;

@Rule(
  key = "TrivialEvaluate",
  name = "EVALUATE statements should have several WHEN clauses",
  priority = Priority.MINOR,
  tags = {"bad-practice"})
@SqaleConstantRemediation("5min")
public class TrivialEvaluateRule extends CobolAstCheck {

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
