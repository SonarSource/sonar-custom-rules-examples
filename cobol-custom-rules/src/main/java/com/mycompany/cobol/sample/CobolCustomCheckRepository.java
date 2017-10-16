/*
 * SonarQube COBOL Custom Rules Example
 * Copyright (C) 2009-2016 SonarSource SA
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
package com.mycompany.cobol.sample;

import com.mycompany.cobol.sample.checks.ForbiddenCallRule;
import com.mycompany.cobol.sample.checks.IssueOnEachFileRule;
import com.mycompany.cobol.sample.checks.TrivialEvaluateRule;
import com.sonarsource.cobol.api.ast.CobolCheckRepository;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionAnnotationLoader;

/**
 * Extension point to list all your custom Cobol rules.
 */
public class CobolCustomCheckRepository implements CobolCheckRepository, RulesDefinition {

  // Change key and name to reflect your company rule repository.
  // Don't use "cobol" key, it's the core repository.
  private static final String REPOSITORY_KEY = "my-custom-cobol";
  private static final String REPOSITORY_NAME = "My Custom Cobol Analyzer";

  // Must be "cobol"
  private static final String REPOSITORY_LANGUAGE = "cobol";

  @Override
  public String getRepositoryKey() {
    return REPOSITORY_KEY;
  }

  @Override
  public Collection<Object> getCheckClassesOrObjects() {
    return Arrays.asList(
      ForbiddenCallRule.class,
      IssueOnEachFileRule.class,
      TrivialEvaluateRule.class);
  }

  @Override
  public void define(Context context) {
    // Create the custom rule repository
    NewRepository repository = context.createRepository(REPOSITORY_KEY, REPOSITORY_LANGUAGE).setName(REPOSITORY_NAME);

    // Load rule meta data from annotations
    RulesDefinitionAnnotationLoader annotationLoader = new RulesDefinitionAnnotationLoader();

    getCheckClassesOrObjects().stream()
      .map(Class.class::cast)
      .forEach(ruleClass -> annotationLoader.load(repository, ruleClass));

    // Optionally override html description from annotation with content from html files
    repository.rules().forEach(rule -> rule.setHtmlDescription(loadResource("/org/sonar/l10n/cobol/rules/cobol/" + rule.key() + ".html")));


    // Optionally define remediation costs
    Map<String,String> remediationCosts = new HashMap<>();
    remediationCosts.put("ForbiddenCall", "5min");
    remediationCosts.put("IssueOnEachFile", "5min");
    remediationCosts.put("TrivialEvaluate", "5min");
    repository.rules().forEach(rule -> rule.setDebtRemediationFunction(
      rule.debtRemediationFunctions().constantPerIssue(remediationCosts.get(rule.key()))));

    // Save changes
    repository.done();
  }

  private String loadResource(String path) {
    URL resource = getClass().getResource(path);
    if (resource == null) {
      throw new IllegalStateException("Resource not found: " + path);
    }
    ByteArrayOutputStream result = new ByteArrayOutputStream();
    try (InputStream in = resource.openStream()) {
      byte[] buffer = new byte[1024];
      for (int len = in.read(buffer); len != -1; len = in.read(buffer)) {
        result.write(buffer, 0, len);
      }
      return new String(result.toByteArray(), StandardCharsets.UTF_8);
    } catch (IOException e) {
      throw new IllegalStateException("Failed to read resource: " + path, e);
    }
  }

}
