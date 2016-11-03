package org.sonar.samples.java.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class AvoidAnnotationCheckTest {

  @Test
  public void detected() {

    // Use an instance of the check under test to raise the issue.
    AvoidAnnotationRule check = new AvoidAnnotationRule();

    // define the forbidden annotation name
    check.name = "Zuper";

    // Verifies that the check will raise the adequate issues with the expected message.
    // In the test file, lines which should raise an issue have been commented out
    // by using the following syntax: "// Noncompliant {{EXPECTED_MESSAGE}}"
    JavaCheckVerifier.verify("src/test/files/AvoidAnnotationCheck.java", check);
  }
}
