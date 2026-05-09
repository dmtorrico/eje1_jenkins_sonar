pipeline {

    agent any

    environment {
        SONAR_TOKEN = credentials('sonarqube-token')
        SONAR_HOST_URL = 'https://sonarcloud.io'
    }

    stages {

        stage('Build & QA Parallel') {

            parallel {

                stage('Build') {
                    steps {
                        sh './gradlew clean build'
                    }
                }

                stage('QA Analysis') {
                    steps {
                        sh """
                            ./gradlew sonarqube \
                            -Dsonar.projectKey=eje1_jenkins_sonar \
                            -Dsonar.organization=dmtorrico \
                            -Dsonar.host.url=${SONAR_HOST_URL} \
                            -Dsonar.token=${SONAR_TOKEN}
                        """
                    }
                }
            }
        }

        stage('Publish Artifact') {
            steps {
                archiveArtifacts artifacts: 'build/libs/*.jar', fingerprint: true
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}