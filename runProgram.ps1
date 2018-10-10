$artifactName = 'javafun-1.0-SNAPSHOT.jar'
$mainClass = 'App'
$mainPackage = 'com.williams.anthony'

Start-Process java -NoNewWindow -Wait -ArgumentList "-cp", ".\target\$artifactName", "$mainPackage.$mainClass"
