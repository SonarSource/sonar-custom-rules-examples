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

cd php-custom-rules	
RunMaven
cd ..

cd python-custom-rules
RunMaven
cd ..

cd rpg-custom-rules
wget https://binaries.sonarsource.com/CommercialDistribution/sonar-rpg-plugin/sonar-rpg-plugin-2.3.0.1187.jar
mv sonar-rpg-plugin-2.3.0.1187.jar ./lib/sonar-rpg-plugin-2.3.0.1187.jar
RunMaven
cd ..

cd cobol-custom-rules
wget https://binaries.sonarsource.com/CommercialDistribution/sonar-cobol-plugin/sonar-cobol-plugin-4.3.0.3019.jar
mv sonar-cobol-plugin-4.3.0.3019.jar ./lib/sonar-cobol-plugin-4.3.0.3019.jar
RunMaven
cd ..
