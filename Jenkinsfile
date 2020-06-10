

static class PrHeadChecker {
	static String prHead;
	static void check() {
		if(env.CHANGE_ID) {
			println("checkPrHead - prHead:" + prHead)
    println("checkPrHead - prHead2:" + pullRequest.head)
    if (prHead == null) {
      prHead = pullRequest.head;
    } else {
      if(prHead != pullRequest.head) {
        throw new RuntimeException("new changes on PR; failing build")
      }
    }

		}
	}
}

//def prHead = null;
def checkPrHead() {

	PrHeadChecker.check()
/*  if(env.CHANGE_ID) {
    println("checkPrHead - prHead:" + prHead)
    println("checkPrHead - prHead2:" + pullRequest.head)
    if (prHead == null) {
      prHead = pullRequest.head;
    } else {
      if(prHead != pullRequest.head) {
        throw new RuntimeException("new changes on PR; failing build")
      }
    }
  }*/
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
