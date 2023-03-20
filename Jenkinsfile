pipeline {

  agent any

  tools {
    maven 'Maven 3.9.1'
    jdk 'Java 19.0.2'
  }

  parameters {
    choice(
        name: 'browser',
        choices: ['chrome', 'firefox'],
        description:  'Select browser to run automation, please select chrome only, not have firefox capabilities yet')
  }

  stages {
    stage('Checkout source code') {
      steps {
        git(url: 'https://github.com/quangle232/weather-serenity-bdd-screenplay.git',
        branch: 'master',
        credentialsId: '990cc9e7-dc83-4cbf-8259-1fe882167d48')
      }
    }

    stage('Running automation test') {
      steps {
        catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                   sh "mvn verify -Dbrowser=${browser}"
                }
      }
    }

  }

  post{
    always{
      publishHTML([
      allowMissing: false,
      alwaysLinkToLastBuild: true,
      keepAll: true,
      reportDir: "target/site/serenity",
      reportFiles: "index.html",
      reportName: "Weather Serenity Report",
      reportTitles: "Weather Serenity Report"
      ])
      archiveArtifacts artifacts: 'weather/*.json'
      cleanWs()
    }
  }
}
