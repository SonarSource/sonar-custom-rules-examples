This example demonstrates how to write **Custom Rules** for the SonarQube COBOL analyzer.

It assumes that the JAR file of the COBOL analyzer (file sonar-cobol-plugin-4.0.0.2525.jar)
is available in the "lib" directory.
You can download the JAR file of the COBOL analyzer from the [SonarSource website](http://redirect.sonarsource.com/plugins/cobol.html).

To deploy your Custom rules to your SonarQube 5.6+ server, you must copy the resulting JAR file of this project, as well as the JAR file
of the COBOL analyzer, to directory "extensions/plugins" of your SonarQube 5.6+ server.

See more complete explanations [here](http://docs.sonarqube.org/display/PLUG/Custom+Rules+for+COBOL).
