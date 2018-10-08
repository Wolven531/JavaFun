# JavaFun
This repository is for messing around with Java

## Reference Materials

* [System properties](https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html)
* [String Formatting](https://dzone.com/articles/java-string-format-examples)
* [Try with resources](https://www.baeldung.com/java-try-with-resources)
* [How to create runnable JAR file](https://www.baeldung.com/executable-jar-with-maven)
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

* Maven commands
  * `mvn compile` - 
  * `mvn assembly:single` - Create a runnable JAR in `./target`
  * `mvn jar:jar` - Create a runnable JAR in `./target`
