#!/bin/bash

MAVEN_CMD="clean org.jacoco:jacoco-maven-plugin:prepare-agent package sonar:sonar -Dsonar.host.url=https://sonarqube.com -Dsonar.login=${SONARQUBE_TOKEN}"
echo $MAVEN_CMD

cd java-custom-rules
mvn $MAVEN_CMD
cd ..

cd javascript-custom-rules
mvn $MAVEN_CMD
cd ..

cd php-custom-rules	
mvn $MAVEN_CMD
cd ..

cd rpg-custom-rules
wget https://sonarsource.bintray.com/CommercialDistribution/sonar-rpg-plugin/sonar-rpg-plugin-2.0.0.496.jar
mv sonar-rpg-plugin-2.0.0.496.jar ./lib/sonar-rpg-plugin-2.0.0.496.jar 
mvn $MAVEN_CMD
cd ..
