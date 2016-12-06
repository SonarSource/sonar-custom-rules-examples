package com.mycompany.cobol.sample.checks;

import com.sonar.sslr.api.AstNode;
import com.sonarsource.cobol.api.ast.CobolAstCheck;
import org.sonar.check.Priority;
import org.sonar.check.Rule;

/**
 * This is an example to demonstrate how to write a custom rule which raises issues on
 * CALL statements which target forbidden programs.
 */
@Rule(
  name = "CALL statements should not use internal programs",
  description = "Programs which name contains 'FORBIDDEN' are not supposed to be called.",
  priority = Priority.CRITICAL)
public class ForbiddenCallCheck extends CobolAstCheck {

  @Override
  public void init() {
    subscribeTo(getCobolGrammar().callStatement);
  }

  @Override
  public void visitNode(AstNode callStatementNode) {
    AstNode calledProgramNode = callStatementNode.getFirstChild(getCobolGrammar().literal);
    if (calledProgramNode != null && calledProgramNode.getTokenValue().indexOf("FORBIDDEN") > -1) {
      log("CALL to this program are forbidden.", calledProgramNode);
    }
  }

}
