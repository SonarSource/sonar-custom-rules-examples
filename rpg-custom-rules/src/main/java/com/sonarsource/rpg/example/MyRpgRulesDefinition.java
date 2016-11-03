/*
 * Copyright (C) 2014-2016 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonarsource.rpg.example;

import com.sonarsource.rpg.api.CustomRulesDefinition;
import com.sonarsource.rpg.example.checks.DataStructureNamingConventionCheck;
import java.util.Arrays;
import java.util.List;

public class MyRpgRulesDefinition implements CustomRulesDefinition {

  public static final String REPOSITORY_KEY = "MyCustomRpgRules";

  @Override
  public void define(Context context) {
    NewRepository repository = context.createRepository(REPOSITORY_KEY, "rpg");
    NewRule rule = repository.createRule(DataStructureNamingConventionCheck.RULE_KEY)
      .setName("This is the title of my rule")
      .setHtmlDescription("This is the HTML description of my rule")
      .addTags("convention");
    rule.setDebtRemediationFunction(rule.debtRemediationFunctions().constantPerIssue("10min"));
    repository.done();
  }

  @Override
  public String repositoryKey() {
    return REPOSITORY_KEY;
  }

  @Override
  public List<Class> checkClasses() {
    return Arrays.asList(DataStructureNamingConventionCheck.class);
  }

}
