/*
 * Copyright (C) 2021-2024 SonarSource SA
 * All rights reserved
 * mailto:info AT sonarsource DOT com
 */
package com.sonarsource.jcl.example.checks;

import org.sonar.plugins.jcl.api.checks.InitContext;
import org.sonar.plugins.jcl.api.checks.JclCheck;
import org.sonar.plugins.jcl.api.tree.SyntaxToken;
import org.sonar.plugins.jcl.api.tree.statement.ExecStatementTree;
import org.sonar.check.Rule;

@Rule(key = MyCustomRule.RULE_KEY)
public class MyCustomRule implements JclCheck {

  public static final String RULE_KEY = "Rule1";

  @Override
  public void init(InitContext context) {
    context.register(ExecStatementTree.class, (ctx, exec) -> {
      SyntaxToken name = exec.name();
      if (name != null && !name.value().startsWith("STEP")) {
        ctx.reportIssue(name, "EXEC statement names must start with 'STEP'");
      }
    });
  }
}
