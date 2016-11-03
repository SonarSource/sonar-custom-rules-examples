/*
 * Copyright (C) 2014-2016 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonarsource.rpg.example.checks;

import com.sonarsource.rpg.api.checks.VisitorBasedCheck;
import com.sonarsource.rpg.api.tree.DataStructureDefinitionTree;
import com.sonarsource.rpg.example.MyRpgRulesDefinition;
import org.sonar.api.rule.RuleKey;
import org.sonar.check.Rule;

@Rule(key = DataStructureNamingConventionCheck.RULE_KEY)
public class DataStructureNamingConventionCheck extends VisitorBasedCheck {

  public static final String RULE_KEY = "MyRule1";

  @Override
  public void visitDataStructureDefinition(DataStructureDefinitionTree definition) {
    if (!definition.name().startsWith("DS_")) {
      context().addIssue(ruleKey(), definition.startLine(), "The name of the data structure should start with 'DS_'", null);
    }
    // super.visitXX(...) should be called to visit child trees
    super.visitDataStructureDefinition(definition);
  }

  private RuleKey ruleKey() {
    return RuleKey.of(MyRpgRulesDefinition.REPOSITORY_KEY, RULE_KEY);
  }

}
