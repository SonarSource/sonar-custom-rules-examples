/*
 * Copyright (C) 2014-2016 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.sonarsource.rpg.example;

import org.sonar.api.Plugin;

public class MyRpgCustomPlugin implements Plugin {

  @Override
  public void define(Context context) {
    context.addExtension(MyRpgRulesDefinition.class);
  }

}
