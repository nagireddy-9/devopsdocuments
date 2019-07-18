pipeline {
    agent {
        node {
            label 'Docker'
        }
    }
    environment {
        IMAGE = readMavenPom().getArtifactId()
        VERSION = readMavenPom().getVersion()

    }
    stages {
        stage ('TEST') {
            steps {
                echo "${IMAGE}"
                echo "${VERSION}"
            }

        }
        stage ('BuildImage') {
            steps {
                sh label: '', script: 'docker build -t tom-jenkins:$BUILD_ID /home/jenkins/sample-files/.'
                
            }
        }
        
        stage ('TAgImage') {
            steps {
                sh label: '', script: 'docker tag tom-jenkins:$BUILD_ID samanagireddy/fromjenkins-game:$BUILD_ID'
                
            }
        }
        stage ('PushImage') {
            steps {
                sh label: '', script: 'docker push samanagireddy/fromjenkins-game:$BUILD_ID'
            }
            
        }
        
    }
    
}