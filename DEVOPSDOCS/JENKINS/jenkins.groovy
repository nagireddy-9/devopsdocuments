def scmVars
def arr
pipeline {
    agent {
        label 'docker-oracle'
    }
    environment {
        ARTIFACT = 'c360-bundle.tar.gz'
        REVISION = 'revision'
    }
    stages {
        stage ('Checkout') {
            steps {
                script {
                    scmVars = Checkout scm

                    configFileProvider([configFile(fileId: 'c2da0bf2-01f2-4c01-604452208fc6', variable: 'config' )]){
                        script {
                            serverConfig = sh  (
                                script: "cat ${env.config}",
                                returnStdout : true
                            ).trim()

                            InputJson = new groovy.json.JsonSlurperClassic().ParseText(serverConfig)
                            host = "${InputJSON.dev.user}@${InputJSON.dev.c360}"
                            PGPASSWORD = "${InputJSON.dev.pgpass}"
                            ENCRYPTION_KEY = "${InputJSON.dev.c360_ENCRYPTION_KEY}"
                            MULE_ACCESS_TOKEN_URL = "${InputJSON.dev.c360_MULE_ACCESS_TOKEN_URL}"
                            MULE_ACCESS_TOKEN_CLIENT_ID = "${InputJSON.dev.c360_MULE_ACCESS_TOKEN_CLIENT_ID}"
                            MULE_ACCESS_TOKEN_CLIENT_SECRET = "${InputJSON.dev.c360_MULE_ACCESS_TOKEN_CLIENT_SECRET}"
                            C360_DB = "${InputJSON.dev.C360_DB}"
                            C360_UNAME = "${InputJSON.dev.C360_UNAME}"
                            C360_DB_PORT = "${InputJSON.dev.C360_DB_PORT}"
                            G_CAPTCHA_SECRET = "${InputJSON.dev.G_CAPTCHA_SECRET}"
                        }
                    }
                    script {
                        // sset current build name
                        currentBuild.displayName = "#${env.BUILD_NUMBER} - ${scmVars.GIT_LOCAL_BRANCH}"
                        //notify bitbucket server about build
                        this.notifyStash('INPROGRESS')

                    }
                }

            }

        }
        stage ('artifactory') {
            steps {

            }
        }
    }

}