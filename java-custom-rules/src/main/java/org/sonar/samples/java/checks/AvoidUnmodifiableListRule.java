package org.sonar.samples.java.checks;

import java.util.List;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.NewClassTree;
import org.sonar.plugins.java.api.tree.Tree;

import com.google.common.collect.ImmutableList;

@Rule(key = "AvoidUnmodifiableList")
public class AvoidUnmodifiableListRule extends IssuableSubscriptionVisitor {

  @Override
  public List<Tree.Kind> nodesToVisit() {
    return ImmutableList.of(Tree.Kind.NEW_CLASS);
  }

  @Override
  public void visitNode(Tree tree) {

    if (((NewClassTree) tree).symbolType().isSubtypeOf("org.apache.commons.collections4.list.UnmodifiableList")) {
      reportIssue(tree, "Avoid using UnmodifiableList");
    }
  }

}
