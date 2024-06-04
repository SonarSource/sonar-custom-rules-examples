#!/bin/bash

RunMaven() {
  typeset MAVEN_CMD="mvn clean verify package"
  typeset RETURN_CODE

  echo $MAVEN_CMD
  eval $MAVEN_CMD
  RETURN_CODE=$?
  if [ $RETURN_CODE != 0 ]; then
    printf "ERROR: [%d] when executing command: '$MAVEN_CMD'" $RETURN_CODE
    exit $RETURN_CODE
  fi
}

cd python-custom-rules
RunMaven
cd ..

cd rpg-custom-rules
wget https://binaries.sonarsource.com/CommercialDistribution/sonar-rpg-plugin/sonar-rpg-plugin-3.9.0.5001.jar
mv sonar-rpg-plugin-3.9.0.5001.jar ./lib/sonar-rpg-plugin-3.9.0.5001.jar
# We explicitly call validate here, to install the sonar-rpg-plugin jar ahead of packaging
mvn validate
RunMaven
cd ..

cd cobol-custom-rules
wget https://binaries.sonarsource.com/CommercialDistribution/sonar-cobol-plugin/sonar-cobol-plugin-4.3.0.3019.jar
mv sonar-cobol-plugin-4.3.0.3019.jar ./lib/sonar-cobol-plugin-4.3.0.3019.jar
RunMaven
cd ..

cd jcl-custom-rules
wget https://binaries.sonarsource.com/CommercialDistribution/sonar-jcl-plugin/sonar-jcl-plugin-1.2.0.1148.jar
mv sonar-jcl-plugin-1.2.0.1148.jar ./lib/sonar-jcl-plugin-1.2.0.1148.jar
RunMaven
cd ..
