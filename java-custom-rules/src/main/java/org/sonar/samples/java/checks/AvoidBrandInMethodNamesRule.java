package org.sonar.samples.java.checks;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;

@Rule(key = "AvoidBrandInMethodNames")
public class AvoidBrandInMethodNamesRule extends BaseTreeVisitor implements JavaFileScanner {

  private JavaFileScannerContext context;

  protected static final String COMPANY_NAME = "MyCompany";

  @Override
  public void scanFile(JavaFileScannerContext context) {
    this.context = context;

    // The call to the scan method on the root of the tree triggers the visit of the AST by this visitor
    scan(context.getTree());

    // For debugging purpose, you can print out the entire AST of the analyzed file
    System.out.println(PrinterVisitor.print(context.getTree()));
  }

  /**
   * Overriding the visitor method to implement the logic of the rule.
   * @param tree AST of the visited method.
   */
  @Override
  public void visitMethod(MethodTree tree) {

    if (tree.simpleName().name().toUpperCase().contains(COMPANY_NAME.toUpperCase())) {
      // Adds an issue by attaching it with the tree and the rule
      context.reportIssue(this, tree, "Avoid using Brand in method name");
    }
    // The call to the super implementation allows to continue the visit of the AST.
    // Be careful to always call this method to visit every node of the tree.
    super.visitMethod(tree);

    // All the code located after the call to the overridden method is executed when leaving the node
  }

}
