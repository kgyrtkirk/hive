


def checkPrHead() {
  if(env.CHANGE_ID) {
    static prHead = null;
    if (prHead == null) {
      prHead=pullRequest.head;
    } else {
      if(prHead != pullRequest.head) {
        throw new RuntimeException("new changes on PR; failing build")
      }
    }
  }
}

checkPrHead()

if (env.CHANGE_ID) {
//  if(pullRequest.head != )
}

def commitHashForBuild(build) {
  def scmAction = build?.actions.find { action -> action instanceof jenkins.scm.api.SCMRevisionAction }
  return scmAction?.revision?.hash
}

pipeline {
	agent {
	  label 'master'
	}
	options { skipDefaultCheckout() }
        stages {
	stage("x") {
		steps {
		  sh 'set'
		checkPrHead()
		  sh 'sleep 30'
checkPrHead()
		}
}
	}

}
