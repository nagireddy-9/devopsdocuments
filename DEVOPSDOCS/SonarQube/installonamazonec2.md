# SONARQUBE INSTALLATION
* install java 8 (is requied)
```
sudo yum update
sudo yum install java-1.8.0 -y
```
* set java home in /etc/environment
```
JAVA_HOME="/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.201.b09-0.amzn2.x86_64"
```
* Install SonarQube
```
sudo wget -O /etc/yum.repos.d/sonar.repo http://downloads.sourceforge.net/project/sonar-pkg/rpm/sonar.repo

sudo yum install sonar -y

sudo service start/stop sonar

http://<ipaddress>:9000
```

test: 13749724c6b06c7098e86c91b4ff2d32a2607912

mvn sonar:sonar \
  -Dsonar.host.url=http://34.221.60.204:9000 \
  -Dsonar.login=13749724c6b06c7098e86c91b4ff2d32a2607912


  node {

  node {

   stage('SCM') {
	  git 'https://github.com/wakaleo/game-of-life.git'
   }
   
   stage ('build the packages') {
	  sh 'mvn package'
   }
   
   stage('SonarQube analysis') {
    // performing sonarqube analysis with "withSonarQubeENV(<Name of Server configured in Jenkins>)"
    withSonarQubeEnv('SonarQube') {
      // requires SonarQube Scanner for Maven 3.2+
      sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar'
    }
  }

}