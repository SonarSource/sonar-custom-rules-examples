[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=org.sonarsource.samples%3Aphp-custom-rules&metric=alert_status)](https://sonarcloud.io/dashboard?id=org.sonarsource.samples%3Aphp-custom-rules)

This example demonstrates how to write **Custom Rules** for SonarPHP.

## API Changes

### 3.1
* Drop of `PhpFile#relativePath`
* `Kind.EXPRESSION_LIST_STATEMENT` and `ExpressionListStatementTree` are deprecated in favor of a new `Kind.ECHO_TAG_STATEMENT`

### 3.0  
* Semantic information available via Symbol interface was cleaned-up. It's no longer exposing the `#scope` method which was internal and shouldn't be used for custom rules
* Symbol interface now provides Symbol#qualifiedName method, which provides qualified name for `NamespaceNameTree` elements. To find out qualified name you can use `SymbolTable#getSymbol(Tree)` method to find the symbol and then invoke 
Symbol#qualifiedName . Alternatively you can use new method `PHPVisitorCheck#getFullyQualifiedName`.

