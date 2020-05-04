
properties([
   rateLimitBuilds(throttle: [count: 1000, durationName: 'hour', userBoost: true]) 
 ]
)


// options { disableConcurrentBuilds() }

//throttle(['hive-precommit']) {

lock(resource: 'some_resource', skipIfLocked: true) {
  echo 'Do something now or never!'
}

def ccLock(lockName, n, block) {
  def run=true;
  while(run) {
    for(int i=0;i<n;i++) {
      def currentLockName = lockName + "_" + i;
      echo "Checking: ${currentLockName}" 
      lock(resource: currentLockName, skipIfLocked: true) {
        echo "Acquired: ${currentLockName}" 
        try {
          block();
        } finally {
          echo "Exiting: ${currentLockName}" 
          run=false
        }
      }
      if(run) {
        sleep(10);
      }
    }
  }
}

//ccLock('hivePrecommit',2)  {
lock(label:'hive-precommit',quantity:1)  {
node {
//	proprtyird
// properties([rateLimitBuilds: 
//   properties([disableConcurrentBuilds()])

node {
//   properties([disableConcurrentBuilds()])
//   checkout scm
   sh "sleep 5"
}
   sh "sleep 5"

def PULL_REQUEST = env.CHANGE_ID
withCredentials([string(credentialsId: 'github3', variable: 'GITHUB_TOKEN')]) {
sh "curl -s -H \"Authorization: token ${GITHUB_TOKEN}\" -X POST -d '{\"body\": \"This is my first test comment from jenkins\"}' \"https://github.com/api/v3/repos/kgyrtkirk/hive/${env.GIT_URL.tokenize("/")[-1].tokenize(".")[0]}/issues/${PULL_REQUEST}/comments\""
}

}

}
//}

echo 'exit'


