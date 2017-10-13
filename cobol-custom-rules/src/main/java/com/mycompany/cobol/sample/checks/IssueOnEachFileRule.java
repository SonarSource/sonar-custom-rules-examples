/*
 * SonarQube COBOL Custom Rules Example
 * Copyright (C) 2009-2016 SonarSource SA
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
package com.mycompany.cobol.sample.checks;

import com.sonar.sslr.api.AstNode;
import com.sonarsource.cobol.api.ast.CobolCheck;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;

@Rule(
  key = "IssueOnEachFile",
  name = "Issue on Each File",
  priority = Priority.MAJOR,
  tags = {"security"})
public class IssueOnEachFileRule extends CobolCheck {

  private static final int PARAMETER_DEFAULT_VALUE = 5;

  @RuleProperty(
    key = "issueLineNumber",
    description = "Line of the code that the issue will be attached to.",
    defaultValue = "" + PARAMETER_DEFAULT_VALUE)
  private int violationLineNumber = PARAMETER_DEFAULT_VALUE;

  @Override
  public void visitFile(AstNode astNode) {
    reportIssue("Issue here !").atLine(violationLineNumber);
  }

}
