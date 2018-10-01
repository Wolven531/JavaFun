# JavaFun
This repository is for messing around with Java

## Reference Materials

* [System properties](https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html)
* [String Formatting](https://dzone.com/articles/java-string-format-examples)
* [Try with resources](https://www.baeldung.com/java-try-with-resources)
* [dos2unix](http://dos2unix.sourceforge.net)

## Useful Tips

* Line endings fix:

```PowerShell
Get-ChildItem -Path . -Filter '*.*' -Recurse | ForEach-Object { dos2unix.exe $_.FullName }
```
