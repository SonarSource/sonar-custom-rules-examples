package com.mycompany.cobol.sample.checks;

import com.sonarsource.cobol.api.ast.CobolCheck;
import com.sonarsource.cobol.api.pp.CopyPreprocessorEvent;
import com.sonarsource.cobol.api.pp.CopyPreprocessorEventContext;
import org.sonar.check.Priority;
import org.sonar.check.Rule;

@Rule(key = "ForbiddenCopy",
      name = "COPY statements should not be used",
      priority = Priority.CRITICAL,
      tags = {"bug"})
public class ForbiddenCopyRule extends CobolCheck implements CopyPreprocessorEvent {

  private static final String MESSAGE = "Don't use COPY!";

  @Override
  public void onCopyPreprocessorEvent(CopyPreprocessorEventContext context) {
      reportIssue(MESSAGE).on(context.tokens().get(0));
  }

}

