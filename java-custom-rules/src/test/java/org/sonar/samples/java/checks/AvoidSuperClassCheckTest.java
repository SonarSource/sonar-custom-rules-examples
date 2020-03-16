/*
 * SonarQube Java Custom Rules Example
 * Copyright (C) 2016-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
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
    JavaCheckVerifier.newVerifier()
      .onFile("src/test/files/AvoidSuperClassCheck.java")
      .withCheck(new AvoidSuperClassRule())
      .withClassPath(CLASSPATH_JAR)
      .verifyIssues();
  }
}
