pipeline {

    agent any

    environment {
        SONAR_SERVER = 'sonarqube-server'
        // EXTRAEMOS DINÁMICAMENTE EL NOMBRE DEL REPO
        // Esto evita que tengas que cambiar el nombre manualmente en cada proyecto
        REPO_NAME = "${env.GIT_URL.split('/').last().split('\\.').first()}"
    }

    stages {

        stage('Build & QA Parallel') {

            parallel {

                stage('Build') {
                    steps {
                        sh 'chmod +x gradlew'
                        sh './gradlew clean build'
                    }
                }

                stage('QA Analysis') {
                    steps {
                        sh """
                            ./gradlew sonarqube \
                            -Dsonar.organization=dmtorrico \
                            -Dsonar.projectKey=${env.REPO_NAME} \
                            -Dsonar.projectName=${env.REPO_NAME}
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