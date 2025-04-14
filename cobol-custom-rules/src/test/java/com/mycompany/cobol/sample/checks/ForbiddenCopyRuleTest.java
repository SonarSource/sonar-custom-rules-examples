/*
 * Copyright (C) 2009-2016 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.mycompany.cobol.sample.checks;

import com.sonarsource.cobol.squid.CobolConfiguration;
import com.sonarsource.cobol.testing.checks.CobolCheckVerifier;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Test;

public class ForbiddenCopyRuleTest {

  @Test
  public void testVisitNode() {
    ForbiddenCopyRule check = new ForbiddenCopyRule();
    CobolConfiguration conf = CobolCheckVerifier.defaultConfig();
    conf.setPreprocessorListeners(Collections.singletonList(check));
    CobolCheckVerifier.verify(
      new File("src/test/resources/checks/SRC/ForbiddenCopy.cbl"), conf, check);
  }

}
