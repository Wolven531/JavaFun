:: Performs clean and install goals with maven and runs java program

@echo off

set artifactName=javafun-1.0-SNAPSHOT.jar
set mainClass=App
set mainPackage=com.williams.anthony

@echo on

mvn clean install
java -cp ".\target\%artifactName%" "%mainPackage%.%mainClass%"
