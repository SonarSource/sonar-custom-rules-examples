/*
 * SonarQube RPG Custom Rules Example
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
package com.sonarsource.rpg.example.checks;

import com.sonarsource.rpg.api.test.RpgCheckVerifier;
import java.io.File;
import java.nio.charset.StandardCharsets;
import org.junit.Test;

public class DataStructureNamingConventionCheckTest {

  @Test
  public void test() throws Exception {
    RpgCheckVerifier.verify(new DataStructureNamingConventionCheck(), new File("src/test/resources/data-structure-name.rpg"), StandardCharsets.UTF_8);
  }

}
