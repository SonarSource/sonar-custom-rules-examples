/*
 * Copyright (C) 2009-2016 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.mycompany.cobol.sample;

import com.mycompany.cobol.sample.checks.SampleCheckRepository;
import org.sonar.api.Plugin;

public class SamplePlugin implements Plugin {

  @Override
  public void define(Context context) {
    // custom checks
    context.addExtension(SampleCheckRepository.class);
  }

}
