package org.sonar.samples.java;

import java.util.List;

import org.sonar.plugins.java.api.JavaCheck;
import org.sonar.samples.java.checks.AvoidAnnotationRule;
import org.sonar.samples.java.checks.AvoidBrandInMethodNamesRule;
import org.sonar.samples.java.checks.AvoidMethodDeclarationRule;
import org.sonar.samples.java.checks.AvoidSuperClassRule;
import org.sonar.samples.java.checks.AvoidUnmodifiableListRule;
import org.sonar.samples.java.checks.MyCustomSubscriptionRule;
import org.sonar.samples.java.checks.SecurityAnnotationMandatoryRule;
import org.sonar.samples.java.checks.SpringControllerRequestMappingEntityRule;

import com.google.common.collect.ImmutableList;

public final class RulesList {

  private RulesList() {
  }

  public static List<Class> getChecks() {
    return ImmutableList.<Class>builder().addAll(getJavaChecks()).addAll(getJavaTestChecks()).build();
  }

  public static List<Class<? extends JavaCheck>> getJavaChecks() {
    return ImmutableList.<Class<? extends JavaCheck>>builder()
      .add(SpringControllerRequestMappingEntityRule.class)
      .add(AvoidAnnotationRule.class)
      .add(AvoidBrandInMethodNamesRule.class)
      .add(AvoidMethodDeclarationRule.class)
      .add(AvoidSuperClassRule.class)
      .add(AvoidUnmodifiableListRule.class)
      .add(MyCustomSubscriptionRule.class)
      .add(SecurityAnnotationMandatoryRule.class)
      .build();
  }

  public static List<Class<? extends JavaCheck>> getJavaTestChecks() {
    return ImmutableList.<Class<? extends JavaCheck>>builder()
      .build();
  }
}
