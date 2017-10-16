#!/bin/bash

RunMaven() {
  typeset MAVEN_CMD="mvn clean org.jacoco:jacoco-maven-plugin:prepare-agent package sonar:sonar -Dsonar.host.url=https://sonarqube.com -Dsonar.login=${SONARQUBE_TOKEN}"
  typeset RETURN_CODE

  echo $MAVEN_CMD
  eval $MAVEN_CMD
  RETURN_CODE=$?
  if [ $RETURN_CODE != 0 ]; then
    printf "ERROR: [%d] when executing command: '$MAVEN_CMD'" $RETURN_CODE
    exit $RETURN_CODE
  fi
}

cd java-custom-rules
RunMaven
cd ..

cd javascript-custom-rules
RunMaven
cd ..

cd php-custom-rules	
RunMaven
cd ..

cd rpg-custom-rules
wget https://sonarsource.bintray.com/CommercialDistribution/sonar-rpg-plugin/sonar-rpg-plugin-2.0.0.496.jar
mv sonar-rpg-plugin-2.0.0.496.jar ./lib/sonar-rpg-plugin-2.0.0.496.jar 
RunMaven
cd ..

cd cobol-custom-rules
wget https://sonarsource.bintray.com/CommercialDistribution/sonar-cobol-plugin/sonar-cobol-plugin-4.0.0.2525.jar
mv sonar-cobol-plugin-4.0.0.2525.jar ./lib/sonar-cobol-plugin-4.0.0.2525.jar
RunMaven
cd ..
