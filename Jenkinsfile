properties([
   rateLimitBuilds(throttle: [count: 1000, durationName: 'hour', userBoost: true]),
   pipelineTriggers([
        issueCommentTrigger('.*test this please.*')
   ])
 ]
)


enum PrLabel {
    SUCCESS("tests passed"),
    UNSTABLE("tests unstable"),
    FAILURE("tests failed"),
    PENDING("tests pending");

    public final String label;
    PrLabel(String label) {
      this.label=label;
    }
    public String getLabel() { return label; }

}

def setPrLabel(PrLabel label) {
   def newLabels = []
   for( String l : pullRequest.labels )
     newLabels.add(l) //All(pullRequest.labels.asList())
//   pullRequest.labels.each { newLabels.add(it) }
   newLabels.removeAll(PrLabel.values()*.label)
   newLabels.add(label)
   pullRequest.labels=newLabels
}


setPrLabel(PrLabel.PENDING);
//PrLabel.PENDING.set();


lock(label:'hive-precommit',quantity:1)  {
node {

node {
   sh "sleep 5"
}
   sh "sleep 5"
   sh "set"

def PULL_REQUEST = env.CHANGE_ID
withCredentials([string(credentialsId: 'github-token2', variable: 'GITHUB_TOKEN')]) {
sh "curl -s -H \"Authorization: token ${GITHUB_TOKEN}\" -X POST -d '{\"body\": \"This is my first test comment from jenkins\"}' \"https://github.com/api/v3/repos/kgyrtkirk/hive/${env.CHANGE_URL.tokenize("/")[-1].tokenize(".")[0]}/issues/${PULL_REQUEST}/comments\""
}

}

}

echo 'exit'


pullRequest.addLabel('Build Failed')
pullRequest.comment('This PR is highly illogical..')





post {
  success {
    PrLabel.SUCCESS.set();
  }
  unstable {
    PrLabel.UNSTABLE.set();
  }
  failure {
    PrLabel.FAILURE.set();
  }
}
pullRequest.comment('This PR is highly illogical..')
