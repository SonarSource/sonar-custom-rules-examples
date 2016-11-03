/*
 * Copyright (C) 2014-2016 SonarSource SA
 * All rights reserved
 * mailto:contact AT sonarsource DOT com
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
