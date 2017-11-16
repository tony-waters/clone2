// see https://dzone.com/refcardz/declarative-pipeline-with-jenkins for examples

node('maven') {
stage 'build'
       openshiftBuild(buildConfig: 'clone2-build', showBuildLogs: 'true')
}

