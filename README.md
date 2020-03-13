# serenity-selenium-framework
framework with serenity, selenium

#!/usr/bin/env groovy

pipeline {

    agent {
        label ''
    }

    triggers {
        cron(env.BRANCH_NAME == 'master' ? '0 9 * * *' : '')
         pollSCM('* H/5 * * *')
    }

     tools {
        maven "maven"
    }

    stages {

        stage('test') {

            steps {
                sh "mvn clean verify -Dtags="EXPLANATION"
            }
        }

        stage('generate-report') {

            steps {
                sh "mvn serenity:aggregate"
                publishHTML (target: [
                                  allowMissing: false,
                                  alwaysLinkToLastBuild: false,
                                  keepAll: true,
                                  reportDir: 'target/site/serenity',
                                  reportFiles: 'index.html',
                                  reportName: "Social Services"
                            ])
            }
        }

    }
}
