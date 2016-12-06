/*
 * SonarQube RPG Custom Rules Example
 * Copyright (C) 2016-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
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
