/*
 * Copyright (C) 2009-2016 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.mycompany.cobol.sample.checks;

import com.sonarsource.cobol.testing.checks.CobolCheckVerifier;
import java.io.File;
import org.junit.Test;

public class ForbiddenCallRuleTest {

  @Test
  public void testVisitNode() {
    ForbiddenCallRule check = new ForbiddenCallRule();
    CobolCheckVerifier.verify(
      new File("src/test/resources/checks/SRC/ForbiddenCall.cbl"),
      check);
  }

}
