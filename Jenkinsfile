pipeline {
    agent any 
  
    stages {
        stage("build") {
            steps {
                checkout scmGit(branches: [[name: 'PIS-1-IntelliJ-Project']], extensions: [], userRemoteConfigs: [[credentialsId: 'PAT_Jenkins', url: 'https://github.com/projekt-pis/etap1.git']])
                echo 'to eksperymentalny pipeline'
            }
        }
        stage("test") {
            steps {
                echo 'kocham testować'
            }
        }
    }
}
