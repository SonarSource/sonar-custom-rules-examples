/*
 * Copyright (C) 2009-2016 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
 */
package com.mycompany.cobol.sample.checks;

import com.sonarsource.cobol.squid.CobolAstScanner;
import com.sonarsource.cobol.testing.checks.CheckMessagesVerifierRule;
import java.io.File;
import org.junit.Rule;
import org.junit.Test;
import org.sonar.squid.api.SourceFile;

public class TrivialEvaluateCheckTest {

  @Rule
  public CheckMessagesVerifierRule checkMessagesVerifier = new CheckMessagesVerifierRule();

  @Test
  public void testVisitNode() {
    TrivialEvaluateCheck check = new TrivialEvaluateCheck();
    SourceFile file = CobolAstScanner.scanSingleFile(new File("src/test/resources/checks/SRC/TrivialEvaluate.cbl"), check);
    checkMessagesVerifier.verify(file.getCheckMessages())
      .next().atLine(4)
      .next().atLine(9);
  }

}
