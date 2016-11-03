package org.sonar.samples.java;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.sonar.api.rules.RuleType;
import org.sonar.api.server.debt.DebtRemediationFunction.Type;
import org.sonar.api.server.rule.RuleParamType;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinition.Param;
import org.sonar.api.server.rule.RulesDefinition.Repository;
import org.sonar.api.server.rule.RulesDefinition.Rule;

public class MyJavaRulesDefinitionTest {

  @Test
  public void test() {
    MyJavaRulesDefinition rulesDefinition = new MyJavaRulesDefinition();
    RulesDefinition.Context context = new RulesDefinition.Context();
    rulesDefinition.define(context);
    RulesDefinition.Repository repository = context.repository(MyJavaRulesDefinition.REPOSITORY_KEY);

    assertThat(repository.name()).isEqualTo("MyCompany");
    assertThat(repository.language()).isEqualTo("java");
    assertThat(repository.rules()).hasSize(RulesList.getChecks().size());

    assertRuleProperties(repository);
    assertParameterProperties(repository);
    assertAllRuleParametersHaveDescription(repository);
  }

  private void assertParameterProperties(Repository repository) {
    // TooManyLinesInFunctionCheck
    Param max = repository.rule("AvoidAnnotation").param("name");
    assertThat(max).isNotNull();
    assertThat(max.defaultValue()).isEqualTo("Inject");
    assertThat(max.description()).isEqualTo("Name of the annotation to avoid, without the prefix @, for instance 'Override'");
    assertThat(max.type()).isEqualTo(RuleParamType.STRING);
  }

  private void assertRuleProperties(Repository repository) {
    Rule rule = repository.rule("AvoidAnnotation");
    assertThat(rule).isNotNull();
    assertThat(rule.name()).isEqualTo("Title of AvoidAnnotation");
    assertThat(rule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
    assertThat(rule.type()).isEqualTo(RuleType.BUG);
  }

  private void assertAllRuleParametersHaveDescription(Repository repository) {
    for (Rule rule : repository.rules()) {
      for (Param param : rule.params()) {
        assertThat(param.description()).as("description for " + param.key()).isNotEmpty();
      }
    }
  }

}
