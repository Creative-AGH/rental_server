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
                    def appImage = docker.image("my-app:${env.BUILD_ID}")
                    def appContainer = appImage.run('-d')
                    sh "docker exec ${appContainer.id} apt-get update && \
                                                           apt-get install -y maven && \
                                                           rm -rf /var/lib/apt/lists/*"
                    sh "docker exec ${appContainer.id} mvn test"
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                        docker.image("my-app:${env.BUILD_ID}").push()
                    }
                }
            }
        }
    }
}
