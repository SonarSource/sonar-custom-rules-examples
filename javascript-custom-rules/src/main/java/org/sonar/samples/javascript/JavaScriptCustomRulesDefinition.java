/*
 * SonarQube JavaScript Custom Rules Example
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
package org.sonar.samples.javascript;

import com.google.common.io.CharStreams;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionAnnotationLoader;
import org.sonar.plugins.javascript.api.CustomRuleRepository;
import org.sonar.samples.javascript.checks.ForbiddenFunctionUseCheck;
import org.sonar.samples.javascript.checks.OtherForbiddenFunctionUseCheck;

/**
 * Extension point to define a JavaScript rule repository.
 */
public class JavaScriptCustomRulesDefinition implements RulesDefinition, CustomRuleRepository {

  @Override
  public void define(Context context) {
    RulesDefinition.NewRepository repository = context.createRepository(repositoryKey(), "js")
      .setName("MyCompany Custom Repository");

    // this will load metadata from @Rule annotation
    new RulesDefinitionAnnotationLoader().load(repository, checkClasses().toArray(new Class[] {}));
    NewRule rule = repository.rule("S1");
    // description is loaded from html file in resources directory
    rule.setHtmlDescription(loadRuleDescription(rule.key()));
    // remediation function sets how much rule violation contributes to technical debt
    rule.setDebtRemediationFunction(rule.debtRemediationFunctions().linear("5min"));

    repository.done();
  }

  /**
   * Provide the repository key
   */
  @Override
  public String repositoryKey() {
    return "custom-javascript-repo";
  }

  /**
   * Provide the list of checks class that implements rules
   * to be part of the rule repository
   */
  @Override
  public List<Class> checkClasses() {
    return Arrays.asList(ForbiddenFunctionUseCheck.class, OtherForbiddenFunctionUseCheck.class);
  }

  private static String loadRuleDescription(String ruleKey) {
    try {
      InputStream resource = JavaScriptCustomRulesDefinition.class.getClassLoader()
        .getResourceAsStream(ruleKey + ".html");
      if (resource == null) {
        throw new IllegalStateException("Rule description not found");
      }
      return CharStreams.toString(new InputStreamReader(resource, StandardCharsets.UTF_8));
    } catch (IOException e) {
      throw new IllegalStateException("Error loading rule description", e);
    }
  }
}
