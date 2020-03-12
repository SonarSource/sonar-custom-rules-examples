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
package org.sonar.samples.java.checks;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class AvoidTreeListTest {

  @Test
  public void verify() {
    // In order to test this check efficiently, we added the test-jar "org.apache.commons.commons-collections4" to the pom,
    // which is normally not used by the code of our custom plugin.
    // All the classes from this jar will then be read when verifying the ticket, allowing correct type resolution.

    // Verifies automatically that the check will raise the adequate issues with the expected message
    JavaCheckVerifier.verify("src/test/files/AvoidTreeList.java", new AvoidTreeListRule());
  }

}
