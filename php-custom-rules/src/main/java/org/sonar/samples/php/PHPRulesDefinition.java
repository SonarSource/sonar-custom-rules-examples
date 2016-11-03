/*
 * Copyright (C) 2009-2013 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package org.sonar.samples.php;


import com.google.common.collect.ImmutableList;

import org.sonar.plugins.php.api.visitors.PHPCustomRulesDefinition;
import org.sonar.samples.php.checks.ForbiddenFunctionUseCheck;

/**
 * Extension point to define a PHP rule repository.
 */
public class PHPRulesDefinition extends PHPCustomRulesDefinition {

  /**
   * Provide the repository name
   */
  @Override
  public String repositoryName() {
    return "Custom Repository";
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
    return ImmutableList.of(ForbiddenFunctionUseCheck.class);
  }
}
