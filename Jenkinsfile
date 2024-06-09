pipeline {
    agent any
    tools {
        maven 'maven_in_jenkins'
    }
    environment {
        dockerHubRepoTag = 'jayoswal20/jenkins-eureka-registry-server:1.0.0'
    }
    stages {
        stage('Checkout Github Repo') {
            steps {
                    // first checkout the github code
                    // get this from pipeline script
                    checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/jayoswal/Microservice-Project-Repo']])

            }
        }
        stage('Change Dir & Build JAR using Maven') {

            // sh 'cd service-registry/ && mvn clean package'
            steps {
                dir('service-registry') {
                    sh 'mvn clean package'
                }
            }
        }
        stage('Build Docker Image') {
            steps{
                dir('service-registry') {
                    script {
                        sh 'docker build -t ${dockerHubRepoTag} .'
                    }
                }
            }
        }
        stage('Push Image to Docker Hub') {
            steps{
                script{
                    withCredentials([usernamePassword(credentialsId: 'dockerCredentials', passwordVariable: 'dockerPassword', usernameVariable: 'dockerUsername')]) {
                        sh 'docker login -u ${dockerUsername} -p ${dockerPassword}'
                        sh 'docker push ${dockerHubRepoTag}'
                    }

                }
            }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}