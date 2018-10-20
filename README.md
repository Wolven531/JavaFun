# JavaFun

This repository is for messing around with Java

## Reference Materials

* [System properties](https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html)
* [String Formatting](https://dzone.com/articles/java-string-format-examples)
* [Try with resources](https://www.baeldung.com/java-try-with-resources)
* [Maven in 5 minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)
* [How to create runnable JAR file](https://www.baeldung.com/executable-jar-with-maven)
* [Build a runnable JAR in 5 minutes](https://cwiki.apache.org/confluence/display/MAVEN/Tutorial%3A+Build+a+JAR+file+with+Maven+in+5+minutes)
  * This is the only runnable JAR related resource that actually worked for me (includes command line example of running JAR)
* [Download page for dos2unix](http://dos2unix.sourceforge.net)
* [Download page for Mockito JAR 2.0.2 beta](https://mvnrepository.com/artifact/org.mockito/mockito-all/2.0.2-beta)
* [Chocolatey install instructions](https://chocolatey.org/docs/installation)

## Useful Tips

* Install Chocolatey

```PowerShell
Set-ExecutionPolicy Bypass -Scope Process -Force; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))
```

* Install JDK

```PowerShell
choco install jdk8 -params 'installdir=C:\\java8' -y
```

* Install Maven

```PowerShell
choco install maven -y
```

* Install netbeans

```PowerShell
choco install netbeans -y
```

* Line endings fix

```PowerShell
Get-ChildItem -Path . -Filter '*.*' -Recurse | ForEach-Object { dos2unix.exe $_.FullName }
```

* Generate JDK 8 project using Maven

```PowerShell
cmd /c mvn archetype:generate -DarchetypeArtifactId="archetype-quickstart-jdk8" -DarchetypeGroupId="com.github.ngeor"
```

* List contents of JAR (**J**-ava **AR**-chive file)

```PowerShell
jar tf .\target\javafun-1.0-SNAPSHOT.jar
```

* Run project using command line (from repository root)

```Cmd
runProgram.cmd
```

* Run project using PowerShell (from repository root)

```PowerShell
Set-ExecutionPolicy Bypass -Scope Process -Force; ./runProgram.ps1
```

* Quick and dirty environment setup (using Chocolatey)

```PowerShell
choco install copyq git jdk8 netbeans -y
```

* Maven commands
  * `mvn clean` - Delete `./target` directory (previous builds)
  * `mvn install` - Create `./target` directory (new build)
  * `mvn clean install` - Delete AND create `./target` directory (💯 new build)
  * `mvn compile` - ???
  * `mvn assembly:single` - ???
  * `mvn jar:jar` - ???
