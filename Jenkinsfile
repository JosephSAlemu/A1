pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M3"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
              }
          }
        stage('Build') {
            steps {
                dir('A1/'){
                    sh "mvn -Dmaven.test.failure.ignore=true clean package"
                }
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    dir('A1/'){
                        junit '**/target/surefire-reports/TEST-*.xml'
                        archiveArtifacts 'target/*.jar'
                    }
                }
            }
        }
    }
}

