/*
 * Copyright (C) 2024-2024 SonarSource SA
 * All rights reserved
 * mailto:info AT sonarsource DOT com
 */
package com.sonarsource.jcl.example;

import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.plugins.jcl.api.CustomRulesDefinition;
import com.sonarsource.jcl.example.checks.MyCustomRule;
import java.util.List;
import org.sonar.api.issue.impact.Severity;
import org.sonar.api.issue.impact.SoftwareQuality;
import org.sonar.api.rules.CleanCodeAttribute;

public class MyJclCustomRulesDefinition implements CustomRulesDefinition {

  public static final String REPOSITORY_KEY = "MyCustomJclRules";

  @Override
  public void define(RulesDefinition.Context context) {
    RulesDefinition.NewRepository repository = context.createRepository(REPOSITORY_KEY, "jcl")
      .setName("My custom JCL repository");
    RulesDefinition.NewRule rule = repository.createRule(MyCustomRule.RULE_KEY)
      .setName("This is the title of my rule")
      .setHtmlDescription("This is the HTML description of my rule")
      .addDefaultImpact(SoftwareQuality.MAINTAINABILITY, Severity.HIGH)
      .setCleanCodeAttribute(CleanCodeAttribute.CLEAR)
      .addTags("confusing");
    rule.setDebtRemediationFunction(rule.debtRemediationFunctions().constantPerIssue("10min"));
    repository.done();
  }

  @Override
  public String repositoryKey() {
    return REPOSITORY_KEY;
  }

  @Override
  public List<Class<?>> checkClasses() {
    return List.of(MyCustomRule.class);
  }

}
