/*
 * Copyright (C) 2009-2016 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.mycompany.cobol.sample.checks;

import com.sonarsource.cobol.api.ast.CobolAstCheckRepository;
import java.util.Arrays;
import java.util.Collection;

public class SampleCheckRepository extends CobolAstCheckRepository {

  @SuppressWarnings("unchecked")
  @Override
  public Collection getCheckClasses() {
    return Arrays.<Class>asList(
      SampleCheck.class,
      ForbiddenCallCheck.class,
      TrivialEvaluateCheck.class);
  }

}
