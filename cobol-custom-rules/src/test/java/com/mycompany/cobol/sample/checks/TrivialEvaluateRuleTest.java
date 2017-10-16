/*
 * Copyright (C) 2009-2016 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.mycompany.cobol.sample.checks;

import com.sonarsource.cobol.testing.checks.CobolCheckVerifier;
import java.io.File;
import org.junit.Test;

public class TrivialEvaluateRuleTest {

  @Test
  public void testVisitNode() {
    TrivialEvaluateRule check = new TrivialEvaluateRule();
    CobolCheckVerifier.verify(
      new File("src/test/resources/checks/SRC/TrivialEvaluate.cbl"),
      check);
  }

}
