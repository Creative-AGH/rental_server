pipeline {
    agent {
        docker {
            image 'docker:dind'
            args '--privileged -v /var/run/docker.sock:/var/run/docker.sock'
        }
    }

    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                        def appImage = docker.build("my-app:${env.BUILD_ID}")
                    }
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    def appContainer = docker.run("my-app:${env.BUILD_ID}")
                    sh "docker exec ${appContainer.id} mvn test"
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                        sh "docker push my-app:${env.BUILD_ID}"
                    }
                }
            }
        }
    }
}
