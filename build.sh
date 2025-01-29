#!/bin/bash

RunMaven() {
  typeset MAVEN_CMD="mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent package"
  typeset RETURN_CODE

  echo $MAVEN_CMD
  eval $MAVEN_CMD
  RETURN_CODE=$?
  if [ $RETURN_CODE != 0 ]; then
    printf "ERROR: [%d] when executing command: '$MAVEN_CMD'" $RETURN_CODE
    exit $RETURN_CODE
  fi
}

cd cobol-custom-rules
wget https://binaries.sonarsource.com/CommercialDistribution/sonar-cobol-plugin/sonar-cobol-plugin-5.7.0.8061.jar
mv sonar-cobol-plugin-5.7.0.8061.jar ./lib/sonar-cobol-plugin-5.7.0.8061.jar
RunMaven
cd ..

