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
        openshiftBuild(buildConfig: 'clone2-build', showBuildLogs: 'true')
      }
    }
  }
}