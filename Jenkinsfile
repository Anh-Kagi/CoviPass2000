pipeline {
	agent none
	environment {
	    dockerImage = ''
	}
	stages {
	    stage('Git Clone') {
	        agent any
            steps {
                git 'https://github.com/Anh-Kagi/CoviPass2000'
            }
	    }
        stage('Docker Build') {
            agent any
            steps {
                script {
                    dockerImage = docker.build 'anhkagi/covipass2000:latest'
                }
            }
        }
        stage('Docker Push') {
            agent any
            steps {
                script {
                    docker.withRegistry('', 'dockerHub') {
                        dockerImage.push()
                    }
                }
            }
        }
    }
}