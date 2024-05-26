pipeline {
    agent any
    tools {
        maven "Maven 3.9.6"
        jdk "jdk17"
    }
    stages {
        stage('Prepare') {
                steps {
                    configFileProvider([configFile(fileId: 'database_info', variable: 'DB_PROPERTIES')]) {
                        sh 'cp $DB_PROPERTIES api/src/main/resources/application.properties'
                    }
                }
        }
        stage('Compile') {
            steps {
                step([$class: 'GitHubCommitStatusSetter'])
                sh 'mvn -f api/pom.xml clean compile'
            }
        }

        stage('Test') {
                    steps {
                        sh 'mvn -f api/pom.xml test'
                    }
        }
        stage('Verify') {
            steps {
                sh 'mvn -f api/pom.xml verify'
            }

        }
	    stage('Deploy') {
            steps {
		        configFileProvider([configFile(fileId: 'maven_settings', variable: 'MAVEN_SETTINGS')]) {
        		sh 'mvn -s $MAVEN_SETTINGS -f api/pom.xml deploy'
    		    }
            }

        }

    }
    post {
        always {
            step([$class: 'GitHubCommitStatusSetter'])
            jiraSendBuildInfo site: 'pis-24l.atlassian.net'
        }
    }
}
