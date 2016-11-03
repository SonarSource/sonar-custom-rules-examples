/*
 * Copyright (C) 2009-2013 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package org.sonar.samples.php;

import org.sonar.api.Plugin;

/**
 * Extension point to define a Sonar Plugin.
 */
public class PHPCustomRulesPlugin implements Plugin {

  @Override
  public void define(Context context) {
    context.addExtension(PHPRulesDefinition.class);
  }
}
