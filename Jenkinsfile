pipeline {
    agent any
    tools {
        jdk 'Java 11'
    }
    stages {
        stage ('Build') {
            steps {
              sh 'gradle clean shadowJar javadoc'
            }
            post {
                success {
                    javadoc javadocDir: 'build/docs/javadoc', keepAll: true
                }
            }
        }
    }
}