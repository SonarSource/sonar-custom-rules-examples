/*
 * Creation : 20 avr. 2015
 */
package org.sonar.samples.java.checks;

import java.util.List;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.Tree;

import com.google.common.collect.ImmutableList;

/**
 * Only to bring out the unit test requirement about classpath when bytecode methods used (see rule unit test class)
 */
@Rule(key = "AvoidSuperClass")
public class AvoidSuperClassRule extends IssuableSubscriptionVisitor {

  public static final List<String> SUPER_CLASS_AVOID = ImmutableList.of("org.slf4j.Logger");

  @Override
  public List<Tree.Kind> nodesToVisit() {
    // Register to the kind of nodes you want to be called upon visit.
    return ImmutableList.of(Tree.Kind.CLASS);
  }

  @Override
  public void visitNode(Tree tree) {
    // Visit CLASS node only => cast could be done
    ClassTree treeClazz = (ClassTree) tree;

    // No extends => stop to visit class
    if (treeClazz.superClass() == null) {
      return;
    }

    // For 'symbolType' usage, jar in dependencies must be on classpath, !unknownSymbol! result otherwise
    String superClassName = treeClazz.superClass().symbolType().fullyQualifiedName();

    // Check if superClass avoid
    if (SUPER_CLASS_AVOID.contains(superClassName)) {
      reportIssue(tree, String.format("The usage of super class %s is forbidden", superClassName));
    }
  }

}
