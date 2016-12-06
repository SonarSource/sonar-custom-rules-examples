/*
 * Copyright (C) 2009-2016 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.mycompany.cobol.sample.checks;

import org.junit.Test;

import static com.sonarsource.cobol.testing.checks.CheckTestingUtils.analyze;
import static com.sonarsource.cobol.testing.checks.CheckTestingUtils.assertOnlyOneViolation;

public class SampleCheckTest {

  @Test
  public void testVisitNode() {
    analyze("/checks/SRC/SampleCheckTest.cobol", new SampleCheck());
    assertOnlyOneViolation().atLine(5).withMessage("Violation here !");
  }

}
