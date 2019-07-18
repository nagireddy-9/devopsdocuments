pipeline {
    agent {
        label 'master'
    }
    environment {
  //The readMavenPom method is not recognized. Is there some configuration I am missing to make this available?
  //I needed to install the (pipeline-utility-steps) plugin.
        IMAGE = readMavenPom().getArtifactId()
        VERSION = readMavenPom().getVersion()
    }
    stages {
        stage ('get-code') {
            steps {
                git 'https://github.com/wakaleo/game-of-life.git'
            }
            
        }
        stage ('MVN-Compile') {
            steps {
                sh label: '', script: 'mvn package'
            }
        }
        stage ('Copy-Package') {
            steps {
                //It will ask to hostkeyverification so use two ways one is 
                //1)i had to use bellow and 2) I needed to copy my id_rsa to /var/lib/jenkins/.ssh
                //The /var/lib/jenkins/.ssh folder and files inside of it need to be owned by jenkins
                sh label: '', script: 'scp -v -o StrictHostKeyChecking=no -r /var/lib/jenkins/workspace/gameoflife/gameoflife-web/target/*.war jenkins@35.167.183.232:/home/jenkins/sample-files'
            }
        }
        stage ('Get-Envronment-Test') {
            steps {
                echo "${IMAGE}"
                echo "${VERSION}"
            }
        }
        stage ('get-docker-version') {
            node {
                label 'DockerNode'
            }
            steps {
                sh label: '', script: 'docker --version'
            }
        }
    }
}