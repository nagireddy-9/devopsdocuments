pipeline {
    agent {
        label 'master'
    }
    stages {
        stage ('GET-git-version') {
            //Run this script on master 
            steps {
                sh label: '', script: 'git --version'
            }

        }
        stage ('GET-docker-version') {
            steps {
                //RUN this script on slave node
                node ('DockerNode') {
                    sh label: '', script: 'docker --version'
                }
            }
        }
    }
}