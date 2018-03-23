/*
 * SonarQube PHP Custom Rules Example
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
package org.sonar.samples.php;


import com.google.common.collect.ImmutableList;
import java.util.HashMap;
import java.util.Map;
import org.sonar.plugins.php.api.visitors.PHPCustomRulesDefinition;
import org.sonar.samples.php.checks.ForbiddenFunctionUseCheck;
import org.sonar.samples.php.checks.OtherForbiddenFunctionUseCheck;
import org.sonar.squidbridge.annotations.AnnotationBasedRulesDefinition;

/**
 * Extension point to define a PHP rule repository.
 */
public class PHPRulesDefinition extends PHPCustomRulesDefinition {

  /**
   * Provide the repository name
   */
  @Override
  public String repositoryName() {
    return "MyCompany Custom Repository";
  }

  /**
   * Provide the repository key
   */
  @Override
  public String repositoryKey() {
    return "custom";
  }

  /**
   * Provide the list of checks class that implements rules
   * to be part of the rule repository
   */
  @Override
  public ImmutableList<Class> checkClasses() {
    return ImmutableList.of(ForbiddenFunctionUseCheck.class, OtherForbiddenFunctionUseCheck.class);
  }

  @Override
  public void define(Context context) {
    NewRepository repository = context.createRepository(this.repositoryKey(), "php").setName(repositoryName());
    (new AnnotationBasedRulesDefinition(repository, "php")).addRuleClasses(false, checkClasses());

    // Optionally define remediation costs
    Map<String, String> remediationCosts = new HashMap<>();
    remediationCosts.put(ForbiddenFunctionUseCheck.KEY, "5min");
    remediationCosts.put(OtherForbiddenFunctionUseCheck.KEY, "5min");
    repository.rules().forEach(rule -> rule.setDebtRemediationFunction(
      rule.debtRemediationFunctions().constantPerIssue(remediationCosts.get(rule.key()))));

    repository.done();
  }
}
