



this.prHead = null;
def checkPrHead() {
  if(env.CHANGE_ID) {
    println("checkPrHead - prHead:" + prHead)
    println("checkPrHead - prHead2:" + pullRequest.head)
    if (prHead == null) {
      prHead = pullRequest.head;
    } else {
      if(prHead != pullRequest.head) {
        currentBuild.result = 'ABORTED'
        error('Found new changes on PR; aborting current build')
      }
    }
  }
}

def getFlakyTestCommand() {
  def cmd=""
  if(env.CHANGE_ID) {
    for( comment in pullRequest.comments) {
      if(comment.body.trim().startsWith("/flakycheck") ) {
        cmd=comment.body.substring(11).trim()
      }
    }
    println("last flakycmd command: ${cmd}")
    if(!(cmd  ==~ /^[\s\d _\-#]+$/)) {
      error("invalid flakycheck cmd")
    }
  }
  if(cmd == "") return null
  return cmd
}

println("flakycmd:"+getFlakyTestCommand())

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
