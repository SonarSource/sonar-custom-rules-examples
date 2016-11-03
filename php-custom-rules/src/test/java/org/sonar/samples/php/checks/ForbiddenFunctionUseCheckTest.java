package org.sonar.samples.php.checks;

import org.junit.Test;
import org.sonar.plugins.php.api.tests.PHPCheckTest;

import java.io.File;

/**
 * Test class to test the check implementation.
 */
public class ForbiddenFunctionUseCheckTest {

  @Test
  public void test() throws Exception {
    PHPCheckTest.check(new ForbiddenFunctionUseCheck(), new File("src/test/resources/checks/forbiddenFunctionUseCheck.php"));
  }

}
