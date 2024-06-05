package com.sonarsource.jcl.example.checks;

import com.sonarsource.jcl.checks.testkit.JclVerifier;
import org.junit.jupiter.api.Test;

class MyCustomRuleTest {

  @Test
  void test() {
    JclVerifier.verify("src/test/resources/checks/stepname.jcl", new MyCustomRule());
  }

}

