package org.sonar.samples.java.checks;

import org.junit.Rule;
import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;
import org.sonar.squidbridge.checks.CheckMessagesVerifierRule;

public class SecurityAnnotationMandatoryCheckTest {

  @Rule
  public CheckMessagesVerifierRule checkMessagesVerifier = new CheckMessagesVerifierRule();

  @Test
  public void detected() {

    // Use an instance of the check under test to raise the issue.
    SecurityAnnotationMandatoryRule check = new SecurityAnnotationMandatoryRule();

    // define the mandatory annotation name
    check.name = "MySecurityAnnotation";

    // Verifies that the check will raise the adequate issues with the expected message.
    // In the test file, lines which should raise an issue have been commented out
    // by using the following syntax: "// Noncompliant {{EXPECTED_MESSAGE}}"
    JavaCheckVerifier.verify("src/test/files/SecurityAnnotationMandatoryCheck.java", check);
  }
}
