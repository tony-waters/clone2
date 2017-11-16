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
      openshiftBuild(bldCfg: 'clone2-build', showBuildLogs: 'true')
    }
  }
}