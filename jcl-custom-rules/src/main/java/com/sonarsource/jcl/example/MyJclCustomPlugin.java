/*
 * Copyright (C) 2024-2024 SonarSource SA
 * All rights reserved
 * mailto:info AT sonarsource DOT com
 */
package com.sonarsource.jcl.example;

import org.sonar.api.Plugin;

public class MyJclCustomPlugin implements Plugin {
  @Override
  public void define(Context context) {
    context.addExtension(MyJclCustomRulesDefinition.class);
  }
}
