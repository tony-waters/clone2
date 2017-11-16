try {
   timeout(time: 5, unit: 'MINUTES') {
      node('maven') {
          stage('build') {
            openshiftBuild(buildConfig: 'clone2-build', showBuildLogs: 'true')
          }
        }
   }
} catch (err) {
   echo "in catch block"
   echo "Caught: ${err}"
   currentBuild.result = 'FAILURE'
   throw err
}
