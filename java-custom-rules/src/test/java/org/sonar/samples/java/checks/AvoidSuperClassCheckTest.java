/*
 * Creation : 20 avr. 2015
 */
package org.sonar.samples.java.checks;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class AvoidSuperClassCheckTest {

  /** JAR dependencies for classpath execution */
  private static final List<File> CLASSPATH_JAR;

  static {
    // Jar ClassPath construction. Don't use 'ClassLoader.getSystemClassLoader()', because with Maven+Surefire/Jacoco execution, only
    // surefirebooter.jar & jacoco.agent-version-runtime.jar are on classpath => 'old schoold way'
    CLASSPATH_JAR = new ArrayList<>();
    for (String jar : System.getProperty("java.class.path").split(File.pathSeparator)) {
      if (jar.endsWith(".jar")) {
        CLASSPATH_JAR.add(new File(jar));
      }
    }
  }

  @Test
  public void checkWithJarDependenciesInClassPath() throws Exception {
    // As external sources are required to run the rule ('symbolType' used in custom rule, which is
    // part of the semantic API), the test requires external dependencies in order to be run correctly.

    // Verifies that the check will raise the adequate issues with the expected message.
    // In the test file, lines which should raise an issue have been commented out
    // by using the following syntax: "// Noncompliant {{EXPECTED_MESSAGE}}"
    JavaCheckVerifier.verify("src/test/files/AvoidSuperClassCheck.java", new AvoidSuperClassRule(), CLASSPATH_JAR);
  }
}
