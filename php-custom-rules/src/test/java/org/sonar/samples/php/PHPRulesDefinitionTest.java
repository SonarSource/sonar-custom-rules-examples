package org.sonar.samples.php;

import org.junit.Test;
import org.sonar.api.server.rule.RulesDefinition;

import static org.junit.Assert.assertEquals;

public class PHPRulesDefinitionTest {

  @Test
  public void rules() {
    PHPRulesDefinition rulesDefinition = new PHPRulesDefinition();
    RulesDefinition.Context context = new RulesDefinition.Context();
    rulesDefinition.define(context);
    RulesDefinition.Repository repository = context.repository("custom");
    assertEquals(2, repository.rules().size());
  }
}
