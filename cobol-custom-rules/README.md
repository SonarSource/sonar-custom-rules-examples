[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=org.sonarsource.samples%3Acobol-custom-rules&metric=alert_status)](https://sonarcloud.io/dashboard?id=org.sonarsource.samples%3Acobol-custom-rules)

This example demonstrates how to write **Custom Rules** for SonarCOBOL.

It assumes that the JAR file of the COBOL analyzer (file sonar-cobol-plugin-4.2.0.2826.jar)
is available in the "lib" directory.
You can download the JAR file of the COBOL analyzer from the [SonarSource website](http://redirect.sonarsource.com/plugins/cobol.html).

To deploy your Custom rules to your SonarQube 6.7+ server, you must copy the resulting JAR file of this project, as well as the JAR file
of the COBOL analyzer, to directory "extensions/plugins" of your SonarQube 6.7+ server.

See more complete explanations [here](http://docs.sonarqube.org/display/PLUG/Custom+Rules+for+COBOL).
