/*
 * Copyright (C) 2009-2016 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.mycompany.cobol.sample.checks;

import com.sonar.sslr.api.AstNode;
import com.sonarsource.cobol.api.ast.CobolAstCheck;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;

@Rule(
    name = "Sample check",
    description = "This basic sample check creates a violation on each source file. " +
        "The line of the violation can be customized thanks to the \"violationLineNumber\" parameter.",
    priority = Priority.INFO)
public class SampleCheck extends CobolAstCheck {

  private static final int PARAMETER_DEFAULT_VALUE = 5;

  @RuleProperty(
    key = "violationLineNumber",
    description = "Line of the code that the violation will be attached to.",
    defaultValue = "" + PARAMETER_DEFAULT_VALUE)
  private int violationLineNumber = PARAMETER_DEFAULT_VALUE;

  @Override
  public void visitFile(AstNode astNode) {
    log("Violation here !", violationLineNumber);
  }

}
