[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=org.sonarsource.samples%3Ajava-custom-rules&metric=alert_status)](https://sonarcloud.io/dashboard?id=org.sonarsource.samples%3Ajava-custom-rules)

This example demonstrates how to write **Custom Rules** for SonarJava.

## API changes

### 5.12
**Dropped**
* `org.sonar.plugins.java.api.JavaFileScannerContext`: Drop deprecated method used to retrieve trees contributing to the complexity of a method from  (deprecated since SonarJava 4.1). 
```
//org.sonar.plugins.java.api.JavaFileScannerContext
/**
 * Computes the list of syntax nodes which are contributing to increase the complexity for the given methodTree.
 * @deprecated use {@link #getComplexityNodes(Tree)} instead
 * @param enclosingClass not used.
 * @param methodTree the methodTree to compute the complexity.
 * @return the list of syntax nodes incrementing the complexity.
 */
@Deprecated
List<Tree> getMethodComplexityNodes(ClassTree enclosingClass, MethodTree methodTree);
```
* `org.sonar.plugins.java.api.JavaResourceLocator`: The following method has been dropped (deprecated since SonarJava 4.1), without replacement.
```
//org.sonar.plugins.java.api.JavaResourceLocator
/**
 * get source file key by class name.
 * @deprecated since 4.1 : will be dropped with no replacement.
 * @param className fully qualified name of the analyzed class.
 * @return key of the source file for the given class.
 */
@Deprecated
String findSourceFileKeyByClassName(String className);
```
* `org.sonar.plugins.surefire.api.SurefireUtils`: Dropping deprecated field with old property (deprecated since SonarJava 4.11)
```
//org.sonar.plugins.surefire.api.SurefireUtils
/**
 * @deprecated since 4.11
 */
@Deprecated
public static final String SUREFIRE_REPORTS_PATH_PROPERTY = "sonar.junit.reportsPath";
```
**Deprecated**  
* `org.sonar.plugins.java.api.JavaFileScannerContext`: Deprecate usage of File-based methods from API, which will be removed in future release. Starting from this version, methods relying on InputFile has to be preferred.
```
//org.sonar.plugins.java.api.JavaFileScannerContext
/**
 * Report an issue at a specific line of a given file.
 * This method is used for one
 * @param file File on which to report
 * @param check The check raising the issue.
 * @param line line on which to report the issue
 * @param message Message to display to the user
 * @deprecated since SonarJava 5.12 - File are not supported anymore. Use corresponding 'reportIssue' methods, or directly at project level
 */
@Deprecated
void addIssue(File file, JavaCheck check, int line, String message);
/**
 * FileKey of currently analyzed file.
 * @return the fileKey of the file currently analyzed.
 * @deprecated since SonarJava 5.12 - Rely on the InputFile key instead, using {@link #getInputFile()}
 */
@Deprecated
String getFileKey();
 
/**
 * File under analysis.
 * @return the currently analyzed file.
 * @deprecated since SonarJava 5.12 - File are not supported anymore. Use {@link #getInputFile()} or {@link #getProject()} instead
 */
@Deprecated
File getFile();
```
* Deprecate methods which are not relevant anymore in switch-related trees from API, following introduction of the new Java 12 `switch` expression:
```
//org.sonar.plugins.java.api.tree.CaseLabelTree
/**
 * @deprecated (since 5.12) use the {@link #expressions()} method.
 */
@Deprecated
@Nullable
ExpressionTree expression();
 
/**
 * @deprecated (since 5.12) use the {@link #colonOrArrowToken()} method.
 */
@Deprecated
SyntaxToken colonToken();
```
**Added**
* `org.sonar.plugins.java.api.JavaFileScannerContext`: Following methods have been added in order to provide help reporting issues at project level, and access data through SonarQube's InputFile API, which won't be possible anymore through files:
```
//JavaFileScannerContext: New methods
/**
 * Report an issue at at the project level.
 * @param check The check raising the issue.
 * @param message Message to display to the user
 */
void addIssueOnProject(JavaCheck check, String message);
 
/**
 * InputFile under analysis.
 * @return the currently analyzed inputFile.
 */
InputFile getInputFile();
 
/**
 * InputComponent representing the project being analyzed
 * @return the project component
 */
InputComponent getProject();
```
* In order to cover the Java 12 new switch expression, introduce a new Tree in the SonarJava Syntax Tree API  (Corresponding `Tree.Kind`: `SWITCH_EXPRESSION` ). New methods have also been added to fluently integrate the new switch expression into the SonarJava API.
```
//org.sonar.plugins.java.api.tree.SwitchExpressionTree
/**
 * 'switch' expression.
 *
 * JLS 14.11
 *
 * <pre>
 *   switch ( {@link #expression()} ) {
 *     {@link #cases()}
 *   }
 * </pre>
 *
 * @since Java 12
 */
@Beta
public interface SwitchExpressionTree extends ExpressionTree {
 
  SyntaxToken switchKeyword();
 
  SyntaxToken openParenToken();
 
  ExpressionTree expression();
 
  SyntaxToken closeParenToken();
 
  SyntaxToken openBraceToken();
 
  List<CaseGroupTree> cases();
 
  SyntaxToken closeBraceToken();
}
```
```
//org.sonar.plugins.java.api.tree.SwitchStatementTree
/**
 * Switch expressions introduced with support Java 12
 * @since SonarJava 5.12
 */
SwitchExpressionTree asSwitchExpression();
```
```
//org.sonar.plugins.java.api.tree.CaseLabelTree
/**
 * @return true for case with colon: "case 3:" or "default:"
 *         false for case with arrow: "case 3 ->" or "default ->"
 * @since 5.12 (Java 12 new features)
 */
boolean isFallThrough();
 
/**
 * @since 5.12 (Java 12 new features)
 */
SyntaxToken colonOrArrowToken();
```
```
//org.sonar.plugins.java.api.tree.BreakStatementTree
/**
 * @since 5.12 (Java 12 new features)
 */
@Nullable
ExpressionTree value();
```
```
//org.sonar.plugins.java.api.tree.TreeVisitor
void visitSwitchExpression(SwitchExpressionTree tree);
```

### 5.7
**Breaking**  
This change will impact mostly the custom rules relying on semantic API. The type returned by some symbols will change from raw type to parameterized type with identity substitution and this will change how subtyping will answer.

It is possible to get the previous behavior back by using type erasure on the newly returned type. Note that not all returned types are impacted by this change.

Example:
```
@Rule(key = "MyFirstCustomRule")
public class MyFirstCustomCheck extends IssuableSubscriptionVisitor {
 
    @Override
    public List<Kind> nodesToVisit() {
        return ImmutableList.of(Kind.METHOD);
    }
 
    @Override
    public void visitNode(Tree tree) {
        MethodTree method = (MethodTree) tree;
        MethodSymbol symbol = method.symbol();
         
        Type returnType = symbol.returnType().type();
        // When analyzing the code "MyClass<Integer> foo() {return null; }"
        // BEFORE: returnType == ClassJavaType
        // NOW: returnType == ParametrizedTypeJavaType
 
        // Getting back previous type
        Type erasedType = returnType.erasure();
        // erasedType == ClassJavaType
    }
}
```
