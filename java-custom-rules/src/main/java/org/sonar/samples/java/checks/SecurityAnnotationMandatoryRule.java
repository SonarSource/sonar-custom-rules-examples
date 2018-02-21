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

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.java.model.PackageUtils;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.AnnotationTree;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.CompilationUnitTree;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.TypeTree;

@Rule(key = "SecurityAnnotationMandatory")
public class SecurityAnnotationMandatoryRule extends BaseTreeVisitor implements JavaFileScanner {

  private static final Logger LOGGER = LoggerFactory.getLogger(SecurityAnnotationMandatoryRule.class);

  private static final String DEFAULT_VALUE = "MySecurityAnnotation";

  private boolean implementsSpecificInterface = Boolean.FALSE;

  private JavaFileScannerContext context;

  @RuleProperty(
    defaultValue = DEFAULT_VALUE,
    description = "Name of the mandatory annotation")
  protected String name;

  @Override
  public void scanFile(JavaFileScannerContext context) {
    this.context = context;
    scan(context.getTree());
  }

  @Override
  public void visitClass(ClassTree tree) {
    List<TypeTree> interfaces = tree.superInterfaces();
    for (TypeTree typeTree : interfaces) {
      LOGGER.info("implements Interface : " + typeTree);
      if ("MySecurityInterface".equals(typeTree.toString())) {
        implementsSpecificInterface = Boolean.TRUE;
      }
    }

    super.visitClass(tree);
  }

  @Override
  public void visitCompilationUnit(CompilationUnitTree tree) {

    if (tree.packageDeclaration() != null) {
      String packageName = PackageUtils.packageName(tree.packageDeclaration(), ".");
      LOGGER.info("PackageName : " + packageName);
    }

    super.visitCompilationUnit(tree);
  }

  @Override
  public void visitMethod(MethodTree tree) {
    if (implementsSpecificInterface) {
      List<AnnotationTree> annotations = tree.modifiers().annotations();

      boolean isHavingMandatoryAnnotation = Boolean.FALSE;

      for (AnnotationTree annotationTree : annotations) {
        if (annotationTree.annotationType().is(Tree.Kind.IDENTIFIER)) {
          IdentifierTree idf = (IdentifierTree) annotationTree.annotationType();
          LOGGER.info("Method Name {}", idf.name());

          if (idf.name().equals(name)) {
            isHavingMandatoryAnnotation = Boolean.TRUE;
          }
        }
      }
      if (!isHavingMandatoryAnnotation) {
        context.reportIssue(this, tree, String.format("Mandatory Annotation not set @%s", name));
      }

    }
    // The call to the super implementation allows to continue the visit of the AST.
    // Be careful to always call this method to visit every node of the tree.
    super.visitMethod(tree);
  }
}
