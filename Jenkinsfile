pipeline {
    agent any
    tools {
        maven "Maven 3.9.6"
        jdk "jdk17"
    }
    stages {
        stage('Compile') {
            steps {
                step([$class: 'GitHubCommitStatusSetter'])
                sh 'mvn -f example/pom.xml clean compile'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn -f example/pom.xml test'
            }
        }
        stage('Test covearge') {
            steps {
                sh 'mvn -f example/pom.xml org.jacoco:jacoco-maven-plugin:0.8.8:prepare-agent verify org.jacoco:jacoco-maven-plugin:0.8.8:report'
            }

        }
    }
    post {
        always {
            step([$class: 'GitHubCommitStatusSetter'])
        }
    }
}