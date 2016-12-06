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
    repository.setName("MyCompany Custom Repository");
    
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
