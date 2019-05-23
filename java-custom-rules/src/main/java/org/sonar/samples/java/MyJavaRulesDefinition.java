/*
 * SonarQube Java Custom Rules Example
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
package org.sonar.samples.java;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import org.apache.commons.lang.StringUtils;
import org.sonar.api.rule.RuleStatus;
import org.sonar.api.rules.RuleType;
import org.sonar.api.server.debt.DebtRemediationFunction;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinitionAnnotationLoader;
import org.sonar.api.utils.AnnotationUtils;
import org.sonar.plugins.java.api.JavaCheck;
import org.sonar.squidbridge.annotations.RuleTemplate;

/**
 * Declare rule metadata in server repository of rules. 
 * That allows to list the rules in the page "Rules".
 */
public class MyJavaRulesDefinition implements RulesDefinition {

  // don't change that because the path is hard coded in CheckVerifier
  private static final String RESOURCE_BASE_PATH = "/org/sonar/l10n/java/rules/squid";

  public static final String REPOSITORY_KEY = "mycompany-java";

  private final Gson gson = new Gson();

  @Override
  public void define(Context context) {
    NewRepository repository = context
      .createRepository(REPOSITORY_KEY, "java")
      .setName("MyCompany Custom Repository");

    for (Class<? extends JavaCheck> check : RulesList.getChecks()) {
      new RulesDefinitionAnnotationLoader().load(repository, check);
      newRule(check, repository);
    }
    repository.done();
  }

  protected void newRule(Class<? extends JavaCheck> ruleClass, NewRepository repository) {

    org.sonar.check.Rule ruleAnnotation = AnnotationUtils.getAnnotation(ruleClass, org.sonar.check.Rule.class);
    if (ruleAnnotation == null) {
      throw new IllegalArgumentException("No Rule annotation was found on " + ruleClass);
    }
    String ruleKey = ruleAnnotation.key();
    if (StringUtils.isEmpty(ruleKey)) {
      throw new IllegalArgumentException("No key is defined in Rule annotation of " + ruleClass);
    }
    NewRule rule = repository.rule(ruleKey);
    if (rule == null) {
      throw new IllegalStateException("No rule was created for " + ruleClass + " in " + repository.key());
    }
    ruleMetadata(rule);

    rule.setTemplate(AnnotationUtils.getAnnotation(ruleClass, RuleTemplate.class) != null);
  }

  private void ruleMetadata(NewRule rule) {
    String metadataKey = rule.key();
    addHtmlDescription(rule, metadataKey);
    addMetadata(rule, metadataKey);
  }

  private void addMetadata(NewRule rule, String metadataKey) {
    URL resource = MyJavaRulesDefinition.class.getResource(RESOURCE_BASE_PATH + "/" + metadataKey + "_java.json");
    if (resource != null) {
      RuleMetatada metatada = gson.fromJson(readResource(resource), RuleMetatada.class);
      rule.setSeverity(metatada.defaultSeverity.toUpperCase(Locale.US));
      rule.setName(metatada.title);
      rule.addTags(metatada.tags);
      rule.setType(RuleType.valueOf(metatada.type));
      rule.setStatus(RuleStatus.valueOf(metatada.status.toUpperCase(Locale.US)));
      if (metatada.remediation != null) {
        rule.setDebtRemediationFunction(metatada.remediation.remediationFunction(rule.debtRemediationFunctions()));
        rule.setGapDescription(metatada.remediation.linearDesc);
      }
    }
  }

  private static void addHtmlDescription(NewRule rule, String metadataKey) {
    URL resource = MyJavaRulesDefinition.class.getResource(RESOURCE_BASE_PATH + "/" + metadataKey + "_java.html");
    if (resource != null) {
      rule.setHtmlDescription(readResource(resource));
    }
  }

  private static String readResource(URL resource) {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.openStream()))) {
      return reader.lines().collect(Collectors.joining("\n"));
    } catch (Exception e) {
      throw new IllegalStateException("Failed to read: " + resource, e);
    }
  }

  private static class RuleMetatada {
    String title;
    String status;
    @Nullable
    Remediation remediation;

    String type;
    String[] tags;
    String defaultSeverity;
  }

  private static class Remediation {
    String func;
    String constantCost;
    String linearDesc;
    String linearOffset;
    String linearFactor;

    public DebtRemediationFunction remediationFunction(DebtRemediationFunctions drf) {
      if (func.startsWith("Constant")) {
        return drf.constantPerIssue(constantCost.replace("mn", "min"));
      }
      if ("Linear".equals(func)) {
        return drf.linear(linearFactor.replace("mn", "min"));
      }
      return drf.linearWithOffset(linearFactor.replace("mn", "min"), linearOffset.replace("mn", "min"));
    }
  }

}
