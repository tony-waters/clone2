// see https://dzone.com/refcardz/declarative-pipeline-with-jenkins for examples

pipeline {
  agent {
    label 'maven'
  }
  stages {
    stage("Checkout Code") {
      steps {
        checkout scm
      }
    }
    stage("Build with Maven") {
      steps {
        sh "mvn clean"
        sh "mvn install"
      }
    }
    stage("Create docker image") {
        steps {
            sh "docker build -t bit1/clone1 ."
            // to do a push you will need to have your own project and own registry details
            // sh "docker push bit1/clone1"
        }
    }
  }
}